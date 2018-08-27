/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : db_drinkerymanage

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-08-27 16:03:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_desk
-- ----------------------------
DROP TABLE IF EXISTS `tb_desk`;
CREATE TABLE `tb_desk` (
  `num` int(11) DEFAULT NULL,
  `seating` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_desk
-- ----------------------------

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `num` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `sort_id` int(11) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `unit_price` decimal(10,0) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_form
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_form`;
CREATE TABLE `tb_order_form` (
  `num` int(11) DEFAULT NULL,
  `desk_num` int(11) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_form
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item` (
  `order_form_num` int(11) DEFAULT NULL,
  `menu_num` int(11) DEFAULT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `total` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for tb_sort
-- ----------------------------
DROP TABLE IF EXISTS `tb_sort`;
CREATE TABLE `tb_sort` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sort
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `freeze` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('ww', '男', '2008-08-08', '123456789425623', '正常', '123456');
