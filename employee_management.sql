-- MySQL dump 10.13  Distrib 26.7.0, for macos26.6 (arm64)
--
-- Host: localhost    Database: employee_management
-- ------------------------------------------------------
-- Server version	26.7.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'bff94578-9f6e-11f1-b870-57eb952a11d7:1-42';

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `status` enum('ABSENT','LEAVE','PRESENT') NOT NULL,
  `employee_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr7q0h8jfngkyybll6o9r3h9ua` (`employee_id`),
  CONSTRAINT `FKr7q0h8jfngkyybll6o9r3h9ua` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (1,'2026-09-15','PRESENT',101),(2,'2026-09-16','PRESENT',102),(3,'2026-09-17','ABSENT',103),(4,'2026-09-18','LEAVE',104);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deduction`
--

DROP TABLE IF EXISTS `deduction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deduction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `date` date NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `employee_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfn2x587u93rxm08nwfiy8fhq8` (`employee_id`),
  CONSTRAINT `FKfn2x587u93rxm08nwfiy8fhq8` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deduction`
--

LOCK TABLES `deduction` WRITE;
/*!40000 ALTER TABLE `deduction` DISABLE KEYS */;
INSERT INTO `deduction` VALUES (1,500,'2026-09-15','Late arrival','Late Attendance',101),(2,2000,'2026-09-15','Monthly loan deduction','Loan',102),(3,1500,'2026-09-15','Additional deduction','Tax Adjustment',103),(4,300,'2026-09-15','Late arrival','Late Attendance',104);
/*!40000 ALTER TABLE `deduction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `department` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (101,'IT','Backend Developer','ayush101@gmail.com','Ayush','Khatri','9811111111',60000,NULL),(102,'HR','HR Officer','ram102@gmail.com','Ram','Sharma','9822222222',50000,NULL),(103,'Finance','Accountant','sita103@gmail.com','Sita','Thapa','9833333333',45000,NULL),(104,'IT','Senior Developer','hari104@gmail.com','Hari','Gurung','9844444444',70000,NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_leave`
--

DROP TABLE IF EXISTS `employee_leave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_leave` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` date DEFAULT NULL,
  `leave_type` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqat82qeb4igyj2vrh07tif37w` (`employee_id`),
  CONSTRAINT `FKqat82qeb4igyj2vrh07tif37w` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_leave`
--

LOCK TABLES `employee_leave` WRITE;
/*!40000 ALTER TABLE `employee_leave` DISABLE KEYS */;
INSERT INTO `employee_leave` VALUES (1,'2026-09-07','PAID','Family function','2026-09-05','APPROVED',101),(2,'2026-09-11','PAID','Personal work','2026-09-10','APPROVED',102),(3,'2026-09-19','UNPAID','Medical leave','2026-09-18','APPROVED',103),(4,'2026-09-27','UNPAID','Travel','2026-09-25','PENDING',104);
/*!40000 ALTER TABLE `employee_leave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `overtime`
--

DROP TABLE IF EXISTS `overtime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `overtime` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `date` date NOT NULL,
  `hours` double NOT NULL,
  `rate` double NOT NULL,
  `status` enum('APPROVED','PENDING','REJECTED') NOT NULL,
  `employee_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsyvwqny32x0g8a7fto1ppycdi` (`employee_id`),
  CONSTRAINT `FKsyvwqny32x0g8a7fto1ppycdi` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `overtime`
--

LOCK TABLES `overtime` WRITE;
/*!40000 ALTER TABLE `overtime` DISABLE KEYS */;
INSERT INTO `overtime` VALUES (1,1500,'2026-09-12',3,500,'APPROVED',101),(2,800,'2026-09-13',2,400,'PENDING',102),(3,1400,'2026-09-14',4,350,'REJECTED',103),(4,3000,'2026-09-15',5,600,'APPROVED',104);
/*!40000 ALTER TABLE `overtime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payroll`
--

DROP TABLE IF EXISTS `payroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payroll` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `base_salary` double NOT NULL,
  `deductions` double DEFAULT NULL,
  `month` int NOT NULL,
  `net_salary` double NOT NULL,
  `overtime_pay` double DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `performance_bonus` double DEFAULT NULL,
  `year` int NOT NULL,
  `employee_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5o7fr6cbvrkgud2unv0p5rqlm` (`employee_id`),
  CONSTRAINT `FK5o7fr6cbvrkgud2unv0p5rqlm` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll`
--

LOCK TABLES `payroll` WRITE;
/*!40000 ALTER TABLE `payroll` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performance`
--

DROP TABLE IF EXISTS `performance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `performance` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `attendance` double DEFAULT NULL,
  `final_rating` double DEFAULT NULL,
  `punctuality` double NOT NULL,
  `review_date` date NOT NULL,
  `task_completion` double NOT NULL,
  `teamwork` double NOT NULL,
  `work_quality` double NOT NULL,
  `employee_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2e06qs6sbg17u12lqpf9o5ldv` (`employee_id`),
  CONSTRAINT `FK2e06qs6sbg17u12lqpf9o5ldv` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `performance_chk_1` CHECK (((`punctuality` <= 5) and (`punctuality` >= 1))),
  CONSTRAINT `performance_chk_2` CHECK (((`task_completion` <= 5) and (`task_completion` >= 1))),
  CONSTRAINT `performance_chk_3` CHECK (((`teamwork` <= 5) and (`teamwork` >= 1))),
  CONSTRAINT `performance_chk_4` CHECK (((`work_quality` <= 5) and (`work_quality` >= 1)))
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance`
--

LOCK TABLES `performance` WRITE;
/*!40000 ALTER TABLE `performance` DISABLE KEYS */;
INSERT INTO `performance` VALUES (1,5,4.6,4,'2026-09-20',5,5,4,101),(2,4,3.85,3,'2026-09-20',4,4,4,102),(3,4,3.15,3,'2026-09-20',3,3,3,103),(4,5,4.65,4,'2026-09-20',5,5,5,104);
/*!40000 ALTER TABLE `performance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ratings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKok7lq3fkq1gred05o8bu2ott9` (`employee_id`),
  CONSTRAINT `FKok7lq3fkq1gred05o8bu2ott9` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` date NOT NULL,
  `start_date` date NOT NULL,
  `status` enum('COMPLETED','IN_PROGRESS','PENDING') NOT NULL,
  `employee_id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmeqi2abtbehx871tag4op3hag` (`employee_id`),
  CONSTRAINT `FKmeqi2abtbehx871tag4op3hag` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'2026-09-10','2026-09-01','COMPLETED',101,'Create CRUD APIs for employees','Develop Employee API'),(2,'2026-09-15','2026-09-05','IN_PROGRESS',102,'Design and implement HR management features','Design HR Module'),(3,'2026-09-20','2026-09-10','PENDING',103,'Prepare the monthly financial report','Prepare Financial Report'),(4,'2026-09-25','2026-09-12','IN_PROGRESS',104,'Implement JWT authentication and authorization','Implement Security');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','USER') NOT NULL,
  `username` varchar(255) NOT NULL,
  `employee_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UKr1usl9qoplqsbrhha5e0niqng` (`employee_id`),
  CONSTRAINT `FK211dk0pe7l3aibwce8yy61ota` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'$2y$10$Dqd27c3.fJnCBjgoCcivbOv/vAa0A5xSSzv1om6OVqp/kg1CSEnty','USER','ayush',101),(2,'$2y$10$myW5tiDaQ2RZuJKezOCtUOgiztuuNdj09zMnGflrbz1.Rjr/F80qe','USER','ram',102),(3,'$2y$10$zQXGXyIKy8On4U4U/M9bjONCnapYXGR2rNkargMyQtco74y7pBomi','USER','sita',103),(4,'$2y$10$KqRul.5OOiwyKpf7r57XuOVMxgNaGtxSMTvrBJR7WCC8krhSPOCj2','USER','hari',104);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-22  8:53:50
