import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Sensor implements Runnable{
	private Thread c_t;
	private String c_SensorID;
	private int[] c_inputs;
	private Boolean c_run = true;
	private int c_PollInterval = 2;
	private SocketHandler c_Socket;

	public Sensor(String i_SensorID, int[] i_Inputs, int i_PollInterval, SocketHandler i_Socket){
		this.c_SensorID = i_SensorID;
		this.c_inputs = i_Inputs;
		this.c_PollInterval = i_PollInterval;
		this.c_Socket = i_Socket;
	}

	public void run(){
		System.out.println("[Info] Started Sensor " + this.c_SensorID);
		String comm = "";
		try{
			while(c_run){
				comm = "set-sensor " + _poll() + " " + this.c_SensorID;
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
		System.out.println("[Info] Stopped Sensor " + this.c_SensorID);
		this.c_run = false;
	}

	private int _poll(){
		Random randGen = new Random();
		int rand = this.c_inputs[randGen.nextInt(this.c_inputs.length)];
		return rand;
	}
}
