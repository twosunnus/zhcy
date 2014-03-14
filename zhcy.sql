/*
SQLyog Professional v10.42 
MySQL - 5.5.27 : Database - zhcy
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zhcy` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zhcy`;

/*Table structure for table `allotinfo` */

DROP TABLE IF EXISTS `allotinfo`;

CREATE TABLE `allotinfo` (
  `ai_id` int(11) NOT NULL AUTO_INCREMENT,
  `ji_id` int(11) DEFAULT NULL,
  `mi_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ai_id`),
  UNIQUE KEY `ai_id` (`ai_id`)
) ENGINE=InnoDB AUTO_INCREMENT=222 DEFAULT CHARSET=utf8;

/*Data for the table `allotinfo` */

insert  into `allotinfo`(`ai_id`,`ji_id`,`mi_id`) values (214,1,1),(215,1,2),(216,1,3),(217,1,4),(218,1,5),(219,1,6),(220,1,7),(221,1,8);

/*Table structure for table `dishesinfo` */

DROP TABLE IF EXISTS `dishesinfo`;

CREATE TABLE `dishesinfo` (
  `di_id` int(11) NOT NULL AUTO_INCREMENT,
  `di_flag` int(11) DEFAULT NULL,
  `di_lastprice` double DEFAULT NULL,
  `di_name` varchar(40) DEFAULT NULL,
  `di_nowprice` double DEFAULT NULL,
  `di_num` int(11) DEFAULT NULL,
  `ui_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`di_id`),
  UNIQUE KEY `di_id` (`di_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dishesinfo` */

/*Table structure for table `dishestypeinfo` */

DROP TABLE IF EXISTS `dishestypeinfo`;

CREATE TABLE `dishestypeinfo` (
  `dti_id` int(11) NOT NULL AUTO_INCREMENT,
  `dti_name` varchar(40) DEFAULT NULL,
  `ui_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dti_id`),
  UNIQUE KEY `dti_id` (`dti_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dishestypeinfo` */

/*Table structure for table `dishestyperelation` */

DROP TABLE IF EXISTS `dishestyperelation`;

CREATE TABLE `dishestyperelation` (
  `dtr_id` int(11) NOT NULL AUTO_INCREMENT,
  `dt_id` int(11) DEFAULT NULL,
  `dti_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dtr_id`),
  UNIQUE KEY `dtr_id` (`dtr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dishestyperelation` */

/*Table structure for table `jobinfo` */

DROP TABLE IF EXISTS `jobinfo`;

CREATE TABLE `jobinfo` (
  `ji_id` int(11) NOT NULL AUTO_INCREMENT,
  `ji_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ji_id`),
  UNIQUE KEY `ji_id` (`ji_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `jobinfo` */

insert  into `jobinfo`(`ji_id`,`ji_name`) values (1,'admin'),(2,'普通商家');

/*Table structure for table `menuinfo` */

DROP TABLE IF EXISTS `menuinfo`;

CREATE TABLE `menuinfo` (
  `mi_id` int(11) NOT NULL AUTO_INCREMENT,
  `checked` varchar(255) DEFAULT NULL,
  `mi_name` varchar(50) DEFAULT NULL,
  `mi_order` int(11) DEFAULT NULL,
  `mi_parentid` int(11) DEFAULT NULL,
  `mi_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`mi_id`),
  UNIQUE KEY `mi_id` (`mi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `menuinfo` */

insert  into `menuinfo`(`mi_id`,`checked`,`mi_name`,`mi_order`,`mi_parentid`,`mi_url`) values (1,'','系统管理',0,0,NULL),(2,'','职位菜单配置',1,1,'allotInfoAction.do?method=look'),(3,'','菜单配置',2,1,'menuTreeAction.do?method=lookMenuTree'),(4,'','职位管理',3,1,'jobInfoAction.do?method=look'),(5,'','用户管理',4,1,'userInfoAction.do?method=look'),(6,NULL,'商户管理',1,0,''),(7,NULL,'菜品类别管理',1,6,''),(8,NULL,'菜品管理',2,6,'');

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `ui_id` int(11) NOT NULL AUTO_INCREMENT,
  `ui_basepassword` varchar(50) DEFAULT NULL,
  `ui_ji_id` varchar(50) DEFAULT NULL,
  `ui_password` varchar(50) DEFAULT NULL,
  `ui_restaurantno` varchar(50) DEFAULT NULL,
  `ui_username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ui_id`),
  UNIQUE KEY `ui_id` (`ui_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `userinfo` */

insert  into `userinfo`(`ui_id`,`ui_basepassword`,`ui_ji_id`,`ui_password`,`ui_restaurantno`,`ui_username`) values (1,NULL,'1','ISMvKXpXpadDiUoOSoAfww==','admin','admin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
