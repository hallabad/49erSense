public class Consol{
	private String c_SysID = "";
	private MySQLHandler c_DBHandler = null;
	private WeatherHandler c_WeatherHandler = null;
	//Creates Consol 
	public Consol(String i_sysID, MySQLHandler i_DBHandler, WeatherHandler i_WeatherHandler){
		this.c_SysID = i_sysID;
		this.c_DBHandler = i_DBHandler;
		this.c_WeatherHandler = i_WeatherHandler;
	}
	//Exicutes a consol command
	public void exicute(String i_line){
		System.out.println("[Info] Remote wanted to: " + i_line);
		_command(i_line);
	}
	//Parses the consol command
	private void _command (String i_In){
		String[] comm = i_In.toLowerCase().split(" ");
		String[] thingID;

		if (comm[0].equals("arm")) {
			_arm(comm[1]);
		} else if (comm[0].equals("set-door")){
			thingID = i_In.toLowerCase().split(comm[0] + " " + comm[1] + " ");
			_door(comm[1], thingID[1]);
		} else if (comm[0].equals("set-therm")){
			thingID = i_In.toLowerCase().split(comm[0] + " " + comm[1] + " ");
			_hvac(comm[1], thingID[1]);
		} else if (comm[0].equals("set-fan")){
			thingID = i_In.toLowerCase().split(comm[0] + " " + comm[1] + " ");
			_fan(comm[1], thingID[1]);
		} else if (comm[0].equals("set-temp")){
			thingID = i_In.toLowerCase().split(comm[0] + " " + comm[1] + " ");
			_temp(Integer.parseInt(comm[1]), thingID[1]); // set-temp 79.5 floor 1
		} else if (comm[0].equals("set-light")){
			thingID = i_In.toLowerCase().split(comm[0] + " " + comm[1] + " ");
			_light(Integer.parseInt(comm[1]), thingID[1]);
		} else if (comm[0].equals("set-sensor")){
			thingID = i_In.toLowerCase().split(comm[0] + " " + comm[1] + " ");
			_sensor(Integer.parseInt(comm[1]), thingID[1]);
		} else if (comm[0].equals("up-weather")){
			_Weather();
		} else { 
			System.out.println("Im dont know the |" + i_In + "| command yet");
		}
	}
	//Handles Arming Commands
	private void _arm(String i_state){ //States Disarm = 0, Here = 1, Away = 2
		int stateNum = -1;
		switch(i_state){
			case "disarm" : stateNum = 0;
			break;
			case "here" : stateNum = 1;
			break;
			case "away" : stateNum = 2;
			break;
		}
		this.c_DBHandler.updateSystem(this.c_SysID, "Armed", stateNum);
		_printInfo(this.c_SysID,"Armed", stateNum);
	}
	//Handles Openings Commands
	private void _door(String i_state , String i_ID){ //States Open = 0, closed = 1 | Locked Unlocked = 0, Locked = 1
		String colnumID = "[|]";
		int state = -1;
		switch(i_state){
			case "open" :  {
				state = 0;
				colnumID = "Open";
			}
			break;
			case "close" : {
				state = 1;
				colnumID = "Open";
			}
			break;
			case "unlock" : {
				state = 0;
				colnumID = "Locked";
			}
			break;
			case "lock" : {
				state = 1;
				colnumID = "Locked";
			}
			break;
		}
		this.c_DBHandler.updateOpening(i_ID, colnumID, state);
		_printInfo(i_ID, colnumID, state);
	}
	//Handles HVAC Mode Commands
	private void _hvac(String i_state , String i_ID){ // Off = 0, Cool = 1, Heat = 2,
		int hvacMode = -1;
		switch(i_state){
			case "off" : hvacMode = 0;
			break;
			case "cool" : hvacMode = 1;
			break;
			case "heat" : hvacMode = 2;
		}
		this.c_DBHandler.updateHVAC(i_ID, "Mode", hvacMode);
		_printInfo(i_ID, "Mode", hvacMode);
	}
	//Handls HVAC Fan Commands
	private void _fan(String i_state , String i_ID){ // Auto = 0, On = 1
		int fanMode = -1;
		switch(i_state){
			case "on" : fanMode = 1;
			break;
			case "auto" : fanMode = 0;
		}
		this.c_DBHandler.updateHVAC(i_ID, "Fan", fanMode);
		_printInfo(i_ID, "Fan", fanMode);
	}
	//Handls Set Temp Commands
	private void _temp(int i_temp , String i_ID){ 
		this.c_DBHandler.updateHVAC(i_ID, "Temp", i_temp);
		_printInfo(i_ID, "Temp", i_temp);
	}
	//Handls Lights Commands
	private void _light(int i_level , String i_ID){
		this.c_DBHandler.updateLight(i_ID, i_level);
		_printInfo(i_ID, "Level", i_level);
	}
	//Handls Sensors Commands
	private void _sensor(int i_Val , String i_ID){
		this.c_DBHandler.updateSensor(i_ID, i_Val);
		_printInfo(i_ID, "Level", i_Val);
	}
	//Handls Weather Commands
	private void _Weather(){
		this.c_WeatherHandler.updateWeather();
	}
	//Printing what the given command did
	private void _printInfo(String i_deviceID, String i_colnumID, int i_state){
		System.out.println("[Info] The " + i_colnumID.toUpperCase() + " state for the divice " + i_deviceID.toUpperCase() + " has been updated to " + i_state);
	}


}

