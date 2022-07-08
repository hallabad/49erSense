import java.net.*;
import java.io.*;

public class IOTClient {

    private Socket c_Socket = null;
    private BufferedReader c_InStream = null;
    public DataOutputStream c_OutStream = null;

    public IOTClient(String i_IPAddr, int i_port){
        try{
            this.c_Socket = new Socket(i_IPAddr,i_port);
            System.out.println("[Info] Socket connected");
            this.c_InStream = new BufferedReader(new InputStreamReader(System.in));
            this.c_OutStream = new DataOutputStream(this.c_Socket.getOutputStream());
        } catch(Exception e){
            System.out.println("[Error] There was an issue creating the soccet | " + e);
        }
        String line = "";
        while((!line.equals("e"))&&(!line.equals("E"))){
            try{
                line = this.c_InStream.readLine();
                this.c_OutStream.writeUTF(line);
            } catch(Exception e){
                System.out.println("[Error] There was an issue reading from the consol | " + e);
                line = "e";
            }
        }
        try{
            this.c_InStream.close();
            this.c_OutStream.close();
            this.c_Socket.close();
        } catch (Exception e){
            System.out.println("[Error] There was an issue closing the socket connection | " + e);
        }
    }
    public static void main(String[] args) throws Exception {
        IOTClient client = new IOTClient("192.168.5.149", 5001);
    }
}
