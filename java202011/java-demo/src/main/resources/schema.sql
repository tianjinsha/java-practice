/*
Navicat H2 Data Transfer
Date: 2020-12-03 18:22:00
*/

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL COMMENT '账号',
  `phone` varchar(11) NOT NULL COMMENT '电话号码',
  `email` varchar(100) NOT NULL COMMENT '邮件地址',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `is_expired` char(1) NOT NULL DEFAULT '0' COMMENT '是否过期',
  `is_locked` char(1) NOT NULL DEFAULT '0' COMMENT '是否锁定',
  `is_enabled` char(1) NOT NULL DEFAULT '0' COMMENT '是否可用',
  `create_time` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
)DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `code` varchar(30) NOT NULL COMMENT '角色代码',
  `name` varchar(20) NOT NULL COMMENT '角色名',
  `desc` varchar(255) NOT NULL COMMENT '角色描述',
  PRIMARY KEY (`code`)
)DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `pattern` varchar(20) NOT NULL COMMENT '路径表达式',
  `desc` varchar(255) NOT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_code` varchar(30) NOT NULL,
--  KEY `user_role` (`userId`),
--  KEY `role_user` (`roleCode`),
  CONSTRAINT `user_role` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `role_user` FOREIGN KEY (`role_code`) REFERENCES `role` (`code`)
) DEFAULT CHARSET=utf8;