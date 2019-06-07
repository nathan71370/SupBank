import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

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
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void init(){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            throw new RuntimeException("Test Failed. MetalLookAndFeel not set "
                    + "for frame");
        }
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
        //panel0.add(start_mining);

        panel1 = new JPanel(new GridLayout(1,4));
        panel1.add(transfert_to_label);
        panel1.add(transfert_to);
        panel1.add(transfert_amount_label);
        panel1.add(tranfert_amount);
        panel1.add(transfer_money);


        //add(panel0, BorderLayout.CENTER);
        panel2 = new JPanel();

        //panel2.add(this.createToolBar(), BorderLayout.NORTH);

        panel2.add(panel0, BorderLayout.CENTER);
        panel2.add(panel1, BorderLayout.CENTER );
        panel2.add(start_mining);
        setControlButton(controlUser);
        setContentPane(panel2);

    }

    public void setControlButton(ControllerUser listener) {
        start_mining.addActionListener(listener);
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();

        JButton btnNew = new JButton( "NEW" );
        btnNew.setToolTipText( "New File (CTRL+N)" );
        toolBar.add( btnNew );

        JButton btnSave = new JButton("SAVE");
        btnSave.setToolTipText( "Save (CTRL+S)" );
        toolBar.add( btnSave );

        toolBar.addSeparator();

        JButton btnCopy = new JButton("CP");
        btnCopy.setToolTipText( "Copy (CTRL+C)" );
        toolBar.add( btnCopy );

        JButton btnCut = new JButton("CUT");
        btnCut.setToolTipText( "Cut (CTRL+X)" );
        toolBar.add( btnCut );

        toolBar.addSeparator();

        JButton btnExit = new JButton("EXIT" );
        btnExit.setToolTipText( "Exit (ALT+F4)" );
        toolBar.add( btnExit );

        toolBar.addSeparator();

        setLocationRelativeTo(null);
        return toolBar;
    }


}