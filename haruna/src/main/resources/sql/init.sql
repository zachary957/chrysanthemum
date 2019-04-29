CREATE TABLE `article` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` bigint(10) unsigned NOT NULL COMMENT '栏目id',
  `title` varchar(30) NOT NULL COMMENT '文章标题',
  `summary` varchar(50) NOT NULL COMMENT '摘要',
  `source` varchar(10) NOT NULL COMMENT '来源',
  `author` varchar(10) NOT NULL COMMENT '作者',
  `content` text NOT NULL COMMENT '文章内容',
  `sort` int(10) NOT NULL COMMENT '排序(越大优先级越高)',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除(0-未删除 1-已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='文章描述';

CREATE TABLE `comment` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` bigint(10) unsigned NOT NULL COMMENT '文章id',
  `content` varchar(100) NOT NULL COMMENT '评论内容',
  `nick_name` varchar(15) NOT NULL COMMENT '评论人昵称',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除(0-未删除 1-已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章评论';

CREATE TABLE `menu` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_description` varchar(15) NOT NULL COMMENT '栏目描述',
  `sort` int(10) unsigned NOT NULL COMMENT '排序(越大优先级越高)',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除(0-未删除 1-已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目';


