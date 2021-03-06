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
INSERT INTO `t_menu` VALUES ('500000', NULL, 0, '账户管理', NULL, 'rightFrame', '500000', 'leftico01.png', NULL);
INSERT INTO `t_menu` VALUES ('500001', '500000', 1, '基本信息', 'web/admin/baseinfo', 'rightFrame', '500001', NULL, NULL);
INSERT INTO `t_menu` VALUES ('600000', NULL, 0, '审核管理', NULL, 'rightFrame', '600000', 'leftico01.png', NULL);
INSERT INTO `t_menu` VALUES ('600001', '600000', 1, '店铺管理', 'web/admin/useradmin/shopmanage/index', 'rightFrame', '600001', NULL, NULL);
INSERT INTO `t_menu` VALUES ('600002', '600000', 1, '续费会员订单管理', 'web/admin/useradmin/dealmanage/index_vip_order', 'rightFrame', '600002', NULL, NULL);
INSERT INTO `t_menu` VALUES ('600003', '600000', 1, '购买积分订单管理', 'web/admin/useradmin/dealmanage/index_score_order', 'rightFrame', '600003', NULL, NULL);
INSERT INTO `t_menu` VALUES ('600004', '600000', 1, '任务列表', 'web/admin/useradmin/taskmanage/index', 'rightFrame', '600004', NULL, NULL);
INSERT INTO `t_menu` VALUES ('600005', '600000', '1', '商户管理', 'web/admin/useradmin/shopusermanage/index', 'rightFrame', '600005', NULL, NULL);
INSERT INTO `t_menu` VALUES ('600006', '600000', '1', '流量记录', 'web/admin/useradmin/liuliangmanage/index', 'rightFrame', '600006', NULL, NULL);

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
INSERT INTO `t_role_menu` VALUES ('100000', '500000');
INSERT INTO `t_role_menu` VALUES ('100000', '500001');
INSERT INTO `t_role_menu` VALUES ('100000', '600000');
INSERT INTO `t_role_menu` VALUES ('100000', '600001');
INSERT INTO `t_role_menu` VALUES ('100000', '600002');
INSERT INTO `t_role_menu` VALUES ('100000', '600003');
INSERT INTO `t_role_menu` VALUES ('100000', '600004');
INSERT INTO `t_role_menu` VALUES ('100000', '600005');
INSERT INTO `t_role_menu` VALUES ('100000', '600006');
INSERT INTO `t_role_menu` VALUES ('100001', '100000');
INSERT INTO `t_role_menu` VALUES ('100001', '100001');
INSERT INTO `t_role_menu` VALUES ('100001', '300000');
INSERT INTO `t_role_menu` VALUES ('100001', '300001');
INSERT INTO `t_role_menu` VALUES ('100001', '500000');
INSERT INTO `t_role_menu` VALUES ('100001', '500001');
INSERT INTO `t_role_menu` VALUES ('100001', '600000');
INSERT INTO `t_role_menu` VALUES ('100001', '600001');
INSERT INTO `t_role_menu` VALUES ('100001', '600002');
INSERT INTO `t_role_menu` VALUES ('100001', '600003');
INSERT INTO `t_role_menu` VALUES ('100001', '600004');
INSERT INTO `t_role_menu` VALUES ('100001', '600005');
INSERT INTO `t_role_menu` VALUES ('100001', '600006');

