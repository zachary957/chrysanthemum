CREATE TABLE `user_info` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `first_salt` varchar(40) NOT NULL COMMENT '首部盐码',
  `last_salt` varchar(40) NOT NULL COMMENT '尾部盐码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除(0-正常 1-锁定)',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `ip` varchar(30) NOT NULL COMMENT 'ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT= '用户信息';

