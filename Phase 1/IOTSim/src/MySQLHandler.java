import java.io.ObjectInputFilter.Config;

//Build Create Weather
//Finish Create Table Weather

public class MySQLHandler {
	private ConfigHandler c_Config = null;
	// Constructor
	public MySQLHandler(ConfigHandler i_config){
		this.c_Config = i_config;
		
	}
	// Main function for configuring the DB
	public void config(String i_SysID){
		if(!_tableExists("Doors")){
			_createTableOpening();
			System.out.println("[Create] Created table");
		} else {
			System.out.println("[Info] Table alredy exits");
		}
		if(!_tableExists("Lights")){
			_createTableLights();
			System.out.println("[Create] Created table");
		} else {
			System.out.println("[Info] Table alredy exits");
		}
		if(!_tableExists("HVAC")){
			_createTableHVAC();
			System.out.println("[Create] Created table");
		} else {
			System.out.println("[Info] Table alredy exits");
		}
		if(!_tableExists("Weather")){
			_createTableWeather();
			System.out.println("[Create] Created table");
		} else {
			System.out.println("[Info] Table alredy exits");
		}
		if(!_tableExists("Sensors")){
			_createTableSensor();
			System.out.println("[Create] Created table");
		} else {
			System.out.println("[Info] Table alredy exits");
		}
		if(!_tableExists("System")){
			_createTableSystem(i_SysID);
			System.out.println("[Create] Created table");
		} else {
			System.out.println("[Info] Table alredy exits");
		}

		for(String opening: this.c_Config.c_OpeningList){
			if(!_entryExists("Doors",opening)){
				_createOpening(opening);	
			}
		}
		for(String light: this.c_Config.c_LightList){
			if(!_entryExists("Lights",light)){
				_createLight(light);	
			}
		}
		for(String hvac: this.c_Config.c_HVACList){
			if(!_entryExists("HVAC",hvac)){
				_createHVAC(hvac);	
			}
		}
		for(String sensor: this.c_Config.c_SensorList){
			if(!_entryExists("Sensors",sensor)){
				_createSensor(sensor);	
			}
		}
		//update weather
	}
	// function for updating door
	public void updateOpening(String i_ID, String i_Colunm, int i_Val){
		this.c_Config.getDB().execute("UPDATE `iotsec`.`Doors` SET `" + i_Colunm + "` = '" + i_Val + "' WHERE (`ID` = '" + i_ID + "');");
	}
	// function for updating light
	public void updateLight(String i_ID, int i_Val){
		this.c_Config.getDB().execute("UPDATE `iotsec`.`Lights` SET `Level` = '" + i_Val + "' WHERE (`ID` = '" + i_ID + "');");
	}
	// function for updating sensor
	public void updateSensor(String i_ID, int i_Val){
		this.c_Config.getDB().execute("UPDATE `iotsec`.`Sensors` SET `Value` = '" + i_Val + "' WHERE (`ID` = '" + i_ID + "');");
	}
	// function for updating HVAC
	public void updateHVAC(String i_ID, String i_Colunm, int i_Val){
		this.c_Config.getDB().execute("UPDATE `iotsec`.`HVAC` SET `" + i_Colunm + "` = '" + i_Val + "' WHERE (`ID` = '" + i_ID + "');");
	}
	// function for updating Sustem
	public void updateSystem(String i_ID, String i_Colunm, int i_Val){
		this.c_Config.getDB().execute("UPDATE `iotsec`.`System` SET `" + i_Colunm + "` = '" + i_Val + "' WHERE (`ID` = '" + i_ID + "');");
	}
	// function for updating Weather
	public void updateWeather(String i_ID, String i_Colunm, Object i_Val){
		this.c_Config.getDB().execute("UPDATE `iotsec`.`Weather` SET `" + i_Colunm + "` = '" + i_Val + "' WHERE (`ID` = '" + i_ID + "');");
	}
	// function for adding door
	private void _createOpening(String i_ID){
		this.c_Config.getDB().execute("INSERT INTO `iotsec`.`Doors` (`ID`, `Open`, `Locked`) VALUES ('" + i_ID + "', '0', '0');");
	}
	// function for adding light
	private void _createLight(String i_ID){
		this.c_Config.getDB().execute("INSERT INTO `iotsec`.`Lights` (`ID`,`Level`) VALUES ('" + i_ID + "', '0');");
	}
	// function for adding sensor
	private void _createSensor(String i_ID){
		this.c_Config.getDB().execute("INSERT INTO `iotsec`.`Sensors` (`ID`,`Value`) VALUES ('" + i_ID + "', '0');");
	}
	// function for adding HVAC
	private void _createHVAC(String i_ID){
		this.c_Config.getDB().execute("INSERT INTO `iotsec`.`HVAC` (`ID`, `Mode`, `Fan`, `Temp`) VALUES ('" + i_ID + "', '0', '0', '0');");
	}
	// function for creating a openings table
	private void _createTableOpening(){
		this.c_Config.getDB().execute("CREATE TABLE `iotsec`.`Doors` (`Id` VARCHAR(45) NOT NULL,`Open` INT NOT NULL,`Locked` INT NOT NULL, PRIMARY KEY (`Id`));");
	}
	// function for creating a Lights table
	private void _createTableLights(){
		this.c_Config.getDB().execute("CREATE TABLE `iotsec`.`Lights` (`Id` VARCHAR(45) NOT NULL,`Level` INT NOT NULL, PRIMARY KEY (`Id`));");
	}
	// function for creating a System table
	private void _createTableSystem(String i_SysID){
		this.c_Config.getDB().execute("CREATE TABLE `iotsec`.`System` (`SysID` VARCHAR(45) NOT NULL,`Armed` INT NOT NULL, PRIMARY KEY (`SysID`));");
		this.c_Config.getDB().execute("INSERT INTO `iotsec`.`System` (`SysID`, `Armed`) VALUES ('" + i_SysID  + "', '0');");
	}
	// function for creating a Sensors table
	private void _createTableSensor(){
		this.c_Config.getDB().execute("CREATE TABLE `iotsec`.`Sensors` (`Id` VARCHAR(45) NOT NULL,`Value` INT NOT NULL, PRIMARY KEY (`Id`));");
	}
	// Creates HVAC Table
	private void _createTableHVAC(){
		this.c_Config.getDB().execute("CREATE TABLE `iotsec`.`HVAC` (`Id` VARCHAR(45) NOT NULL,`Mode` INT NOT NULL,`Fan` INT NOT NULL,`Temp` INT NOT NULL, PRIMARY KEY (`Id`));");
	}
	// Creates Weather Table
	private void _createTableWeather(){
		this.c_Config.getDB().execute("CREATE TABLE `iotsec`.`Weather` (`ID` VARCHAR(45) NOT NULL DEFAULT 'def', `Sunrise` INT NOT NULL DEFAULT -1, `Sunset` INT NOT NULL DEFAULT -1, `TempMax` INT NOT NULL DEFAULT -1, `TempMin` INT NOT NULL DEFAULT -1, `FeelsLikeMax` INT NOT NULL DEFAULT -1, `FeelsLikeMin` INT NOT NULL DEFAULT -1,`Pressure` INT NOT NULL DEFAULT -1,`Humidity` INT NOT NULL DEFAULT -1, `UVIndex` INT NOT NULL DEFAULT -1, `CloudCover` INT NOT NULL DEFAULT -1, `WindSpeed` INT NOT NULL DEFAULT -1, `WindDirection` INT NOT NULL DEFAULT -1, `WeatherType` VARCHAR(45) NOT NULL DEFAULT ' ', `ChanceOfRain` INT NOT NULL DEFAULT -1, `Visability` INT NOT NULL DEFAULT -1, PRIMARY KEY (`ID`));");
		this.c_Config.getDB().execute("INSERT INTO `iotsec`.`Weather` (`ID`) VALUES ('Day0');");
		this.c_Config.getDB().execute("INSERT INTO `iotsec`.`Weather` (`ID`) VALUES ('Day1');");
		this.c_Config.getDB().execute("INSERT INTO `iotsec`.`Weather` (`ID`) VALUES ('Day2');");
		this.c_Config.getDB().execute("INSERT INTO `iotsec`.`Weather` (`ID`) VALUES ('Day3');");
		this.c_Config.getDB().execute("INSERT INTO `iotsec`.`Weather` (`ID`) VALUES ('Day4');");
		this.c_Config.getDB().execute("INSERT INTO `iotsec`.`Weather` (`ID`) VALUES ('Day5');");
		this.c_Config.getDB().execute("INSERT INTO `iotsec`.`Weather` (`ID`) VALUES ('Day6');");
		this.c_Config.getDB().execute("INSERT INTO `iotsec`.`Weather` (`ID`) VALUES ('Day7');");	
	}
	// function for checking if a Entry exists
	private Boolean _entryExists(String i_TableName, String i_ID){
		Boolean exitsts = false;
			exitsts = this.c_Config.getDB().entryExists(i_TableName, i_ID);
		return exitsts;
	}
	// function for checking if a table exists
	private Boolean _tableExists(String i_TableName){
		Boolean exitsts = false;
			exitsts = this.c_Config.getDB().tableExists(i_TableName);
		return exitsts;
	}
}