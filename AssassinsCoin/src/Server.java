import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import java.sql.*;

public class Server {

    /*static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/blockchain";

    static final String USER = "root";
    static final String PASS = "root";*/


    public static void main(String[] args) throws IOException{
        /*Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            System.out.println("Database created successfully...");

            String sql = "CREATE DATABASE IF NOT EXISTS BLOCKCHAIN";

            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS WALLET " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " value DOUBLE, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO WALLET " +
                    "VALUES (null, 'walletA', 0)";
            stmt.executeUpdate(sql);
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }*/

        ServerSocket serverSock = new ServerSocket(6066);
        AssassinsCoin assassinsCoin = new AssassinsCoin();
        while(true) {
            Socket Sock = serverSock.accept();
            DataOutputStream out = new DataOutputStream(Sock.getOutputStream());
            out.writeUTF("i am fine, thank you");
            DataInputStream in = new DataInputStream(Sock.getInputStream());
            System.out.println(in.readUTF());
            //Sock.close();
        }
    }
}