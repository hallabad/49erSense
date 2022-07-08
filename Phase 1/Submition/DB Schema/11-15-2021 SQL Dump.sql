-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 192.168.5.247    Database: iotsec
-- ------------------------------------------------------
-- Server version	8.0.27-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Doors`
--

DROP TABLE IF EXISTS `Doors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Doors` (
  `Id` varchar(45) NOT NULL,
  `Open` int NOT NULL,
  `Locked` int NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Doors`
--

LOCK TABLES `Doors` WRITE;
/*!40000 ALTER TABLE `Doors` DISABLE KEYS */;
INSERT INTO `Doors` VALUES ('back door',1,0),('bed 1 windows',0,0),('bed 2 windows',0,0),('frount door',0,0),('garage windows',0,0),('garagedoor 1',0,0),('garagedoor 2',0,0),('garagedoor door',0,0),('kitchen windows',0,0),('livingroom windows',0,0),('side door',0,0);
/*!40000 ALTER TABLE `Doors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HVAC`
--

DROP TABLE IF EXISTS `HVAC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `HVAC` (
  `Id` varchar(45) NOT NULL,
  `Mode` int NOT NULL,
  `Fan` int NOT NULL,
  `Temp` int NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HVAC`
--

LOCK TABLES `HVAC` WRITE;
/*!40000 ALTER TABLE `HVAC` DISABLE KEYS */;
INSERT INTO `HVAC` VALUES ('floor 1',0,0,0),('floor 2',0,0,0);
/*!40000 ALTER TABLE `HVAC` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Lights`
--

DROP TABLE IF EXISTS `Lights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Lights` (
  `Id` varchar(45) NOT NULL,
  `Level` int NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lights`
--

LOCK TABLES `Lights` WRITE;
/*!40000 ALTER TABLE `Lights` DISABLE KEYS */;
INSERT INTO `Lights` VALUES ('bed 1 fan',0),('bed 1 pot',0),('bed 2 fan',0),('bed 2 pot',0),('entryway',0),('garage pot',0),('kitchen pot',0),('kitchen sink',0),('kitchen table',0),('livingroom fan',0),('livingroom pot',0);
/*!40000 ALTER TABLE `Lights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sensors`
--

DROP TABLE IF EXISTS `Sensors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Sensors` (
  `Id` varchar(45) NOT NULL,
  `Value` int NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sensors`
--

LOCK TABLES `Sensors` WRITE;
/*!40000 ALTER TABLE `Sensors` DISABLE KEYS */;
INSERT INTO `Sensors` VALUES ('a',0),('b',0),('c',0),('d',0),('e',0);
/*!40000 ALTER TABLE `Sensors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `System`
--

DROP TABLE IF EXISTS `System`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `System` (
  `SysID` varchar(45) NOT NULL,
  `Armed` int NOT NULL,
  PRIMARY KEY (`SysID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `System`
--

LOCK TABLES `System` WRITE;
/*!40000 ALTER TABLE `System` DISABLE KEYS */;
INSERT INTO `System` VALUES ('Dev House 1',0);
/*!40000 ALTER TABLE `System` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Weather`
--

DROP TABLE IF EXISTS `Weather`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Weather` (
  `ID` varchar(45) NOT NULL DEFAULT 'def',
  `Sunrise` int NOT NULL DEFAULT '-1',
  `Sunset` int NOT NULL DEFAULT '-1',
  `TempMax` int NOT NULL DEFAULT '-1',
  `TempMin` int NOT NULL DEFAULT '-1',
  `FeelsLikeMax` int NOT NULL DEFAULT '-1',
  `FeelsLikeMin` int NOT NULL DEFAULT '-1',
  `Pressure` int NOT NULL DEFAULT '-1',
  `Humidity` int NOT NULL DEFAULT '-1',
  `UVIndex` int NOT NULL DEFAULT '-1',
  `CloudCover` int NOT NULL DEFAULT '-1',
  `WindSpeed` int NOT NULL DEFAULT '-1',
  `WindDirection` int NOT NULL DEFAULT '-1',
  `WeatherType` varchar(45) NOT NULL DEFAULT ' ',
  `ChanceOfRain` int NOT NULL DEFAULT '-1',
  `Visability` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Weather`
--

LOCK TABLES `Weather` WRITE;
/*!40000 ALTER TABLE `Weather` DISABLE KEYS */;
INSERT INTO `Weather` VALUES ('Day0',1636977521,1637014656,56,40,49,36,1022,24,280,2,8,298,'clear sky',0,-1),('Day1',1637063981,1637101019,63,42,56,42,1023,33,285,0,7,240,'clear sky',0,-1),('Day2',1637150441,1637187384,71,50,66,49,1024,54,292,0,8,213,'clear sky',0,-1),('Day3',1637236900,1637273751,70,51,69,50,1021,52,296,20,12,223,'light rain',0,-1),('Day4',1637323359,1637360120,55,41,49,36,1027,24,300,0,10,1,'clear sky',0,-1),('Day5',1637409818,1637446491,49,34,47,29,1032,30,300,21,7,40,'few clouds',0,-1),('Day6',1637496277,1637532863,55,39,51,39,1024,47,300,100,5,237,'overcast clouds',0,-1),('Day7',1636977521,1637014656,40,-1,38,-1,1016,80,0,40,3,250,'scattered clouds',-1,10000);
/*!40000 ALTER TABLE `Weather` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-15  3:46:44
