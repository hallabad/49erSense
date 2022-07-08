import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IOTClient {
    public static void main(String[] args) throws Exception {
        String d_ip = "";
        int d_port = 800000;
        String d_id = "";
        HashMap<String,Object> d_testMap = new HashMap<>();
        d_testMap = YamlHandler.readYaml("conf.yaml");
        d_ip = (String) d_testMap.get("serverAddress");
        d_port = (Integer) d_testMap.get("serverPort");
        d_id = (String) d_testMap.get("nodeID");

        ArrayList<String> d_doorList = new ArrayList<>();
        ArrayList<String> d_lightList = new ArrayList<>();
        ArrayList<String> d_sensorList = new ArrayList<>();
        ArrayList<String> d_hvacList = new ArrayList<>();

        for(String name:(ArrayList<String>) d_testMap.get("doors")){
            d_doorList.add(d_id + "-Door-" + name);
        }
        for(String name:(ArrayList<String>) d_testMap.get("windows")){
            d_doorList.add(d_id + "-Window-" + name);
        }
        for(String name:(ArrayList<String>) d_testMap.get("lights")){
            d_lightList.add(d_id + "-Light-" + name);
        }
        for(String name:((ArrayList<String>) (((HashMap<String,Object>) d_testMap.get("sensors"))).get("temp"))){
            d_sensorList.add(d_id + "-Temp-Sensor-" + name);
        }
        for(String name:((ArrayList<String>) (((HashMap<String,Object>) d_testMap.get("sensors"))).get("light"))){
            d_sensorList.add(d_id + "-Light-Sensor-" + name);
        }
        for(String name:((ArrayList<String>) (((HashMap<String,Object>) d_testMap.get("sensors"))).get("motion"))){
            d_sensorList.add(d_id + "-Motion-Sensor-" + name);
        }
        for(String name:(ArrayList<String>) d_testMap.get("hvac")){
            d_hvacList.add(d_id + "-HVAC-" + name);
        }
        
        System.out.println(d_doorList);
        System.out.println(d_lightList);
        System.out.println(d_sensorList);
        System.out.println(d_hvacList);

        // Boolean d_servAlive = SocketHandler.isServerAlive(d_ip, d_port);
        int[] d_doorinputs = {0,1};
        int[] d_lightinputs = {1,2,3,4,5,6,7,8,9};
        int[] d_sensorinputs = {1,2,3,4,5,6,7,8,9};
        int[] d_hvacinputs = {1,2,3,4,5};

        ArrayList<Door> d_Doors = new ArrayList<>();
        for(String door: d_doorList){
            SocketHandler d_test = new SocketHandler(d_ip, d_port);
            Door d_tDoor = new Door(door, d_doorinputs,5,d_test);
            d_Doors.add(d_tDoor);
            d_tDoor.start();
        }
        ArrayList<Light> d_Lights = new ArrayList<>();
        for(String light: d_lightList){
            SocketHandler d_test = new SocketHandler(d_ip, d_port);
            Light d_tlight = new Light(light, d_lightinputs,5,d_test);
            d_Lights.add(d_tlight);
            d_tlight.start();
        }
        ArrayList<Sensor> d_Sensors = new ArrayList<>();
        for(String sensor: d_sensorList){
            SocketHandler d_test = new SocketHandler(d_ip, d_port);
            Sensor d_tSensor = new Sensor(sensor, d_sensorinputs,5,d_test);
            d_Sensors.add(d_tSensor);
            d_tSensor.start();
        }
        ArrayList<HVAC> d_HVACs = new ArrayList<>();
        for(String hvac: d_hvacList){
            SocketHandler d_test = new SocketHandler(d_ip, d_port);
            HVAC d_tHVAC = new HVAC(hvac, d_hvacinputs,5,d_test);
            d_HVACs.add(d_tHVAC);
            d_tHVAC.start();
        }
        TimeUnit.SECONDS.sleep(1000);
        for(Door t: d_Doors){
            t.stop();
        }
        for(Light t: d_Lights){
            t.stop();
        }
        for(Sensor t: d_Sensors){
            t.stop();
        }
        for(HVAC t: d_HVACs){
            t.stop();
        }
    }
}
