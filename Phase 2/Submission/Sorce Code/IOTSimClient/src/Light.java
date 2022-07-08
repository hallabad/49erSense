import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Light implements Runnable{
	private Thread c_t;
	private String c_LightID;
	private int[] c_inputs;
	private Boolean c_run = true;
	private int c_PollInterval = 2;
	private SocketHandler c_Socket;

	public Light(String i_LightID, int[] i_Inputs, int i_PollInterval, SocketHandler i_Socket){
		this.c_LightID = i_LightID;
		this.c_inputs = i_Inputs;
		this.c_PollInterval = i_PollInterval;
		this.c_Socket = i_Socket;
	}

	public void run(){
		System.out.println("[Info] Started light " + this.c_LightID);
		String comm = "";
		try{
			while(c_run){
				comm = "set-light " + _poll() + " " + this.c_LightID;
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
		System.out.println("[Info] Stopped light " + this.c_LightID);
		this.c_run = false;
	}

	private int _poll(){
		Random randGen = new Random();
		int rand = this.c_inputs[randGen.nextInt(this.c_inputs.length)];
		return rand;
	}
}
