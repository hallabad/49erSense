import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.yaml.snakeyaml.*;

public class YamlHandler {

	public static HashMap<String,Object> readYaml(String i_confFile){
		Yaml yaml = new Yaml();
		HashMap<String,Object> yamlMap = new HashMap<>();
		try{
		InputStream configStream = new FileInputStream(i_confFile);
		yamlMap = yaml.load(configStream);
		} catch(Exception e) {
			System.out.println("[Error] There was an isse reading the yaml file: " + e.getMessage());
		}
		return yamlMap;
	}

	public static HashMap<String,Object> breakMap(HashMap<String,Object> i_yamlMap){
		HashMap<String,Object> outMap = new HashMap<>();
		

		return outMap;
	}

	public static ArrayList<String> mapToList(HashMap<String,Object> i_Map){
		ArrayList<String> outList = new ArrayList<>();
		String tempString;

		for(String key: i_Map.keySet()){
			tempString = (String) i_Map.get(key);
			outList.add(tempString);
		}

		return outList;
	}

}
