import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Door implements Runnable{
	private Thread c_t;
	private String c_DoorID;
	private int[] c_inputs;
	private Boolean c_run = true;
	private int c_PollInterval = 2;
	private SocketHandler c_Socket;

	public Door(String i_DoorID, int[] i_Inputs, int i_PollInterval, SocketHandler i_Socket){
		this.c_DoorID = i_DoorID;
		this.c_inputs = i_Inputs;
		this.c_PollInterval = i_PollInterval;
		this.c_Socket = i_Socket;
	}

	public void run(){
		System.out.println("[Info] Started Door " + this.c_DoorID);
		String comm = "";
		try{
			while(c_run){
				comm = "set-Door " + _poll() + " " + this.c_DoorID;
				System.out.println(comm);
				this.c_Socket.send(comm);
				TimeUnit.SECONDS.sleep(this.c_PollInterval);
			}
		} catch (Exception e){

		}
	}

	public void start(){
		if (this.c_t == null) {
			this.c_t = new Thread (this);
			this.c_t.start ();
		}
	}

	public void stop(){
		System.out.println("[Info] Stopped Door " + this.c_DoorID);
		this.c_run = false;
	}

	private String _poll(){
		Random randGen = new Random();
		int rand = this.c_inputs[randGen.nextInt(this.c_inputs.length)];
		if (rand == 0){
			return "open";
		} else {
			return "close";
		}
	}
}
