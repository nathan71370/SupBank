import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Security;
import java.io.*;

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
        if (e.getSource() == loginForm.connection) {
            String keyValue = String.valueOf(loginForm.key_text.getPassword());

            try {
                BufferedReader br = new BufferedReader(new FileReader("keyPrivate.txt"));
                String line;
                while ((line = br.readLine()) != null) {
                    if(keyValue.equals(line)) {
                        System.out.println("Connexion réussie !");
                        UserAccountForm userAccountForm = new UserAccountForm(newKey);
                        success = true;
                    }
                }
                br.close();
            } catch (IOException exe) {
                System.out.println(exe);
            }
            if (!success) {
                System.out.println("Erreur, cette clé n'existe pas !");
            }
        } else if(e.getSource() == loginForm.register) {
            newKey.generateKeyPair();
            AssassinsCoin.walletHashMap.put(newKey.getPrivateKey(),newKey);
            try {
                PrintWriter pWriter = new PrintWriter(new FileWriter("keyPrivate.txt", true));
                pWriter.print(newKey.getPrivateKey()+"\n");
                pWriter.close() ;
            } catch (IOException ex) {
                System.out.println(ex);
            }
            UserAccountForm userAccountForm = new UserAccountForm(newKey);
        }
    }
}
