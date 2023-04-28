# PATIENTS MANAGEMENT SYSTEM
///////////////////////////////// How to install and launch the “Patients System Management” back-end ////////////////////////

What you need to have: 
- IntelliJ IDEA (Community or Ultimate).
- MySQL Workbench. 
- Ensure your computer has Java (17 or higher).
- Install Java JDK version 17 or higher. 

Installment: 
- Go to this link to install the website’s back-end: https://github.com/kayenx00/Thesis
- Please follow the guidelines of the following link to install and launch MySQL on your local machine: 
https://www.simplilearn.com/tutorials/mysql-tutorial/mysql-workbench-installation (please remember your root password when installing).

![image](https://user-images.githubusercontent.com/53591019/234654664-f1f52e2e-04ac-409f-9bdc-93b940097fe2.png)

Launching: 
- First, open MySQL Workbench and choose the root.

![image](https://user-images.githubusercontent.com/53591019/234654706-26995eba-fe56-4442-86d4-88122e6cdbdf.png) 

- Run the following script to create the database (note that most password for user is "654321", if it is not correct, try "123456":
```
CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `test`;
-- MySQL dump 10.13  Distrib 8.0.24, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `is_confirmed` bit(1) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `doctor_id` bigint DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoeb98n82eph1dx43v3y2bcmsl` (`doctor_id`),
  KEY `FK4apif2ewfyf14077ichee8g06` (`patient_id`),
  CONSTRAINT `FK4apif2ewfyf14077ichee8g06` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FKoeb98n82eph1dx43v3y2bcmsl` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (45,'2023-03-21 00:00:00',10,_binary '\0','7:30 AM','Request',1,NULL),(46,'2023-03-22 00:00:00',10,_binary '\0','7:50 PM','Request',1,NULL),(47,'2023-03-26 00:00:00',10,_binary '\0','7:00 AM','Request',1,NULL),(48,'2023-03-26 00:00:00',10,_binary '\0','9:00 AM','Request',1,NULL),(49,'2023-03-31 00:00:00',10,_binary '\0','6:30 AM','Request',1,NULL),(50,'2023-03-28 00:00:00',10,_binary '\0','8:30 AM','Request',1,NULL),(51,'2023-04-01 00:00:00',10,_binary '\0','7:45 AM','Initiate',1,NULL),(52,'2023-03-20 00:00:00',10,_binary '\0','5:30 PM','Initiate',1,NULL),(53,'2023-03-21 00:00:00',10,_binary '\0','8:45 AM','Initiate',1,NULL),(54,'2023-03-26 00:00:00',10,_binary '\0','8:30 AM','Initiate',1,NULL),(56,'2023-03-31 00:00:00',20,_binary '\0','8:00 AM','Request',1,NULL),(57,'2023-03-20 00:00:00',10,_binary '\0','8:30 AM','Request',1,NULL),(58,'2023-04-09 00:00:00',15,_binary '\0','9:30 AM','Request',1,NULL),(59,'2023-04-18 00:00:00',20,_binary '\0','9:24 AM','Request',1,NULL),(60,'2023-07-08 00:00:00',20,_binary '\0','7:30 AM','Request',1,NULL),(61,'2023-03-26 00:00:00',10,_binary '\0','10:30 AM','Request',1,NULL),(62,'2023-03-28 00:00:00',10,_binary '\0','9:25 AM','Request',1,NULL),(63,'2023-03-31 00:00:00',10,_binary '\0','8:25 AM','Request',1,NULL),(64,'2023-04-29 00:00:00',20,_binary '\0','8:25 AM','Request',1,NULL),(65,'2023-03-25 00:00:00',10,_binary '\0','8:56 AM','Request',1,NULL),(66,'2023-03-21 00:00:00',10,_binary '\0','5:30 PM','Request',1,NULL),(67,'2023-03-25 00:00:00',10,_binary '\0','1:32 PM','Initiate',1,NULL),(68,'2023-03-29 00:00:00',10,_binary '\0','6:44 AM','Request',1,NULL),(69,'2023-03-30 00:00:00',10,_binary '\0','6:50 AM','Initiate',1,NULL),(72,'2023-11-20 00:00:00',10,_binary '\0','7:33 AM','Request',1,NULL),(79,'2023-02-12 00:00:00',10,_binary '\0','6:34 AM','Request',1,1),(81,'2023-12-12 00:00:00',10,_binary '\0','6:43 AM','Request',1,NULL),(82,'2023-10-03 00:00:00',10,_binary '\0','6:53 AM','Request',1,NULL),(84,'2023-03-26 00:00:00',10,_binary '\0','9:00 AM','Request',1,NULL),(85,'2023-03-26 00:00:00',10,_binary '\0','8:00 AM','Request',1,NULL),(86,'2023-03-30 00:00:00',10,_binary '\0','8:00 AM','Request',1,NULL),(87,'2023-03-24 00:00:00',10,_binary '\0','8:00 AM','Request',1,NULL),(88,'2023-04-01 00:00:00',20,_binary '\0','11:00 AM','Request',1,NULL),(89,'2023-04-09 00:00:00',10,_binary '\0','9:00 AM','Request',1,NULL),(90,'2023-03-29 00:00:00',10,_binary '\0','8:40 AM','Request',1,NULL),(91,'2023-04-30 00:00:00',10,_binary '','7:42 AM','Initiate',1,15),(92,'2023-03-30 00:00:00',20,_binary '\0','9:08 AM','Request',1,NULL),(93,'2023-04-28 00:00:00',10,_binary '','8:36 AM','Initiate',1,15),(94,'2023-03-20 00:00:00',10,_binary '\0','4:30 AM','Request',1,1),(95,'2023-04-30 00:00:00',10,_binary '\0','6:56 AM','Request',1,NULL),(96,'2023-03-31 00:00:00',10,_binary '\0','7:15 AM','Request',1,NULL),(97,'2023-04-01 00:00:00',10,_binary '\0','7:16 AM','Initiate',1,NULL),(98,'2023-03-31 00:00:00',10,_binary '\0','8:00 AM','Request',1,NULL),(99,'2023-05-30 00:00:00',10,_binary '\0','7:59 AM','Initiate',1,NULL),(100,'2023-12-12 00:00:00',10,_binary '\0','8:00 AM','Request',1,NULL),(101,'2024-02-01 00:00:00',10,_binary '\0','8:05 AM','Request',1,NULL),(102,'2023-10-09 00:00:00',10,_binary '\0','8:05 AM','Request',1,NULL),(107,'2023-12-15 00:00:00',10,_binary '\0','7:01 AM','Initiate',1,NULL),(110,'2023-10-09 00:00:00',10,_binary '\0','8:07 AM','Request',1,NULL),(111,'2023-10-10 00:00:00',10,_binary '\0','7:08 AM','Request',1,NULL),(112,'2023-12-12 00:00:00',10,_binary '\0','8:08 AM','Request',1,NULL),(113,'2023-11-11 00:00:00',10,_binary '\0','7:10 AM','Request',1,NULL);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `work_place` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK3q0j5r6i4e9k3afhypo6uljph` (`user_id`),
  CONSTRAINT `FK11wrxiolc8qa2e64s32xc2yy4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (1,'Nguyen Ngoc Vi','0908782635',2,'Can Tho'),(2,'Le Thi Tram','0908374716',4,'15/8 Pham Ngu Lao, Tp.HCM'),(3,'William A. Abdu','09087654231',5,'10, CMT8, Tp.HCM'),(4,'Smith Regal','09087654231',6,'15/83,Vo Duy Ninh, Tp.HCM'),(5,'Nguyen Ngoc Ha Vi','0987654321',18,'15/83,Vo Duy Ninh, Tp.HCM'),(6,'William Regal','0908374716',22,'15/83,Vo Duy Ninh, Tp.HCM'),(7,'Jacob William','0908900800',29,'15/83,Vo Duy Ninh, Tp.HCM'),(8,'Nguyen Hong Phat','0906807226',3,'15/83,Vo Duy Ninh, Tp.HCM'),(9,'Le Thi Tuyet Van','0906806419',33,'169/145 Ngo Tat To, Tp.HCM'),(11,'Nguyen Ngoc Phuong','0906806419',35,'169/145 Ngo Tat To, Tp.HCM'),(12,'Le Thuy tram','0906807216',46,'Soc Trang');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `healthinformation`
--

DROP TABLE IF EXISTS `healthinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `healthinformation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `advice` varchar(255) DEFAULT NULL,
  `blood_pressure` int DEFAULT NULL,
  `comment_from_nurse` varchar(255) DEFAULT NULL,
  `fever` varchar(255) DEFAULT NULL,
  `headache` varchar(255) DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `measured_by` varchar(255) DEFAULT NULL,
  `muscleache` varchar(255) DEFAULT NULL,
  `other_diagnose` varchar(255) DEFAULT NULL,
  `oxygen_level` int DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKohisfe4sqifmytc4p4kkliskc` (`patient_id`),
  CONSTRAINT `FKohisfe4sqifmytc4p4kkliskc` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `healthinformation`
--

LOCK TABLES `healthinformation` WRITE;
/*!40000 ALTER TABLE `healthinformation` DISABLE KEYS */;
INSERT INTO `healthinformation` VALUES (3,'Do no go out',90,'Seem good','none','none','2022-11-02 15:50:56','Nurse:Le Nha Truong','none','none',88,1),(4,NULL,90,'Seem good\r\n','none','none','2022-11-02 15:53:24','Nurse:Le Nha Truong','none','none',99,2),(5,NULL,88,'Seem good','none','none','2022-11-05 08:30:08','Nurse:Le Nha Truong','none','None',90,10),(6,NULL,88,NULL,'none','none','2022-11-05 08:33:06','Patient','none','None',90,10),(7,'No Out side',90,NULL,'none','none','2022-11-08 12:32:45','Patient','none','None',90,10),(8,'Uống thuốc đầy đủ, đo nhiệt độ và ăn uống điều độ. Cần chú ý thêm',90,'Seem good','medium','none','2023-01-09 18:50:30','Nurse: Le Nha Truong','none','None',90,10),(9,NULL,90,'Seem good','medium','none','2023-01-09 18:50:30','Nurse: Le Nha Truong','none','None',90,10),(10,NULL,90,NULL,'none','none','2023-01-12 08:35:52','Patient','none','None',90,3),(11,'Seem normal',90,NULL,'none','none','2023-01-12 08:36:18','Patient','none','None',90,3),(12,'Nothing particular',90,NULL,'serve','mild','2023-01-12 08:36:52','Patient','none','High Fever, Vomit',89,3),(13,'Seem normal, Symptom has been better',90,'Nothing abnormal','none','none','2023-01-12 08:47:38','Nurse: Nguyen Nguyen Hung','none','None',90,3),(15,NULL,90,NULL,'none','none','2023-01-12 11:07:43','Patient','none','There are no other Diganose',90,10),(16,NULL,90,NULL,'none','none','2023-01-12 11:07:45','Patient','none','There are no other Diganose',90,10),(17,NULL,90,NULL,'serve','serve','2023-02-09 14:21:10','Patient','none','None',90,1),(18,NULL,90,NULL,'serve','serve','2023-02-09 14:21:14','Patient','none','None',90,1),(19,NULL,90,NULL,'serve','none','2023-02-13 20:31:59','Patient','none','None',90,10),(20,NULL,90,NULL,'none','none','2023-03-17 17:49:28','Patient','none','Feel hurt in throat',80,1),(21,'Need Penicillin',90,'the throat is dry','none','none','2023-03-17 17:57:22','Nurse: Le Nha Truong','none','Do not tatse very well',80,1),(35,NULL,90,'Feel better than the last visit','none','none','2023-03-20 04:19:14','Nurse: Le Nha Truong','none','None',80,10),(38,'Need more observation',90,NULL,'none','none','2023-03-20 08:38:10','Patient','none','Feel hurt in throat',80,15),(39,NULL,90,'Feel better than the last visit\r\n','none','none','2023-03-20 08:43:40','Nurse: Le Nha Truong','none','None',80,15),(40,NULL,90,NULL,'none','none','2023-04-27 19:08:41','Patient','none','Feel hurt in throat',80,10),(41,NULL,90,NULL,'none','none','2023-04-27 20:00:07','Patient','none','None',80,2);
/*!40000 ALTER TABLE `healthinformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicalstaff`
--

DROP TABLE IF EXISTS `medicalstaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicalstaff` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKq46f91ysipnj9ae5nl919oa9p` (`user_id`),
  CONSTRAINT `FKr8fyeq0qlg8j4nyaut13ifxqj` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicalstaff`
--

LOCK TABLES `medicalstaff` WRITE;
/*!40000 ALTER TABLE `medicalstaff` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicalstaff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nurse`
--

DROP TABLE IF EXISTS `nurse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurse` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `doctor` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `work_place` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKbl2q6g60tor4whxe60hlrbdx8` (`user_id`),
  KEY `FKec6tne09ijkxbdi9yje06fdfd` (`doctor`),
  CONSTRAINT `FKec6tne09ijkxbdi9yje06fdfd` FOREIGN KEY (`doctor`) REFERENCES `doctor` (`id`),
  CONSTRAINT `FKtip0bset456yx2hq3crkutmso` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurse`
--

LOCK TABLES `nurse` WRITE;
/*!40000 ALTER TABLE `nurse` DISABLE KEYS */;
INSERT INTO `nurse` VALUES (1,'Nguyen Nguyen Hung','09087615423',1,27,'15/8 Pham Ngu Lao, TP.HCM'),(2,'Le Nha Truong','09083837291',1,28,'15/8 Pham Ngu Lao, Tp.HCM'),(3,'nguyen hoang long','0908374716',2,30,'15/8 Pham Ngu Lao, Tp.HCM'),(4,'Le Ba Dung','09087615423',3,32,'15/8 Pham Ngu Lao, Tp.HCM'),(5,'Pham van hieu','0906806419',1,36,'15/8 Pham Ngu Lao, TP.HCM');
/*!40000 ALTER TABLE `nurse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `id_num` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `chosen_doctor` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6i3fp8wcdxk473941mbcvdao4` (`user_id`),
  KEY `FKobholp9j64wa87dy035vn7tbn` (`chosen_doctor`),
  CONSTRAINT `FKie6vajiyur53rjcl5nc2pe83t` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKobholp9j64wa87dy035vn7tbn` FOREIGN KEY (`chosen_doctor`) REFERENCES `doctor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'Ho Chi Minh','Binh Thanh','7200984682619','Nguyen Hoang Phat','0908374716',NULL,3),(2,'Ho Chi Minh','Binh Thanh','73847291793','Nguyen Hong Phuc','09812653410',1,7),(3,'Soc Trang','Ly thanh tong','097829899992','Nguyen Hong Quyen','0906807216',1,8),(4,NULL,NULL,NULL,NULL,NULL,NULL,9),(10,'Ho Chi Minh','Binh Thanh','7200984682619','Nguyen Hoang Phi Long','0908374716',NULL,26),(11,NULL,NULL,NULL,NULL,NULL,4,31),(12,NULL,NULL,NULL,NULL,NULL,NULL,42),(13,'TP.HCM','Ly thanh tong','783648920212','Dang Hoang Long','0906807226',1,43),(15,'Ho Chi Minh','Binh Thanh','0720010192287','Nguyen Hoang Long','0906807226',1,45);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_PATIENT'),(3,'ROLE_DOCTOR'),(4,'ROLE_NURSE');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatmentduration`
--

DROP TABLE IF EXISTS `treatmentduration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatmentduration` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `doctor_id` bigint DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7f8jjlrg3gp6y0tj4owoucsv4` (`doctor_id`),
  KEY `FKidqlhq5f1vwfrg8s00adede9k` (`patient_id`),
  CONSTRAINT `FK7f8jjlrg3gp6y0tj4owoucsv4` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`),
  CONSTRAINT `FKidqlhq5f1vwfrg8s00adede9k` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatmentduration`
--

LOCK TABLES `treatmentduration` WRITE;
/*!40000 ALTER TABLE `treatmentduration` DISABLE KEYS */;
INSERT INTO `treatmentduration` VALUES (1,'2022-11-07 15:13:37','2022-11-07 15:11:51',1,10),(2,'2022-12-03 08:22:59','2022-11-08 12:32:26',1,10),(3,NULL,'2022-11-12 13:32:09',4,11),(4,'2023-04-27 20:13:59','2022-12-03 08:23:43',1,10),(5,'2022-12-20 21:54:59','2022-12-20 21:54:06',8,1),(6,'2023-02-09 15:30:45','2022-12-20 21:55:06',1,1),(7,NULL,'2023-01-12 08:34:44',1,3),(8,NULL,'2023-02-09 14:46:40',1,13),(9,'2023-03-14 17:53:45','2023-02-13 20:15:34',1,1),(11,'2023-03-18 19:47:46','2023-03-17 15:56:27',1,1),(13,'2023-03-18 19:54:36','2023-03-18 19:50:53',1,1),(14,'2023-03-19 13:48:08','2023-03-18 20:20:35',1,1),(18,'2023-03-19 21:36:02','2023-03-19 18:32:14',1,1),(21,'2023-03-20 04:54:25','2023-03-19 21:43:00',1,1),(24,'2023-03-20 08:41:13','2023-03-20 05:02:10',1,1),(25,NULL,'2023-03-20 08:37:59',1,15);
/*!40000 ALTER TABLE `treatmentduration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(3,2),(7,2),(8,2),(9,2),(26,2),(31,2),(41,2),(42,2),(43,2),(45,2),(2,3),(3,3),(4,3),(5,3),(6,3),(18,3),(22,3),(29,3),(33,3),(35,3),(46,3),(27,4),(28,4),(30,4),(32,4),(36,4);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `verification_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'nguyenhloan09@gmail.com','$2a$10$jM9VC9O8yzqRghpN35Qct.R040oFNoWFaNqe04aT7FhVbdirxB.XO','Kayen',_binary '',NULL),(2,'havi@gmail.com','$2a$10$QRL2VlrTTQr1bpNTufg99O89bjt5lSqrV4WgU7omvS85BZ4RkN4eG','nahvi',_binary '',NULL),(3,'hongphat@gmail.com','$2a$10$w2HBcHkXpyjkgmOyAvvBZ.Jf1.PwA9r52dcgA5c064aUEsDCiqb5O','HongPhat',_binary '',NULL),(4,'tram@gmail.com','$2a$10$PtjadlWc03um3z0RX9lH/ustAAUK0xZLjgKtxwd0ds8EUe0/NqnBS','TramB',_binary '',NULL),(5,'will@gmail.com','$2a$10$XQKXx0wgJ25AjbD5XMMm/O1ZKYObcgZ8DalCil4GPHjR3qbpR9hSG','will',_binary '',NULL),(6,'smith@gmail.com','$2a$10$H3k3UG9JiG3x62hcLplPt..Pf.bGDf2CgtSXIPCNR3RBV2tuV8SgG','Smith',_binary '',NULL),(7,'hongphuc@gmail.com','$2a$10$BiP1lE23VB6UoWPB7olRVus3DHaMwS7DD.OUtYNJ6ySHd3d0zIcQ.','HongPhuc',_binary '',NULL),(8,'hongquyen@gmail.com','$2a$10$xNirTktLxOKSytmNEhEsa.QkezFuOpEApsQ756xIydohzjwmUqKca','HongQuyen',_binary '',NULL),(9,'lequyen@gmail.com','$2a$10$dSkc3Ti8FMkrcgG0CJvLEe.hipfcgiKV2WJcDzBQUKZTpOfjbFMA2','LeQuyen',_binary '',NULL),(18,'nhavi@gmail.com','$2a$10$97nmBMkjZfyHKNJB2DBpmOZJSspOnKhwovA7GuDtWszCTgAVHSKz.','nvi',_binary '',NULL),(22,'wregal@gmail.com','$2a$10$FJJqndnoi3J5zPXYH/mz5.u3T9NXB9Og2cvG5a6P693AczL.SzPXu','Regal',_binary '',NULL),(26,'nguyenhlong0910@gmail.com','$2a$10$OgNw0sC7oFDgJQGF2hTTheLf.2Kat46b/hc.CXUwpWYP.70d4mjyW','Long2',_binary '',NULL),(27,'nnhung@gmail.com','$2a$10$/mkAzqoLGUh9YmCfzUv0OObxPydsU6HPighXRFm6Zip9vtTjSuHQG','nnh',_binary '',NULL),(28,'lntruong@gmail.com','$2a$10$yR6wckAh8K8OoAG2HkhdaOAYk56DDtKstTRTjPfG09ieg5PvTRd0m','lnt',_binary '',NULL),(29,'jacob2@gmail.com','$2a$10$idHgjpmsY1P1TdFQP3BYpeO7vyBNA57vjEW2y3ZbMIgwLxvb5yPIu','Jacab5',_binary '',NULL),(30,'nguyenloan@gmail.com','$2a$10$grfEKRWlBaz4ZHe0FizXF.1VxS.Kth0HxP71n4Ny5zaYNXhHwVUui','nguyenhlong',_binary '',NULL),(31,'khuukhonlam@gmail.com','$2a$10$JbkaHb6441ti4mWOab4DO.iVsu4pZQfUR8BO3hRY.fPnY14.AvGMi','KhonLam',_binary '',NULL),(32,'dung@gmail.com','$2a$10$OhUZzCsGxDxtHkC9CtXj8.Wunr7i76VY/W/kHjRbSyuCKr/uYj2xO','Dung',_binary '',NULL),(33,'tuyetvan09@gmail.com','$2a$10$obkeI.UGh7JGmRkyhwQEn.nM8mM8zWPSmHqUh7UP9VUj1K67fMhoq','lttv',_binary '',NULL),(35,'NguyenNPhuong@Gmail.Com','$2a$10$vVeVHmVc9hcRCnydxMSKIO.BJjTIc1F7gfcyn77jThSyaiaNqnX0a','nnp',_binary '',NULL),(36,'phamHieu@gmail.com','$2a$10$oZsJ.218W1An7n6eY6PjMe.oVMltPLI0ZzAd3qvXvblOkqKkjFEni','PVH',_binary '',NULL),(41,'phamHieu2@gmail.com','$2a$10$zycFhSLnXAd4Nr4Xv7.BTuDguh3hNI5E6LmRpJcWZ.vD9eHuNnM6q','Longg',_binary '\0','WfZbOEA8g66LGqaksZySSQVxxdMsG2pj54NES3yaR5VP6tXpLwiFaYZcsTUroK09'),(42,'nvsinh@gmail.com','$2a$10$7MYaFvA9FHpvFZy5XmUOYuuxnfaHwWDBEqY03HrYBW5exaJb0WCsC','nvs',_binary '',NULL),(43,'Long23@gmail.com','$2a$10$4sGrJHX8phMdtFxGz.NJ3ug80QRHaZYFZVIzXDQaXloI8R5KYUNqG','Long23',_binary '\0','8nUDjReqpJyDUnilIc18ZfhGqKAp3p7sEEYMcUqFW1jwbDXNj2bVP7hvaCCEdUVL'),(45,'Longnhfx21613@Funix.Edu.Vn','$2a$10$2FuRuxFEJsOR/baAuxYFRuTEjBiF6sNIcF.4BsCjv9eYyhkaNuOL6','LongFX',_binary '',NULL),(46,'Bobap@gmail.com','$2a$10$cdkHBZUtiRW0GW2f19m.9.fJrVGB6RwJoHoTObHtOAU3Ley2PONvW','Bobap',_binary '',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-28  8:39:58

```
- Now open your back-end folder with IntelliJ IDEA. (open IntelliJ IDEA -> File -> Open). 
- Open Project (File -> Project Structure -> Project Settings -> Project).

![image](https://user-images.githubusercontent.com/53591019/234654741-3704e0bf-f72f-4352-8ddc-6025915adc5a.png)

- In your Project window, select SDK, then choose the Java JDK version you installed. 
- Close the Project window.
- After adding JDK to the project, you need to Build the project (Build -> Build Project).
- Then go to the “resources” folder, and access the application.properties file.  

![image](https://user-images.githubusercontent.com/53591019/234654789-866ea932-bc74-4900-a3a0-9c679ddeadfc.png)

- In the file, you must change line 4 to your root password(the password you set when installing the MySQL Workbench).

![image](https://user-images.githubusercontent.com/53591019/234654841-15670e9d-e45b-4b08-9b09-86379a90dbd7.png)

(Replace ‚”Nguyenlong09102014” with your password). 
- Now click run to launch the back-end. (Run -> Run ‘ThesisApplication’)
- Now you successfully launch the websites's back-end. 

