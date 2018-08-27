/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : db_net

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-08-27 16:05:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_business
-- ----------------------------
DROP TABLE IF EXISTS `tb_business`;
CREATE TABLE `tb_business` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  `ms` varchar(100) DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  `submittime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_business
-- ----------------------------

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` int(11) NOT NULL,
  `categoryname` varchar(50) DEFAULT NULL,
  `submittime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_category
-- ----------------------------

-- ----------------------------
-- Table structure for tb_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE `tb_news` (
  `id` int(11) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `submittime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_news
-- ----------------------------

-- ----------------------------
-- Table structure for tb_usertable
-- ----------------------------
DROP TABLE IF EXISTS `tb_usertable`;
CREATE TABLE `tb_usertable` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_usertable
-- ----------------------------
