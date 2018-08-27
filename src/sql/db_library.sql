/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : db_library

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-08-27 16:04:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_bookinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_bookinfo`;
CREATE TABLE `tb_bookinfo` (
  `ISBN` varchar(255) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `bookname` varchar(255) DEFAULT NULL,
  `writer` varchar(255) DEFAULT NULL,
  `translator` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_bookinfo
-- ----------------------------

-- ----------------------------
-- Table structure for tb_booktype
-- ----------------------------
DROP TABLE IF EXISTS `tb_booktype`;
CREATE TABLE `tb_booktype` (
  `id` int(11) NOT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  `days` varchar(255) DEFAULT NULL,
  `fk` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_booktype
-- ----------------------------

-- ----------------------------
-- Table structure for tb_operator
-- ----------------------------
DROP TABLE IF EXISTS `tb_operator`;
CREATE TABLE `tb_operator` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `identityCard` varchar(255) DEFAULT NULL,
  `workdate` datetime DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `admin` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_operator
-- ----------------------------
INSERT INTO `tb_operator` VALUES ('1', 'admin', '1', '12', '123456', '2018-08-09 14:17:07', '12564565', '123456', '1');
