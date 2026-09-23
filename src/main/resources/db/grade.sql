-- MySQL dump 10.13  Distrib 8.0.46, for Linux (x86_64)
--
-- Host: localhost    Database: db
-- ------------------------------------------------------
-- Server version	8.0.46

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

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` (`id`, `descricao`, `grade_ativa`, `nome`, `periodo_fim`, `periodo_inicio`, `status_desc`, `uuid`) VALUES (1,'Grade principal com toda a programação semanal de segunda a domingo',_binary '','Grade Principal','2026-12-31','2026-01-01','Active','e9342c84-0ebd-4448-a1e6-3268f289b27c'),(2,'Programação voltada para o público infantil com desenhos animados e animes leves',_binary '\0','Grade Kids','2026-12-31','2026-01-01','Active','5ce64f6b-02af-4752-82e2-5c6d5cc55172'),(3,'Programação noturna com animes mais maduros e séries para adultos',_binary '\0','Grade Noite','2026-12-31','2026-01-01','Active','c679d6b2-3dcb-4352-94b8-bd4b71e8fbc5'),(4,'Programação especial para sábados e domingos com maratons e especiais',_binary '\0','Grade Fim de Semana','2026-12-31','2026-01-01','Active','58470fb5-a349-4d64-bdca-52d041916e23'),(5,'Animes e desenhos clássicos dos anos 80, 90 e 2000',_binary '\0','Grade Clássicos','2026-12-31','2026-01-01','Active','16ae36fe-904f-4d8f-806b-46c12284920e');
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-18 10:59:15