-- ----------------------------
-- Table structure for t_server_user
-- ----------------------------
DROP TABLE IF EXISTS `t_server_user`;
CREATE TABLE `t_server_user` (
  `user_id_` varchar(64) NOT NULL,
  `user_type_` int(11) NOT NULL,
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
  `score_used_` int(11) NOT NULL COMMENT '用户当前已用积分',
  `vip_end_time_` datetime DEFAULT NULL COMMENT 'vip结束时间',
  `user_qq_` varchar(20) DEFAULT NULL,
  `user_mobile_` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_server_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_server_user` VALUES ('402881e846e4b3910146e4b8ce6c0004', 1, 'admin', 'T0tQNVM=', '6cff5807cf61610da2446669acd09838', '超级管理员', '15000000000', 'admin@abc.com', null, '2014-12-10 11:11:11', '2015-03-27 15:32:39', '2015-03-27 15:49:26', '1', '127.0.0.1', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_server_user` VALUES ('402882044c981aee014c981b344a0001', 1, 'useradmin', 'T0tQNVM=', '6cff5807cf61610da2446669acd09838', '管理员', '15000000000', 'useradmin@abc.com', '402881e846e4b3910146e4b8ce6c0004', '2014-12-10 11:11:11', '2015-03-27 15:32:39', '2015-03-27 15:49:26', '1', '127.0.0.1', 0, 0, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_system_param
-- ----------------------------
DROP TABLE IF EXISTS `t_system_param`;
CREATE TABLE `t_system_param` (
  `param_id_` varchar(200) NOT NULL,
  `name_` varchar(500) DEFAULT NULL,
  `value_` varchar(500) DEFAULT NULL,
  `create_time_` datetime DEFAULT NULL,
  `modify_time_` datetime DEFAULT NULL,
  PRIMARY KEY (`param_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_param
-- ----------------------------
INSERT INTO `t_system_param` VALUES ('initPassword', '新用户初始化密码', '123456', '2014-12-18 09:34:22', '2014-12-18 09:42:22');
INSERT INTO `t_system_param` VALUES ('emailStmpServer', '邮件服务器', 'smtp.tom.com', '2015-04-03 09:34:22', '2015-04-03 09:42:22');
INSERT INTO `t_system_param` VALUES ('emailUsername', '系统邮箱用户名', 'liuliang_server@tom.com', '2015-04-03 09:34:22', '2015-04-03 09:42:22');
INSERT INTO `t_system_param` VALUES ('emailPassword', '系统邮箱密码', 'liuliang654321', '2015-04-03 09:34:22', '2015-04-03 09:42:22');

INSERT INTO `t_system_param` VALUES ('crazyClickAppkey', '第三方接口appKey', 'test', '2015-05-31 14:34:22', '2015-05-31 14:34:22');
INSERT INTO `t_system_param` VALUES ('crazyClickAppsecret', '第三方接口appsecret', 'a9d11a189099ac9b483ab982e849e939', '2015-05-31 14:34:22', '2015-05-31 14:34:22');
INSERT INTO `t_system_param` VALUES ('crazyClickBaseUrl', '第三方接口基础url', 'http://api.sandbox.aymoo.com/api/', '2015-05-31 14:34:22', '2015-05-31 14:34:22');

INSERT INTO `t_system_param` VALUES ('oneVisitCostScore', '每个流量花费积分数', '3', '2015-04-03 09:34:22', '2015-04-03 09:42:22');
INSERT INTO `t_system_param` VALUES ('oneKeywordCostScore', '每额外添加一个关键词话费的积分数', '30', '2015-04-03 09:34:22', '2015-04-03 09:42:22');
INSERT INTO `t_system_param` VALUES ('pageStayCostScore1', '增值服务，页面停留60~120秒花费积分数', '40', '2015-04-03 09:34:22', '2015-04-03 09:42:22');
INSERT INTO `t_system_param` VALUES ('pageStayCostScore2', '增值服务，页面停留120~180秒花费积分数', '50', '2015-04-03 09:34:22', '2015-04-03 09:42:22');
INSERT INTO `t_system_param` VALUES ('visitTimeCostScore1', '增值服务，流量访问时间随机分布花费积分数', '50', '2015-04-03 09:34:22', '2015-04-03 09:42:22');
INSERT INTO `t_system_param` VALUES ('visitTimeCostScore2', '增值服务，流量访问时间网购用户习惯曲线分布花费积分数', '60', '2015-04-03 09:34:22', '2015-04-03 09:42:22');
INSERT INTO `t_system_param` VALUES ('quickVerifyCostScore', '增值服务，优先审核花费积分数', '50', '2015-04-03 09:34:22', '2015-04-03 09:42:22');
INSERT INTO `t_system_param` VALUES ('quickExecuteCostScore', '增值服务，优先执行花费积分数', '50', '2015-04-03 09:34:22', '2015-04-03 09:42:22');

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
  `num_` int(11) NOT NULL COMMENT '积分数目',
  `price_` int(11) NOT NULL COMMENT '订单价格',
  `pay_img_url_` varchar(500) NOT NULL COMMENT '转账成功截图url',
  `deal_id_` varchar(50) NOT NULL COMMENT '转账交易号',
  `reason_` varchar(50) NOT NULL COMMENT '理由（平台账号）',
  `pay_time_` datetime DEFAULT NULL COMMENT '付款时间',
  `create_time_` datetime DEFAULT NULL COMMENT '创建时间',
  `check_time_` datetime DEFAULT NULL COMMENT '验证发货时间',
  `check_admin_id_` varchar(64) DEFAULT NULL COMMENT '验证发货的管理员id',
  `status_` int(11) NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`order_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ll_score_record
-- ----------------------------
DROP TABLE IF EXISTS `ll_score_record`;
CREATE TABLE `ll_score_record` (
  `record_id_` varchar(64) NOT NULL COMMENT '积分变动记录id',
  `user_id_` varchar(64) NOT NULL COMMENT '购买用户',
  `num_` int(11) NOT NULL COMMENT '积分数目',
  `type_` int(11) NOT NULL COMMENT '变动类型，1：购买，2：消费，3：管理员修改，4：任务消费返还',
  `remain_` int(11) NOT NULL COMMENT '剩余积分',
  `create_time_` datetime DEFAULT NULL COMMENT '创建时间',
  `remark_` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`record_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ll_score_order
-- ----------------------------
DROP TABLE IF EXISTS `ll_vip_order`;
CREATE TABLE `ll_vip_order` (
  `order_id_` varchar(64) NOT NULL COMMENT '购买订单id',
  `user_id_` varchar(64) NOT NULL COMMENT '购买用户',
  `num_` int(11) NOT NULL COMMENT 'vip续费月数',
  `price_` int(11) NOT NULL COMMENT '订单价格',
  `pay_img_url_` varchar(500) NOT NULL COMMENT '转账成功截图url',
  `deal_id_` varchar(50) NOT NULL COMMENT '转账交易号',
  `reason_` varchar(50) NOT NULL COMMENT '理由（平台账号）',
  `pay_time_` datetime DEFAULT NULL COMMENT '付款时间',
  `create_time_` datetime DEFAULT NULL COMMENT '创建时间',
  `check_time_` datetime DEFAULT NULL COMMENT '验证发货时间',
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
  `create_time_` datetime DEFAULT NULL COMMENT '创建时间',
  `check_time_` datetime DEFAULT NULL COMMENT '审核时间',
  `check_admin_id_` varchar(64) DEFAULT NULL COMMENT '审核的管理员id',
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
  `client_type_` varchar(50) NOT NULL DEFAULT '' COMMENT '客户端类型',
  `task_type_` int(11) NOT NULL DEFAULT '0' COMMENT '流量类型',
  `goods_url_` varchar(500) NOT NULL COMMENT '商品url',
  `goods_name_` varchar(500) NOT NULL COMMENT '商品名称',
  `goods_img_` varchar(500) NOT NULL COMMENT '商品图片地址',
  `keyword_1_` varchar(500) NOT NULL DEFAULT '' COMMENT '关键词1',
  `order_number_one_day_1_` int(11) NOT NULL DEFAULT '0' COMMENT '关键词1每日访客数',
  `keyword_2_` varchar(500) NOT NULL DEFAULT '' COMMENT '关键词2',
  `order_number_one_day_2_` int(11) NOT NULL DEFAULT '0' COMMENT '关键词2每日访客数',
  `keyword_3_` varchar(500) NOT NULL DEFAULT '' COMMENT '关键词3',
  `order_number_one_day_3_` int(11) NOT NULL DEFAULT '0' COMMENT '关键词3每日访客数',
  `keyword_4_` varchar(500) NOT NULL DEFAULT '' COMMENT '关键词4',
  `order_number_one_day_4_` int(11) NOT NULL DEFAULT '0' COMMENT '关键词4每日访客数',
  `keyword_5_` varchar(500) NOT NULL DEFAULT '' COMMENT '关键词5',
  `order_number_one_day_5_` int(11) NOT NULL DEFAULT '0' COMMENT '关键词5每日访客数',
  `search_source_` int(11) NOT NULL DEFAULT '0' COMMENT '搜索来源分布占比，平台为天猫时该项才有值，表示淘宝的自然搜索占比百分数',
  `duration_day_` int(11) NOT NULL COMMENT '持续天数',
  `page_stay_type_` int(11) NOT NULL DEFAULT '0' COMMENT '增值服务，页面停留时间（0：默认 停留30-60秒，1：停留时间60-120秒，2：停留时间120-180秒）',
  `visit_time_type_` int(11) NOT NULL DEFAULT '0' COMMENT '访问时间类型（0：默认 全天平均分布，1：随即分布，2：网购用户习惯曲线分布）',
  `is_quick_verify_` int(11) NOT NULL DEFAULT '0' COMMENT '是否优先审核',
  `is_quick_execute_` int(11) NOT NULL DEFAULT '0' COMMENT '是否优先执行',
  `cost_score_` int(11) NOT NULL COMMENT '消耗积分',
  `create_time_` datetime DEFAULT NULL COMMENT '创建时间',
  `check_time_` datetime DEFAULT NULL COMMENT '审核通过（开始执行）时间',
  `check_admin_` varchar(64) DEFAULT NULL COMMENT '操作管理员id',
  `finish_time_` datetime DEFAULT NULL COMMENT '任务执行完毕时间',
  `status_` int(11) NOT NULL COMMENT '任务状态',
  `remark_` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`task_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ll_liuliang
-- ----------------------------
DROP TABLE IF EXISTS `ll_liuliang`;
CREATE TABLE `ll_liuliang` (
  `id_` varchar(64) NOT NULL,
  `third_id_` int(11) NOT NULL COMMENT '0',
  `task_id_` varchar(64) DEFAULT NULL,
  `good_id_` varchar(50) DEFAULT NULL,
  `keyword_` varchar(100) DEFAULT NULL,
  `shop_type_` varchar(50) DEFAULT NULL,
  `times_` int(11) NOT NULL DEFAULT '0',
  `path1_` int(11) NOT NULL DEFAULT '0',
  `path2_` int(11) NOT NULL DEFAULT '0',
  `path3_` int(11) NOT NULL DEFAULT '0',
  `sleep_time_` int(11) NOT NULL DEFAULT '0',
  `click_start_` int(11) NOT NULL DEFAULT '0',
  `click_end_` int(11) NOT NULL DEFAULT '0',
  `date_` datetime DEFAULT NULL,
  `num_` int(11) NOT NULL DEFAULT '0',
  `finish_num_` int(11) NOT NULL DEFAULT '0',
  `finish_num_update_time_` bigint(11) NOT NULL DEFAULT '0',
  `is_finish_` int(11) NOT NULL DEFAULT '0',
  `create_time_` datetime DEFAULT NULL,
  `status_` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
