-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 26, 2024 at 05:31 PM
-- Server version: 8.2.0
-- PHP Version: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quiz_game_java`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `api_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `category`, `api_url`) VALUES
(1, 'All', ''),
(2, 'General knowledge', '&category=9'),
(3, 'Entertainment: Books', '&category=10'),
(4, 'Entertainment: Film', '&category=11'),
(5, 'Entertainment: Music', '&category=12'),
(6, 'Entertainment: Musicals & Theatres', '&category=13'),
(7, 'Entertainment: Television', '&category=14'),
(8, 'Entertainment: Video Games', '&category=15'),
(9, 'Entertainment: Board Games', '&category=16'),
(10, 'Entertainment: Comics', '&category=29'),
(11, 'Entertainment: Japanese Anime & Manga', '&category=31'),
(12, 'Entertainment: Cartoon & Animations', '&category=32'),
(13, 'Science & Nature', '&category=17'),
(14, 'Science: Computers', '&category=18'),
(15, 'Science: Mathematics', '&category=19'),
(16, 'Science: Gadgets', '&category=30'),
(17, 'Mythology', '&category=20'),
(18, 'Sports', '&category=21'),
(19, 'Geography', '&category=22'),
(20, 'History', '&category=23'),
(21, 'Politics', '&category=24'),
(22, 'Art', '&category=25'),
(23, 'Celebrities', '&category=26'),
(24, 'Animals', '&category=27'),
(25, 'Vehicles', '&category=28');

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
CREATE TABLE IF NOT EXISTS `history` (
  `game_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `date` datetime NOT NULL,
  `score` int NOT NULL,
  `player_id` int NOT NULL,
  PRIMARY KEY (`game_id`),
  KEY `player_id` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`game_id`, `username`, `date`, `score`, `player_id`) VALUES
(1, 'Nick', '2024-05-15 11:01:30', 3, 1),
(2, 'Anna', '2024-05-15 11:03:45', 1, 2),
(3, 'Nick', '2024-05-15 12:19:28', 3, 1),
(13, 'Nick', '2024-05-17 00:56:26', 4, 1),
(14, 'Nick', '2024-05-18 17:21:51', 0, 1),
(16, 'Nick', '2024-05-19 19:32:08', 0, 1),
(17, 'Nick', '2024-05-19 19:33:25', 0, 1),
(18, 'Nick', '2024-05-19 22:49:31', 0, 1),
(19, 'Nick', '2024-05-19 22:50:32', 0, 1),
(20, 'Nick', '2024-05-20 01:37:45', 0, 1),
(21, 'Nick', '2024-05-24 17:33:46', 0, 1),
(22, 'Nick', '2024-05-25 03:47:44', 4, 1),
(23, 'Nick', '2024-05-25 03:48:29', 5, 1),
(24, 'Nick', '2024-05-26 17:43:55', 6, 1),
(25, 'Nick', '2024-05-26 18:53:15', 1, 1),
(26, 'Nick', '2024-05-26 18:53:43', 4, 1),
(27, 'Test', '2024-05-26 20:16:02', 1, 3),
(28, 'Nick', '2024-05-26 20:19:01', 4, 1),
(29, 'Nick', '2024-05-26 20:20:34', 1, 1),
(30, 'Nick', '2024-05-26 20:20:49', 3, 1),
(31, 'Nick', '2024-05-26 20:21:07', 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
CREATE TABLE IF NOT EXISTS `players` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(200) NOT NULL,
  `score` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`id`, `username`, `password`, `score`) VALUES
(1, 'Nick', '$2a$12$di0zWz8Ynx4llClR2fXJ2OUK9sGVg48CNYpJXHS7IMC5FRfaizhGq', 42),
(2, 'Anna', '$2a$12$6ncxO33gGQi2hx3lt2E4Pu03GArdjaz5Upw4ECzPNDrVnrEiNq6K.', 1),
(3, 'Test', '$2a$12$mU1diD3D8/8ePBHrEm9ZSeS3MlrNpOPXjjIrOzY.tkKdB1y/1hnNu', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `history_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `players` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
