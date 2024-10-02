CREATE DATABASE  IF NOT EXISTS `altruistapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `altruistapp`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: altruistapp
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo` (
  `id_articulo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `id_donacion` int DEFAULT NULL,
  `id_puntorecogida` int DEFAULT NULL,
  PRIMARY KEY (`id_articulo`),
  KEY `id_donacion` (`id_donacion`),
  KEY `id_puntorecogida` (`id_puntorecogida`),
  CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`id_donacion`) REFERENCES `donacion` (`id_donacion`),
  CONSTRAINT `articulo_ibfk_2` FOREIGN KEY (`id_puntorecogida`) REFERENCES `puntoderecogida` (`id_puntorecogida`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (1,'flan','nuevo',4,1),(2,'leon','usado',5,2),(3,'caballito','nuevo',6,3),(4,'coche','usado',7,1),(5,'jirafa','nuevo',8,4),(6,'trona','usado',9,3),(7,'akuna matata','nuevo',10,3),(8,'barbie','nuevo',11,1),(9,'gambas','nuevo',12,3),(10,'humberto','nuevo',13,4);
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donacion`
--

DROP TABLE IF EXISTS `donacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donacion` (
  `id_donacion` int NOT NULL AUTO_INCREMENT,
  `fecha_ingresoarticulo` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_aceptaciondonacion` timestamp NULL DEFAULT NULL,
  `id_ofrecedonacion` int DEFAULT NULL,
  `id_aceptadonacion` int DEFAULT NULL,
  `estado` enum('Pendiente','Aceptada','Rechazada') DEFAULT 'Pendiente',
  PRIMARY KEY (`id_donacion`),
  KEY `id_ofrecedonacion` (`id_ofrecedonacion`),
  KEY `id_aceptadonacion` (`id_aceptadonacion`),
  CONSTRAINT `donacion_ibfk_1` FOREIGN KEY (`id_ofrecedonacion`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `donacion_ibfk_2` FOREIGN KEY (`id_aceptadonacion`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donacion`
--

LOCK TABLES `donacion` WRITE;
/*!40000 ALTER TABLE `donacion` DISABLE KEYS */;
INSERT INTO `donacion` VALUES (4,'2024-10-02 13:17:30','2024-10-02 13:18:32',1,2,'Aceptada'),(5,'2024-10-02 13:44:21','2024-10-02 13:44:48',1,2,'Aceptada'),(6,'2024-10-02 13:55:09','2024-10-02 13:55:29',1,2,'Aceptada'),(7,'2024-10-02 14:11:48','2024-10-02 14:12:11',1,2,'Aceptada'),(8,'2024-10-02 14:19:02','2024-10-02 14:20:24',1,2,'Aceptada'),(9,'2024-10-02 14:31:12','2024-10-02 14:38:43',1,2,'Aceptada'),(10,'2024-10-02 15:10:20','2024-10-02 15:14:22',1,2,'Aceptada'),(11,'2024-10-02 15:15:31','2024-10-02 15:17:20',1,2,'Aceptada'),(12,'2024-10-02 15:31:25',NULL,2,NULL,'Pendiente'),(13,'2024-10-02 15:32:29',NULL,1,NULL,'Pendiente');
/*!40000 ALTER TABLE `donacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puntoderecogida`
--

DROP TABLE IF EXISTS `puntoderecogida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `puntoderecogida` (
  `id_puntorecogida` int NOT NULL AUTO_INCREMENT,
  `nombre_punto` varchar(100) NOT NULL,
  PRIMARY KEY (`id_puntorecogida`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puntoderecogida`
--

LOCK TABLES `puntoderecogida` WRITE;
/*!40000 ALTER TABLE `puntoderecogida` DISABLE KEYS */;
INSERT INTO `puntoderecogida` VALUES (1,'Locutorio'),(2,'Estanco'),(3,'Centro cultural'),(4,'Nuestro punto Altruistapp');
/*!40000 ALTER TABLE `puntoderecogida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(100) NOT NULL,
  `contraseña` varchar(100) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'jordi','jordi'),(2,'amanda','amanda'),(3,'amanda','amanda');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-02 17:40:32
