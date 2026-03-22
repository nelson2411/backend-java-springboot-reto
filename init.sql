CREATE DATABASE  IF NOT EXISTS `gestion_eventos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestion_eventos`;
-- MySQL dump 10.13  Distrib 8.0.43, for macos15 (arm64)
--
-- Host: localhost    Database: gestion_eventos
-- --------------------eventos----------------------------------
-- Server version	8.0.43

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


select * from eventos;

--
-- Table structure for table `eventos`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `titulo` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` text COLLATE utf8mb4_unicode_ci,
  `fecha_evento` date NOT NULL,
  `hora_evento` time NOT NULL,
  `lugar` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `aforo_maximo` int NOT NULL,
  `entradas_vendidas` int NOT NULL DEFAULT '0',
  `precio` decimal(8,2) NOT NULL,
  `imagen_url` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `destacado` tinyint(1) NOT NULL DEFAULT '0',
  `estado` enum('ACTIVO','CANCELADO','TERMINADO') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ACTIVO',
  `tipo_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_evento_estado` (`estado`),
  KEY `idx_evento_destacado` (`destacado`),
  KEY `idx_evento_fecha` (`fecha_evento`),
  KEY `idx_evento_tipo` (`tipo_id`),
  CONSTRAINT `fk_evento_tipo` FOREIGN KEY (`tipo_id`) REFERENCES `tipos` (`id`),
  CONSTRAINT `eventos_chk_1` CHECK ((`aforo_maximo` > 0)),
  CONSTRAINT `eventos_chk_2` CHECK ((`entradas_vendidas` >= 0)),
  CONSTRAINT `eventos_chk_3` CHECK ((`precio` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
INSERT INTO `eventos` VALUES (1,'Rock en la Plaza','Concierto de rock al aire libre con bandas locales e internacionales.','2026-04-15','20:00:00','Plaza Mayor, Madrid',500,120,25.00,'https://images.unsplash.com/photo-1470229722913-7c0e2dbbafd3?w=600',1,'ACTIVO',1),(2,'Hamlet – Compañía Nacional','Representación clásica de Hamlet por la Compañía Nacional de Teatro.','2026-03-28','19:30:00','Teatro Real, Madrid',200,85,35.00,'https://images.unsplash.com/photo-1503095396549-807759245b35?w=600',1,'ACTIVO',2),(3,'Final Copa Regional Baloncesto','La gran final de la Copa Regional de Baloncesto.','2026-05-10','18:00:00','Pabellón Central, Valencia',3000,1800,15.00,'https://images.unsplash.com/photo-1546519638-68e109498ffc?w=600',0,'ACTIVO',3),(4,'Tech Summit 2026','Conferencia de tecnología con ponentes de primer nivel.','2026-06-05','09:00:00','Centro de Convenciones, Barcelona',400,400,50.00,'https://images.unsplash.com/photo-1540575467063-178a50c2df87?w=600',1,'ACTIVO',4),(5,'Flamenco Fusión','Espectáculo de flamenco contemporáneo fusionado con jazz.','2026-04-20','21:00:00','Auditorio de Sevilla',300,150,30.00,'https://images.unsplash.com/photo-1514320291840-2e0a9bf2a9ae?w=600',0,'ACTIVO',1),(6,'Festival de Verano','Tres días de música, gastronomía y cultura.','2026-07-15','12:00:00','Recinto Ferial, Málaga',5000,3200,60.00,'https://images.unsplash.com/photo-1533174072545-7a4b6ad7a6c3?w=600',1,'ACTIVO',5),(7,'Concierto Navideño','Concierto de villancicos y música navideña.','2025-12-23','19:00:00','Catedral, Burgos',250,250,10.00,NULL,0,'TERMINADO',1),(8,'Torneo Cancelado Ajedrez','Campeonato que fue cancelado por problemas logísticos.','2026-05-01','10:00:00','Casa de Cultura, Bilbao',100,0,5.00,NULL,0,'CANCELADO',3);
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfiles`
--

