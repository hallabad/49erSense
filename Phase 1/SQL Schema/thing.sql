CREATE DATABASE  IF NOT EXISTS `iotsec` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `iotsec`;
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
  `ID` varchar(45) NOT NULL,
  `Open` int NOT NULL,
  `Locked` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Doors`
--

LOCK TABLES `Doors` WRITE;
/*!40000 ALTER TABLE `Doors` DISABLE KEYS */;
INSERT INTO `Doors` VALUES ('back door',0,0),('Bed 1 Windows',0,0),('Bed 2 Windows',0,0),('Frount Door',0,0),('Garage Windows',0,0),('GarageDoor 1',0,0),('GarageDoor 2',0,0),('GarageDoor Door',0,0),('Kitchen Windows',0,0),('Livingroom Windows',0,0),('Side Door',0,0);
/*!40000 ALTER TABLE `Doors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HVAC`
--

DROP TABLE IF EXISTS `HVAC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `HVAC` (
  `ID` varchar(45) NOT NULL,
  `Mode` int NOT NULL DEFAULT '0',
  `Fan` int NOT NULL DEFAULT '0',
  `Temp` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HVAC`
--

LOCK TABLES `HVAC` WRITE;
/*!40000 ALTER TABLE `HVAC` DISABLE KEYS */;
INSERT INTO `HVAC` VALUES ('floor 1',1,1,80),('floor 2',0,1,100);
/*!40000 ALTER TABLE `HVAC` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Lights`
--

DROP TABLE IF EXISTS `Lights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Lights` (
  `ID` varchar(45) NOT NULL,
  `Level` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lights`
--

LOCK TABLES `Lights` WRITE;
/*!40000 ALTER TABLE `Lights` DISABLE KEYS */;
INSERT INTO `Lights` VALUES ('Bed 1 Fan',0),('Bed 1 Pot',0),('Bed 2 Fan',0),('Bed 2 Pot',0),('Entryway',50),('Garage Pot',0),('Kitchen Pot',0),('Kitchen Sink',0),('Kitchen Table',0),('Livingroom Fan',0),('Livingroom Pot',0);
/*!40000 ALTER TABLE `Lights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sensors`
--

DROP TABLE IF EXISTS `Sensors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Sensors` (
  `ID` varchar(48) NOT NULL,
  `Value` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sensors`
--

LOCK TABLES `Sensors` WRITE;
/*!40000 ALTER TABLE `Sensors` DISABLE KEYS */;
INSERT INTO `Sensors` VALUES ('Bed 1 co2',15),('Bed 1 Temp',81),('Bed 2 co2',13),('Bed 2 Temp',80),('Garadge Temp',86),('Kitchen co2',10),('Kitchen Temp',79),('Living Room co2',20),('Living Room Temp',79);
/*!40000 ALTER TABLE `Sensors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `System`
--

DROP TABLE IF EXISTS `System`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `System` (
  `SysID` varchar(45) NOT NULL DEFAULT 'Default_12345',
  `Armed` int NOT NULL DEFAULT '2',
  PRIMARY KEY (`SysID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `System`
--

LOCK TABLES `System` WRITE;
/*!40000 ALTER TABLE `System` DISABLE KEYS */;
INSERT INTO `System` VALUES ('Test_1234',0);
/*!40000 ALTER TABLE `System` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Weathers`
--

DROP TABLE IF EXISTS `Weathers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Weathers` (
  `ID` varchar(45) NOT NULL DEFAULT 'none',
  `Sunrise` int NOT NULL DEFAULT '-1',
  `Sunset` int NOT NULL DEFAULT '-1',
  `Temp` int NOT NULL DEFAULT '-1',
  `FeelsLike` int NOT NULL DEFAULT '-1',
  `Pressure` int NOT NULL DEFAULT '-1',
  `Humidity` int NOT NULL DEFAULT '-1',
  `UVIndex` decimal(10,0) NOT NULL DEFAULT '-1',
  `CloudCover` int NOT NULL DEFAULT '-1',
  `Visability` int NOT NULL DEFAULT '-1',
  `WindSpeed` int NOT NULL DEFAULT '-1',
  `WindDirection` int NOT NULL DEFAULT '-1',
  `WeatherType` varchar(45) NOT NULL DEFAULT 'none',
  `ChaneOfRain` decimal(10,0) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Weathers`
--

LOCK TABLES `Weathers` WRITE;
/*!40000 ALTER TABLE `Weathers` DISABLE KEYS */;
INSERT INTO `Weathers` VALUES ('Day1',-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,'none',-1),('Day2',-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,'none',-1);
/*!40000 ALTER TABLE `Weathers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-12 20:48:40
