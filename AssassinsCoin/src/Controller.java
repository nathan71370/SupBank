import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Security;

public class Controller implements ActionListener {
    private LoginForm loginForm;
    public Wallet newKey = new Wallet();
    private UserAccountForm userAccountForm;

    public Controller(LoginForm loginForm, Wallet newKey, UserAccountForm userAccountForm) {
        this.loginForm = loginForm;
        this.newKey= newKey;
        this.userAccountForm = userAccountForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //newKey = new Wallet();
        if(e.getSource() == userAccountForm.start_minning){
            
        }

        if (e.getSource() == loginForm.connection) {
            String keyValue = String.valueOf(loginForm.key_text.getPassword());
            if (keyValue.equals("")) {
                System.out.println("Veuillez entrer une valeur ! ");
            } else if (keyValue.equals(StringHash.getStringFromKey(newKey.getPrivateKey()))) {
                System.out.println("Connexion réussie !");
                UserAccountForm userAccountForm = new UserAccountForm();
            } else {
                System.out.println("Erreur, cette clé n'existe pas !");
            }
        } else if(e.getSource() == loginForm.register) {
            System.out.println("Lancement de la fonction générate key ");
            newKey.generateKeyPair();
            System.out.println("My private key : " + StringHash.getStringFromKey(newKey.getPrivateKey()));
            System.out.println("My public key : " + StringHash.getStringFromKey(newKey.getPublicKey()));

            System.out.println("Enregistrement de la clé ");
            System.out.println("Inscription réussie ");
        }

    }
}
