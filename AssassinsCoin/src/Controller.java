import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Security;

public class Controller implements ActionListener {
    private LoginForm loginForm;
    private Wallet newKey;
    private boolean success = false;

    public Controller(LoginForm loginForm, Wallet newKey) {
        this.loginForm = loginForm;
        this.newKey= newKey;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //newKey = new Wallet();

        if (e.getSource() == loginForm.connection) {
            String keyValue = String.valueOf(loginForm.key_text.getPassword());
            for (int i = 0; i < AssassinsCoin.walletHashMap.size(); i++) {
                if(AssassinsCoin.walletHashMap.get(newKey.getPrivateKey()).equals(newKey)){
                    System.out.println("Connexion réussie !");
                    UserAccountForm userAccountForm = new UserAccountForm(newKey);
                    success = true;
                }
            }
            if (!success) {
                System.out.println("Erreur, cette clé n'existe pas !");
            }
        } else if(e.getSource() == loginForm.register) {
            System.out.println("Lancement de la fonction générate key ");
            newKey.generateKeyPair();
            AssassinsCoin.walletHashMap.put(newKey.getPrivateKey(),newKey);
            System.out.println("My public key : " + newKey.getPublicKey());
            System.out.println("My private key : " + newKey.getPrivateKey());

            System.out.println("Enregistrement de la clé ");
            System.out.println("Inscription réussie ");
        }
    }
}
