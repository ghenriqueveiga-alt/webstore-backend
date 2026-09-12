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
INSERT INTO `grade` (`id`, `descricao`, `grade_ativa`, `nome`, `periodo_fim`, `periodo_inicio`, `status_desc`, `uuid`) VALUES (1,'Grade principal com toda a programa├º├úo semanal de segunda a domingo',_binary '','Grade Principal','2026-12-31','2026-01-01','Active','c194b59f-7136-48af-bb5f-0013556155af'),(2,'Programa├º├úo voltada para o p├║blico infantil com desenhos animados e animes leves',_binary '\0','Grade Kids','2026-12-31','2026-01-01','Active','ae37b097-5005-43bc-ad1b-f1258e8f5751'),(3,'Programa├º├úo noturna com animes mais maduros e s├®ries para adultos',_binary '\0','Grade Noite','2026-12-31','2026-01-01','Active','7311de52-ef83-45a8-9ad9-4d0a477dd30c'),(4,'Programa├º├úo especial para s├íbados e domingos com maratons e especiais',_binary '\0','Grade Fim de Semana','2026-12-31','2026-01-01','Active','765d5367-48f8-4ff2-8bd5-7123a08061ee'),(5,'Animes e desenhos cl├íssicos dos anos 80, 90 e 2000',_binary '\0','Grade Cl├íssicos','2026-12-31','2026-01-01','Active','cf26713d-7d0a-4dd1-8429-f11c83a872ea');
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

-- Dump completed on 2026-09-12 11:56:22
