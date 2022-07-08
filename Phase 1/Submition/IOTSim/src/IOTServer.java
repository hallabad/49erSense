
public class IOTServer {
    
    public static void main(String[] args) throws Exception {
        String sys_ID = "Dev House 1";
        ConfigHandler sys_ConfHandler = new ConfigHandler();
        MySQLHandler sys_DBHandler = new MySQLHandler(sys_ConfHandler);
        WeatherHandler sys_WeatherHandler = null;
        Consol sys_Consol = null;
        NetworkingHandler sys_NetConsol = null;
        sys_ConfHandler.config("config.yaml");
        sys_DBHandler.config(sys_ID);
        System.out.println("[Info] Database is setup.");
        sys_WeatherHandler = new WeatherHandler(sys_ConfHandler.c_WeatherConf, sys_DBHandler);
        sys_WeatherHandler.updateWeatherConfig(sys_ConfHandler.c_WeatherConf);
        sys_WeatherHandler.updateWeather();
        sys_Consol = new Consol(sys_ID, sys_DBHandler, sys_WeatherHandler);
        sys_NetConsol = new NetworkingHandler(5001, sys_Consol);
        sys_NetConsol.start();
        sys_Consol.startServerConsol();
        System.out.println("Th...Th...Thats all Folks!!");
    }
}
