/*
SQLyog Ultimate v9.62 
MySQL - 5.5.43-0ubuntu0.14.04.1 : Database - nfc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nfc` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `nfc`;

/*Table structure for table `CATEGORY` */

DROP TABLE IF EXISTS `CATEGORY`;

CREATE TABLE `CATEGORY` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `SHORTNAME` varchar(20) DEFAULT NULL,
  `FULLNAME` varchar(100) DEFAULT NULL,
  `SORTORDER` char(5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `CATEGORY` */

insert  into `CATEGORY`(`ID`,`SHORTNAME`,`FULLNAME`,`SORTORDER`) values (1,'VEG','Veg','11000'),(2,'NONVEG','Non-Veg','12000'),(3,'BEV','BEVERAGE','13000'),(4,'DESERTS','Deserts','14000');

/*Table structure for table `ERROR` */

DROP TABLE IF EXISTS `ERROR`;

CREATE TABLE `ERROR` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `REASON` varchar(100) NOT NULL,
  `CREATED_ON` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `ERROR` */

/*Table structure for table `MENU` */

DROP TABLE IF EXISTS `MENU`;

CREATE TABLE `MENU` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for the dish.',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of the dish',
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL COMMENT 'How much quantity is avaiable for this dish',
  `AMOUNT` decimal(6,2) NOT NULL COMMENT 'Cost of the dish till 2 decimal digits',
  `PREP_TIME` int(11) DEFAULT NULL COMMENT 'How much time its required to prepare a dish.',
  `CATEGORY` varchar(20) NOT NULL COMMENT 'Category identify the menu item falls under which category',
  `URL` varchar(100) DEFAULT NULL COMMENT 'Item image stored in our public server',
  `SORTORDER` char(5) DEFAULT '00000',
  `CREATED_ON` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'When this menu item is created',
  `CREATED_BY` varchar(20) NOT NULL COMMENT 'Who created this menu item.',
  `MODIFIED_ON` timestamp NULL DEFAULT NULL COMMENT 'When this menu item is modified.',
  `MODIFIED_BY` varchar(20) DEFAULT NULL COMMENT 'Who modified this menu item.',
  `MERCHANT_ID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

/*Data for the table `MENU` */

insert  into `MENU`(`ID`,`NAME`,`DESCRIPTION`,`QUANTITY`,`AMOUNT`,`PREP_TIME`,`CATEGORY`,`URL`,`SORTORDER`,`CREATED_ON`,`CREATED_BY`,`MODIFIED_ON`,`MODIFIED_BY`,`MERCHANT_ID`) values (28,'XYZ','Hello Welcome Here..',1,'1.00',1,'VEG','null','null','2015-04-26 21:25:20','Kamal Joshi','2015-04-26 00:00:00','Kamal Joshi',1002),(31,'PQR','Hello PQRS',1,'1.00',1,'DESERTS','null','null','2015-04-26 19:32:59','Kamal Joshi','2015-04-26 00:00:00','Kamal Joshi',1002),(32,'XYZ','Hello',1,'1.00',1,'BEV','null','null','2015-04-26 00:00:00','Kamal Joshi','2015-04-26 00:00:00','Kamal Joshi',1002);

/*Table structure for table `MERCHANT` */

DROP TABLE IF EXISTS `MERCHANT`;

CREATE TABLE `MERCHANT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `BusinessName` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Mobile` varchar(20) DEFAULT NULL,
  `Landline` varchar(20) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `State` varchar(50) DEFAULT NULL,
  `Pincode` varchar(50) DEFAULT NULL,
  `website` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `MERCHANT` */

insert  into `MERCHANT`(`ID`,`FirstName`,`LastName`,`BusinessName`,`Email`,`Mobile`,`Landline`,`City`,`State`,`Pincode`,`website`) values (3,'Jitendra','Mathur','Waah ji Food','jeet427@gmail.com','9899050514','01127488990','Rohini','Delhi','110089','www.jeet427.com'),(4,'Gaurav','Oli','Waah ji Food1','xxxx@gmail.com','9899050514','01127488990','Rohini','Delhi','110089','www.jeet427.com'),(5,'Raj','Kumar','Waah ji Food4','aaaaa@gmail.com','9899050514','01127488990','Rohini','Delhi','110089','www.jeet427.com'),(6,'Ankit','Seth','Waah ji Food5','bbbbb@gmail.com','9899050514','01127488990','Rohini','Delhi','110089','www.jeet427.com'),(7,'Anil','Sharma','Waah ji Food6','ccccc@gmail.com','9899050514','01127488990','Rohini','Delhi','110089','www.jeet427.com'),(8,'Saurabh','Jain','Waah ji Food7','dddd@gmail.com','9899050514','01127488990','Rohini','Delhi','110089','www.jeet427.com');

/*Table structure for table `ORDERS` */

DROP TABLE IF EXISTS `ORDERS`;

CREATE TABLE `ORDERS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for Order.',
  `ORDER_ID` varchar(40) NOT NULL COMMENT 'This order id will remain common for complete order.',
  `TABLE_ID` int(11) NOT NULL COMMENT 'This indicate the table numner',
  `MENU_ID` int(11) NOT NULL COMMENT 'Menu IDs are stored here as comma seprated values. As we can have multiple menu id for the same order.',
  `QUANTITY` int(11) NOT NULL COMMENT 'Quantity of the menu item',
  `AMOUNT` decimal(6,2) NOT NULL COMMENT 'This amount doesn''t include S.Tax & VAT till 2 decimal digits.',
  `STATUS` varchar(8) DEFAULT NULL,
  `CREATED_ON` timestamp NULL DEFAULT NULL COMMENT 'This is the date when this order is placed.',
  `MODIFIED_ON` timestamp NULL DEFAULT NULL COMMENT 'This is the date when this order is modified.',
  `MERCHANT_ID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `ORDERS` */

insert  into `ORDERS`(`ID`,`ORDER_ID`,`TABLE_ID`,`MENU_ID`,`QUANTITY`,`AMOUNT`,`STATUS`,`CREATED_ON`,`MODIFIED_ON`,`MERCHANT_ID`) values (1,'7d148a03-a104-4fd1-8b42-dc20d1caa2b1',5001,3,1,'1.00','ACTIVE','2015-04-13 22:36:46','2015-04-13 22:36:46',1002),(2,'7d148a03-a104-4fd1-8b42-dc20d1caa2b1',5001,3,1,'1.00','ACTIVE','2015-04-13 22:49:05','2015-04-13 22:49:05',1002),(3,'7d148a03-a104-4fd1-8b42-dc20d1caa2b1',5001,3,1,'1.00','ACTIVE','2015-04-13 23:24:24','2015-04-13 23:24:24',1002),(4,'7d148a03-a104-4fd1-8b42-dc20d1caa2b1',5001,3,1,'1.00','ACTIVE','2015-04-13 23:29:51','2015-04-13 23:29:51',1002);

/*Table structure for table `PMENU` */

DROP TABLE IF EXISTS `PMENU`;

CREATE TABLE `PMENU` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `uri` varchar(80) DEFAULT NULL,
  `Role_id` tinyint(1) DEFAULT '0',
  `identifier` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `PMENU` */

insert  into `PMENU`(`id`,`name`,`uri`,`Role_id`,`identifier`) values (1,'Dashboard','/admin/dashboardView',1,'1100000000'),(2,'Merchants','',1,'1200000000'),(3,'Add Merchant','/views/admin/addMerchant.html',1,'1211000000'),(4,'List Merchants','/admin/getMerchantList?msg',1,'1212000000'),(5,'Transaction','',1,'1300000000'),(6,'View Transaction','',1,'1311000000'),(7,'Orders','',1,'1400000000'),(8,'View Orders','',1,'1411000000'),(9,'Analyse Orders','',1,'1412000000'),(10,'Transaction','/merchant/transactions',2,'2100000000'),(12,'Orders History','/merchant/orders',2,'2211000000'),(15,'Logout','/logout',9,'8100000000'),(16,'Manage Menu','/merchant/addMenu',2,'2500000000'),(20,'Manage Orders',NULL,2,'2200000000'),(21,'Active Orders',NULL,2,'2212000000');

/*Table structure for table `ROLE` */

DROP TABLE IF EXISTS `ROLE`;

CREATE TABLE `ROLE` (
  `id` int(11) NOT NULL,
  `rolename` varchar(45) DEFAULT NULL,
  `grantid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rolename_UNIQUE` (`rolename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ROLE` */

insert  into `ROLE`(`id`,`rolename`,`grantid`) values (1,'Admin',NULL),(2,'Merchant',NULL);

/*Table structure for table `STATUS` */

DROP TABLE IF EXISTS `STATUS`;

CREATE TABLE `STATUS` (
  `ID` int(5) NOT NULL AUTO_INCREMENT,
  `MESSAGE` varchar(50) DEFAULT NULL,
  `CODE` int(4) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `STATUS` */

insert  into `STATUS`(`ID`,`MESSAGE`,`CODE`,`DESCRIPTION`) values (1,'ACTIVE',0,'Table is occupied'),(2,'INACTIVE',1,'Table is free'),(3,'SUCCESS',200,'Request completed successfully'),(4,'FAIL',400,'Request failed');

/*Table structure for table `TXNHISTORY` */

DROP TABLE IF EXISTS `TXNHISTORY`;

CREATE TABLE `TXNHISTORY` (
  `TXN_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` varchar(40) NOT NULL,
  `TAX_AMT` double DEFAULT NULL,
  `SHIPPING_AMT` double DEFAULT NULL,
  `ORDER_AMT` double DEFAULT NULL,
  `TOTAL_AMT` double DEFAULT NULL,
  `BANK_TXN_ID` varchar(20) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `CREATED_ON` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `MERCHANT_ID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TXN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `TXNHISTORY` */

insert  into `TXNHISTORY`(`TXN_ID`,`ORDER_ID`,`TAX_AMT`,`SHIPPING_AMT`,`ORDER_AMT`,`TOTAL_AMT`,`BANK_TXN_ID`,`STATUS`,`CREATED_ON`,`CREATED_BY`,`MERCHANT_ID`) values (4,'6549ea53-8506-4e73-807a-b0536474e545',0,0,0,862,'null',111,'2015-04-23 01:08:08','System',1002),(8,'7d148a03-a104-4fd1-8b42-dc20d1caa2b1',0,0,0,4,'null',111,'2015-04-23 01:08:13','System',1002);

/*Table structure for table `USER` */

DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `loginname` varchar(45) DEFAULT NULL,
  `email` varchar(70) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginname_UNIQUE` (`loginname`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8;

/*Data for the table `USER` */

insert  into `USER`(`id`,`username`,`password`,`status`,`loginname`,`email`,`dob`,`created_by`,`created_time`,`updated_by`,`updated_time`) values (1001,'Gaurav Oli','pass@123','ACTIVE','gaurav','gauravoli@live.in','1985-05-16',NULL,NULL,NULL,NULL),(1002,'Kamal Joshi','pass@123','ACTIVE','kamal','jjoshikkamal@gmail.com','1986-02-19',NULL,NULL,NULL,NULL);

/*Table structure for table `USERSANDROLES` */

DROP TABLE IF EXISTS `USERSANDROLES`;

CREATE TABLE `USERSANDROLES` (
  `user_id` int(11) NOT NULL,
  `role_id` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `USERSANDROLES` */

insert  into `USERSANDROLES`(`user_id`,`role_id`) values (1001,'1'),(1002,'2');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
