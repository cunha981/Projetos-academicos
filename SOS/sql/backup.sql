CREATE DATABASE  IF NOT EXISTS `projeto2017` DEFAULT CHARACTER SET utf8 ;
USE `projeto2017`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: projeto2017
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `tbareaatuacao`
--

DROP TABLE IF EXISTS `tbareaatuacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbareaatuacao` (
  `idAreaAtuacao` int(11) NOT NULL AUTO_INCREMENT,
  `desc_areaAtuacao` varchar(100) NOT NULL,
  PRIMARY KEY (`idAreaAtuacao`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbareaatuacao`
--

LOCK TABLES `tbareaatuacao` WRITE;
/*!40000 ALTER TABLE `tbareaatuacao` DISABLE KEYS */;
INSERT INTO `tbareaatuacao` VALUES (1,'Saúde'),(2,'Cultura'),(3,'Esporte'),(4,'Atendimento ou promoção dos direitos das pessoas com deficiência'),(5,'Atendimento ou promoção dos direitos de crianças e adolescentes'),(6,'Proteção e conservação do meio ambiente'),(7,'Promoção de investimentos, de competitividade e de desenvolvimento');
/*!40000 ALTER TABLE `tbareaatuacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbareaatuacaovoluntario`
--

DROP TABLE IF EXISTS `tbareaatuacaovoluntario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbareaatuacaovoluntario` (
  `idAreaAtuacao` int(11) NOT NULL,
  `idVoluntario` int(11) NOT NULL,
  PRIMARY KEY (`idAreaAtuacao`,`idVoluntario`),
  KEY `fk_tbAreaAtuacao_has_tbVoluntario_tbVoluntario1_idx` (`idVoluntario`),
  KEY `fk_tbAreaAtuacao_has_tbVoluntario_tbAreaAtuacao1_idx` (`idAreaAtuacao`),
  CONSTRAINT `fk_tbAreaAtuacao_has_tbVoluntario_tbAreaAtuacao1` FOREIGN KEY (`idAreaAtuacao`) REFERENCES `tbareaatuacao` (`idAreaAtuacao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbAreaAtuacao_has_tbVoluntario_tbVoluntario1` FOREIGN KEY (`idVoluntario`) REFERENCES `tbvoluntario` (`idVoluntario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbareaatuacaovoluntario`
--

LOCK TABLES `tbareaatuacaovoluntario` WRITE;
/*!40000 ALTER TABLE `tbareaatuacaovoluntario` DISABLE KEYS */;
INSERT INTO `tbareaatuacaovoluntario` VALUES (3,1),(1,2),(2,2),(3,2),(1,3),(2,3),(7,3);
/*!40000 ALTER TABLE `tbareaatuacaovoluntario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbdisponibilidade`
--

DROP TABLE IF EXISTS `tbdisponibilidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbdisponibilidade` (
  `idDisponibilidade` int(11) NOT NULL AUTO_INCREMENT,
  `periodo` varchar(45) NOT NULL,
  `dia` varchar(45) NOT NULL,
  PRIMARY KEY (`idDisponibilidade`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbdisponibilidade`
--

LOCK TABLES `tbdisponibilidade` WRITE;
/*!40000 ALTER TABLE `tbdisponibilidade` DISABLE KEYS */;
INSERT INTO `tbdisponibilidade` VALUES (1,'Manhã','Segunda-feira'),(2,'Tarde','Segunda-feira'),(3,'Noite','Segunda-feira'),(4,'Manhã','Terça-feira'),(5,'Tarde','Terça-feira'),(6,'Noite','Terça-feira'),(7,'Manhã','Quarta-feira'),(8,'Tarde','Quarta-feira'),(9,'Noite','Quarta-feira'),(10,'Manhã','Quinta-feira'),(11,'Tarde','Quinta-feira'),(12,'Noite','Quinta-feira'),(13,'Manhã','Sexta-feira'),(14,'Tarde','Sexta-feira'),(15,'Noite','Sexta-feira'),(16,'Manhã','Sábado'),(17,'Tarde','Sábado'),(18,'Noite','Sábado'),(19,'Manhã','Domingo'),(20,'Tarde','Domingo'),(21,'Noite','Domingo');
/*!40000 ALTER TABLE `tbdisponibilidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbdisponibilidadeprojeto`
--

DROP TABLE IF EXISTS `tbdisponibilidadeprojeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbdisponibilidadeprojeto` (
  `idDisponibilidade` int(11) NOT NULL,
  `idProjeto` int(11) NOT NULL,
  PRIMARY KEY (`idDisponibilidade`,`idProjeto`),
  KEY `fk_tbDiaDisponivel_has_tbProjeto_tbProjeto1_idx` (`idProjeto`),
  KEY `fk_tbDiaDisponivel_has_tbProjeto_tbDiaDisponivel1_idx` (`idDisponibilidade`),
  CONSTRAINT `fk_tbDiaDisponivel_has_tbProjeto_tbDiaDisponivel1` FOREIGN KEY (`idDisponibilidade`) REFERENCES `tbdisponibilidade` (`idDisponibilidade`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbDiaDisponivel_has_tbProjeto_tbProjeto1` FOREIGN KEY (`idProjeto`) REFERENCES `tbprojeto` (`idProjeto`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbdisponibilidadeprojeto`
--

LOCK TABLES `tbdisponibilidadeprojeto` WRITE;
/*!40000 ALTER TABLE `tbdisponibilidadeprojeto` DISABLE KEYS */;
INSERT INTO `tbdisponibilidadeprojeto` VALUES (1,1),(6,1),(1,3),(5,3),(7,3),(16,4),(17,4),(18,4),(19,4),(20,4),(21,4),(1,5),(5,5),(9,5),(11,5),(13,5),(1,7),(7,7),(13,7),(19,7);
/*!40000 ALTER TABLE `tbdisponibilidadeprojeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbdisponibilidadevoluntario`
--

DROP TABLE IF EXISTS `tbdisponibilidadevoluntario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbdisponibilidadevoluntario` (
  `idDisponibilidade` int(11) NOT NULL,
  `idVoluntario` int(11) NOT NULL,
  PRIMARY KEY (`idDisponibilidade`,`idVoluntario`),
  KEY `fk_tbDiaDisponivel_has_tbVoluntario_tbVoluntario1_idx` (`idVoluntario`),
  KEY `fk_tbDiaDisponivel_has_tbVoluntario_tbDiaDisponivel1_idx` (`idDisponibilidade`),
  CONSTRAINT `fk_tbDiaDisponivel_has_tbVoluntario_tbDiaDisponivel1` FOREIGN KEY (`idDisponibilidade`) REFERENCES `tbdisponibilidade` (`idDisponibilidade`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbDiaDisponivel_has_tbVoluntario_tbVoluntario1` FOREIGN KEY (`idVoluntario`) REFERENCES `tbvoluntario` (`idVoluntario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbdisponibilidadevoluntario`
--

LOCK TABLES `tbdisponibilidadevoluntario` WRITE;
/*!40000 ALTER TABLE `tbdisponibilidadevoluntario` DISABLE KEYS */;
INSERT INTO `tbdisponibilidadevoluntario` VALUES (1,1),(1,2),(4,2),(7,2),(10,2),(13,2),(16,2),(19,2),(1,3),(5,3),(9,3),(11,3),(13,3),(18,3);
/*!40000 ALTER TABLE `tbdisponibilidadevoluntario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbendereco`
--

DROP TABLE IF EXISTS `tbendereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbendereco` (
  `idEndereco` int(11) NOT NULL AUTO_INCREMENT,
  `logradouro` varchar(200) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `cep` varchar(10) NOT NULL,
  `estado` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idEndereco`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbendereco`
--

LOCK TABLES `tbendereco` WRITE;
/*!40000 ALTER TABLE `tbendereco` DISABLE KEYS */;
INSERT INTO `tbendereco` VALUES (1,'Rua Anselmo Bonini',664,'Poá','08550330','SP'),(2,'Rua catu',669,'São Paulo','08440330','SP'),(3,'Rua Porto do Bezerra',333,'São Paulo','0844000','SP'),(4,'Rua Catu',257,'São Paulo','08440330','SP'),(5,'Rua Catu',257,'São Paulo','08440330','SP'),(6,'Rua Catu',257,'São Paulo','08440330','SP'),(7,'Rua Catu',257,'São Paulo','08440330','SP'),(8,'Rua Catu',257,'São Paulo','08440330','SP'),(9,'Rua Catu',257,'São Paulo','08440330','SP'),(10,'Rua Porto do Bezerra',665,'São Paulo','08440000','SP');
/*!40000 ALTER TABLE `tbendereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbos`
--

DROP TABLE IF EXISTS `tbos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbos` (
  `idOS` int(11) NOT NULL AUTO_INCREMENT,
  `nm_instituicao` varchar(200) NOT NULL,
  `nm_fantasia` varchar(200) NOT NULL,
  `cnpj` varchar(20) NOT NULL,
  `idEndereco` int(11) NOT NULL,
  `tel` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `dt_inicio` date DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idOS`),
  KEY `fk_tbOS_tbEndereco1_idx` (`idEndereco`),
  CONSTRAINT `fk_tbOS_tbEndereco1` FOREIGN KEY (`idEndereco`) REFERENCES `tbendereco` (`idEndereco`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbos`
--

LOCK TABLES `tbos` WRITE;
/*!40000 ALTER TABLE `tbos` DISABLE KEYS */;
INSERT INTO `tbos` VALUES (1,'Sem chave não compila','SCNC ltda','084602023',1,'11986024048','igor.cunha@scnc.com.br','2013-03-03',1),(2,'Organização das fumaças','fumaças SA','69696969420',2,'11986024548','fumaca@mail.com.br','2002-02-02',1),(3,'os dos bebum','bebum ltda','69696969428',3,'11986545454','bebum@bebe.com.br','2009-12-25',1),(4,'ONG ONU','ONG ONU LTDA','57551522000107',5,'11986024548','cunha.981@gmail.com','2017-02-02',1);
/*!40000 ALTER TABLE `tbos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbprojeto`
--

DROP TABLE IF EXISTS `tbprojeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbprojeto` (
  `idProjeto` int(11) NOT NULL AUTO_INCREMENT,
  `nm_projeto` varchar(100) NOT NULL,
  `desc_projeto` varchar(500) NOT NULL,
  `idOS` int(11) NOT NULL,
  `qtd_voluntario` int(11) NOT NULL,
  `status` varchar(45) NOT NULL,
  `dt_cadastro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt_periodo_inscricao` date NOT NULL,
  `dt_inicio` date DEFAULT NULL,
  `idAreaAtuacao` int(11) NOT NULL,
  PRIMARY KEY (`idProjeto`),
  KEY `fk_tbProjeto_tbOS1_idx` (`idOS`),
  KEY `fk_tbProjeto_tbAreaAtuacao1_idx` (`idAreaAtuacao`),
  CONSTRAINT `fk_tbProjeto_tbAreaAtuacao1` FOREIGN KEY (`idAreaAtuacao`) REFERENCES `tbareaatuacao` (`idAreaAtuacao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbProjeto_tbOS1` FOREIGN KEY (`idOS`) REFERENCES `tbos` (`idOS`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbprojeto`
--

LOCK TABLES `tbprojeto` WRITE;
/*!40000 ALTER TABLE `tbprojeto` DISABLE KEYS */;
INSERT INTO `tbprojeto` VALUES (1,'projeto Sâo Judas','este projeto ajudara crianças com deficiências, terá inicio dia 02-10-2017.',1,1000,'BuscaDeVoluntario','2017-04-30 14:24:26','2017-02-01','2017-10-02',4),(3,'fumaça sem fogo','vamos todos fazer muita fumaça',2,420,'BuscaDeVoluntario','2017-04-30 18:55:13','2017-05-02','2017-05-30',2),(4,'projeto de resenha','vamos todos para igrejaprojeto dvamos todos para igrejaprojeto de resenhavamos todos para igrejaprojeto de resenhavamos todos para igrejaprojeto de resenhavamos todos para igrejaprojeto de resenhavamos todos para igrejaprojeto de resenhagrejaprojeto de resenha',3,10,'BuscaDeVoluntario','2017-04-30 23:49:34','2017-12-25','2017-01-01',1),(5,'Mundo mais quente','Campanha do agasalho para ajudar pessoas que moram nas ruas de São Paulo. Vamos participar!!!',1,9999,'BuscaDeVoluntario','2017-05-13 13:37:14','2017-06-01','2017-05-14',1),(7,'Criança não trabalha','Vamos denunciar o trabalho infantil',2,2002,'Ativo','2017-05-13 13:44:31','2017-05-30','2017-06-02',5);
/*!40000 ALTER TABLE `tbprojeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbvoluntario`
--

DROP TABLE IF EXISTS `tbvoluntario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbvoluntario` (
  `idVoluntario` int(11) NOT NULL AUTO_INCREMENT,
  `nm_voluntario` varchar(45) NOT NULL,
  `tel` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `dt_nasc` date NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `genero` varchar(45) NOT NULL,
  `estado_civil` varchar(45) NOT NULL,
  `idEndereco` int(11) NOT NULL,
  PRIMARY KEY (`idVoluntario`),
  KEY `fk_tbVoluntario_tbEndereco1_idx` (`idEndereco`),
  CONSTRAINT `fk_tbVoluntario_tbEndereco1` FOREIGN KEY (`idEndereco`) REFERENCES `tbendereco` (`idEndereco`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbvoluntario`
--

LOCK TABLES `tbvoluntario` WRITE;
/*!40000 ALTER TABLE `tbvoluntario` DISABLE KEYS */;
INSERT INTO `tbvoluntario` VALUES (1,'igor cunha de almeida','11986024548','fumaca@mail.com.br','1998-05-30','42016714883','m','solteiro',4),(2,'igor cunha de almeida','11986024548','fumaca@mail.com.br','1998-05-30','42016714883','m','Solteiro',9),(3,'Jean Carlos de Almeida','11986024548','jeanjnx@gmail.com','1994-01-02','42016714897','m','Casado',10);
/*!40000 ALTER TABLE `tbvoluntario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbvoluntarioprojeto`
--

DROP TABLE IF EXISTS `tbvoluntarioprojeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbvoluntarioprojeto` (
  `idVoluntario` int(11) NOT NULL,
  `idProjeto` int(11) NOT NULL,
  PRIMARY KEY (`idVoluntario`,`idProjeto`),
  KEY `fk_tbVoluntario_has_tbProjeto_tbProjeto1_idx` (`idProjeto`),
  KEY `fk_tbVoluntario_has_tbProjeto_tbVoluntario1_idx` (`idVoluntario`),
  CONSTRAINT `fk_tbVoluntario_has_tbProjeto_tbProjeto1` FOREIGN KEY (`idProjeto`) REFERENCES `tbprojeto` (`idProjeto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbVoluntario_has_tbProjeto_tbVoluntario1` FOREIGN KEY (`idVoluntario`) REFERENCES `tbvoluntario` (`idVoluntario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbvoluntarioprojeto`
--

LOCK TABLES `tbvoluntarioprojeto` WRITE;
/*!40000 ALTER TABLE `tbvoluntarioprojeto` DISABLE KEYS */;
INSERT INTO `tbvoluntarioprojeto` VALUES (3,4);
/*!40000 ALTER TABLE `tbvoluntarioprojeto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-14 19:06:28
