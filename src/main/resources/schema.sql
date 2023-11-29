CREATE DATABASE  IF NOT EXISTS `ap-news`
USE `ap-news`;


--
-- Table structure for table `player`
--

CREATE TABLE `player` (
  `age` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `level` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `player_sport`
--
CREATE TABLE `player_sport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `player_id` int NOT NULL,
  `sport_id` int NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_player_key` (`player_id`),
  KEY `FK_sport_key` (`sport_id`),
  CONSTRAINT `FK_player_key` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`),
  CONSTRAINT `FK_sport_key` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `sport`
--

CREATE TABLE `sport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


