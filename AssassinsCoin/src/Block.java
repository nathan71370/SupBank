import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Block {

    public String hash;
    public String previousHash;
    public String merkleRoot;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    public long timeStamp;
    public int nonce;


    public Block(String previousHash ) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash();
    }



    public String calculateHash() {
        String calculatedhash = StringHash.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        merkleRoot
        );
        return calculatedhash;
    }


    public void mineBlock(int difficulty) {

        merkleRoot = StringHash.getMerkleRoot(transactions);
        String target = StringHash.getDifficultyString(difficulty);
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);

        //float total = 0;
        if(!AssassinsCoin.firstTime) {
            for (Map.Entry<String, TransactionOutput> item : AssassinsCoin.UTXOs.entrySet()) {
                System.out.println(item.getValue().value);
                item.getValue().value = item.getValue().value + 10;
                item.setValue(item.getValue());
                TransactionOutput UTXO = item.getValue();
                if (UTXO.isMine(AssassinsCoin.getCurrentWallet().publicKey)) {
                    AssassinsCoin.getCurrentWallet().UTXOs.put(UTXO.id, UTXO);
                    //total += UTXO.value;
                }
            }
        }
    }


    public boolean addTransaction(Transaction transaction) {

        if(transaction == null) return false;
        if((previousHash != "0")) {
            if((!transaction.processTransaction())) {
                System.out.println("Transaction failed to process. Discarded.");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }

}