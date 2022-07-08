import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.*;
import org.json.simple.parser.*;

public class WeatherHandler {
	private HashMap<String, String> c_Config = new HashMap<>();
	private URL c_httpQuerry;
	private String c_WeatherData;
	private Weather[] c_Weather = new Weather[8];
	private MySQLHandler c_Handler;

	public WeatherHandler(HashMap<String, String> i_conf, MySQLHandler i_Handler){
		this.c_Config = i_conf;
		this.c_Handler = i_Handler;
	}
	//updates the wather config from the weather config map
	public void updateWeatherConfig(HashMap<String, String> i_conf){
		String APIKey = i_conf.get("APIKey");
		String Long = i_conf.get("Long");
		String Lat = i_conf.get("Lat");
		String Exclude = i_conf.get("Exclude");
		String Units = i_conf.get("Units");
		String Lang = i_conf.get("Lang");
		try{
		this.c_httpQuerry = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=" + Lat + "&lon=" + Long + "&units=" + Units + "&exclude=" + Exclude + "&appid=" + APIKey + "&lang=" + Lang) ;
		} catch (Exception e) {
			System.out.println("[Error] There was an issue updating the weather config: " + e.getMessage());
		}
	}
	//updates the weather data from the http API and pushes it to the DB
	public void updateWeather(){
		try{
			_getWeather();
			_parseWeather();
			_setWeather();
		} catch (Exception e) {
			System.out.println("[Error] There was an issue updating the weather: " + e);
		}
	}
	// pushes the new weather data to the BD
	private void _setWeather(){
		c_Weather[0].pushWeather();
		c_Weather[1].pushWeather();
		c_Weather[2].pushWeather();
		c_Weather[3].pushWeather();
		c_Weather[4].pushWeather();
		c_Weather[5].pushWeather();
		c_Weather[6].pushWeather();
		c_Weather[7].pushWeather(); //Current Weather
	}
	//Takes the json resopnse and create the 8 weather events
	private void _parseWeather(){
		int sunrise;
		int sunset;
		int tempMax;
		int tempMin;
		int feelsLikeMax;
		int feelsLikeMin;
		int pressure;
		int humidity;
		Double uVIndex_double;
		int uVIndex;
		int cloudCover;
		int visability;
		int windSpeed;
		int windDirection;
		String weatherType;
		int chanceOfPrecip;
		JSONParser weatherParser = new JSONParser();
		JSONObject weatherJSON = null;
		JSONArray weatherDaily = null;
		JSONObject weatherDayCurr = null;
		JSONObject weatherDay = null;
		try{
			weatherJSON = (JSONObject) weatherParser.parse(this.c_WeatherData);
		} catch (Exception e) {
			System.out.println("[Error] There was an issue parsing the Json weather data: " + e.getMessage());
		}
		weatherDayCurr = (JSONObject) weatherJSON.get("current");
		weatherDaily = (JSONArray) weatherJSON.get("daily");
		sunrise = ((Number) weatherDayCurr.get("sunrise")).intValue();
		sunset = ((Number) weatherDayCurr.get("sunset")).intValue();
		tempMax = ((Number) weatherDayCurr.get("temp")).intValue();
		tempMin = -1;
		feelsLikeMax = (((Number) weatherDayCurr.get("feels_like"))).intValue();
		feelsLikeMin = -1;
		pressure = ((Number) weatherDayCurr.get("pressure")).intValue();
		humidity = ((Number) weatherDayCurr.get("humidity")).intValue();
		uVIndex_double = ((((Number) weatherDayCurr.get("uvi")).doubleValue())*100);
		uVIndex = uVIndex_double.intValue(); 
		cloudCover = ((Number) weatherDayCurr.get("clouds")).intValue();
		visability = ((Number) weatherDayCurr.get("visibility")).intValue();
		windSpeed = ((Number) weatherDayCurr.get("wind_speed")).intValue();
		windDirection = ((Number) weatherDayCurr.get("wind_deg")).intValue();
		weatherType = ((String)((JSONObject)(((JSONArray) weatherDayCurr.get("weather")).get(0))).get("description"));
		chanceOfPrecip = -1;
		this.c_Weather[7] = new Weather(this.c_Handler);
		this.c_Weather[7].updateWeather("Day" + 7, sunrise, sunset, tempMax, tempMin, feelsLikeMax, feelsLikeMin, pressure, humidity, uVIndex, cloudCover, visability, windSpeed, windDirection, weatherType, chanceOfPrecip);

		for(int i = 0 ; i < 7 ; i++){
			weatherDay = (JSONObject) weatherDaily.get(i);
			sunrise = ((Number) weatherDay.get("sunrise")).intValue();
			sunset = ((Number) weatherDay.get("sunset")).intValue();
			tempMax = ((Number) (((JSONObject) weatherDay.get("temp")).get("max"))).intValue();
			tempMin = ((Number) (((JSONObject) weatherDay.get("temp")).get("min"))).intValue();
			feelsLikeMax = ((Number)((JSONObject) weatherDay.get("feels_like")).get("day")).intValue();
			feelsLikeMin = ((Number)((JSONObject) weatherDay.get("feels_like")).get("morn")).intValue();
			pressure = ((Number) weatherDay.get("pressure")).intValue();
			humidity = ((Number) weatherDay.get("humidity")).intValue();
			uVIndex = ((Double)(((((Number) weatherDay.get("uvi")).doubleValue())*100))).intValue();
			cloudCover = ((Number) weatherDay.get("clouds")).intValue();
			visability = -1;
			windSpeed = ((Number) weatherDay.get("wind_speed")).intValue();
			windDirection = ((Number) weatherDay.get("wind_deg")).intValue();
			weatherType = (String) (((JSONObject)((JSONArray) weatherDay.get("weather")).get(0)).get("description"));
			chanceOfPrecip = ((Number) weatherDay.get("pop")).intValue();
			this.c_Weather[i] = new Weather(this.c_Handler);
			this.c_Weather[i].updateWeather("Day" + i, sunrise, sunset, tempMax, tempMin, feelsLikeMax, feelsLikeMin, pressure, humidity, uVIndex, cloudCover, visability, windSpeed, windDirection, weatherType, chanceOfPrecip);
		}

		for(Weather t: this.c_Weather){
			System.out.println("[Info] " + t.toString());
		}


	}
	//issues http calls and returns the json data from it.
	private void _getWeather(){
		try{
			HttpURLConnection thing = (HttpURLConnection) this.c_httpQuerry.openConnection();
			if (thing.getResponseCode() == 200){
				System.out.println("[Info] WeatherAPI HTTP call sucsessful");
			} else if (thing.getResponseCode() == 401){
				System.out.println("[Error] WeatherAPI HTTP call error " + thing.getResponseMessage());
			} else if (thing.getResponseCode() == 404){
				System.out.println("[Error] WeatherAPI HTTP call error " + thing.getResponseMessage());
			} else {
				System.out.println("[Info] WeatherAPI HTTP call unsucsessful " + thing.getResponseMessage());
			}
			BufferedReader stream_reader = new BufferedReader(new InputStreamReader(thing.getInputStream()));
			this.c_WeatherData = stream_reader.readLine();
			// System.out.println(this.c_WeatherData);
		} catch (Exception e) {
			System.out.println("[Error] There was an issue issuing the HTTP call: " + e.getMessage());
		}
	}

}
