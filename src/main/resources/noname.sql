/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : noname

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-03-11 21:55:08
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `main` mediumtext CHARACTER SET utf8 COMMENT '信息主体',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '1', '你好呀', '11111', '233', '2017-12-29 00:38:22', 'sssss');
INSERT INTO `article` VALUES ('2', null, '你好呀', null, null, null, null);
INSERT INTO `article` VALUES ('3', null, null, null, null, null, null);
INSERT INTO `article` VALUES ('4', null, '吱吱吱吱吱吱吱吱吱吱吱吱吱吱吱吱吱吱吱', null, null, null, null);
INSERT INTO `article` VALUES ('5', null, null, null, null, '2018-01-26 14:44:08', '吱吱吱吱吱吱吱');
INSERT INTO `article` VALUES ('6', null, null, null, null, null, null);
INSERT INTO `article` VALUES ('7', null, null, null, null, null, null);
INSERT INTO `article` VALUES ('8', null, null, null, null, null, null);
INSERT INTO `article` VALUES ('9', null, null, null, null, null, null);
INSERT INTO `article` VALUES ('11', null, null, null, null, null, null);
INSERT INTO `article` VALUES ('12', null, null, null, null, null, null);
INSERT INTO `article` VALUES ('13', null, null, null, null, null, null);
INSERT INTO `article` VALUES ('14', null, null, null, null, null, null);
INSERT INTO `article` VALUES ('15', null, null, null, null, null, null);
INSERT INTO `article` VALUES ('16', null, null, null, null, null, null);
INSERT INTO `article` VALUES ('17', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom` (
  `id` int(11) NOT NULL,
  `building` varchar(255) DEFAULT NULL,
  `room_num` varchar(255) DEFAULT NULL,
  `used` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of classroom
-- ----------------------------

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `teacher` varchar(255) DEFAULT NULL COMMENT '老师',
  `selected_max` int(11) DEFAULT NULL COMMENT '限选',
  `selected_now` int(11) DEFAULT NULL COMMENT '已选',
  `course_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '青春期性生理健康', '99', '宋桂冬', '118', '2', 'Hentai书籍');
INSERT INTO `course` VALUES ('2', '房中要术', '99', '宋桂冬', '120', '1', 'Hentai书籍');
INSERT INTO `course` VALUES ('3', '读者', '99', '卓钟侄', '120', '1', '文化毒物');
INSERT INTO `course` VALUES ('4', '公残党复兴', '99', '卓钟侄', '120', '1', '政治启蒙毒物');

-- ----------------------------
-- Table structure for course_room_plan
-- ----------------------------
DROP TABLE IF EXISTS `course_room_plan`;
CREATE TABLE `course_room_plan` (
  `id` int(11) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of course_room_plan
-- ----------------------------

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` int(255) NOT NULL,
  `username` varchar(11) NOT NULL,
  `password` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', 'admin', 'admin');
INSERT INTO `manager` VALUES ('2', 'zzz', 'zzz');
INSERT INTO `manager` VALUES ('3', 'zzz', 'zzz');

-- ----------------------------
-- Table structure for selected
-- ----------------------------
DROP TABLE IF EXISTS `selected`;
CREATE TABLE `selected` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT '2',
  `teacher` varchar(255) DEFAULT NULL,
  `stu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of selected
-- ----------------------------
INSERT INTO `selected` VALUES ('1', 'A', '2', 'AA', '1');
INSERT INTO `selected` VALUES ('2', 'B', '2', 'BB', '1');
INSERT INTO `selected` VALUES ('3', 'C', '2', 'CC', '1');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `no` varchar(16) DEFAULT NULL,
  `pwd` varchar(12) DEFAULT '1',
  `sex` int(1) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `clas` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '201424133100', '000000', '1', '王大锤', '弹道系统研究1班');

-- ----------------------------
-- Table structure for u_permission
-- ----------------------------
DROP TABLE IF EXISTS `u_permission`;
CREATE TABLE `u_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_permission
-- ----------------------------
INSERT INTO `u_permission` VALUES ('1', '/myLogin', 'shiro登陆');
INSERT INTO `u_permission` VALUES ('2', '/user/list', '查询用户列表');

-- ----------------------------
-- Table structure for u_role
-- ----------------------------
DROP TABLE IF EXISTS `u_role`;
CREATE TABLE `u_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_role
-- ----------------------------
INSERT INTO `u_role` VALUES ('1', 'user', '1');

-- ----------------------------
-- Table structure for u_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `u_role_permission`;
CREATE TABLE `u_role_permission` (
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_role_permission
-- ----------------------------
INSERT INTO `u_role_permission` VALUES ('1', '1');
INSERT INTO `u_role_permission` VALUES ('1', '2');

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` bigint(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES ('15', 'zzz', null, 'zzz', null, null, '1');

-- ----------------------------
-- Table structure for u_user_role
-- ----------------------------
DROP TABLE IF EXISTS `u_user_role`;
CREATE TABLE `u_user_role` (
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user_role
-- ----------------------------
INSERT INTO `u_user_role` VALUES ('15', '1');
