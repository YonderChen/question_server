/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : liuliang

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
  PRIMARY KEY (`menu_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('100000', NULL, 0, '权限管理', NULL, NULL, '100000', 'leftico01.png', NULL);
INSERT INTO `t_menu` VALUES ('100001', '100000', 1, '用户管理', 'web/admin/useradmin/user/index', 'rightFrame', '100001', NULL, NULL);
INSERT INTO `t_menu` VALUES ('100002', '100000', 1, '角色管理', 'web/admin/useradmin/role/index', 'rightFrame', '100002', NULL, NULL);
INSERT INTO `t_menu` VALUES ('300000', NULL, 0, '系统管理', NULL, NULL, '300000', 'leftico01.png', NULL);
INSERT INTO `t_menu` VALUES ('300001', '300000', 1, '系统参数设置', 'web/admin/useradmin/system/param/index', 'rightFrame', '300001', NULL, NULL);
INSERT INTO `t_menu` VALUES ('400000', NULL, 0, '流量管理', NULL, 'rightFrame', '400000', 'leftico01.png', NULL);
INSERT INTO `t_menu` VALUES ('400001', '400000', 1, '任务管理', 'web/admin/usershop/taskmanage/index', 'rightFrame', '400001', NULL, NULL);
INSERT INTO `t_menu` VALUES ('500000', NULL, 0, '账户管理', NULL, 'rightFrame', '500000', 'leftico01.png', NULL);
INSERT INTO `t_menu` VALUES ('500001', '500000', 1, '基本信息', 'web/admin/usershop/accountmanage/baseinfo', 'rightFrame', '500001', NULL, NULL);
INSERT INTO `t_menu` VALUES ('500002', '500000', 1, '绑定店铺', 'web/admin/usershop/accountmanage/shopmanage/index', 'rightFrame', '500002', NULL, NULL);
INSERT INTO `t_menu` VALUES ('500003', '500000', 1, '店铺列表', 'web/admin/usershop/accountmanage/shopmanage/list', 'rightFrame', '500003', NULL, NULL);
INSERT INTO `t_menu` VALUES ('500004', '500000', 1, '续费会员', 'web/admin/usershop/accountmanage/renewalvip', 'rightFrame', '500004', NULL, NULL);
INSERT INTO `t_menu` VALUES ('500005', '500000', 1, '积分管理', 'web/admin/usershop/accountmanage/scoremanage/index', 'rightFrame', '500005', NULL, NULL);
INSERT INTO `t_menu` VALUES ('500006', '500000', 1, '邀请商家', 'web/admin/usershop/accountmanage/inviteothers', 'rightFrame', '500006', NULL, NULL);

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
INSERT INTO `t_role` VALUES ('100000', '超级管理员', '可管理后台整个系统', '2014-12-10 17:36:33', '2014-12-10 17:36:36', NULL);
INSERT INTO `t_role` VALUES ('100001', '管理员', '管理员', '2015-4-7 19:41:27', '2015-4-7 19:42:04', '402881e846e4b3910146e4b8ce6c0004');
INSERT INTO `t_role` VALUES ('100002', '商家用户', '店铺商家', '2015-4-7 19:41:57', '2015-4-7 19:41:57', '402881e846e4b3910146e4b8ce6c0004');

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
INSERT INTO `t_role_menu` VALUES ('100000', '300000');
INSERT INTO `t_role_menu` VALUES ('100000', '300001');
INSERT INTO `t_role_menu` VALUES ('100000', '400000');
INSERT INTO `t_role_menu` VALUES ('100000', '400001');
INSERT INTO `t_role_menu` VALUES ('100000', '500000');
INSERT INTO `t_role_menu` VALUES ('100000', '500001');
INSERT INTO `t_role_menu` VALUES ('100000', '500002');
INSERT INTO `t_role_menu` VALUES ('100000', '500003');
INSERT INTO `t_role_menu` VALUES ('100000', '500004');
INSERT INTO `t_role_menu` VALUES ('100000', '500005');
INSERT INTO `t_role_menu` VALUES ('100000', '500006');
INSERT INTO `t_role_menu` VALUES ('100001', '100000');
INSERT INTO `t_role_menu` VALUES ('100001', '100001');
INSERT INTO `t_role_menu` VALUES ('100001', '300000');
INSERT INTO `t_role_menu` VALUES ('100001', '300001');
INSERT INTO `t_role_menu` VALUES ('100002', '400000');
INSERT INTO `t_role_menu` VALUES ('100002', '400001');
INSERT INTO `t_role_menu` VALUES ('100002', '500000');
INSERT INTO `t_role_menu` VALUES ('100002', '500001');
INSERT INTO `t_role_menu` VALUES ('100002', '500002');
INSERT INTO `t_role_menu` VALUES ('100002', '500003');
INSERT INTO `t_role_menu` VALUES ('100002', '500004');
INSERT INTO `t_role_menu` VALUES ('100002', '500005');
INSERT INTO `t_role_menu` VALUES ('100002', '500006');

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
  `email_` varchar(50) DEFAULT NULL,
  `parent_id_` varchar(64) DEFAULT NULL,
  `create_time_` datetime DEFAULT NULL,
  `modify_time_` datetime DEFAULT NULL,
  `last_login_time_` datetime DEFAULT NULL,
  `status_` int(11) DEFAULT NULL,
  `last_login_ip_` varchar(20) DEFAULT NULL,
  `score_` int(11) NOT NULL COMMENT '用户当前积分',
  PRIMARY KEY (`user_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_server_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_server_user` VALUES ('402881e846e4b3910146e4b8ce6c0004', 'admin', 'T0tQNVM=', '6cff5807cf61610da2446669acd09838', '超级管理员', '15000000000', 'admin@abc.com', null, '2014-12-10 11:11:11', '2015-03-27 15:32:39', '2015-03-27 15:49:26', '1', '127.0.0.1', 0);
INSERT INTO `t_server_user` VALUES ('402882044c981aee014c981b344a0001', 'useradmin', 'T0tQNVM=', '6cff5807cf61610da2446669acd09838', '管理员', '15000000000', 'useradmin@abc.com', '402881e846e4b3910146e4b8ce6c0004', '2014-12-10 11:11:11', '2015-03-27 15:32:39', '2015-03-27 15:49:26', '1', '127.0.0.1', 0);
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
INSERT INTO `t_user_role` VALUES ('402882044c981aee014c981b344a0001', '100001');


-- ----------------------------
-- 业务逻辑表
-- ----------------------------

-- ----------------------------
-- Table structure for ll_score_order
-- ----------------------------
DROP TABLE IF EXISTS `ll_score_order`;
CREATE TABLE `ll_score_order` (
  `order_id_` varchar(64) NOT NULL COMMENT '购买订单id',
  `user_id_` varchar(64) NOT NULL COMMENT '购买用户',
  `score_num_` int(11) NOT NULL COMMENT '积分数目',
  `price_` int(11) NOT NULL COMMENT '订单价格',
  `check_admin_id_` varchar(64) DEFAULT NULL COMMENT '验证发货的管理员id',
  `status_` int(11) NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`order_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ll_shop
-- ----------------------------
DROP TABLE IF EXISTS `ll_shop`;
CREATE TABLE `ll_shop` (
  `shop_id_` varchar(64) NOT NULL COMMENT '流量任务id',
  `user_id_` varchar(64) NOT NULL COMMENT '用户id',
  `bind_plat_` varchar(20) NOT NULL COMMENT '绑定平台',
  `bind_name_` varchar(150) NOT NULL COMMENT '店主旺旺',
  `shop_url_` varchar(150) NOT NULL COMMENT '店铺url',
  `verify_goods_url_` varchar(500) NOT NULL COMMENT '审核商品url',
  `verify_code_` varchar(50) NOT NULL COMMENT '审核验证码',
  `status_` int(11) NOT NULL COMMENT '店铺审核状态',
  PRIMARY KEY (`shop_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ll_task
-- ----------------------------
DROP TABLE IF EXISTS `ll_task`;
CREATE TABLE `ll_task` (
  `task_id_` varchar(64) NOT NULL COMMENT '流量任务id',
  `user_id_` varchar(64) NOT NULL COMMENT '发布的用户id',
  `shop_id_` varchar(64) NOT NULL COMMENT '店铺id',
  `shop_url_` varchar(150) NOT NULL COMMENT '店铺url',
  `goods_url_` varchar(500) NOT NULL COMMENT '商品url',
  `order_number_one_day_` int(11) NOT NULL COMMENT '每天订单数',
  `duration_day_` int(11) NOT NULL COMMENT '持续天数',
  `cost_score_` int(11) NOT NULL COMMENT '消耗积分',
  `status_` int(11) NOT NULL COMMENT '任务状态',
  PRIMARY KEY (`task_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
