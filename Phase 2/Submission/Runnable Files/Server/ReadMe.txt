This is the code used to run central server for the IoT System. This code can be run by editing the config file and using the commnd "java -jar IOTSim.jar". The code will read the conf.yaml, connect to the database, make sure the database is properly setup, querry the weather API for data, Finally the server starts the licening socket and main server consol.

Explaning conf.yaml
	dbAddress - This is the address the server will communcate with the mysql database.
	dbPassword - This is the password used by the server to connect to the mysql database.
	dbUsername - This is the username used by the server to connect to the mysql database.
	weatherAPIKey - This is the API key used by the server to obtain the weather data. This can be gotten from the OpenWeatherAPI website.
	houseLong - This is the Longitude of the house.
	houseLat - This is the Latitude of the house.
	weatherExclude - This is the weather data that will be excluded from the weather API call. The options for this can be seen from the weather API documentation in the in the Usefull Documentation section
	weatherUnits - This is the units that the weather data will be retuned in.
	weatherlang - This is the language the weather data will be retuned in.
	floors - The list of floors that will be monitored
		doors - The list of all the doors the system will be monitoring
		lights - The list of all the lights the system will be moitoring
		sensors - This is a list of all the sensors that will be moditored by the system catagorized by type
			temp - The list of all temp sensors
			light - The list of all light level sensors
			motion - The list of all motion sensores
		windows - The list of all windows that will be monitored
		hvac - The list of all hvac systems that will be monitored


To use this server:
	- Set up a MySQL database with the name iotsec. 
	- Set up a user in the MySQL server for the server to use.
	- Input the IP, Username, and Password for the MySQL database into the config.yaml
	- Start the server with the command "java -jar IOTSim.jar"
		- The server will make sure sure all needed tables are configureed properly and populates them with the divices from the yaml file.
		- The server will start a socket handler to allow for clients to connect.
		- The server will start a local consol allowing for local debugging.
	- To stop the server from running, type "stop"(not case sensitive) into the consol and hit enter.

Usefull Documentation:
Weather API:
	https://openweathermap.org/api/one-call-api