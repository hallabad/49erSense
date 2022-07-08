-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 06, 2021 at 09:40 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `iotsec`
--

-- --------------------------------------------------------

--
-- Table structure for table `details`
--

CREATE TABLE `details` (
  `id` int(255) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` mediumtext NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `details`
--

INSERT INTO `details` (`id`, `username`, `password`, `email`, `phone`) VALUES
(8, 'demo', '$2y$10$E7aZ80bqVwwdApe1wjs/qultOr9kJD07KGg6FeI1NwZOx.MCeg73C', 'a@a.com', 123456),
(11, 'demo3', '$2y$10$KaKlV/pW8zZPZboKI9x4RexDvG5nNCm98pv0TBWZ4Eg/vEYZ6vMhK', 'a@a.com', 123456789),
(15, 'haha', '$2y$10$YienVo9UTiM2PiUBySsMTO2mytCknmHcdMLKcKZF7p/JFfyrym66a', 'hhh', 44),
(16, '123', '$2y$10$4M8o1c.1E4XKzWgxh/ae3.u2sJi7mdCRP0qafuJaxmV1xJFguNtIO', '123', 123);

-- --------------------------------------------------------

--
-- Table structure for table `Doors`
--

CREATE TABLE `Doors` (
  `Id` varchar(45) NOT NULL,
  `Open` int(11) NOT NULL,
  `Locked` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Doors`
--

INSERT INTO `Doors` (`Id`, `Open`, `Locked`) VALUES
('floor1-door-back door', 0, 0),
('floor1-door-frount door', 0, 0),
('floor1-door-garagedoor 2', 0, 0),
('floor1-door-side door', 0, 0),
('floor1-window-garage windows', 0, 0),
('floor1-window-kitchen windows', 1, 0),
('floor1-window-livingroom windows', 1, 0),
('floor2-window-bed 1 windows', 0, 0),
('floor2-window-bed 2 windows', 0, 0),
('GarageDoor 2', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `HVAC`
--

CREATE TABLE `HVAC` (
  `Id` varchar(45) NOT NULL,
  `Mode` int(11) NOT NULL,
  `Fan` int(11) NOT NULL,
  `Temp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `HVAC`
--

INSERT INTO `HVAC` (`Id`, `Mode`, `Fan`, `Temp`) VALUES
('floor1-hvac-floor 1', 0, 1, 0),
('floor2-hvac-floor 2', 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `Lights`
--

CREATE TABLE `Lights` (
  `Id` varchar(45) NOT NULL,
  `Level` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Lights`
--

INSERT INTO `Lights` (`Id`, `Level`) VALUES
('floor1-light-entryway', 6),
('floor1-light-garage pot', 7),
('floor1-light-kitchen pot', 8),
('floor1-light-kitchen sink', 3),
('floor1-light-kitchen table', 3),
('floor1-light-livingroom fan', 4),
('floor1-light-livingroom pot', 1),
('floor2-light-bed 1 fan', 0),
('floor2-light-bed 1 pot', 0),
('floor2-light-bed 2 fan', 0),
('floor2-light-bed 2 pot', 0);

-- --------------------------------------------------------

--
-- Table structure for table `Sensors`
--

CREATE TABLE `Sensors` (
  `Id` varchar(45) NOT NULL,
  `Value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Sensors`
--

INSERT INTO `Sensors` (`Id`, `Value`) VALUES
('floor1-Light-Sensor-Entryway', 6),
('floor1-Light-Sensor-Garage', 3),
('floor1-Light-Sensor-Kitchen', 1),
('floor1-Light-Sensor-Livingroom', 8),
('floor1-Motion-Sensor-Entryway', 9),
('floor1-Motion-Sensor-Garage', 9),
('floor1-Motion-Sensor-Kitchen', 8),
('floor1-Motion-Sensor-Livingroom', 1),
('floor1-Temp-Sensor-Entryway', 9),
('floor1-Temp-Sensor-Garage', 6),
('floor1-Temp-Sensor-Kitchen', 8),
('floor1-Temp-Sensor-Livingroom', 5),
('floor2-Light-Sensor-Bed 1', 0),
('floor2-Light-Sensor-Bed 2', 0),
('floor2-Motion-Sensor-Hallway', 0),
('floor2-Temp-Sensor-Bed 1', 0),
('floor2-Temp-Sensor-Bed 2', 0);

-- --------------------------------------------------------

--
-- Table structure for table `System`
--

CREATE TABLE `System` (
  `SysID` varchar(45) NOT NULL,
  `Armed` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `System`
--

INSERT INTO `System` (`SysID`, `Armed`) VALUES
('System', 1);

-- --------------------------------------------------------

--
-- Table structure for table `Weather`
--

CREATE TABLE `Weather` (
  `ID` varchar(45) NOT NULL DEFAULT 'def',
  `Sunrise` int(11) NOT NULL DEFAULT -1,
  `Sunset` int(11) NOT NULL DEFAULT -1,
  `TempMax` int(11) NOT NULL DEFAULT -1,
  `TempMin` int(11) NOT NULL DEFAULT -1,
  `FeelsLikeMax` int(11) NOT NULL DEFAULT -1,
  `FeelsLikeMin` int(11) NOT NULL DEFAULT -1,
  `Pressure` int(11) NOT NULL DEFAULT -1,
  `Humidity` int(11) NOT NULL DEFAULT -1,
  `UVIndex` int(11) NOT NULL DEFAULT -1,
  `CloudCover` int(11) NOT NULL DEFAULT -1,
  `WindSpeed` int(11) NOT NULL DEFAULT -1,
  `WindDirection` int(11) NOT NULL DEFAULT -1,
  `WeatherType` varchar(45) NOT NULL DEFAULT ' ',
  `ChanceOfRain` int(11) NOT NULL DEFAULT -1,
  `Visability` int(11) NOT NULL DEFAULT -1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Weather`
--

INSERT INTO `Weather` (`ID`, `Sunrise`, `Sunset`, `TempMax`, `TempMin`, `FeelsLikeMax`, `FeelsLikeMin`, `Pressure`, `Humidity`, `UVIndex`, `CloudCover`, `WindSpeed`, `WindDirection`, `WeatherType`, `ChanceOfRain`, `Visability`) VALUES
('Day0', -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, ' ', -1, -1),
('Day1', -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, ' ', -1, -1),
('Day2', -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, ' ', -1, -1),
('Day3', -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, ' ', -1, -1),
('Day4', -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, ' ', -1, -1),
('Day5', -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, ' ', -1, -1),
('Day6', -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, ' ', -1, -1),
('Day7', -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, ' ', -1, -1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `details`
--
ALTER TABLE `details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Doors`
--
ALTER TABLE `Doors`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `HVAC`
--
ALTER TABLE `HVAC`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `Lights`
--
ALTER TABLE `Lights`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `Sensors`
--
ALTER TABLE `Sensors`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `System`
--
ALTER TABLE `System`
  ADD PRIMARY KEY (`SysID`);

--
-- Indexes for table `Weather`
--
ALTER TABLE `Weather`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `details`
--
ALTER TABLE `details`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
