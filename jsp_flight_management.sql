/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : jsp_flight_management

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-01-08 19:55:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_airplane`
-- ----------------------------
DROP TABLE IF EXISTS `t_airplane`;
CREATE TABLE `t_airplane` (
  `A_NO` int(11) NOT NULL AUTO_INCREMENT,
  `A_MODEL` varchar(255) COLLATE utf8_bin NOT NULL,
  `A_CAPACITY` int(11) NOT NULL,
  PRIMARY KEY (`A_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_airplane
-- ----------------------------
INSERT INTO `t_airplane` VALUES ('1', 'Boeing 747', '366');
INSERT INTO `t_airplane` VALUES ('2', 'Airbus A380', '555');
INSERT INTO `t_airplane` VALUES ('6', 'Y20', '200');
INSERT INTO `t_airplane` VALUES ('8', 'Boeing 767', '300');

-- ----------------------------
-- Table structure for `t_flight`
-- ----------------------------
DROP TABLE IF EXISTS `t_flight`;
CREATE TABLE `t_flight` (
  `F_NO` int(11) NOT NULL AUTO_INCREMENT,
  `F_A_NO` int(11) NOT NULL,
  `F_START` varchar(255) COLLATE utf8_bin NOT NULL,
  `F_DIST` varchar(255) COLLATE utf8_bin NOT NULL,
  `F_PRICE` float NOT NULL,
  `F_TIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `F_PSG_NUM` int(11) NOT NULL,
  PRIMARY KEY (`F_NO`),
  KEY `F_FK_AIRPLANE_NO` (`F_A_NO`),
  CONSTRAINT `F_FK_AIRPLANE_NO` FOREIGN KEY (`F_A_NO`) REFERENCES `t_airplane` (`A_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_flight
-- ----------------------------
INSERT INTO `t_flight` VALUES ('1', '1', '北京', '上海', '1101', '2020-01-08 15:43:30', '4');
INSERT INTO `t_flight` VALUES ('2', '6', '益阳', '长沙', '200', '2020-01-08 19:04:53', '3');
INSERT INTO `t_flight` VALUES ('3', '2', '纽约', '东京', '5000', '2020-01-08 15:51:28', '5');
INSERT INTO `t_flight` VALUES ('4', '6', '漓江塔', '直布罗陀', '198', '2020-01-08 19:01:52', '2');
INSERT INTO `t_flight` VALUES ('9', '8', '努巴尼', '阿努比斯神殿', '10000', '2020-07-08 22:27:50', '0');
INSERT INTO `t_flight` VALUES ('10', '6', '花村', '国王大道', '1300', '2020-01-08 16:01:47', '1');
INSERT INTO `t_flight` VALUES ('11', '2', '东京', '首尔', '5500', '2020-07-09 12:04:17', '0');
INSERT INTO `t_flight` VALUES ('12', '2', '华盛顿', '曼谷', '5200', '2020-07-10 01:00:26', '2');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `O_NO` int(11) NOT NULL AUTO_INCREMENT,
  `O_U_NO` int(11) NOT NULL,
  `O_F_NO` int(11) NOT NULL,
  `O_IS_PAYED` int(11) NOT NULL,
  `O_IS_CANCELED` int(11) NOT NULL,
  `O_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`O_NO`),
  KEY `O_FK_U_NO` (`O_U_NO`),
  KEY `O_FK_F_NO` (`O_F_NO`),
  CONSTRAINT `O_FK_F_NO` FOREIGN KEY (`O_F_NO`) REFERENCES `t_flight` (`F_NO`),
  CONSTRAINT `O_FK_U_NO` FOREIGN KEY (`O_U_NO`) REFERENCES `t_user` (`U_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '2', '1', '1', '1', '2020-07-07 13:37:32');
