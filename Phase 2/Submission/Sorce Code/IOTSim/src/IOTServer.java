import java.net.*;

import org.w3c.dom.Node;

public class IOTServer {
    
    public static void main(String[] args) throws Exception {
        String sys_ID = "Dev House 1";
        ConfigHandler sys_ConfHandler = new ConfigHandler();
        MySQLHandler sys_DBHandler = new MySQLHandler(sys_ConfHandler);
        WeatherHandler sys_WeatherHandler = null;
        LocalConsol sys_Local_Consol = null;
        sys_ConfHandler.config("config.yaml");
        sys_DBHandler.config(sys_ID);
        System.out.println("[Info] Database is setup.");
        sys_WeatherHandler = new WeatherHandler(sys_ConfHandler.c_WeatherConf, sys_DBHandler);
        sys_WeatherHandler.updateWeatherConfig(sys_ConfHandler.c_WeatherConf);
        sys_WeatherHandler.updateWeather();
        sys_Local_Consol = new LocalConsol(sys_ID, sys_DBHandler, sys_WeatherHandler);
        ClientHandler d_ClientHandler = new ClientHandler(5001,sys_ID,sys_DBHandler,sys_WeatherHandler);
        d_ClientHandler.start();
        sys_Local_Consol.runConsol();
        d_ClientHandler.stop();
        System.out.println("Th...Th...Thats all Folks!!");
    }
}
