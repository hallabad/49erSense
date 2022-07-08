import java.net.*;
import java.io.*;

public class SocketTestServer {

    private Socket c_Socket = null;
    private ServerSocket c_ServerSocket = null;
    private DataInputStream c_in = null;

    public SocketTestServer(int i_port){
        try{
            this.c_ServerSocket = new ServerSocket(i_port);
            System.out.println("[Info] Server Started \n[Info] Waiting for client to connect...");
            this.c_Socket = this.c_ServerSocket.accept();
            System.out.println("[Info] A client has connected");
            c_in = new DataInputStream(new BufferedInputStream(this.c_Socket.getInputStream()));
        } catch (Exception e) {
            System.out.println("[Error] There was an issue creating the server socket | " + e.getMessage());
        }
        String line = "";
        while((!line.equals("e"))&&(!line.equals("E"))){
            try{
                line = this.c_in.readUTF();
                System.out.println(line.toUpperCase());
            } catch (Exception e) {
                System.out.println("[Error] There was an issue receving from the server socket | " + e.getMessage());
            }
        }
        System.out.println("[Info] Closing Connection");
        try{
            this.c_Socket.close();
            this.c_in.close();
        } catch (Exception e) {
            System.out.println("[Error] There was an issue closing the server socket | " + e);
        }
    }

    public static void main(String[] args) throws Exception {
        SocketTestServer server = new SocketTestServer(5000);
    }
}
