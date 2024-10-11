CREATE DATABASE  IF NOT EXISTS `altruistapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `altruistapp`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: altruistapp
-- ------------------------------------------------------
-- Server version	8.0.39

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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (22,'Mochila','Disponible',25,1),(23,'Sabanas','Disponible',26,2),(24,'Abrigo','Disponible',27,3),(25,'Video consola','Disponible',28,4),(26,'Cuadernos','Disponible',29,3),(27,'Platos','Disponible',30,1),(28,'Trona','Disponible',31,4),(29,'cepillos dentales','Disponible',32,1),(30,'Oso de peluche','Disponible',33,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donacion`
--

LOCK TABLES `donacion` WRITE;
/*!40000 ALTER TABLE `donacion` DISABLE KEYS */;
INSERT INTO `donacion` VALUES (25,'2024-10-11 10:52:48','2024-10-11 10:59:41',4,2,'Aceptada'),(26,'2024-10-11 10:53:32','2024-10-11 11:01:19',4,1,'Aceptada'),(27,'2024-10-11 10:56:23','2024-10-11 11:01:41',1,1,'Aceptada'),(28,'2024-10-11 10:56:54','2024-10-11 11:02:13',1,4,'Aceptada'),(29,'2024-10-11 10:58:04',NULL,2,NULL,'Pendiente'),(30,'2024-10-11 10:58:35','2024-10-11 11:02:37',2,4,'Aceptada'),(31,'2024-10-11 11:10:39',NULL,4,NULL,'Pendiente'),(32,'2024-10-11 11:11:34',NULL,4,NULL,'Pendiente'),(33,'2024-10-11 11:12:06',NULL,4,NULL,'Pendiente');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'jordi','jordi'),(2,'amanda','amanda'),(4,'cristina','cristina'),(5,'daniel','daniel'),(6,'camila','camila'),(7,'jorge','jorge'),(8,'manuel','manuel');
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

-- Dump completed on 2024-10-11 13:15:54
