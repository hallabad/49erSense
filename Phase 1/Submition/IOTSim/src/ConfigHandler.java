import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.yaml.snakeyaml.*;

public class ConfigHandler {
	MysqlConn c_db = null;
	HashMap<String,Object> c_configMap = new HashMap<>();
	ArrayList<String> c_OpeningList = new ArrayList<>();
	ArrayList<String> c_LightList = new ArrayList<>();
	ArrayList<String> c_HVACList = new ArrayList<>();
	ArrayList<String> c_SensorList = new ArrayList<>();
	HashMap<String, String> c_WeatherConf = new HashMap<>();
	//Takes data from the YAML for configuring
	public void config(String i_confFile){
		_readConfig(i_confFile);
		_dbconfig();
		_sysSetUp();
		_weatherConfig();
	}
	//Getter for the DB
	public MysqlConn getDB(){
		return this.c_db;
	}
	//creates the DB using credentals from the yaml
	private void _dbconfig(){
		String adder = (String) this.c_configMap.get("dbAddress");
		String user = (String) this.c_configMap.get("dbUsername");
		String pass = (String) this.c_configMap.get("dbPassword");
		this.c_db = new MysqlConn(adder,user,pass);
	}	
	//Reads the YAML File
	private void _readConfig(String i_confFile){
		Yaml yaml = new Yaml();
		try{
		InputStream configStream = new FileInputStream(i_confFile);
		this.c_configMap = yaml.load(configStream);
		System.out.println(this.c_configMap);
		} catch(Exception e) {
			System.out.println("[Error] There was an isse reading the config file: " + e.getMessage());
		}
	}
	//Gets Weather config from  yaml
	private void _weatherConfig(){
		this.c_WeatherConf.put("APIKey",(String) this.c_configMap.get("weatherAPIKey"));
		this.c_WeatherConf.put("Long",((Double) this.c_configMap.get("houseLong")).toString());
		this.c_WeatherConf.put("Lat",((Double) this.c_configMap.get("houseLat")).toString());
		this.c_WeatherConf.put("Exclude",(String) this.c_configMap.get("weatherExclude"));
		this.c_WeatherConf.put("Units",(String) this.c_configMap.get("weatherUnits"));
		this.c_WeatherConf.put("Lang",(String) this.c_configMap.get("weatherlang"));
	}
	//Gets House data from the yaml
	private void _sysSetUp(){
		ArrayList<Object> doorsFromMap = (ArrayList<Object>) this.c_configMap.get("doors");
		ArrayList<Object> lightsFromMap = (ArrayList<Object>) this.c_configMap.get("lights");
		ArrayList<Object> sensorsFromMap = (ArrayList<Object>) this.c_configMap.get("sensors");
		ArrayList<Object> windowsFromMap = (ArrayList<Object>) this.c_configMap.get("windows");
		ArrayList<Object> hvacFromMap = (ArrayList<Object>) this.c_configMap.get("hvac");
		String doorSTR;
		String lightSTR;
		String sensorSTR;
		String windowSTR;
		String hvacSTR;

		// //Door loop
		for (Object door: doorsFromMap) { 
			doorSTR = door.toString().toLowerCase();
			this.c_OpeningList.add(doorSTR);
			System.out.println("[Info] Added OPENING  " + doorSTR + " to the OPENING list");
		}
		// //Light loop
		for (Object light: lightsFromMap) { 
			lightSTR = light.toString().toLowerCase();
			this.c_LightList.add(lightSTR);
			System.out.println("[Info] Added LIGHT  " + lightSTR + " to the LIGHT list");
		}
		// //Sensor loop
		for (Object sensor: sensorsFromMap) { 
			sensorSTR = sensor.toString().toLowerCase();
			this.c_SensorList.add(sensorSTR);
			System.out.println("[Info] Added SENSOR " + sensorSTR + " to the SENSOR list");
		}
		// //window Loop
		for (Object window: windowsFromMap) { 
			windowSTR = window.toString().toLowerCase();
			this.c_OpeningList.add(windowSTR);
			System.out.println("[Info] Added OPENING " + windowSTR + " to the OPENING list");
		}
		// //hvac Loop
		for (Object hvac: hvacFromMap) { 
			hvacSTR = hvac.toString().toLowerCase();
			this.c_HVACList.add(hvacSTR);
			System.out.println("[Info] Added HVAC " + hvacSTR + " to the HVAC list");
		}
	}
}
