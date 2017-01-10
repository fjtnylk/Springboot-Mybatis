/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : muy_user

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2017-01-04 21:36:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for muy_customer
-- ----------------------------
DROP TABLE IF EXISTS `muy_customer`;
CREATE TABLE `muy_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录编号',
  `first_name` varchar(12) DEFAULT '' COMMENT '姓',
  `last_name` varchar(12) DEFAULT '' COMMENT '名',
  `email` varchar(25) DEFAULT '' COMMENT '邮箱',
  `address` varchar(255) DEFAULT '' COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
