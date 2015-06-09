
-- ----------------------------
-- Table structure for app_user_follow
-- ----------------------------
DROP TABLE IF EXISTS `app_user_follow`;
CREATE TABLE `app_user_follow` (
  `id_` varchar(50) NOT NULL,
  `owner_id_` varchar(50) NOT NULL COMMENT '主体用户uid',
  `follower_id_` varchar(50) NOT NULL COMMENT '关注用户uid',
  `create_time_` datetime NOT NULL COMMENT '关系创建时间',
  `status_` int(11) NOT NULL COMMENT '状态，0：单向关注，2：双向关注',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `unique_follow_index` (`owner_id_`,`follower_id_`) USING HASH,
  KEY `owner_id_index` (`owner_id_`) USING BTREE,
  KEY `follower_id_index` (`follower_id_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app_text_image_comment
-- ----------------------------
DROP TABLE IF EXISTS `app_comment`;
CREATE TABLE `app_comment` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `type_` int(11) NOT NULL COMMENT '评论类型。0：图片，1：声音',
  `record_id_` int(11) NOT NULL COMMENT '记录id',
  `owner_id_` varchar(50) NOT NULL COMMENT '评论人id',
  `content_` varchar(500) NOT NULL COMMENT '评论内容',
  `create_time_` datetime NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`id_`),
  KEY `record_id_index` (`record_id_`) USING BTREE,
  KEY `owner_id_index` (`owner_id_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

