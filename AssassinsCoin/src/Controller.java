import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private LoginForm loginForm;

    public Controller(LoginForm loginForm){
        this.loginForm=loginForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginForm.submit){
            String password = String.valueOf(loginForm.key_text.getPassword());
            if (password.trim().equals("admin")) {
                loginForm.message.setText(" Hello " + password + "");

                UserAccountForm userAccountForm = new UserAccountForm();

            } else {
                loginForm.message.setText(" Invalid user.. ");
            }
        }

    }
}
