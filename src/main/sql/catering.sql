/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.26 : Database - catering
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`catering` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `catering`;

/*Table structure for table `appointment` */

DROP TABLE IF EXISTS `appointment`;

CREATE TABLE `appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(11) NOT NULL DEFAULT '1',
  `room_id` int(11) NOT NULL,
  `room_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `room_type` int(11) DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `modifier` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `appointment` */

insert  into `appointment`(`id`,`status`,`room_id`,`room_name`,`room_type`,`created`,`modified`,`creator`,`modifier`,`start_time`,`end_time`) values
(1,-1,1,'大厅1号桌',1,'2021-04-25 20:31:52','2021-04-25 20:35:01','admin','admin','2021-04-25 08:00:00','2021-04-25 10:00:00'),
(2,1,1,'大厅1号桌',1,'2021-04-25 20:32:44','2021-04-25 20:32:44','admin','admin','2021-04-25 10:00:00','2021-04-25 12:00:00');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `level` int(11) NOT NULL DEFAULT '1',
  `status` int(11) NOT NULL DEFAULT '1',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifier` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `category` */

insert  into `category`(`id`,`name`,`parent_id`,`remark`,`level`,`status`,`created`,`modified`,`creator`,`modifier`) values
(2,'一级分类',0,NULL,1,-1,'2021-03-06 21:10:46','2021-03-06 21:10:46','admin','admin'),
(3,'二级分类',2,NULL,2,-1,'2021-03-06 21:11:12','2021-03-06 21:11:12','admin','admin'),
(4,'二级分类2',2,NULL,2,-1,'2021-03-06 21:12:28','2021-03-06 21:12:28','admin','admin'),
(5,'二级分类3',2,NULL,2,-1,'2021-03-06 21:12:31','2021-03-06 21:12:31','admin','admin'),
(6,'一级分类2',0,NULL,1,-1,'2021-03-06 21:12:48','2021-03-06 21:12:48','admin','admin'),
(7,'小吃',0,NULL,1,1,'2021-03-20 15:03:07','2021-03-20 15:03:07','admin','admin'),
(8,'早餐',0,NULL,1,1,'2021-03-20 15:03:52','2021-03-20 15:03:52','admin','admin'),
(9,'陕菜',0,NULL,1,1,'2021-03-20 15:05:04','2021-03-20 15:05:04','admin','admin'),
(10,'陕北',0,NULL,1,-1,'2021-03-20 15:05:21','2021-03-20 15:05:21','admin','admin'),
(11,'陕北',0,NULL,1,-1,'2021-03-20 15:05:35','2021-03-20 15:05:35','admin','admin'),
(12,'陕北美食',9,NULL,2,-1,'2021-03-20 15:06:38','2021-03-20 15:06:38','admin','admin'),
(13,'陕北美食',9,NULL,2,1,'2021-03-20 15:07:01','2021-03-20 15:07:01','admin','admin'),
(14,'关中美食',9,NULL,2,1,'2021-03-20 15:14:33','2021-03-20 15:14:33','admin','admin'),
(15,'陕南美食',9,NULL,2,1,'2021-03-20 15:14:42','2021-03-20 15:14:42','admin','admin'),
(16,'小吃',7,NULL,2,1,'2021-03-20 15:14:50','2021-03-20 15:14:50','admin','admin'),
(17,'早餐',8,NULL,2,1,'2021-03-20 15:14:57','2021-03-20 15:14:57','admin','admin');

/*Table structure for table `dishes` */

DROP TABLE IF EXISTS `dishes`;

CREATE TABLE `dishes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(512) COLLATE utf8_unicode_ci DEFAULT '暂无描述',
  `price` double(10,3) NOT NULL COMMENT '售卖价',
  `taste` varchar(256) COLLATE utf8_unicode_ci DEFAULT '暂无描述' COMMENT ',',
  `url` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) DEFAULT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifier` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `category` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `cost_price` double(10,3) NOT NULL COMMENT '成本价',
  `unit` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dishes` */

