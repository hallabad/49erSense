import java.net.Socket;
import java.io.DataOutputStream;

public class SocketHandler {
	private Socket c_Socket = null;
	public DataOutputStream c_OutStream = null;

	public static Boolean isServerAlive (String i_ServerAddress, int i_ServerPort){
		Boolean serverAlive = false;
		try{
			Socket s = new Socket(i_ServerAddress,i_ServerPort);
			serverAlive = s.isConnected() && !s.isClosed();
			s.close();
		} catch(Exception e){
			System.out.println("[Error] There was an issue checking the server | " + e);
		}
		return serverAlive;
	}

	public SocketHandler (String i_ServerAddress, int i_ServerPort){
		try{
			this.c_Socket = new Socket(i_ServerAddress,i_ServerPort);
			System.out.println("[Info] Socket connected");
			this.c_OutStream = new DataOutputStream(this.c_Socket.getOutputStream());
		} catch(Exception e){
			System.out.println("[Error] There was an issue creating the soccet | " + e);
		}
	}

	public void send (String i_CommString){
		try{
			this.c_OutStream.writeUTF(i_CommString);
		} catch (Exception e){
			System.out.println("[Error] There was an issue sending |" + i_CommString + "| to the server at |" + this.c_Socket.getInetAddress() + "| " + e.getMessage());
		}
	}

	public void close (){
		try{
			this.c_OutStream.close();
            		this.c_Socket.close();
		} catch (Exception e) {
			System.out.println("[Error] There was an issue closing the socket| " + e.getMessage());
		}
	}
}
