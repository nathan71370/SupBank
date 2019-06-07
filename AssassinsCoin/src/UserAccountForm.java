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

    JPanel panel0, panel1, panel2;
    JLabel user_label, password_label, message, user_balance_label, transfert_to_label, transfert_amount_label;
    JTextField transfert_to, tranfert_amount;
    JPasswordField password_text;
    JButton cancel, transfer_money, start_mining;
    ControllerUser controlUser;

    public UserAccountForm(){
        setTitle("Account");
        init();
        createView();
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void init(){
        //JLabel
        user_label = new JLabel("Hello User : -- GET USER CONNECTED --");
        user_balance_label = new JLabel("-- GET USER CONNECTED -- : -- GET USER BALANCE --");
        transfert_to_label = new JLabel("Transfer to : ");
        transfert_amount_label = new JLabel("Amount : ");
        password_label = new JLabel("Password :");

        //JTextField
        transfert_to = new JTextField();
        tranfert_amount = new JTextField();
        password_text = new JPasswordField();

        //JButton
        transfer_money = new JButton("Transfert Money");
        start_mining = new JButton("Start Mining");

        controlUser = new ControllerUser(this);
    }

    private void createView(){
        //Jpanel
        panel0 = new JPanel(new GridLayout(1, 2));
        panel0.add(user_label);
        panel0.add(user_balance_label);
        panel0.add(start_mining);

        panel1 = new JPanel(new GridLayout(2,4));
        panel1.add(transfert_to_label);
        panel1.add(transfert_to);
        panel1.add(transfert_amount_label);
        panel1.add(tranfert_amount);
        panel1.add(transfer_money);


        //add(panel0, BorderLayout.CENTER);
        panel2 = new JPanel();
        panel2.add(panel0, BorderLayout.CENTER);
        panel2.add(panel1, BorderLayout.CENTER );
        setContentPane(panel2);

    }

    public void setControlButton(Controller listener) {
        start_mining.addActionListener(listener);
    }

}