import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Client {

    public static void main(String[] args)throws IOException {
        Socket sock=new Socket("localhost", 6066);
        DataInputStream in= new DataInputStream(sock.getInputStream());
        System.out.println(in.readUTF());
        DataOutputStream out =new DataOutputStream(sock.getOutputStream());
        out.writeUTF("waiting for connection");
        sock.close();
    }
}