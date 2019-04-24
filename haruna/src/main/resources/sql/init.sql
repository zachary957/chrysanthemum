CREATE TABLE `article_description` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(30) NOT NULL COMMENT '文章标题',
  `source` varchar(10) NOT NULL COMMENT '来源',
  `author` varchar(10) NOT NULL COMMENT '作者',
  `sort` int(10) NOT NULL COMMENT '排序(越大优先级越高)',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除(0-未删除 1-已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='文章-描述';