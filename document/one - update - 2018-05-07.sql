SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for msg_announce
-- ----------------------------
DROP TABLE IF EXISTS `msg_announce`;
CREATE TABLE `msg_announce` (
  `announce_id` varchar(50) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `create_user_id` varchar(40) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `read_count` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `is_top` int(11) DEFAULT NULL,
  `content` longtext,
  `target_type` int(11) DEFAULT NULL,
  `target` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`announce_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for msg_channel
-- ----------------------------
DROP TABLE IF EXISTS `msg_channel`;
CREATE TABLE `msg_channel` (
  `channel_id` varchar(10) NOT NULL COMMENT '渠道编号',
  `channel_name` varchar(20) DEFAULT NULL COMMENT '渠道名称',
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息渠道表';

-- ----------------------------
-- Table structure for msg_message
-- ----------------------------
DROP TABLE IF EXISTS `msg_message`;
CREATE TABLE `msg_message` (
  `message_id` varchar(40) NOT NULL COMMENT '消息ID',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `content` longtext COMMENT '消息内容',
  `title` varchar(100) DEFAULT NULL COMMENT '消息标题',
  `template_id` varchar(40) NOT NULL COMMENT '消息模板ID',
  `target_type` int(11) DEFAULT NULL COMMENT '目标用户类型',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';

-- ----------------------------
-- Table structure for msg_message_user
-- ----------------------------
DROP TABLE IF EXISTS `msg_message_user`;
CREATE TABLE `msg_message_user` (
  `message_id` varchar(40) NOT NULL COMMENT '消息ID',
  `user_id` varchar(40) NOT NULL COMMENT '用户ID',
  `status` int(11) DEFAULT NULL COMMENT '消息状态',
  PRIMARY KEY (`message_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息用户关联表';

-- ----------------------------
-- Table structure for msg_template
-- ----------------------------
DROP TABLE IF EXISTS `msg_template`;
CREATE TABLE `msg_template` (
  `template_id` varchar(40) NOT NULL COMMENT '消息模板ID',
  `title` varchar(100) DEFAULT NULL COMMENT '消息模板标题',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `content` longtext COMMENT '消息内容',
  `msg_type_code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统消息模板表';

-- ----------------------------
-- Table structure for msg_template_channel
-- ----------------------------
DROP TABLE IF EXISTS `msg_template_channel`;
CREATE TABLE `msg_template_channel` (
  `channel_id` varchar(10) NOT NULL COMMENT '渠道编号',
  `template_id` varchar(40) NOT NULL COMMENT '消息模板ID',
  PRIMARY KEY (`channel_id`,`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息模板渠道表';

-- ----------------------------
-- Table structure for msg_template_weixin
-- ----------------------------
DROP TABLE IF EXISTS `msg_template_weixin`;
CREATE TABLE `msg_template_weixin` (
  `wx_template_id` varchar(100) DEFAULT NULL COMMENT '微信模板编号',
  `wx_template_head` varchar(100) DEFAULT NULL COMMENT '微信模板头部',
  `wx_template_tail` varchar(100) DEFAULT NULL COMMENT '微信模板尾部',
  `template_id` varchar(40) NOT NULL COMMENT '微信消息模板ID',
  `title` varchar(100) DEFAULT NULL COMMENT '消息模板标题',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信消息模板表';

-- ----------------------------
-- Table structure for msg_type
-- ----------------------------
DROP TABLE IF EXISTS `msg_type`;
CREATE TABLE `msg_type` (
  `msg_type_code` varchar(10) NOT NULL COMMENT '消息类型编码',
  `msg_type_name` varchar(20) DEFAULT NULL COMMENT '消息类型名称',
  PRIMARY KEY (`msg_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统消息类型表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `log_id` varchar(40) NOT NULL,
  `http_method` varchar(10) DEFAULT NULL,
  `http_uri` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  `http_user_agent` varchar(200) DEFAULT NULL,
  `http_remote_host` varchar(20) DEFAULT NULL,
  `http_paramters` varchar(500) DEFAULT NULL,
  `execute_time` int(11) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
