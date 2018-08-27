/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : db_eq

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-08-27 16:04:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_location
-- ----------------------------
DROP TABLE IF EXISTS `tb_location`;
CREATE TABLE `tb_location` (
  `xLocation` int(11) DEFAULT NULL,
  `yLocation` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_location
-- ----------------------------
INSERT INTO `tb_location` VALUES ('1180', '330', '240', '500');
INSERT INTO `tb_location` VALUES ('1180', '330', '240', '500');
INSERT INTO `tb_location` VALUES ('1180', '330', '240', '500');
INSERT INTO `tb_location` VALUES ('1180', '330', '240', '500');
INSERT INTO `tb_location` VALUES ('1180', '330', '240', '500');
INSERT INTO `tb_location` VALUES ('1180', '330', '240', '500');
INSERT INTO `tb_location` VALUES ('1180', '330', '240', '500');
INSERT INTO `tb_location` VALUES ('1180', '330', '240', '500');
INSERT INTO `tb_location` VALUES ('1180', '330', '240', '500');
INSERT INTO `tb_location` VALUES ('1180', '330', '240', '500');

-- ----------------------------
-- Table structure for tb_users
-- ----------------------------
DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users` (
  `ip` varchar(16) NOT NULL,
  `host` varchar(30) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `tooltip` varchar(50) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_users
-- ----------------------------
INSERT INTO `tb_users` VALUES ('192.168.0.1', '192.168.0.1', '192.168.0.1', null, '1.gif');
INSERT INTO `tb_users` VALUES ('192.168.233.1', 'MyWindow10', 'MyWindow10', '192.168.233.1', '2.gif');
