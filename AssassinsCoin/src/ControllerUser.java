import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Security;

public class ControllerUser implements ActionListener{
    private UserAccountForm userAccountForm;
    private  Block block = AssassinsCoin.getGenesisBlock();

    public ControllerUser(UserAccountForm userAccountForm) {
        this.userAccountForm = userAccountForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == userAccountForm.start_mining){
            block.mineBlock(5);
            System.out.println("test");
            while (1 != 2){
                Block nextBlock = new Block(block.previousHash);
                AssassinsCoin.addBlock(nextBlock);
                System.out.println("test");
            }

        }

    }
}
