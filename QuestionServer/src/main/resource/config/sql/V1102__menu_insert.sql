INSERT INTO `t_menu` (`menu_id_`, `parent_id_`, `level_`, `text_`, `href_url_`, `target_`, `sort_`, `icon_`, `title_`, `visit_key_`) VALUES ('400000', '400000', '0', '购物APP管理', NULL, NULL, '400000', 'leftico01.png', NULL, NULL);
INSERT INTO `t_menu` (`menu_id_`, `parent_id_`, `level_`, `text_`, `href_url_`, `target_`, `sort_`, `icon_`, `title_`, `visit_key_`) VALUES ('400001', '400000', '1', 'API测试', 'web/admin/shoppingapitest/index', 'rightFrame', '400001', '', '', '400001');
INSERT INTO `t_role_menu` (`role_id_`, `menu_id_`) VALUES ('100000', '400000');
INSERT INTO `t_role_menu` (`role_id_`, `menu_id_`) VALUES ('100000', '400001');