insert  into `dishes`(`id`,`name`,`description`,`price`,`taste`,`url`,`status`,`created`,`modified`,`creator`,`modifier`,`category_id`,`category`,`cost_price`,`unit`) values
(1,'烤羊腿','暂无描述',200.000,'暂无描述','/static/929aecb2f77f481288a034fd1b856dc1.jpg',-1,'2021-03-06 23:34:28','2021-03-06 23:34:28','admin','admin',3,'二级分类',100.100,''),
(2,'烤羊腿','暂无描述',200.000,'暂无描述','/static/929aecb2f77f481288a034fd1b856dc1.jpg',2,'2021-04-11 19:26:11','2021-04-11 19:26:11','admin','admin',3,'二级分类',100.100,''),
(3,'烤羊腿','暂无描述',200.000,'暂无描述','/static/929aecb2f77f481288a034fd1b856dc1.jpg',1,'2021-03-06 23:36:45','2021-03-06 23:36:45','admin','admin',3,'二级分类',100.100,''),
(4,'烤羊腿','暂无描述',200.000,'暂无描述','/static/929aecb2f77f481288a034fd1b856dc1.jpg',1,'2021-03-06 23:36:46','2021-03-06 23:36:46','admin','admin',3,'二级分类',100.100,''),
(5,'烤羊腿','暂无描述',200.000,'暂无描述','/static/929aecb2f77f481288a034fd1b856dc1.jpg',1,'2021-03-06 23:36:46','2021-03-06 23:36:46','admin','admin',3,'二级分类',100.100,''),
(6,'烤羊腿','暂无描述',200.000,'暂无描述','/static/929aecb2f77f481288a034fd1b856dc1.jpg',1,'2021-03-06 23:36:47','2021-03-06 23:36:47','admin','admin',3,'二级分类',100.100,''),
(7,'烤羊腿','暂无描述',200.000,'暂无描述','/static/929aecb2f77f481288a034fd1b856dc1.jpg',2,'2021-04-11 19:26:13','2021-04-11 19:26:13','admin','admin',3,'二级分类',100.100,''),
(8,'烧羊蹄','暂无描述',10.000,'暂无描述','/static/116b5457799141ed8965f9b96b4370d2.jpg',3,'2021-04-11 16:03:06','2021-04-11 16:03:06','admin','admin',3,'二级分类',5.000,''),
(9,'烧羊蹄','暂无描述',10.000,'暂无描述','/static/116b5457799141ed8965f9b96b4370d2.jpg',3,'2021-04-11 16:02:56','2021-04-11 16:02:56','admin','admin',3,'二级分类',5.000,''),
(10,'烧羊蹄','暂无描述',10.000,'暂无描述','/static/116b5457799141ed8965f9b96b4370d2.jpg',2,'2021-04-11 19:26:14','2021-04-11 19:26:14','admin','admin',3,'二级分类',5.000,''),
(11,'啊实打实的a\'s','暂无描述',2.200,'暂无描述','/static/e51ec4a5d39e4306a3390863a994c070.png',-1,'2021-04-11 19:18:59','2021-04-11 19:18:59','admin','admin',16,'小吃',1.100,'一份'),
(12,'测试商品','暂无描述',3.200,'暂无描述','/static/a211a841dbf04a4b8c672f30e323b8c4.jpg',2,'2021-04-11 19:36:56','2021-04-11 19:36:56','admin','admin',16,'小吃',1.100,'一杯');

/*Table structure for table `operate_log` */

DROP TABLE IF EXISTS `operate_log`;

CREATE TABLE `operate_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `system_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '系统类型',
  `business_type` int(11) NOT NULL COMMENT '业务类型',
  `business_id` bigint(20) NOT NULL COMMENT '业务id',
  `old_data` json DEFAULT NULL COMMENT '更新前的数据',
  `new_data` json DEFAULT NULL COMMENT '更新后的数据',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '操作者',
  `version` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '版本信息',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `operate_type` int(11) NOT NULL COMMENT '操作类型',
  `request` varchar(1023) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '请求参数',
  `response` varchar(1023) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '响应参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='操作日志';

/*Data for the table `operate_log` */

/*Table structure for table `order_detail` */

DROP TABLE IF EXISTS `order_detail`;

CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dishes_id` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `total_price` double(10,3) NOT NULL,
  `dishes_price` double(10,3) NOT NULL,
  `order_id` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `dishes_name` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifier` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `order_detail` */

