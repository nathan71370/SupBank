import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Security;

public class ControllerUser implements ActionListener, Runnable{
    private UserAccountForm userAccountForm;
    private Block previousBlock;
    private boolean firstTime = true;
    public static Transaction genesisTransaction;
    Wallet coinbase = new Wallet();
    Wallet wallet;
    int i=1;
    private boolean doStop = false;

    public ControllerUser(UserAccountForm userAccountForm, Wallet wallet) {
        this.userAccountForm = userAccountForm;
        this.wallet = wallet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        new Thread(new Runnable() {
            public void run() {
                if (e.getSource() == userAccountForm.start_mining) {
                    if (!firstTime) {
                        System.out.println("test");
                        while (true) {

                            genesisTransaction = new Transaction(coinbase.publicKey, AssassinsCoin.walletHashMap.get(wallet.getPrivateKey()).publicKey, 10f, null);
                            genesisTransaction.generateSignature(coinbase.privateKey);
                            genesisTransaction.transactionId = "0";
                            genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId));
                            AssassinsCoin.UTXOs.put(genesisTransaction.outputs.get(i).id, genesisTransaction.outputs.get(i));

                            Block nextBlock = new Block(previousBlock.previousHash);
                            nextBlock.addTransaction(genesisTransaction);
                            AssassinsCoin.addBlock(nextBlock);
                            previousBlock = nextBlock;
                            System.out.println("test");
                            i++;
                            if (e.getSource() == userAccountForm.start_mining) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                                break;
                            }
                        }
                    } else {
                        genesisTransaction = new Transaction(coinbase.publicKey, AssassinsCoin.walletHashMap.get(wallet.getPrivateKey()).publicKey, 100f, null);
                        genesisTransaction.generateSignature(coinbase.privateKey);
                        genesisTransaction.transactionId = "0";
                        genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId));
                        AssassinsCoin.UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));

                        System.out.println("Creating and Mining Genesis block... ");
                        previousBlock = new Block("0");
                        previousBlock.addTransaction(genesisTransaction);
                        AssassinsCoin.addBlock(previousBlock);
                        firstTime = false;
                    }

                }
            }
        }).start();

    }

    @Override
    public void run() {

    }
}