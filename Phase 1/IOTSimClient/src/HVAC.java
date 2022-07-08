import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HVAC implements Runnable{
	private Thread c_t;
	private String c_HVACID;
	private int[] c_inputs;
	private Boolean c_run = true;
	private int c_PollInterval = 2;
	private SocketHandler c_Socket;

	public HVAC(String i_HVACID, int[] i_Inputs, int i_PollInterval, SocketHandler i_Socket){
		this.c_HVACID = i_HVACID;
		this.c_inputs = i_Inputs;
		this.c_PollInterval = i_PollInterval;
		this.c_Socket = i_Socket;
	}

	public void run(){
		System.out.println("[Info] Started HVAC " + this.c_HVACID);
		String comm = "";
		try{
			while(c_run){
				comm = _poll() + " " + this.c_HVACID;
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
		System.out.println("[Info] Stopped HVAC " + this.c_HVACID);
		this.c_run = false;
	}

	private String _poll(){
		Random randGen = new Random();
		int rand = this.c_inputs[randGen.nextInt(this.c_inputs.length)];
		switch (rand){
			case 1: return "set-therm off";
			case 2: return "set-therm cool";
			case 3: return "set-therm heat";
			case 4: return "set-fan on";
			case 5: return "set-fan auto";
		}
		return "";
	}
}
