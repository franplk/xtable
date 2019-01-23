-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: x_report
-- ------------------------------------------------------
-- Server version	5.7.23

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
-- Table structure for table `xv_dashboard`
--

DROP TABLE IF EXISTS `xv_dashboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xv_dashboard` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `dim_id` int(4) DEFAULT NULL,
  `board` varchar(32) DEFAULT NULL,
  `datespan` varchar(16) DEFAULT 'before',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xv_dashboard`
--

LOCK TABLES `xv_dashboard` WRITE;
/*!40000 ALTER TABLE `xv_dashboard` DISABLE KEYS */;
INSERT INTO `xv_dashboard` VALUES (1001,'quality_city',1001,NULL,'curr'),(1002,'quality_industry',1002,NULL,'curr'),(1003,'qulaity_education',1003,NULL,'curr'),(1004,'quality_sex',1004,NULL,'curr');
/*!40000 ALTER TABLE `xv_dashboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xv_dashboard_drill`
--

DROP TABLE IF EXISTS `xv_dashboard_drill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xv_dashboard_drill` (
  `board_id` int(8) NOT NULL,
  `drill_id` int(8) NOT NULL,
  `drill_name` varchar(16) NOT NULL,
  `drill_label` varchar(16) NOT NULL,
  `drill_title` varchar(16) NOT NULL DEFAULT 'New Tab',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1044 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xv_dashboard_drill`
--

