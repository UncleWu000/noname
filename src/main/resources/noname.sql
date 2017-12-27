/*
Navicat MySQL Data Transfer

Source Server         : Local
Source Server Version : 50558
Source Host           : localhost:3306
Source Database       : noname

Target Server Type    : MYSQL
Target Server Version : 50558
File Encoding         : 65001

Date: 2017-12-27 18:37:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(11) CHARACTER SET latin1 NOT NULL,
  `password` varchar(16) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '分类id',
  `title` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '标题',
  `view_count` int(255) DEFAULT NULL COMMENT '浏览次数',
  `like_count` int(255) DEFAULT NULL COMMENT '点赞次数',
  `create_date` datetime DEFAULT NULL COMMENT '发布时间',
  `main` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '信息主体',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '欧美图片', '32CM大黑粗,猴赛雷!!!', '1', '15', '2017-12-14 10:24:38', '啊啊啊啊啊啊啊啊啊啊,, oye oye oye 啊啊啊啊啊啊啊,, oye oye oye 啊,, 啊啊啊啊啊啊,, oye oye oye o啊啊啊啊啊啊,, oye oye oye y啊啊啊啊啊啊,, oye oye oye e啊啊啊啊啊啊,, oye oye oye  啊啊啊啊啊啊,, oye oye oye o啊啊啊啊啊啊,, oye oye oye y啊啊啊啊啊啊,, oye oye oye e oye ');
INSERT INTO `article` VALUES ('2', '亚洲激情', '寂寞白富美深夜叫春~~, 火钳围观!!', '2', '14', '2017-12-20 16:32:08', '哇, 好索啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊哇, 好索啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊哇, 好索啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊哇, 好索啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊哇, 好索啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊哇, 好索啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊哇, 好索啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊');
INSERT INTO `article` VALUES ('3', '资源分享', null, '3', '13', null, null);
INSERT INTO `article` VALUES ('4', null, null, '4', '12', null, null);
INSERT INTO `article` VALUES ('5', null, null, '5', '11', null, null);
INSERT INTO `article` VALUES ('6', null, null, '6', '10', null, null);
INSERT INTO `article` VALUES ('7', null, null, '7', '9', null, null);
INSERT INTO `article` VALUES ('8', null, null, '8', '8', null, null);
INSERT INTO `article` VALUES ('9', null, null, '9', '7', null, null);
INSERT INTO `article` VALUES ('10', null, null, '10', '6', null, null);
INSERT INTO `article` VALUES ('11', null, null, '11', '5', null, null);
INSERT INTO `article` VALUES ('12', null, null, '12', '4', null, null);
INSERT INTO `article` VALUES ('13', null, null, '13', '3', null, null);
INSERT INTO `article` VALUES ('14', null, null, '14', '2', null, null);
INSERT INTO `article` VALUES ('15', null, null, '15', '7', null, null);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of type
-- ----------------------------