INSERT INTO `t_order` VALUES ('2', '2', '1', '1', '0', '2020-07-08 14:36:58');
INSERT INTO `t_order` VALUES ('3', '2', '3', '0', '0', '2020-07-08 00:45:52');
INSERT INTO `t_order` VALUES ('4', '3', '1', '0', '1', '2020-07-08 17:18:23');
INSERT INTO `t_order` VALUES ('5', '3', '2', '1', '1', '2020-07-08 22:28:13');
INSERT INTO `t_order` VALUES ('6', '3', '3', '1', '1', '2020-07-08 22:28:12');
INSERT INTO `t_order` VALUES ('7', '3', '4', '1', '1', '2020-07-08 22:28:12');
INSERT INTO `t_order` VALUES ('8', '3', '9', '1', '1', '2020-07-08 22:27:50');
INSERT INTO `t_order` VALUES ('9', '3', '10', '1', '1', '2020-07-08 22:37:49');
INSERT INTO `t_order` VALUES ('10', '3', '11', '1', '1', '2020-07-08 22:47:59');
INSERT INTO `t_order` VALUES ('11', '4', '1', '0', '1', '2020-07-09 11:58:29');
INSERT INTO `t_order` VALUES ('12', '4', '3', '1', '1', '2020-07-09 12:04:23');
INSERT INTO `t_order` VALUES ('13', '4', '11', '1', '1', '2020-07-09 12:04:17');
INSERT INTO `t_order` VALUES ('14', '4', '12', '0', '0', '2020-07-09 11:59:10');
INSERT INTO `t_order` VALUES ('15', '1', '1', '0', '0', '2020-07-09 12:02:20');
INSERT INTO `t_order` VALUES ('16', '4', '2', '0', '0', '2020-07-09 13:13:39');
INSERT INTO `t_order` VALUES ('17', '6', '3', '1', '0', '2020-07-10 01:01:29');
INSERT INTO `t_order` VALUES ('18', '6', '1', '0', '1', '2020-07-10 01:01:36');
INSERT INTO `t_order` VALUES ('19', '6', '12', '1', '0', '2020-07-10 01:01:39');
INSERT INTO `t_order` VALUES ('20', '6', '4', '0', '0', '2020-07-10 01:00:27');
INSERT INTO `t_order` VALUES ('21', '6', '3', '0', '0', '2020-07-10 22:59:12');
INSERT INTO `t_order` VALUES ('22', '9', '1', '1', '1', '2020-07-11 14:11:20');
INSERT INTO `t_order` VALUES ('23', '9', '2', '1', '0', '2019-08-20 15:44:42');
INSERT INTO `t_order` VALUES ('24', '9', '3', '1', '0', '2019-08-20 16:27:39');
INSERT INTO `t_order` VALUES ('25', '10', '3', '1', '0', '2020-01-08 16:01:19');
INSERT INTO `t_order` VALUES ('26', '10', '2', '1', '0', '2020-01-08 16:01:21');
INSERT INTO `t_order` VALUES ('27', '11', '1', '1', '0', '2020-01-08 15:49:53');
INSERT INTO `t_order` VALUES ('28', '12', '3', '1', '0', '2020-01-08 15:51:33');
INSERT INTO `t_order` VALUES ('29', '10', '10', '0', '0', '2020-01-08 08:01:47');
INSERT INTO `t_order` VALUES ('30', '9', '4', '1', '0', '2020-01-08 19:01:55');
INSERT INTO `t_order` VALUES ('31', '16', '2', '0', '1', '2020-01-08 19:04:53');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `U_NO` int(11) NOT NULL AUTO_INCREMENT,
  `U_USERNAME` varchar(255) COLLATE utf8_bin NOT NULL,
  `U_PASSWORD` varchar(255) COLLATE utf8_bin NOT NULL,
  `U_NICKNAME` varchar(255) COLLATE utf8_bin NOT NULL,
  `U_ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `U_TYPE` int(11) NOT NULL,
  `U_BALANCE` float NOT NULL,
  PRIMARY KEY (`U_NO`),
  UNIQUE KEY `U_CK_USERNAME` (`U_USERNAME`),
  UNIQUE KEY `U_CK_ID` (`U_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', '超级管理员', '430903199509210612', '1', '10000000');
INSERT INTO `t_user` VALUES ('9', 'user', '123456', '源码码头', '43090311111111111', '0', '105725');
INSERT INTO `t_user` VALUES ('10', 'power', 'power', 'power', '1111', '0', '94799');
INSERT INTO `t_user` VALUES ('11', '1235', '1235', '1235', '484851', '0', '3899');
INSERT INTO `t_user` VALUES ('12', '123', '123', '123', '123', '0', '555556000000000');
INSERT INTO `t_user` VALUES ('13', '123456', '123456', '123456', '123456', '0', '0');
INSERT INTO `t_user` VALUES ('14', '1234567', '1234567', '1234567', '1234567', '0', '0');
INSERT INTO `t_user` VALUES ('16', 'aaa', 'aaa', 'aaa', 'aaa', '0', '0');