LOCK TABLES `xv_dashboard_drill` WRITE;
/*!40000 ALTER TABLE `xv_dashboard_drill` DISABLE KEYS */;
INSERT INTO `xv_dashboard_drill` VALUES (1002,1001,'city','城市','城市分布',1001),(1001,1002,'industry','行业','行业分析',1002),(1001,1003,'education','学历','学历分析',1003),(1001,1004,'sex','性别','性别分析',1004),(1002,1003,'education','学历','学历分析',1005),(1002,1004,'sex','性别','性别分析',1006),(1003,1001,'city','城市','城市分布',1031),(1003,1002,'industry','行业','行业分析',1032),(1003,1004,'sex','性别','性别分析',1034),(1004,1001,'city','城市','城市分布',1041),(1004,1002,'industry','行业','行业分布',1042),(1004,1003,'education','学历','学历分析',1043);
/*!40000 ALTER TABLE `xv_dashboard_drill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xv_dashboard_rel`
--

DROP TABLE IF EXISTS `xv_dashboard_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xv_dashboard_rel` (
  `dashboard_id` int(4) NOT NULL,
  `data_model_id` int(4) NOT NULL,
  `chart_id` int(4) DEFAULT NULL,
  `filter_id` int(4) NOT NULL DEFAULT '1',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `dashbaord` (`dashboard_id`),
  KEY `datamodel` (`data_model_id`),
  KEY `chart` (`chart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xv_dashboard_rel`
--

LOCK TABLES `xv_dashboard_rel` WRITE;
/*!40000 ALTER TABLE `xv_dashboard_rel` DISABLE KEYS */;
INSERT INTO `xv_dashboard_rel` VALUES (1001,1001,NULL,1000,1),(1002,1002,NULL,1000,2),(1003,1003,NULL,1000,3),(1004,1004,NULL,1000,4);
/*!40000 ALTER TABLE `xv_dashboard_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xv_datamodel`
--

DROP TABLE IF EXISTS `xv_datamodel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xv_datamodel` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `ord_id` int(4) DEFAULT NULL COMMENT 'OrderField',
  `is_sum` int(2) NOT NULL DEFAULT '1',
  `theme_id` int(4) NOT NULL COMMENT 'ThemeID',
  `description` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `datatheme` (`theme_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xv_datamodel`
--

LOCK TABLES `xv_datamodel` WRITE;
/*!40000 ALTER TABLE `xv_datamodel` DISABLE KEYS */;
INSERT INTO `xv_datamodel` VALUES (1001,1001,0,1001,'quality_city'),(1002,1002,0,1001,'quality_industry'),(1003,1003,0,1001,'quality_education'),(1004,1004,0,1001,'quality_sex');
/*!40000 ALTER TABLE `xv_datamodel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xv_filter_condition`
--

DROP TABLE IF EXISTS `xv_filter_condition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xv_filter_condition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filter_id` int(8) NOT NULL,
  `col_id` int(8) NOT NULL,
  `level` int(2) NOT NULL DEFAULT '1',
  `code` int(4) NOT NULL DEFAULT '1',
  `type_id` int(8) NOT NULL DEFAULT '1',
  `value_id` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xv_filter_condition`
--

LOCK TABLES `xv_filter_condition` WRITE;
/*!40000 ALTER TABLE `xv_filter_condition` DISABLE KEYS */;
INSERT INTO `xv_filter_condition` VALUES (1001,1000,1001,2,1,4,NULL),(1002,1000,1002,2,2,4,NULL),(1003,1000,1003,2,3,4,NULL),(1004,1000,1004,2,4,4,NULL),(1005,1000,1005,2,5,1,NULL),(1006,1000,1006,2,6,1,NULL),(1007,1000,1006,2,7,1,NULL);
/*!40000 ALTER TABLE `xv_filter_condition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xv_filter_type`
--

DROP TABLE IF EXISTS `xv_filter_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xv_filter_type` (
  `id` int(4) NOT NULL,
  `value` varchar(32) NOT NULL,
  `text` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xv_filter_type`
--

LOCK TABLES `xv_filter_type` WRITE;
/*!40000 ALTER TABLE `xv_filter_type` DISABLE KEYS */;
INSERT INTO `xv_filter_type` VALUES (1,'gte','大于等于'),(1,'gt','大于'),(1,'eq','等于'),(1,'lte','小于等于'),(1,'lt','小于'),(2,'inc','包含'),(3,'span','区间为'),(2,'eq','等于'),(4,'eq','等于'),(5,'inc','等于');
/*!40000 ALTER TABLE `xv_filter_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xv_filter_value`
--

DROP TABLE IF EXISTS `xv_filter_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xv_filter_value` (
  `id` int(8) NOT NULL,
  `value` varchar(32) NOT NULL,
  `text` varchar(32) NOT NULL,
  `code` int(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xv_filter_value`
--

LOCK TABLES `xv_filter_value` WRITE;
/*!40000 ALTER TABLE `xv_filter_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `xv_filter_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xv_menu`
--

DROP TABLE IF EXISTS `xv_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xv_menu` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `url` varchar(256) DEFAULT NULL,
  `subject` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `code` int(11) NOT NULL COMMENT 'order code',
  `valid` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xv_menu`
--

LOCK TABLES `xv_menu` WRITE;
/*!40000 ALTER TABLE `xv_menu` DISABLE KEYS */;
INSERT INTO `xv_menu` VALUES (500,'我的模版',NULL,500,1,1,1),(1000,'收入消费','',1000,1,1,1),(1001,'城市维度','/board/1001',1000,2,1,1),(1002,'行业维度','/board/1002',1000,2,2,1),(1003,'学历维度','/board/1003',1000,2,3,1),(1004,'性别维度','/board/1004',1000,2,4,1);
/*!40000 ALTER TABLE `xv_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xv_metadata_column`
--

DROP TABLE IF EXISTS `xv_metadata_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xv_metadata_column` (
  `id` int(4) NOT NULL,
  `name` varchar(32) NOT NULL,
  `label` varchar(32) NOT NULL,
  `dim` int(2) NOT NULL,
  `datatype` varchar(16) DEFAULT NULL,
  `formula` varchar(64) DEFAULT NULL,
  `relyon` varchar(32) DEFAULT NULL COMMENT 'Display Together',
  `ex_value` varchar(16) DEFAULT NULL,
  `mapname` varchar(32) DEFAULT NULL,
  `maptitle` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xv_metadata_column`
--

LOCK TABLES `xv_metadata_column` WRITE;
/*!40000 ALTER TABLE `xv_metadata_column` DISABLE KEYS */;
INSERT INTO `xv_metadata_column` VALUES (1001,'city','城市',1,'map',NULL,NULL,NULL,NULL,NULL),(1002,'industry','职业',1,'map',NULL,NULL,NULL,NULL,NULL),(1003,'education','教育',1,'map',NULL,NULL,NULL,NULL,NULL),(1004,'sex','性别',1,'map',NULL,NULL,NULL,NULL,NULL),(1005,'salary','平均工资',0,'digit','avg(salary)',NULL,NULL,NULL,NULL),(1006,'cost','平均消费',0,'digit','avg(cost)',NULL,NULL,NULL,NULL),(1007,'savings','年积蓄',0,'digit','avg(salary-cost)','salary,cost',NULL,NULL,NULL);
/*!40000 ALTER TABLE `xv_metadata_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xv_template`
--

DROP TABLE IF EXISTS `xv_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xv_template` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `title` varchar(32) CHARACTER SET gbk NOT NULL,
  `board` varchar(16) NOT NULL,
  `ord_idx` varchar(16) DEFAULT NULL,
  `rel_idx` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xv_template`
--

LOCK TABLES `xv_template` WRITE;
/*!40000 ALTER TABLE `xv_template` DISABLE KEYS */;
INSERT INTO `xv_template` VALUES (1,'我的测试','6','clk','bid'),(2,'我的测试','6','clk','bid'),(3,'我的测试','6','clk','bid');
/*!40000 ALTER TABLE `xv_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xv_theme`
--

DROP TABLE IF EXISTS `xv_theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xv_theme` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT 'table name',
  `date_key` varchar(32) NOT NULL DEFAULT 'rep_date',
  `table_strategy` int(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xv_theme`
--

LOCK TABLES `xv_theme` WRITE;
/*!40000 ALTER TABLE `xv_theme` DISABLE KEYS */;
INSERT INTO `xv_theme` VALUES (1001,'quality_life','',0);
/*!40000 ALTER TABLE `xv_theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xv_theme_column`
--

DROP TABLE IF EXISTS `xv_theme_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xv_theme_column` (
  `theme_id` int(4) NOT NULL,
  `col_id` int(4) NOT NULL,
  `flag` int(2) NOT NULL DEFAULT '1',
  `sorting` int(2) DEFAULT NULL,
  `code` int(2) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xv_theme_column`
--

LOCK TABLES `xv_theme_column` WRITE;
/*!40000 ALTER TABLE `xv_theme_column` DISABLE KEYS */;
INSERT INTO `xv_theme_column` VALUES (1,102,0,0,101),(2,101,0,0,101),(5,106,0,0,101),(7,111,0,1,130),(7,112,0,1,160),(7,103,0,1,100),(7,119,0,1,150),(7,101,0,1,110),(1,201,1,1,101),(1,202,1,1,102),(1,203,1,1,103),(2,204,1,1,101),(2,205,0,1,102),(2,206,0,1,103),(2,207,0,1,104),(2,208,0,1,105),(2,209,0,1,106),(2,217,1,1,107),(2,220,1,1,108),(2,211,1,1,109),(2,212,1,1,110),(2,216,1,1,111),(2,210,1,1,112),(2,218,0,1,113),(2,219,1,1,114),(5,208,1,1,102),(5,209,1,1,103),(5,221,1,1,104),(5,222,1,1,105),(5,213,1,1,106),(5,223,1,1,107),(5,224,1,1,108),(5,216,1,1,109),(5,225,1,1,110),(5,226,1,1,111),(5,218,1,1,112),(5,227,1,1,113),(5,228,1,1,114),(5,229,1,1,115),(7,205,1,1,110),(7,231,0,1,120),(7,206,1,1,130),(7,232,1,0,140),(7,208,1,1,150),(7,234,0,0,180),(7,235,0,0,200),(7,236,1,0,210),(7,209,1,1,220),(7,237,1,0,230),(7,238,1,0,240),(7,210,1,0,250),(7,239,0,0,260),(7,240,0,1,270),(7,241,0,0,280),(7,242,0,1,290),(7,243,0,0,300),(7,244,0,0,310),(7,245,0,1,320),(7,246,0,0,330),(7,248,0,1,340),(7,249,0,0,350),(7,250,0,0,360),(7,251,0,1,370),(7,252,0,1,380),(7,213,0,1,400),(7,253,0,0,410),(7,216,0,1,420),(7,219,0,0,430),(7,218,0,0,440),(7,217,0,0,190),(7,233,0,0,170),(7,254,0,1,390),(7,204,0,1,100),(7,207,1,1,149),(7,247,0,0,335),(7,113,0,1,170),(7,106,2,0,180),(7,108,2,0,190),(6,101,0,1,110),(6,103,0,0,100),(6,104,0,0,120),(6,106,0,1,140),(6,107,0,1,130),(6,108,0,1,160),(6,111,0,1,180),(6,112,0,1,200),(6,113,0,1,190),(6,205,1,1,110),(6,206,1,1,130),(6,207,1,1,150),(6,208,1,1,160),(6,209,1,1,220),(6,210,1,0,250),(6,213,0,1,390),(6,216,0,1,410),(6,218,0,0,430),(6,219,0,0,420),(6,231,0,1,120),(6,232,1,0,140),(6,233,0,0,170),(6,234,0,0,180),(6,235,0,0,190),(6,236,1,0,210),(6,237,1,0,230),(6,238,1,0,240),(6,239,0,0,260),(6,240,0,1,270),(6,241,0,0,280),(6,242,0,1,290),(6,243,0,0,300),(6,244,0,0,310),(6,245,0,1,320),(6,246,0,0,330),(6,247,0,0,335),(6,248,0,1,340),(6,249,0,0,350),(6,250,0,0,360),(6,251,0,1,370),(6,252,0,1,380),(6,253,0,0,400),(6,254,0,1,385),(7,117,0,1,140),(502,502,1,0,20),(502,503,1,0,40),(502,504,1,0,50),(502,505,1,0,60),(502,508,1,0,90),(502,511,1,0,100),(501,513,1,0,10),(501,509,1,0,20),(501,510,1,0,30),(501,503,1,0,40),(501,504,1,0,50),(501,505,1,0,60),(501,508,1,0,90),(501,511,1,0,100),(502,512,1,0,10),(502,513,1,0,15),(7,107,2,0,200),(6,121,0,1,170),(7,121,2,0,210),(1001,1001,1,0,101),(1001,1002,1,0,102),(1001,1003,1,0,103),(1001,1004,1,0,104),(1001,1005,1,1,105),(1001,1006,1,1,106),(1001,1007,1,1,107);
/*!40000 ALTER TABLE `xv_theme_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xv_theme_drill`
--

DROP TABLE IF EXISTS `xv_theme_drill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xv_theme_drill` (
  `theme_id` int(4) NOT NULL,
  `drill_id` int(4) NOT NULL,
  `drill_name` varchar(32) NOT NULL,
  `drill_label` varchar(32) NOT NULL,
  `drill_title` varchar(32) NOT NULL,
  `code` int(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xv_theme_drill`
--

LOCK TABLES `xv_theme_drill` WRITE;
/*!40000 ALTER TABLE `xv_theme_drill` DISABLE KEYS */;
INSERT INTO `xv_theme_drill` VALUES (6,6,'rep_date','日期','日统计',10),(6,7,'hour_id','时段','时段统计',20),(6,9,'project_id','项目','项目统计',40),(6,10,'campaign_id','活动','活动统计',50),(6,14,'channel_id','渠道','渠道分析',60),(6,8,'category_id','行业','行业统计',30),(6,12,'trade_type','交易方式','交易方式',80),(6,13,'flow_type','流量类型','流量类型',90),(6,17,'adslot_id','广告位','广告位分析',110),(6,16,'domain','域名','域名(PC/APP)',100),(6,31,'creative_id','创意','创意统计',70),(7,6,'rep_date','日期','日统计',10),(7,7,'hour_id','时段','时段统计',20),(7,8,'category_id','行业','行业统计',30),(7,9,'project_id','项目','项目统计',40),(7,10,'campaign_id','活动','活动统计',50),(7,12,'trade_type','交易方式','交易方式',80),(7,13,'flow_type','流量类型','流量类型',90),(7,14,'channel_id','渠道','渠道分析',60),(7,16,'domain','域名','域名(PC/APP)',100),(7,17,'adslot_id','广告位','广告位分析',110),(7,31,'creative_id','创意','创意统计',70);
/*!40000 ALTER TABLE `xv_theme_drill` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-23 17:17:31