insert  into `order_detail`(`id`,`dishes_id`,`total`,`total_price`,`dishes_price`,`order_id`,`dishes_name`,`url`,`created`,`modified`,`creator`,`modifier`,`status`) values
(3,1,2,400.000,200.000,'bea4bf2ad1c440e28b7c625c52627a49','烤羊腿','/static/929aecb2f77f481288a034fd1b856dc1.jpg','2021-03-07 00:28:50','2021-03-07 00:28:50','admin','admin',1),
(4,10,1,10.000,10.000,'bea4bf2ad1c440e28b7c625c52627a49','烧羊蹄','/static/116b5457799141ed8965f9b96b4370d2.jpg','2021-03-07 00:28:50','2021-03-07 00:28:50','admin','admin',1),
(5,3,1,200.000,200.000,'00551c08195a46cca49346b58a47f2be','烤羊腿','/static/929aecb2f77f481288a034fd1b856dc1.jpg','2021-04-11 18:40:16','2021-04-11 18:40:16','admin','admin',1),
(6,8,1,10.000,10.000,'00551c08195a46cca49346b58a47f2be','烧羊蹄','/static/116b5457799141ed8965f9b96b4370d2.jpg','2021-04-11 18:40:16','2021-04-11 18:40:16','admin','admin',1),
(7,5,2,400.000,200.000,'00551c08195a46cca49346b58a47f2be','烤羊腿','/static/929aecb2f77f481288a034fd1b856dc1.jpg','2021-04-11 18:40:16','2021-04-11 18:40:16','admin','admin',1),
(8,3,1,200.000,200.000,'6774ef4f5a7f4a5fbb1d1a0ad40ac0b1','烤羊腿','/static/929aecb2f77f481288a034fd1b856dc1.jpg','2021-04-11 18:51:15','2021-04-11 18:51:15','admin','admin',1),
(9,12,4,12.800,3.200,'676d164d34114109b6278e2c6fb73f66','测试商品','/static/a211a841dbf04a4b8c672f30e323b8c4.jpg','2021-04-11 19:27:57','2021-04-11 19:27:57','admin','admin',1),
(10,10,1,10.000,10.000,'676d164d34114109b6278e2c6fb73f66','烧羊蹄','/static/116b5457799141ed8965f9b96b4370d2.jpg','2021-04-11 19:27:57','2021-04-11 19:27:57','admin','admin',1);

/*Table structure for table `order_table` */

DROP TABLE IF EXISTS `order_table`;

CREATE TABLE `order_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `total_price` double(10,3) NOT NULL,
  `phone` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT '无',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `modifier` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `order_table` */

insert  into `order_table`(`id`,`order_id`,`total_price`,`phone`,`address`,`remark`,`created`,`modified`,`creator`,`modifier`,`status`) values
(1,'bea4bf2ad1c440e28b7c625c52627a49',410.000,'18220573635','北京','无','2021-03-07 00:32:34','2021-03-07 00:32:34','admin','admin',1),
(2,'00551c08195a46cca49346b58a47f2be',610.000,'','','无','2021-04-11 18:40:16','2021-04-11 18:40:16','admin','admin',5),
(3,'6774ef4f5a7f4a5fbb1d1a0ad40ac0b1',200.000,'','','无','2021-04-11 18:51:15','2021-04-11 18:51:15','admin','admin',2),
(4,'676d164d34114109b6278e2c6fb73f66',22.800,'','','无','2021-04-11 19:27:57','2021-04-11 19:27:57','admin','admin',5);

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL,
  `no` int(11) DEFAULT NULL,
  `human_num` int(11) NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifier` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `room` */

insert  into `room`(`id`,`name`,`type`,`no`,`human_num`,`created`,`modified`,`creator`,`modifier`,`status`) values
(1,'大厅1号桌',1,1,4,'2021-04-25 17:33:53','2021-04-25 17:33:53',NULL,NULL,1),
(2,'大厅2号桌',1,2,4,'2021-04-25 17:34:06','2021-04-25 17:34:06',NULL,NULL,1),
(3,'大厅3号桌',1,3,4,'2021-04-25 17:34:20','2021-04-25 17:34:20',NULL,NULL,1),
(4,'大厅4号桌',1,4,4,'2021-04-25 17:34:38','2021-04-25 17:34:38',NULL,NULL,1),
(5,'扬子江',2,NULL,8,'2021-04-25 17:35:26','2021-04-25 17:35:26',NULL,NULL,1),
(6,'钓鱼台',2,NULL,16,'2021-04-25 17:35:48','2021-04-25 17:35:48',NULL,NULL,1),
(7,'测试',1,1,5,'2021-04-25 17:40:19','2021-04-25 17:42:51',NULL,NULL,-1);

/*Table structure for table `test_table` */

DROP TABLE IF EXISTS `test_table`;

CREATE TABLE `test_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `val` json NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `test_table` */

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `creator` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改者',
  `role_id` int(11) NOT NULL COMMENT '角色Id',
  `phone` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `mail` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`username`,`password`,`birthday`,`status`,`created`,`modified`,`creator`,`modifier`,`role_id`,`phone`,`mail`) values
(3,'admin','21232f297a57a5a743894a0e4a801fc3',NULL,1,'2021-04-18 19:23:27','2021-04-18 19:23:27',NULL,NULL,1,'18220573635',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
