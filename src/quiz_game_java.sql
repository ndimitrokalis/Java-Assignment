-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 20, 2024 at 12:13 AM
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `category`, `api_url`) VALUES
(1, 'General knowledge', '&category=9'),
(2, 'Entertainment: Books', '&category=10'),
(3, 'Entertainment: Film', '&category=11'),
(4, 'Entertainment: Music', '&category=12'),
(5, 'Entertainment: Musicals & Theatres', '&category=13'),
(6, 'Entertainment: Television', '&category=14'),
(7, 'Entertainment: Video Games', '&category=15'),
(8, 'Entertainment: Board Games', '&category=16'),
(9, 'Entertainment: Comics', '&category=29'),
(10, 'Entertainment: Japanese Anime & Manga', '&category=31'),
(11, 'Entertainment: Cartoon & Animations', '&category=32'),
(12, 'Science & Nature', '&category=17'),
(13, 'Science: Computers', '&category=18'),
(14, 'Science: Mathematics', '&category=19'),
(15, 'Science: Gadgets', '&category=30'),
(16, 'Mythology', '&category=20'),
(17, 'Sports', '&category=21'),
(18, 'Geography', '&category=22'),
(19, 'History', '&category=23'),
(20, 'Politics', '&category=24'),
(21, 'Art', '&category=25'),
(22, 'Celebrities', '&category=26'),
(23, 'Animals', '&category=27'),
(24, 'Vehicles', '&category=28');

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`game_id`, `username`, `date`, `score`, `player_id`) VALUES
(1, 'Nick', '2024-05-15 11:01:30', 3, 1),
(2, 'Anna', '2024-05-15 11:03:45', 1, 2),
(3, 'Nick', '2024-05-15 12:19:28', 3, 1),
(13, 'Nick', '2024-05-17 00:56:26', 4, 1),
(14, 'Nick', '2024-05-18 17:21:51', 0, 1),
(15, 'Test', '2024-05-18 19:48:38', 0, 3),
(16, 'Nick', '2024-05-19 19:32:08', 0, 1),
(17, 'Nick', '2024-05-19 19:33:25', 0, 1),
(18, 'Nick', '2024-05-19 22:49:31', 0, 1),
(19, 'Nick', '2024-05-19 22:50:32', 0, 1),
(20, 'Nick', '2024-05-20 01:37:45', 0, 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`id`, `username`, `password`, `score`) VALUES
(1, 'Nick', '$2a$12$di0zWz8Ynx4llClR2fXJ2OUK9sGVg48CNYpJXHS7IMC5FRfaizhGq', 10),
(2, 'Anna', '$2a$12$6ncxO33gGQi2hx3lt2E4Pu03GArdjaz5Upw4ECzPNDrVnrEiNq6K.', 1),
(3, 'Test', '$2a$12$AKCP0Qh8jf0duktlvo0zIOuSlZ0JWOTxMV8oqdhff59FnzJM8G342', 0);

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
