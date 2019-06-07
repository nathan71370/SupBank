import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Security;

public class ControllerUser implements ActionListener{
    private UserAccountForm userAccountForm;
    private Block previousBlock;
    private boolean firstTime = true;
    AssassinsCoin assassinsCoin = new AssassinsCoin();
    Wallet coinbase = new Wallet();
    public Wallet wallet;
    //int i=0;

    public ControllerUser(UserAccountForm userAccountForm, Wallet wallet) {
        this.userAccountForm = userAccountForm;
        this.wallet = wallet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        assassinsCoin.setCurrentWallet(wallet);
        System.out.println("test");
        System.out.println(wallet);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
                if (e.getSource() == userAccountForm.start_mining) {
                    new Thread(new Runnable() {
                        public void run() {
                            while (!Thread.currentThread().isInterrupted()) {
                        if (!firstTime) {
                                AssassinsCoin.genesisTransaction = new Transaction(coinbase.publicKey, AssassinsCoin.walletHashMap.get(wallet.getPrivateKey()).publicKey, 10f, null);
                                AssassinsCoin.genesisTransaction.generateSignature(coinbase.privateKey);
                                AssassinsCoin.genesisTransaction.outputs.add(new TransactionOutput(AssassinsCoin.genesisTransaction.reciepient, AssassinsCoin.genesisTransaction.value, AssassinsCoin.genesisTransaction.transactionId));
                                AssassinsCoin.UTXOs.put(AssassinsCoin.genesisTransaction.outputs.get(0).id, AssassinsCoin.genesisTransaction.outputs.get(0));
                                Block currentBlock = new Block(previousBlock.previousHash);
                                currentBlock.addTransaction(AssassinsCoin.genesisTransaction);
                                AssassinsCoin.addBlock(currentBlock);
                                previousBlock = currentBlock;
                                System.out.println(wallet.getBalance());
                                userAccountForm.updateBalance();
                                AssassinsCoin.isChainValid();
                                if (e.getSource() == userAccountForm.start_mining) {
                                    //Thread.currentThread().interrupt();

                                }
                        } else {
                            assassinsCoin.firstTime=true;
                            AssassinsCoin.genesisTransaction = new Transaction(coinbase.publicKey, AssassinsCoin.walletHashMap.get(wallet.getPrivateKey()).publicKey, 100f, null);
                            AssassinsCoin.genesisTransaction.generateSignature(coinbase.privateKey);
                            AssassinsCoin.genesisTransaction.transactionId = "0";
                            AssassinsCoin.genesisTransaction.outputs.add(new TransactionOutput(AssassinsCoin.genesisTransaction.reciepient, AssassinsCoin.genesisTransaction.value, AssassinsCoin.genesisTransaction.transactionId));
                            AssassinsCoin.UTXOs.put(AssassinsCoin.genesisTransaction.outputs.get(0).id, AssassinsCoin.genesisTransaction.outputs.get(0));

                            System.out.println("Creating and Mining Genesis block... ");
                            System.out.println("test1");
                            previousBlock = new Block("0");
                            System.out.println("test2");
                            previousBlock.addTransaction(AssassinsCoin.genesisTransaction);
                            System.out.println("test3");
                            AssassinsCoin.addBlock(previousBlock);
                            System.out.println("test4");
                            userAccountForm.updateBalance();
                            System.out.println("test5");
                            AssassinsCoin.isChainValid();
                            System.out.println("test6");
                            assassinsCoin.firstTime=false;
                            firstTime = false;
                        }
                    }

                }
            }).start();
        } else if (e.getSource() == userAccountForm.transfer_money) {
                    new Thread(new Runnable() {
                        public void run() {
                            Block block = new Block(previousBlock.hash);
                            System.out.println("\nWalletA Attempting to send more funds (1000) than it has...");
                            if (AssassinsCoin.walletHashMap.get(userAccountForm.transfert_to_label.getText()) != null) {
                                block.addTransaction(wallet.sendFunds(StringHash.getPublicKeyFromString(userAccountForm.transfert_to_label.getText()), Float.parseFloat(userAccountForm.transfert_amount_label.getText())));
                                assassinsCoin.addBlock(block);
                                previousBlock = block;
                            } else {
                                System.out.println("Wallet does not exist!");
                            }

                        }
                    }).start();
                }

    }

}