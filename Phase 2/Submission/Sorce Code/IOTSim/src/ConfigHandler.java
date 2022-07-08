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
		// System.out.println(this.c_configMap);
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
		ArrayList<HashMap<String,Object>> floorsList = (ArrayList<HashMap<String,Object>>) this.c_configMap.get("floors");
		for(int i = 0; i<floorsList.size(); i++){
			HashMap<String,Object> floor = (HashMap<String,Object>) ((floorsList.get(i)).get(i+1));
			ArrayList<String> doorsFromMap = (ArrayList<String>) (floor.get("doors"));
			System.out.println(doorsFromMap);
			ArrayList<String> lightsFromMap = (ArrayList<String>) floor.get("lights");
			HashMap<String,Object> sensorsMap = (HashMap<String,Object>) floor.get("sensors");
			System.out.println(sensorsMap);
			ArrayList<String> sensorsFromMap = new ArrayList<>();
			for(String name:((ArrayList<String>) sensorsMap.get("temp"))){
				sensorsFromMap.add("-Temp-Sensor-" + name);
			}
			for(String name:((ArrayList<String>) sensorsMap.get("light"))){
				sensorsFromMap.add("-Light-Sensor-" + name);
			}
			for(String name:((ArrayList<String>) sensorsMap.get("motion"))){
				sensorsFromMap.add("-Motion-Sensor-" + name);
			}
			ArrayList<String> windowsFromMap = (ArrayList<String>) floor.get("windows");
			ArrayList<String> hvacFromMap = (ArrayList<String>) floor.get("hvac");
			String doorSTR;
			String lightSTR;
			String sensorSTR;
			String windowSTR;
			String hvacSTR;

			// //Door loop
			if (doorsFromMap != null){
				for (String door: doorsFromMap) { 
					doorSTR = "floor" + (i+1) + "-door-" + door;
					this.c_OpeningList.add(doorSTR.toLowerCase());
					System.out.println("[Info] Added OPENING  " + doorSTR + " to the OPENING list");
				}
			}
			// //Light loop
			for (String light: lightsFromMap) { 
				lightSTR = "floor" + (i+1) + "-light-" +light;
				this.c_LightList.add(lightSTR.toLowerCase());
				System.out.println("[Info] Added LIGHT  " + lightSTR + " to the LIGHT list");
			}
			// //Sensor loop
			for (String sensor: sensorsFromMap) { 
				sensorSTR = "floor" + (i+1) + sensor;
				this.c_SensorList.add(sensorSTR);
				System.out.println("[Info] Added SENSOR " + sensorSTR + " to the SENSOR list");
			}
			// //window Loop
			for (String window: windowsFromMap) { 
				windowSTR = "floor" + (i+1) + "-window-" + window;
				this.c_OpeningList.add(windowSTR.toLowerCase());
				System.out.println("[Info] Added OPENING " + windowSTR + " to the OPENING list");
			}
			// //hvac Loop
			for (String hvac: hvacFromMap) { 
				hvacSTR = "floor" + (i+1) + "-HVAC-" + hvac;
				this.c_HVACList.add(hvacSTR.toLowerCase());
				System.out.println("[Info] Added HVAC " + hvacSTR + " to the HVAC list");
			}
		}
	}
}
