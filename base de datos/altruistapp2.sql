CREATE DATABASE  IF NOT EXISTS `altruistapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `altruistapp`;
-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: localhost    Database: altruistapp
-- ------------------------------------------------------
-- Server version	8.0.26

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
  `id_donacion` int DEFAULT NULL,
  `id_puntorecogida` int DEFAULT NULL,
  PRIMARY KEY (`id_articulo`),
  KEY `id_donacion` (`id_donacion`),
  KEY `id_puntorecogida` (`id_puntorecogida`),
  CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`id_donacion`) REFERENCES `donacion` (`id_donacion`),
  CONSTRAINT `articulo_ibfk_2` FOREIGN KEY (`id_puntorecogida`) REFERENCES `puntoderecogida` (`id_puntorecogida`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (1,'flan',4,1),(2,'leon',5,2),(3,'caballito',6,3),(4,'coche',7,1),(5,'jirafa',8,4),(6,'trona',9,3),(7,'akuna matata',10,3),(8,'barbie',11,1),(9,'gambas',12,3),(10,'humberto',13,4),(11,'mesa',14,3),(12,'silla',15,2),(13,'sillas',16,1),(14,'mesas',17,1),(15,'jarron',18,1),(16,'mesa',19,3),(17,'palo',20,1),(18,'ropa niño 6 años',21,4),(21,'una cosa',24,1);
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
  PRIMARY KEY (`id_donacion`),
  KEY `id_ofrecedonacion` (`id_ofrecedonacion`),
  KEY `id_aceptadonacion` (`id_aceptadonacion`),
  CONSTRAINT `donacion_ibfk_1` FOREIGN KEY (`id_ofrecedonacion`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `donacion_ibfk_2` FOREIGN KEY (`id_aceptadonacion`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donacion`
--

LOCK TABLES `donacion` WRITE;
/*!40000 ALTER TABLE `donacion` DISABLE KEYS */;
INSERT INTO `donacion` VALUES (4,'2024-10-02 13:17:30','2024-10-02 13:18:32',1,2),(5,'2024-10-02 13:44:21','2024-10-02 13:44:48',1,2),(6,'2024-10-02 13:55:09','2024-10-02 13:55:29',1,2),(7,'2024-10-02 14:11:48','2024-10-02 14:12:11',1,2),(8,'2024-10-02 14:19:02','2024-10-02 14:20:24',1,2),(9,'2024-10-02 14:31:12','2024-10-02 14:38:43',1,2),(10,'2024-10-02 15:10:20','2024-10-02 15:14:22',1,2),(11,'2024-10-02 15:15:31','2024-10-02 15:17:20',1,2),(12,'2024-10-02 15:31:25',NULL,2,NULL),(13,'2024-10-02 15:32:29','2024-10-03 19:56:51',1,2),(14,'2024-10-03 09:21:35',NULL,4,NULL),(15,'2024-10-03 09:23:45','2024-10-03 09:24:02',4,4),(16,'2024-10-03 09:25:37',NULL,4,NULL),(17,'2024-10-03 09:26:59',NULL,4,NULL),(18,'2024-10-03 14:00:35',NULL,2,NULL),(19,'2024-10-03 14:54:36','2024-10-04 07:41:20',2,2),(20,'2024-10-04 07:40:39',NULL,2,NULL),(21,'2024-10-04 07:54:11',NULL,2,NULL),(24,'2024-10-04 12:55:29',NULL,2,NULL);
/*!40000 ALTER TABLE `donacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_articulo`
--

DROP TABLE IF EXISTS `estado_articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_articulo` (
  `idestado_articulo` int NOT NULL AUTO_INCREMENT,
  `nuevo` varchar(45) DEFAULT NULL,
  `buenestado` varchar(45) DEFAULT NULL,
  `muyusado` varchar(45) DEFAULT NULL,
  `para arreglar` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idestado_articulo`),
  CONSTRAINT `fk_estadoarticulo` FOREIGN KEY (`idestado_articulo`) REFERENCES `articulo` (`id_articulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_articulo`
--

LOCK TABLES `estado_articulo` WRITE;
/*!40000 ALTER TABLE `estado_articulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_donacion`
--

DROP TABLE IF EXISTS `estado_donacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_donacion` (
  `idestado_donacion` int NOT NULL,
  `disponible` varchar(45) DEFAULT NULL,
  `reservado` varchar(45) DEFAULT NULL,
  `donado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idestado_donacion`),
  CONSTRAINT `fk_estadodonacion` FOREIGN KEY (`idestado_donacion`) REFERENCES `donacion` (`id_donacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_donacion`
--

LOCK TABLES `estado_donacion` WRITE;
/*!40000 ALTER TABLE `estado_donacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_donacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_solicitud`
--

DROP TABLE IF EXISTS `estado_solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_solicitud` (
  `idestado_solicitud` int NOT NULL,
  `pendiente` varchar(45) DEFAULT NULL,
  `aceptada` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idestado_solicitud`),
  CONSTRAINT `fk_estadosolicitud` FOREIGN KEY (`idestado_solicitud`) REFERENCES `solicitudes` (`idsolicitudes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_solicitud`
--

LOCK TABLES `estado_solicitud` WRITE;
/*!40000 ALTER TABLE `estado_solicitud` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_solicitud` ENABLE KEYS */;
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
-- Table structure for table `solicitudes`
--

DROP TABLE IF EXISTS `solicitudes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitudes` (
  `idsolicitudes` int NOT NULL AUTO_INCREMENT,
  `id_usuariorecibe` int NOT NULL,
  `id_usuariorealiza` int NOT NULL,
  PRIMARY KEY (`idsolicitudes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitudes`
--

LOCK TABLES `solicitudes` WRITE;
/*!40000 ALTER TABLE `solicitudes` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitudes` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'jordi','jordi'),(2,'amanda','amanda'),(3,'amanda','amanda'),(4,'paco','paco'),(5,'1','1');
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

-- Dump completed on 2024-10-05 21:06:22
