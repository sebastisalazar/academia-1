-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: academia
-- ------------------------------------------------------
-- Server version	5.7.31-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `academia`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `academia` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `academia`;

--
-- Table structure for table `alumnos_curso`
--

DROP TABLE IF EXISTS `alumnos_curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumnos_curso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_alumno` int(11) NOT NULL,
  `id_curso` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `necesitamos_curso_FK` (`id_curso`),
  KEY `necesitamos_alumnos_FK` (`id_alumno`),
  CONSTRAINT `necesitamos_alumnos_FK` FOREIGN KEY (`id_alumno`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `necesitamos_curso_FK` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos_curso`
--

LOCK TABLES `alumnos_curso` WRITE;
/*!40000 ALTER TABLE `alumnos_curso` DISABLE KEYS */;
INSERT INTO `alumnos_curso` VALUES (1,1,1),(2,1,2),(3,2,1),(4,2,3),(5,3,2),(6,3,3),(7,4,1),(8,4,3),(14,4,158),(15,4,160);
/*!40000 ALTER TABLE `alumnos_curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `identificador` varchar(10) NOT NULL,
  `horas` int(11) DEFAULT NULL,
  `id_profesor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cursos_nombre_UN` (`nombre`),
  UNIQUE KEY `cursos_identificador_UN` (`identificador`),
  KEY `cursos_neceista_profesor_FK` (`id_profesor`),
  CONSTRAINT `cursos_neceista_profesor_FK` FOREIGN KEY (`id_profesor`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES (1,'Microsoft Office 2016','I001',50,1),(2,'Experto en Desarrollo de Aplicaciones WEB y Bases de Datos','I002',630,2),(3,'Desarrollo Avanzado con JAVA/JEE','I003',510,3),(158,'PHP','EIE84',70,2),(159,'SQL','TEST',600,2),(160,'PLSQL','IEQ23',80,2);
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `rol` int(11) NOT NULL DEFAULT '1' COMMENT '1: Alumno\n2: Profesor',
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuarios_UN` (`nombre`,`apellidos`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Alain','Moles',2,'e10adc3949ba59abbe56e057f20f883e'),(2,'Ander','Uraga',2,'e10adc3949ba59abbe56e057f20f883e'),(3,'Dr','Bacterio',2,'e10adc3949ba59abbe56e057f20f883e'),(4,'Elier','Otero',1,'e10adc3949ba59abbe56e057f20f883e'),(5,'Beatriz','Martinez',1,'e10adc3949ba59abbe56e057f20f883e'),(6,'Asier','Mintegi',1,'e10adc3949ba59abbe56e057f20f883e'),(7,'Lander','Bilbao',1,'e10adc3949ba59abbe56e057f20f883e');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-18  4:06:01
