import java.awt.*;
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

public class UserAccountForm extends JFrame {

    JPanel panel0, panel1;
    JLabel user_label, password_label, message, user_balance_label, transfert_to_label, transfert_amount_label;
    JTextField transfert_to, tranfert_amount;
    JPasswordField password_text;
    JButton cancel, transfer_money;

    UserAccountForm(){
        user_label = new JLabel();
        user_label.setText("Hello User : -- GET USER CONNECTED --" );

        user_balance_label = new JLabel();
        user_balance_label.setText("-- GET USER CONNECTED -- : -- GET USER BALANCE --");

        transfert_to_label.setText("Transfer to : ");
        transfert_to = new JTextField();
        transfert_amount_label.setText("Amount : ");
        tranfert_amount = new JTextField();
        transfer_money = new JButton();

        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();


        panel0 = new JPanel(new GridLayout(1, 2));
        panel0.add(user_label);
        panel0.add(user_balance_label);

        panel1 = new JPanel(new GridLayout(2,4));
        panel1.add(transfert_to_label);
        panel1.add(transfert_to);
        panel1.add(transfert_amount_label);
        panel1.add(tranfert_amount);
        panel1.add(transfer_money);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(panel0, BorderLayout.CENTER);
        add(panel1, BorderLayout.CENTER);
        setTitle("Account");
        setSize(600, 600);
        setVisible(true);

    }


}