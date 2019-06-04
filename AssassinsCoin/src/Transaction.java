import java.security.*;
import java.util.ArrayList;

public class Transaction {

    String transactionId;
    public PublicKey sender;
    public PublicKey reciepient;
    public float value;
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    private static int sequence = 0;


    public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.reciepient = to;
        this.value = value;
        this.inputs = inputs;
    }


    private String calulateHash() {
        sequence++;
        return StringHash.applySha256(
                StringHash.getStringFromKey(sender) +
                        StringHash.getStringFromKey(reciepient) +
                        Float.toString(value) + sequence
        );
    }

    void generateSignature(PrivateKey privateKey) {
        String data = StringHash.getStringFromKey(sender) + StringHash.getStringFromKey(reciepient) + Float.toString(value)	;
        signature = StringHash.applyECDSASig(privateKey,data);
    }

    public boolean verifiySignature() {
        String data = StringHash.getStringFromKey(sender) + StringHash.getStringFromKey(reciepient) + Float.toString(value)	;
        return StringHash.verifyECDSASig(sender, data, signature);
    }

    public boolean processTransaction() {

        if(!verifiySignature()) {
            System.out.println("#Transaction Signature failed to verify");
            return false;
        }


        for(TransactionInput i : inputs) {
            i.UTXO = AssassinsCoin.UTXOs.get(i.transactionOutputId);
        }


        if(getInputsValue() < AssassinsCoin.minimumTransaction) {
            System.out.println("#Transaction Inputs to small: " + getInputsValue());
            return false;
        }


        float leftOver = getInputsValue() - value;
        transactionId = calulateHash();
        outputs.add(new TransactionOutput( this.reciepient, value,transactionId));
        outputs.add(new TransactionOutput( this.sender, leftOver,transactionId));


        for(TransactionOutput o : outputs) {
            AssassinsCoin.UTXOs.put(o.id , o);
        }


        for(TransactionInput i : inputs) {
            if(i.UTXO == null) continue;
            AssassinsCoin.UTXOs.remove(i.UTXO.id);
        }

        return true;
    }


    public float getInputsValue() {
        float total = 0;
        for(TransactionInput i : inputs) {
            if(i.UTXO == null) continue;
            total += i.UTXO.value;
        }
        return total;
    }


    public float getOutputsValue() {
        float total = 0;
        for(TransactionOutput o : outputs) {
            total += o.value;
        }
        return total;
    }
}