DROP TABLE IF EXISTS `shopping_goods`;
CREATE TABLE `shopping_goods` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `good_type_` int(11) NOT NULL DEFAULT 1 COMMENT '商品类型',
  `title_` varchar(500) NOT NULL DEFAULT '' COMMENT '商品标题',
  `content_` varchar(500) NOT NULL DEFAULT '' COMMENT '商品内容描述',
  `image_url_` varchar(500) DEFAULT NULL COMMENT '一图片url',
  `create_at_` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `source_store_` int(11) NOT NULL DEFAULT 0 COMMENT '点赞数目',
  PRIMARY KEY (`id_`),
  KEY `create_at_index` (`create_at_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
