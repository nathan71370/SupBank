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

    private void init(){
        //JLabel
        key_label = new JLabel("My Key :");

        //JTextField
        key_text = new JPasswordField();

        //JButton
        connection = new JButton("Connection");
        register = new JButton("Register");

        control = new Controller(this);
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

    public void setControlButton(Controller listener) {
        connection.addActionListener(listener);
        register.addActionListener(listener);
    }

    public void actionPerformed(ActionEvent e) {
    }
}
