import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

// Server Hangs when stoping becalse of server socker accept() 

public class ClientHandler implements Runnable{
	private Thread c_t;
	private ArrayList<NodeSocket> c_nodes = new ArrayList<>();
	private ServerSocket c_ServerSocket = null; 
	private Boolean c_run = true;
	private ExecutorService c_NodePool = Executors.newFixedThreadPool(100);
	private String c_sysId;
	private MySQLHandler c_DBHandler = null;
	private WeatherHandler c_WeatherHandler = null;

	public ClientHandler(int i_port, String i_sysID, MySQLHandler i_DBHandler, WeatherHandler i_WeatherHandler) throws IOException{
		this.c_ServerSocket = new ServerSocket(i_port);
		this.c_DBHandler = i_DBHandler;
		this.c_WeatherHandler = i_WeatherHandler;
		this.c_sysId = i_sysID;
	}

	public void run(){
		try{
			System.out.println("[Remote] Remote node server started.");
			while(this.c_run){
				// System.out.println("[Debug] In the Client Handler Loop");
				Socket newClientSocket = c_ServerSocket.accept();
				System.out.println("[Remote] A remote node has connected.");
				Consol newConsol = new Consol(this.c_sysId,this.c_DBHandler,this.c_WeatherHandler);
				NodeSocket newNodeSocket = new NodeSocket(newClientSocket,newConsol);
				this.c_nodes.add(newNodeSocket);
				this.c_NodePool.execute(newNodeSocket);
			}
		} catch (Exception e) {
			
		}

	}

	public void stop() throws IOException{
		this.c_run = false;
		Socket exitor = new Socket(this.c_ServerSocket.getInetAddress(), this.c_ServerSocket.getLocalPort());
		exitor.close();
		this.c_ServerSocket.close();
		System.out.println("[Remote] Stoping Nodes");
		for(NodeSocket t: this.c_nodes){
			t.stop();
		}
	}

	
	public void start(){
		if (this.c_t == null) {
			this.c_t = new Thread (this);
			this.c_t.start ();
		}
	}
}
