-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2021 at 09:17 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `app`
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
(16, 'hassan', '$2y$10$PEvQAVzSCmF7GtQR1xZQMOrhm8PjIVkGweBN2G7TnHw6pRx95FTr6', 'hassan@labbad.tk ', 980),
(17, '123', '$2y$10$2PeKQi2OHIY48Jh3sSqEZuXtFiWxAhM7sku4HVuiNV7rbnzofEVOe', '123', 123),
(18, '123', '$2y$10$v/HPiQYAs4HUdzY3fBO9uOszgXjB/kkzxXFJGAJxGx46RuUAWKYVG', '123', 123),
(19, 'demo22', '$2y$10$L8O2FiC5Fh9hIWl1c56ECOXqSAiYHX.UD1b0nzHPf9RhE9vlRqdUK', 'demo', 980),
(20, '123', '$2y$10$d1rC9fG6r/FX4ybQe19C2OMeYAjb.HlhoKCGK4x3QgAj448DK7b16', '123', 123);

-- --------------------------------------------------------

--
-- Table structure for table `doors`
--

CREATE TABLE `doors` (
  `ID` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Open` int(11) NOT NULL,
  `Locked` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `doors`
--

INSERT INTO `doors` (`ID`, `Open`, `Locked`) VALUES
('back door', 0, 0),
('Bed 1 Windows', 1, 1),
('Bed 2 Windows', 1, 0),
('Frount Door', 0, 1),
('Garage Windows', 0, 0),
('GarageDoor 1', 0, 0),
('GarageDoor 2', 1, 0),
('GarageDoor Door', 0, 0),
('Kitchen Windows', 0, 0),
('Livingroom Windows', 0, 0),
('Side Door', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `garage`
--

CREATE TABLE `garage` (
  `ID` int(11) NOT NULL,
  `State` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `garage`
--

INSERT INTO `garage` (`ID`, `State`) VALUES
(1, 1),
(2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hvac`
--

CREATE TABLE `hvac` (
  `ID` varchar(45) NOT NULL,
  `Mode` int(11) NOT NULL DEFAULT 0,
  `Fan` int(11) NOT NULL DEFAULT 0,
  `Temp` int(11) NOT NULL DEFAULT 0,
  `Target` int(11) NOT NULL DEFAULT 65
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hvac`
--

INSERT INTO `hvac` (`ID`, `Mode`, `Fan`, `Temp`, `Target`) VALUES
('floor 1', 1, 0, 80, 65),
('floor 2', 0, 1, 100, 65);

-- --------------------------------------------------------

--
-- Table structure for table `lights`
--

CREATE TABLE `lights` (
  `ID` text DEFAULT NULL,
  `Level` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lights`
--

INSERT INTO `lights` (`ID`, `Level`) VALUES
('Bed 1 Fan', 1),
('Bed 1 Pot', 3),
('Bed 2 Fan', 5),
('Bed 2 Pot', 1),
('Entryway', 3),
('Garage Pot', 5),
('Kitchen Pot', 5),
('Kitchen Sink', 3),
('Kitchen Table', 1),
('Livingroom Fan', 3),
('Livingroom Pot', 1);

-- --------------------------------------------------------

--
-- Table structure for table `sensors`
--

CREATE TABLE `sensors` (
  `ID` varchar(45) NOT NULL,
  `Value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sensors`
--

INSERT INTO `sensors` (`ID`, `Value`) VALUES
('Bed 1 co2', 15),
('Bed 1 Temp', 81),
('Bed 2 co2', 13),
('Bed 2 Temp', 80),
('Garadge Temp', 86),
('Kitchen co2', 10),
('Kitchen Temp', 79),
('Living Room co2', 20),
('Living Room Temp', 79),
('Motion', 1);

-- --------------------------------------------------------

--
-- Table structure for table `system`
--

CREATE TABLE `system` (
  `SysID` varchar(45) NOT NULL,
  `Armed` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `system`
--

INSERT INTO `system` (`SysID`, `Armed`) VALUES
('System', 2);

-- --------------------------------------------------------

--
-- Table structure for table `weathers`
--

CREATE TABLE `weathers` (
  `ID` text DEFAULT NULL,
  `Sunrise` int(11) DEFAULT NULL,
  `Sunset` int(11) DEFAULT NULL,
  `Temp` int(11) DEFAULT NULL,
  `FeelsLike` int(11) DEFAULT NULL,
  `Pressure` int(11) DEFAULT NULL,
  `Humidity` int(11) DEFAULT NULL,
  `UVIndex` double DEFAULT NULL,
  `CloudCover` int(11) DEFAULT NULL,
  `Visability` int(11) DEFAULT NULL,
  `WindSpeed` int(11) DEFAULT NULL,
  `WindDirection` int(11) DEFAULT NULL,
  `WeatherType` text DEFAULT NULL,
  `ChaneOfRain` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `weathers`
--

INSERT INTO `weathers` (`ID`, `Sunrise`, `Sunset`, `Temp`, `FeelsLike`, `Pressure`, `Humidity`, `UVIndex`, `CloudCover`, `Visability`, `WindSpeed`, `WindDirection`, `WeatherType`, `ChaneOfRain`) VALUES
('Day1', -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 'none', -1),
('Day2', -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 'none', -1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `details`
--
ALTER TABLE `details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `doors`
--
ALTER TABLE `doors`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `garage`
--
ALTER TABLE `garage`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `hvac`
--
ALTER TABLE `hvac`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sensors`
--
ALTER TABLE `sensors`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `system`
--
ALTER TABLE `system`
  ADD PRIMARY KEY (`SysID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `details`
--
ALTER TABLE `details`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `garage`
--
ALTER TABLE `garage`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
