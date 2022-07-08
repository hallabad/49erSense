This is the server for the IOT App.

This server acts as the main communication point between the nodes and the database. This is done with a socket between the server and a client. 

To configure the server for the "house" this system would be used in you can edit the config.yaml file ajusting the opetions below.
	dbAddress: This is the address the server will communcate with the mysql database.
	dbPassword: This is the password used by the server to connect to the mysql database.
	dbUsername: This is the username used by the server to connect to the mysql database.
	weatherAPIKey: This is the API key used by the server to obtain the weather data. This can be gotten from the OpenWeatherAPI website.
	houseLong: This is the Longitude of the house.
	houseLat: This is the Latitude of the house.
	weatherExclude: This is the weather data that will be excluded from the weather API call. The options for this can be seen from the weather API documentation in the in the Usefull Documentation section
	weatherUnits: This is the units that the weather data will be retuned in.
	weatherlang : This is the language the weather data will be retuned in.
	doors: This is the list of monitored doors.
	lights: This is the list of lights that the system will monitor.
	sensors: This is the list of sensors that the system will monitor.
	windows: This is the list of monitored windows.
	hvac: This is the list of monitored HVAC units.

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