DROP TABLE IF EXISTS `perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfiles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

select * from perfiles;

--
-- Dumping data for table `perfiles`
--

LOCK TABLES `perfiles` WRITE;
/*!40000 ALTER TABLE `perfiles` DISABLE KEYS */;
INSERT INTO `perfiles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_CLIENTE');
/*!40000 ALTER TABLE `perfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint NOT NULL,
  `evento_id` bigint NOT NULL,
  `num_entradas` int NOT NULL,
  `fecha_reserva` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` enum('CONFIRMADA','CANCELADA') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'CONFIRMADA',
  PRIMARY KEY (`id`),
  KEY `idx_reserva_usuario` (`usuario_id`),
  KEY `idx_reserva_evento` (`evento_id`),
  KEY `idx_reserva_estado` (`estado`),
  CONSTRAINT `fk_reserva_evento` FOREIGN KEY (`evento_id`) REFERENCES `eventos` (`id`),
  CONSTRAINT `fk_reserva_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `reservas_chk_1` CHECK (((`num_entradas` > 0) and (`num_entradas` <= 10)))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (1,2,1,3,'2026-03-05 12:30:56','CONFIRMADA'),(2,2,2,2,'2026-03-05 12:30:56','CONFIRMADA'),(3,3,1,5,'2026-03-05 12:30:56','CONFIRMADA'),(4,3,3,2,'2026-03-05 12:30:56','CONFIRMADA'),(5,4,6,4,'2026-03-05 12:30:56','CONFIRMADA'),(6,4,1,2,'2026-03-05 12:30:56','CANCELADA');
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos`
--

DROP TABLE IF EXISTS `tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos`
--

LOCK TABLES `tipos` WRITE;
/*!40000 ALTER TABLE `tipos` DISABLE KEYS */;
INSERT INTO `tipos` VALUES (1,'Concierto','Eventos musicales en vivo',1),(2,'Teatro','Obras de teatro y musicales',1),(3,'Deportes','Eventos deportivos',1),(4,'Conferencia','Charlas y conferencias',1),(5,'Festival','Festivales y eventos multidía',1);
/*!40000 ALTER TABLE `tipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_perfiles`
--

DROP TABLE IF EXISTS `usuario_perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_perfiles` (
  `usuario_id` bigint NOT NULL,
  `perfil_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`perfil_id`),
  KEY `fk_up_perfil` (`perfil_id`),
  CONSTRAINT `fk_up_perfil` FOREIGN KEY (`perfil_id`) REFERENCES `perfiles` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_up_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_perfiles`
--

LOCK TABLES `usuario_perfiles` WRITE;
/*!40000 ALTER TABLE `usuario_perfiles` DISABLE KEYS */;
INSERT INTO `usuario_perfiles` VALUES (1,1),(1,2),(2,2),(3,2),(4,2);
/*!40000 ALTER TABLE `usuario_perfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apellidos` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefono` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `fecha_registro` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `idx_usuario_email` (`email`),
  KEY `idx_usuario_activo` (`activo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

select * from usuarios;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Admin','Sistema','admin@reservaentradas.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy','600000001',1,'2026-03-05 12:24:30'),(2,'María','García López','maria@correo.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy','600000002',1,'2026-03-05 12:24:30'),(3,'Carlos','Martín Ruiz','carlos@correo.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy','600000003',1,'2026-03-05 12:24:30'),(4,'Laura','Sánchez','laura@correo.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy','600000004',1,'2026-03-05 12:24:30');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v_disponibilidad_eventos`
--

DROP TABLE IF EXISTS `v_disponibilidad_eventos`;
/*!50001 DROP VIEW IF EXISTS `v_disponibilidad_eventos`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_disponibilidad_eventos` AS SELECT 
 1 AS `id`,
 1 AS `titulo`,
 1 AS `aforo_maximo`,
 1 AS `entradas_vendidas`,
 1 AS `plazas_disponibles`,
 1 AS `estado`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_reservas_por_usuario_evento`
--

DROP TABLE IF EXISTS `v_reservas_por_usuario_evento`;
/*!50001 DROP VIEW IF EXISTS `v_reservas_por_usuario_evento`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_reservas_por_usuario_evento` AS SELECT 
 1 AS `usuario_id`,
 1 AS `evento_id`,
 1 AS `total_reservadas`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_disponibilidad_eventos`
--

/*!50001 DROP VIEW IF EXISTS `v_disponibilidad_eventos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_disponibilidad_eventos` AS select `e`.`id` AS `id`,`e`.`titulo` AS `titulo`,`e`.`aforo_maximo` AS `aforo_maximo`,`e`.`entradas_vendidas` AS `entradas_vendidas`,(`e`.`aforo_maximo` - `e`.`entradas_vendidas`) AS `plazas_disponibles`,`e`.`estado` AS `estado` from `eventos` `e` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_reservas_por_usuario_evento`
--

/*!50001 DROP VIEW IF EXISTS `v_reservas_por_usuario_evento`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_reservas_por_usuario_evento` AS select `r`.`usuario_id` AS `usuario_id`,`r`.`evento_id` AS `evento_id`,sum(`r`.`num_entradas`) AS `total_reservadas` from `reservas` `r` where (`r`.`estado` = 'CONFIRMADA') group by `r`.`usuario_id`,`r`.`evento_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-21 11:28:18
