/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : db_expressletter

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-08-27 16:04:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_info`;
CREATE TABLE `tb_info` (
  `num` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_info
-- ----------------------------

-- ----------------------------
-- Table structure for tb_personnel
-- ----------------------------
DROP TABLE IF EXISTS `tb_personnel`;
CREATE TABLE `tb_personnel` (
  `num` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `name` varchar(8) NOT NULL,
  `sex` char(2) NOT NULL,
  `birthday` date NOT NULL,
  `company` varchar(50) NOT NULL,
  `dept` varchar(40) NOT NULL,
  `duty` varchar(30) NOT NULL,
  `handset` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_personnel
-- ----------------------------

-- ----------------------------
-- Table structure for tb_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_type`;
CREATE TABLE `tb_type` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `used` char(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_type
-- ----------------------------
INSERT INTO `tb_type` VALUES ('1', '但是很多时候', 'card');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL,
  `name` varchar(8) NOT NULL,
  `sex` char(2) NOT NULL,
  `birthday` date NOT NULL,
  `id_card` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `freeze` char(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------

-- ----------------------------
-- View structure for v_info_type
-- ----------------------------
DROP VIEW IF EXISTS `v_info_type`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_info_type` AS select `tb_info`.`num` AS `num`,`tb_type`.`name` AS `type_name`,`tb_info`.`content` AS `content` from (`tb_info` join `tb_type` on((`tb_info`.`type_id` = `tb_type`.`id`))) ;

-- ----------------------------
-- View structure for v_personnel_type
-- ----------------------------
DROP VIEW IF EXISTS `v_personnel_type`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_personnel_type` AS select `tb_personnel`.`num` AS `num`,`tb_type`.`name` AS `type_name`,`tb_personnel`.`name` AS `name`,`tb_personnel`.`sex` AS `sex`,`tb_personnel`.`birthday` AS `birthday`,`tb_personnel`.`company` AS `company`,`tb_personnel`.`dept` AS `dept`,`tb_personnel`.`duty` AS `duty`,`tb_personnel`.`handset` AS `handset`,`tb_personnel`.`email` AS `email` from (`tb_personnel` join `tb_type` on((`tb_personnel`.`type_id` = `tb_type`.`id`))) ;
