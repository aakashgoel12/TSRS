-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: tns
-- ------------------------------------------------------
-- Server version	5.5.43-0ubuntu0.12.04.1

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
-- Table structure for table `audio`
--

DROP TABLE IF EXISTS `audio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audio` (
  `pathaud` char(60) DEFAULT NULL,
  `lat` char(30) NOT NULL,
  `lng` char(30) NOT NULL,
  `time` char(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audio`
--

LOCK TABLES `audio` WRITE;
/*!40000 ALTER TABLE `audio` DISABLE KEYS */;
INSERT INTO `audio` VALUES ('uploads/AUD_20150629_164221.mp3','28.583334','77.205833','1435576349'),('uploads/IMG_20150623_164225.jp','28.509297','77.179813','1435057971'),('uploads/IMG_20150623_165743.jp','28.527203','77.217092','1435058897'),('uploads/AUD_20150624_104826.mp','28.522816','77.163709','1435123119'),('uploads/AUD_20150630_164558.mp3','28.523972','77.207075','1435662970'),('uploads/AUD_20150709_182835.mp3','28.5446549','77.1900132','1436446725'),('uploads/AUD_20150720_105614.mp3','28.5447481','77.1913127','1437370027'),('uploads/AUD_20150723_105526.mp3','28.5447423','77.1913476','1437629125');
/*!40000 ALTER TABLE `audio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `lat` char(30) NOT NULL,
  `lng` char(30) NOT NULL,
  `traffic` char(30) NOT NULL,
  `weather` char(30) NOT NULL,
  `safe` char(30) NOT NULL,
  `time` char(30) NOT NULL,
  `saferating` double NOT NULL,
  `nop` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES ('28.5447552','77.1912742','0.03605419397354126','3.429839611053467','2.991804599761963','1437660790',1.6125,2),('28.553267','77.193716','4.572298049926758','6.445059776306152','8.8482084274292','1437036829',7.22595,2),('28.54167','77.187271','3.2812561988830566','4.3246564865112305','6.307159900665283','1437371420',1.9029,1),('28.5447532','77.1913166','5.184078693389893','4.658322334289551','7.544109344482422','1437632261',4.9617,1),('28.542515','77.186986','5.784920692443848','5.893758773803711','7.438237190246582','1437554837',5.0479,1),('28.542042','77.185589','8.28110122680664','6.542503833770752','7.484906196594238','1437554930',8.4882,1),('28.546579','77.196807','3.0492005348205566','5.447568893432617','4.338104248046875','1437555191',1.5993,1),('28.553691','77.194115','6.111255645751953','2.191226005554199','7.580970287322998','1437555306',1.5052,1),('28.538552','77.198768','8.149402618408203','8.618343353271484','4.790614128112793','1437555431',4.9666,1),('28.544884','77.191443','6.378354549407959','8.457748413085938','7.553451061248779','1437555667',8.507,1),('28.547593','77.184507','8.052794456481934','6.253671169281006','8.143566131591797','1437555850',8.5791,1),('28.546341','77.196651','4.6845903396606445','7.839136123657227','6.909611701965332','1437555948',6.2218,1),('28.538579','77.198782','6.981039047241211','7.279840469360352','7.3044633865356445','1437556021',7.1131,1),('28.540682','77.18532','3.3576548099517822','7.597241401672363','6.7768473625183105','1437556086',5.8958,1);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `path` varchar(100) NOT NULL,
  `lat` char(20) NOT NULL,
  `time` char(64) NOT NULL,
  `lng` char(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES ('uploads/IMG_20150617_105545.jpg','28.557456','1434518754','77.16352'),('uploads/IMG_20150624_104535.jpg','28.583334','1435122951','77.205833'),('uploads/IMG_20150616_151154.jpg','28.568514','1434540902','77.186927'),('uploads/IMG_20150623_163040.jpg','0.0','1435057256','0.0'),('uploads/IMG_20150616_152336.jpg','28.563368','1434448438','77.229483'),('','28.560265','1434540825','77.164286'),('','29.482022','1434979214','77.70155'),('uploads/IMG_20150622_231342.jpg','29.486463','1434995033','77.696602'),('uploads/IMG_20150623_164225.jpg','28.509297','1435057962','77.179813'),('uploads/IMG_20150623_165743.jpg','28.527203','1435058879','77.217092'),('uploads/IMG_20150624_104642.jpg','28.528917','1435123013','77.152065'),('uploads/IMG_20150624_104748.jpg','28.522816','1435123082','77.163709'),('uploads/IMG_20150624_111258.jpg','28.528262','1435124591','77.201957'),('uploads/IMG_20150624_120525.jpg','28.583334','1435127739','77.205833'),('uploads/IMG_20150624_121448.jpg','28.583334','1435128302','77.205833'),('uploads/IMG_20150624_121506.jpg','28.583334','1435128318','77.205833'),('uploads/IMG_20150624_162629.jpg','28.583334','1435143408','77.205833'),('uploads/IMG_20150624_162651.jpg','28.583334','1435143424','77.205833'),('uploads/IMG_20150624_183401.jpg','28.583334','1435151055','77.205833'),('uploads/IMG_20150624_183417.jpg','28.583334','1435151070','77.205833'),('uploads/IMG_20150624_183436.jpg','28.583334','1435151090','77.205833'),('uploads/IMG_20150628_160358.jpg','28.583334','1435487651','77.205833'),('uploads/IMG_20150628_160428.jpg','28.583334','1435487681','77.205833'),('uploads/IMG_20150629_163940.jpg','28.583334','1435576204','77.205833'),('uploads/IMG_20150629_164159.jpg','28.583334','1435576334','77.205833'),('uploads/IMG_20150629_184015.jpg','28.523972','1435583431','77.207075'),('uploads/IMG_20150630_163837.jpg','28.523972','1435662556','77.207075'),('uploads/IMG_20150630_165022.jpg','28.572578','1435663242','77.173523'),('uploads/IMG_20150630_165629.jpg','28.523972','1435663608','77.207075'),('uploads/IMG_20150630_165653.jpg','28.523972','1435663631','77.207075'),('uploads/IMG_20150630_165719.jpg','28.523972','1435663657','77.207075'),('uploads/IMG_20150630_165719.jpg','28.523972','1435663661','77.207075'),('uploads/IMG_20150703_121425.jpg','28.583334','1435905898','77.205833'),('uploads/IMG_20150703_121425.jpg','28.583334','1435905910','77.205833'),('uploads/IMG_20150709_174143.jpg','28.5446549','1436443942','77.1900132'),('uploads/IMG_20150709_174437.jpg','28.5446549','1436444118','77.1900132'),('uploads/IMG_20150709_174437.jpg','28.5446549','1436444135','77.1900132'),('uploads/IMG_20150716_142115.jpg','28.5447375','1437036709','77.1913574'),('uploads/IMG_20150720_105110.jpg','28.5447572','1437369690','77.1913218'),('uploads/IMG_20150720_105149.jpg','28.544759','1437369720','77.1913381'),('uploads/IMG_20150720_105209.jpg','28.5447515','1437369741','77.1912964'),('uploads/IMG_20150720_105256.jpg','28.5447481','1437369789','77.1913127'),('uploads/IMG_20150722_232029.jpg','28.7252393','1437587464','77.0993146');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register`
--

DROP TABLE IF EXISTS `register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `checkin` varchar(30) NOT NULL,
  `lat` char(30) DEFAULT NULL,
  `lng` char(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register`
--

LOCK TABLES `register` WRITE;
/*!40000 ALTER TABLE `register` DISABLE KEYS */;
INSERT INTO `register` VALUES (1,'chhavi','chhavi.agarwal2208@gmail.com','*****','yes','28.54475','77.1913063'),(2,'aakash','aakashgoel12@gmail.com','****','yes','28.5447218','77.1913387'),(3,'anam','anamj09@gmail.com','******','yes','0.0','0.0'),(4,'satabdy','satabdy@gmail.com','*****','yes','28.5447441','77.1913303'),(5,'yasha','yasha.agarwal13@gmail.com','******','yes','28.542042','77.185589'),(6,'udit','udit.18garg@gmail.com','*****','yes','0.0','0.0'),(7,'vidit','viditagg@gmail.com','*******','yes','28.553691','77.194115'),(8,'Ashul','anshulji.garg@gmail.com','****','yes','28.538552','77.198768'),(9,'Ankit','ankit2081@gmail.com','******','yes','28.544884','77.191443'),(10,'Asif','asif.jawed@gmail.com','*****','yes','28.547593','77.184507'),(11,'shubham','sahu.shubham@gmail.com','*****','yes','0.0','0.0'),(12,'Megha','megha.gupta1995@gmail.com','*******','yes','28.538579','77.198782'),(13,'Jasmeet','jassi.jmit8@gmail.com','******','yes','28.5447441','77.191322'),(20,'adit','aa@gmail.com','lll','',NULL,NULL),(21,'adity','a@c.com','jjj','yes','28.7240646','77.0949558'),(22,'me','me@gmail.com','mere','yes','28.5447396','77.1913033');
/*!40000 ALTER TABLE `register` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-24 13:02:48
