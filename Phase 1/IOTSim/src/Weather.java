public class Weather {
	private MySQLHandler c_DBHandler;
	private String c_Day = "";
	private int c_Sunrise = -1;
	private int c_Sunset = -1;
	private int c_TempMax = -1;
	private int c_TempMin = -1;
	private int c_FeelsLikeMax = -1;
	private int c_FeelsLikeMin = -1;
	private int c_Pressure = -1;
	private int c_Humidity = -1;
	private int c_UVIndex = -1;
	private int c_CloudCover = -1;
	private int c_Visability = -2;
	private int c_WindSpeed = -1;
	private int c_WindDirection = -1;
	private String c_WeatherType = "";
	private int c_ChanceOfPrecip = -1;
	//Constructor that creates the MySQLHandler and Weather ID Map
	public Weather(MySQLHandler i_DBHandler){
		this.c_DBHandler = i_DBHandler;
		
	}
	//Updates the weather data
	public void updateWeather(String i_Day , int i_Sunrise , int i_Sunset , int i_TempMax , int i_TempMin , int i_FeelsLikeMax , int i_FeelsLikeMin , int i_Pressure , int i_Humidity , int i_UVIndex , int i_CloudCover , int i_Visability , int i_WindSpeed , int i_WindDirection , String i_WeatherType , int i_ChanceOfPrecip){
		this.c_Day = i_Day;
		this.c_Sunrise = i_Sunrise;
		this.c_Sunset = i_Sunset;
		this.c_TempMax = i_TempMax;
		this.c_TempMin = i_TempMin;
		this.c_FeelsLikeMax = i_FeelsLikeMax;
		this.c_FeelsLikeMin = i_FeelsLikeMin;
		this.c_Pressure = i_Pressure;
		this.c_Humidity = i_Humidity;
		this.c_UVIndex = i_UVIndex;
		this.c_CloudCover = i_CloudCover;
		this.c_Visability = i_Visability;
		this.c_WindSpeed = i_WindSpeed;
		this.c_WindDirection = i_WindDirection;
		this.c_WeatherType = i_WeatherType;
		this.c_ChanceOfPrecip = i_ChanceOfPrecip;
	}
	//Pushes weather to the DB
	public void pushWeather(){
		this.c_DBHandler.updateWeather(this.c_Day, "Sunrise", this.c_Sunrise);
		this.c_DBHandler.updateWeather(this.c_Day, "Sunset", this.c_Sunset);
		this.c_DBHandler.updateWeather(this.c_Day, "TempMax", this.c_TempMax);
		this.c_DBHandler.updateWeather(this.c_Day, "TempMin", this.c_TempMin);
		this.c_DBHandler.updateWeather(this.c_Day, "FeelsLikeMax", this.c_FeelsLikeMax);
		this.c_DBHandler.updateWeather(this.c_Day, "FeelsLikeMin", this.c_FeelsLikeMin);
		this.c_DBHandler.updateWeather(this.c_Day, "Pressure", this.c_Pressure);
		this.c_DBHandler.updateWeather(this.c_Day, "Humidity", this.c_Humidity);
		this.c_DBHandler.updateWeather(this.c_Day, "UVIndex", this.c_UVIndex);
		this.c_DBHandler.updateWeather(this.c_Day, "CloudCover", this.c_CloudCover);
		this.c_DBHandler.updateWeather(this.c_Day, "Visability", this.c_Visability);
		this.c_DBHandler.updateWeather(this.c_Day, "WindSpeed", this.c_WindSpeed);
		this.c_DBHandler.updateWeather(this.c_Day, "WindDirection", this.c_WindDirection);
		this.c_DBHandler.updateWeather(this.c_Day, "WeatherType", this.c_WeatherType);
		this.c_DBHandler.updateWeather(this.c_Day, "ChanceOfRain", this.c_ChanceOfPrecip);
	}
	//Convertes the Weather data to string
	public String toString(){
		return "Day:" + this.c_Day + " Sunrise: " + this.c_Sunrise + " Sunset: " + this.c_Sunset + " TempMax: " + this.c_TempMax + " TempMin: " + this.c_TempMin + " FeelsLikeMax: " + this.c_FeelsLikeMax + " FeelsLikeMin: " + this.c_FeelsLikeMin + " Pressure: " + this.c_Pressure + " Humidity: " + this.c_Humidity + " UVIndex: " + this.c_UVIndex + " CloudCover: " + this.c_CloudCover + " Visability: " + this.c_Visability + " WindSpeed: " + this.c_WindSpeed + " WindDirection: " + this.c_WindDirection + " WeatherType: " + this.c_WeatherType + " ChanceOfPrecip: " + this.c_ChanceOfPrecip;
	}
}
