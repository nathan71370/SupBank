import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.Security;

import javax.swing.*;

public class LoginForm extends JFrame{
    JPanel panel1, panel2;
    JLabel key_label, message;
    JPasswordField key_text;
    JButton connection, register;
    Controller control;

    public LoginForm(){
        init();
        createView();
        setTitle("Please Login Here !");
        setSize(300, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void init() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            throw new RuntimeException("Test Failed. MetalLookAndFeel not set "
                    + "for frame");
        }
        //JLabel
        key_label = new JLabel("My Key :");
        //JTextField
        key_text = new JPasswordField();
        //JButton
        connection = new JButton("Connection");
        register = new JButton("Register");

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        control = new Controller(this, new Wallet());
    }

    private void createView(){
        //JPanel
        panel1 = new JPanel(new GridLayout(3, 1));
        panel2 = new JPanel();

        panel1.add(key_label);
        panel1.add(key_text);
        panel1.add(connection);
        panel1.add(register);

        panel2.add(panel1, BorderLayout.CENTER);

        setControlButton(control);
        setContentPane(panel2);
    }

    private void setControlButton(Controller listener) {
        connection.addActionListener(listener);
        register.addActionListener(listener);
    }

}
