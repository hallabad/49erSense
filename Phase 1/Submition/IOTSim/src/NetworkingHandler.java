import java.net.*;
import java.io.*;

public class NetworkingHandler implements Runnable {
	private Thread c_t;
	private int c_Port = -1;
	private Socket c_Socket = null;
	private ServerSocket c_ServerSocket = null;
	private DataInputStream c_in = null;
	private Consol c_Consol = null;

	public NetworkingHandler(int i_Port, Consol i_Consol){
		this.c_Consol = i_Consol;
		this.c_Port = i_Port;
	}

	public void run(){
		try{
			this.c_ServerSocket = new ServerSocket(this.c_Port);
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
			    System.out.println("[DBUG] The remote consol submited: " + line);
			    this.c_Consol.exicute(line);
			    System.out.println(line.toUpperCase());
			} catch (Exception e) {
			    System.out.println("[Error] There was an issue receving from the server socket | " + e);
			    line = "e";
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

	public void start(){
		if(this.c_t == null){
		    this.c_t = new Thread(this);
		    this.c_t.start();
		}
	}
}
