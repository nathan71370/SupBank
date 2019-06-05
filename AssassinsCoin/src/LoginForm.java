import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame implements ActionListener{
    JPanel panel;
    JLabel key_label, message;
    JPasswordField key_text;
    JButton submit, cancel;

    LoginForm(){

        key_label = new JLabel();
        key_label.setText("Connection Key :");
        key_text = new JPasswordField();

        submit = new JButton("SUBMIT");

        panel = new JPanel(new GridLayout(3, 1));

        panel.add(key_label);
        panel.add(key_text);

        message = new JLabel();
        panel.add(message);
        panel.add(submit);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submit.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle("Please Login Here !");
        setSize(300, 100);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        String password = key_text.getText();
        if (password.trim().equals("admin")) {
            message.setText(" Hello " + password + "");

            UserAccountForm userAccountForm = new UserAccountForm();

        } else {
            message.setText(" Invalid user.. ");
        }

    }

}
