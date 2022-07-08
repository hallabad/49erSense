This is the code used to run a node on the IoT System. This code can be run by renaming the wanted config file to conf.yaml and using the commnd "java -jar IOTSimClient.jar". The code will read the conf.yaml, create a new object for each device in the config file, then start a new thread that will simulate a sensore changing value and sending the value to the server.

Explaning conf.yaml
	serverAddress - The IP of the server the node will communicate with
	serverPort - The port the socket will use for server communication
	nodeID - The name of the node (floor 1, floor 2, basement)
	doors - The list of all the doors the system will be monitoring
	lights - The list of all the lights the system will be moitoring
	sensors - This is a list of all the sensors that will be moditored by the system catagorized by type
		temp - The list of all temp sensors
		light - The list of all light level sensors
		motion - The list of all motion sensores
	windows - The list of all windows that will be monitored
	hvac - The list of all hvac systems that will be monitored