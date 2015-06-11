/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : question

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2015-03-20 17:24:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_id_` varchar(64) NOT NULL,
  `parent_id_` varchar(64) DEFAULT NULL,
  `level_` int(11) DEFAULT NULL,
  `text_` varchar(50) DEFAULT NULL,
  `href_url_` varchar(100) DEFAULT NULL,
  `target_` varchar(20) DEFAULT NULL,
  `sort_` varchar(10) DEFAULT NULL,
  `icon_` varchar(20) DEFAULT NULL,
  `title_` varchar(50) DEFAULT NULL,
  `visit_key_` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`menu_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('100000', null, '0', '权限管理', null, null, '100000', 'leftico01.png', null, null);
INSERT INTO `t_menu` VALUES ('100001', '100000', '1', '用户管理', 'web/admin/user/index', 'rightFrame', '100001', null, null, '100001');
INSERT INTO `t_menu` VALUES ('100002', '100000', '1', '角色管理', 'web/admin/role/index', 'rightFrame', '100002', null, null, '100002');
INSERT INTO `t_menu` VALUES ('200000', null, '0', 'APP管理', null, null, '200000', 'leftico01.png', null, null);
INSERT INTO `t_menu` VALUES ('200001', '200000', '1', '用户管理', 'web/admin/appuser/index', 'rightFrame', '200001', null, null, '200001');
INSERT INTO `t_menu` VALUES ('200002', '200000', '1', '图片文字管理', 'web/admin/apptextimage/index', 'rightFrame', '200002', null, null, '200002');
INSERT INTO `t_menu` VALUES ('200003', '200000', '1', '声音文字管理', 'web/admin/apptextvoice/index', 'rightFrame', '200003', null, null, '200003');
INSERT INTO `t_menu` VALUES ('200004', '200000', '1', 'API测试', 'web/admin/appapitest/index', 'rightFrame', '200004', null, null, '200004');
INSERT INTO `t_menu` VALUES ('300000', null, '0', '系统管理', null, null, '300000', 'leftico01.png', null, null);
INSERT INTO `t_menu` VALUES ('300001', '300000', '1', '系统参数设置', 'web/admin/system/param/index', 'rightFrame', '300001', null, null, '300001');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id_` varchar(64) NOT NULL,
  `name_` varchar(50) DEFAULT NULL,
  `description_` varchar(100) DEFAULT NULL,
  `create_time_` datetime DEFAULT NULL,
  `modify_time_` datetime DEFAULT NULL,
  `user_id_` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`role_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('100000', '超级管理员', '可管理后台整个系统', '2014-12-10 17:36:33', '2014-12-10 17:36:36', null);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `role_id_` varchar(64) NOT NULL,
  `menu_id_` varchar(64) NOT NULL,
  PRIMARY KEY (`role_id_`,`menu_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('100000', '100000');
INSERT INTO `t_role_menu` VALUES ('100000', '100001');
INSERT INTO `t_role_menu` VALUES ('100000', '100002');
INSERT INTO `t_role_menu` VALUES ('100000', '200000');
INSERT INTO `t_role_menu` VALUES ('100000', '200001');
INSERT INTO `t_role_menu` VALUES ('100000', '200002');
INSERT INTO `t_role_menu` VALUES ('100000', '200003');
INSERT INTO `t_role_menu` VALUES ('100000', '200004');
INSERT INTO `t_role_menu` VALUES ('100000', '300000');
INSERT INTO `t_role_menu` VALUES ('100000', '300001');

-- ----------------------------
-- Table structure for t_server_user
-- ----------------------------
DROP TABLE IF EXISTS `t_server_user`;
CREATE TABLE `t_server_user` (
  `user_id_` varchar(64) NOT NULL,
  `username_` varchar(50) DEFAULT NULL,
  `assistant_password_` varchar(20) DEFAULT NULL,
  `encrypted_password_` varchar(50) DEFAULT NULL,
  `name_` varchar(50) DEFAULT NULL,
  `phone_` varchar(20) DEFAULT NULL,
  `parent_id_` varchar(64) DEFAULT NULL,
  `create_time_` datetime DEFAULT NULL,
  `modify_time_` datetime DEFAULT NULL,
  `last_login_time_` datetime DEFAULT NULL,
  `status_` int(11) DEFAULT NULL,
  `last_login_ip_` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_server_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_server_user` VALUES ('402881e846e4b3910146e4b8ce6c0004', 'admin', 'T0tQNVM=', '6cff5807cf61610da2446669acd09838', '管理员', '15000000000', null, '2014-12-10 11:11:11', '2015-03-27 15:32:39', '2015-03-27 15:49:26', '1', '127.0.0.1');
COMMIT;

-- ----------------------------
-- Table structure for t_system_param
-- ----------------------------
DROP TABLE IF EXISTS `t_system_param`;
CREATE TABLE `t_system_param` (
  `param_id_` varchar(20) NOT NULL,
  `name_` varchar(50) DEFAULT NULL,
  `value_` varchar(100) DEFAULT NULL,
  `create_time_` datetime DEFAULT NULL,
  `modify_time_` datetime DEFAULT NULL,
  PRIMARY KEY (`param_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_param
-- ----------------------------
INSERT INTO `t_system_param` VALUES ('initPassword', '新用户初始化密码', '123456', '2014-12-18 09:34:22', '2014-12-18 09:42:22');
INSERT INTO `t_system_param` VALUES ('client_version', '客户端版本号', '1.0.0', '2015-04-03 09:34:22', '2015-04-03 09:42:22');
INSERT INTO `t_system_param` VALUES ('client_version_info', '客户端版本信息', '这是一个新版本提示', '2015-04-03 09:34:22', '2015-04-03 09:42:22');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id_` varchar(64) NOT NULL,
  `role_id_` varchar(64) NOT NULL,
  PRIMARY KEY (`role_id_`,`user_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('402881e846e4b3910146e4b8ce6c0004', '100000');


-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `uid_` varchar(50) NOT NULL COMMENT '本地id',
  `user_type_` int(11) NOT NULL COMMENT '用户类型，0：第三方平台，1：本地账户',
  `open_id_` varchar(100) NOT NULL COMMENT '第三方id',
  `username_` varchar(100) NOT NULL COMMENT '用户名',
  `password_` varchar(255) NOT NULL COMMENT '密码',
  `name_` varchar(255) DEFAULT NULL COMMENT '昵称',
  `gender_` varchar(20) DEFAULT NULL COMMENT '性别',
  `figureurl_` varchar(255) DEFAULT NULL COMMENT '头像url',
  `create_time_` datetime NOT NULL COMMENT '用户创建时间',
  `update_at_` datetime NOT NULL COMMENT '最近一次更新时间',
  `last_login_ip_` varchar(20) DEFAULT NULL COMMENT '上一次登录ip',
  `last_load_followers_time_` datetime NOT NULL COMMENT '最近一次加载关注自己的用户列表时间',
  `status_` int NOT NULL COMMENT '用户状态，0：正常，1：禁言，2：封号',
  PRIMARY KEY (`uid_`),
  KEY `open_id_index_` (`open_id_`) USING BTREE,
  KEY `username_index_` (`username_`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app_text_image
-- ----------------------------
DROP TABLE IF EXISTS `app_text_image`;
CREATE TABLE `app_text_image` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `owner_id_` varchar(50) NOT NULL COMMENT '发表人id',
  `content_` varchar(500) DEFAULT NULL COMMENT '一句话内容',
  `image_url_` varchar(500) DEFAULT NULL COMMENT '一张图片url',
  `image_info_` varchar(50) DEFAULT NULL COMMENT '图片信息',
  `create_time_` datetime NOT NULL COMMENT '创建时间',
  `praise_count_` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数目',
  `share_count_` int(11) NOT NULL DEFAULT '0' COMMENT '被分享次数',
  PRIMARY KEY (`id_`),
  KEY `owner_id_index` (`owner_id_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app_text_image_praise_log
-- ----------------------------
DROP TABLE IF EXISTS `app_text_image_praise_log`;
CREATE TABLE `app_text_image_praise_log` (
  `op_id_` varchar(50) NOT NULL COMMENT '操作标识，文字图片记录id + 用户uid',
  `record_id_` int(11) NOT NULL,
  `uid_` varchar(50) NOT NULL,
  `status_` int(11) NOT NULL COMMENT '是否点过赞',
  `op_time_` datetime NOT NULL,
  PRIMARY KEY (`op_id_`),
  KEY `record_index` (`record_id_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app_text_voice
-- ----------------------------
DROP TABLE IF EXISTS `app_text_voice`;
CREATE TABLE `app_text_voice` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `owner_id_` varchar(50) NOT NULL COMMENT '发表人id',
  `content_` varchar(500) DEFAULT NULL COMMENT '一句话内容',
  `voice_url_` varchar(500) DEFAULT NULL COMMENT '一张图片url',
  `voice_info_` varchar(50) DEFAULT NULL COMMENT '声音信息',
  `create_time_` datetime NOT NULL COMMENT '创建时间',
  `praise_count_` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数目',
  `share_count_` int(11) NOT NULL DEFAULT '0' COMMENT '被分享次数',
  PRIMARY KEY (`id_`),
  KEY `owner_id_index` (`owner_id_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app_text_voice_praise_log
-- ----------------------------
DROP TABLE IF EXISTS `app_text_voice_praise_log`;
CREATE TABLE `app_text_voice_praise_log` (
  `op_id_` varchar(50) NOT NULL COMMENT '操作标识，文字图片记录id + 用户uid',
  `record_id_` int(11) NOT NULL,
  `uid_` varchar(50) NOT NULL,
  `status_` int(11) NOT NULL COMMENT '是否点过赞',
  `op_time_` datetime NOT NULL,
  PRIMARY KEY (`op_id_`),
  KEY `record_index` (`record_id_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app_feedback
-- ----------------------------
DROP TABLE IF EXISTS `app_feedback`;
CREATE TABLE `app_feedback` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content_` varchar(500) NOT NULL COMMENT '反馈内容',
  `uid_` varchar(50) NOT NULL COMMENT '反馈用户uid',
  `create_time_` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app_tip_off
-- ----------------------------
DROP TABLE IF EXISTS `app_tip_off`;
CREATE TABLE `app_tip_off` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type_` int(11) NOT NULL COMMENT '举报的记录类型，0：文字图片，1：文字声音',
  `record_id_` int(11) NOT NULL COMMENT '被举报的记录id',
  `content_` varchar(500) NOT NULL COMMENT '举报内容描述',
  `uid_` varchar(50) NOT NULL COMMENT '举报用户uid',
  `create_time_` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app_risk_word
-- ----------------------------
DROP TABLE IF EXISTS `app_risk_word`;
CREATE TABLE `app_risk_word` (
  `risk_word_` varchar(250) NOT NULL COMMENT '敏感词',
  PRIMARY KEY (`risk_word_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='敏感词';

-- ----------------------------
-- Records of app_risk_word
-- ----------------------------
INSERT INTO `app_risk_word` VALUES ('干你娘老鸡巴');
INSERT INTO `app_risk_word` VALUES ('干你娘鸡巴');
INSERT INTO `app_risk_word` VALUES ('操你他妈的');
INSERT INTO `app_risk_word` VALUES ('操你她妈的');
INSERT INTO `app_risk_word` VALUES ('操你妈');
INSERT INTO `app_risk_word` VALUES ('操你妈的');
INSERT INTO `app_risk_word` VALUES ('操你娘');
INSERT INTO `app_risk_word` VALUES ('操你老母');
INSERT INTO `app_risk_word` VALUES ('操妳他妈的');
INSERT INTO `app_risk_word` VALUES ('操妳娘');
