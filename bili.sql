/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 8.0.11 : Database - bilibili
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bilibili` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bilibili`;

/*Table structure for table `bili` */

DROP TABLE IF EXISTS `bili`;

CREATE TABLE `bili` (
  `title` text,
  `title_image` text,
  `up` text,
  `info` text,
  `p_and_address` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `play_number` text,
  `bullet_number` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bili` */

insert  into `bili`(`title`,`title_image`,`up`,`info`,`p_and_address`,`play_number`,`bullet_number`) values ('2012地球便当之日宣传片','<meta data-vue-meta=\"true\" itemprop=\"image\" content=\"http://i1.hdslb.com/bfs/archive/9d916d187a48a85febfd6cfdbd1e527d63f177c4.jpg\">','碧诗','ohyeah.导演!快给全人类发便当~','null:https://www.bilibili.com/video/av7/?p1 ','2147761','50609'),('[思密达]无敌金馆长.','<meta data-vue-meta=\"true\" itemprop=\"image\" content=\"http://i0.hdslb.com/bfs/archive/00a48770fc0a2f57934e2c64a73e9f8128851235.jpg\">','碧诗',' !噢类挖,思密达! ','null:https://www.bilibili.com/video/av9/?p1 ','1768746','11641');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
