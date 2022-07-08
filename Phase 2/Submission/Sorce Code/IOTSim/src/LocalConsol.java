import java.util.*;

public class LocalConsol{
	private Boolean c_Loop_Flag = true;
	private Consol c_Consol = null;
	//Creates Consol 
	public LocalConsol(String i_sysID, MySQLHandler i_DBHandler, WeatherHandler i_WeatherHandler){
		this.c_Consol = new Consol(i_sysID, i_DBHandler, i_WeatherHandler);
	}
	//Starts local consol
	public void runConsol(){
		Scanner scanner = new Scanner(System.in);
		while(this.c_Loop_Flag){
			System.out.println("");
			System.out.print("What would you like to do: ");
			String line = scanner.nextLine();
			System.out.println("You wanted to: " + line);
			if (line.toLowerCase().equals("stop")){
				System.out.println("Exiting Loop");
				scanner.close();
				this.c_Loop_Flag = false;
			} else {
				this.c_Consol.exicute(line);
			}
		}
	}
}
