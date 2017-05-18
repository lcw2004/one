/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50713
Source Host           : 127.0.0.1:3306
Source Database       : one

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-05-18 10:27:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flow_audit_item
-- ----------------------------
DROP TABLE IF EXISTS `flow_audit_item`;
CREATE TABLE `flow_audit_item` (
  `audit_item_id` varchar(40) NOT NULL,
  `task_name` varchar(80) DEFAULT NULL,
  `business_type` varchar(50) DEFAULT NULL,
  `apply_user_id` varchar(40) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `effect_time` datetime DEFAULT NULL,
  `audit_status` int(11) DEFAULT NULL,
  `flow_instance_id` varchar(40) DEFAULT NULL,
  `flow_definition_id` varchar(60) DEFAULT NULL,
  `business_id` varchar(100) DEFAULT NULL,
  `is_effective` int(11) DEFAULT '0',
  `is_finished` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`audit_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flow_audit_item
-- ----------------------------
INSERT INTO `flow_audit_item` VALUES ('845893a3a184466ea6588d757a1082db', '参数-交易时间变更审核', 'ParamTradeTimeChange', '8739116988', '2017-05-11 14:11:09', null, '1', '55001', 'ParamTradeTimeChange', '5749500575', '0', '0', null);
INSERT INTO `flow_audit_item` VALUES ('932db9cc5fd44e329e7a09c8eddee9d1', '供应商投标类别审核', 'SupplierPurchaseTypeApply', '1', '2017-05-11 18:19:36', null, '3', '80018', 'SupplierPurchaseTypeApply', '00001', '0', '1', null);
INSERT INTO `flow_audit_item` VALUES ('95cae01f980c49d08ef25f64c8bf8b85', '参数-交易时间变更审核', 'ParamTradeTimeChange', '5749500575', '2017-05-11 16:23:14', null, '1', '60001', 'ParamTradeTimeChange', '8739116988', '0', '0', null);
INSERT INTO `flow_audit_item` VALUES ('b9669eef63cf4254b9c7f094710cdcf9', '供应商投标类别审核', 'SupplierPurchaseTypeApply', '1', '2017-05-11 18:18:07', '2017-05-11 18:19:00', '2', '80001', 'SupplierPurchaseTypeApply', '00001', '1', '1', null);
INSERT INTO `flow_audit_item` VALUES ('cc876f211bce4e10bc34a635deacfc92', '供应商投标类别审核', 'SupplierPurchaseTypeApply', '1', '2017-05-11 17:35:12', null, '1', '75001', 'SupplierPurchaseTypeApply', '1231', '0', '0', null);
INSERT INTO `flow_audit_item` VALUES ('d0e5c3781e874d5785c7cf644b692182', null, 'ParamTradeTimeChange', '8739116988', '2017-05-11 12:04:36', null, '1', '47513', 'ParamTradeTimeChange', '5749500575', '0', '0', null);
INSERT INTO `flow_audit_item` VALUES ('d5368d7fdc354a1fbec4dc27fcd33ba8', '供应商投标类别审核', 'SupplierPurchaseTypeApply', '1', '2017-05-11 18:54:02', null, '1', '82501', 'SupplierPurchaseTypeApply', '8739116988,9868839cef', '0', '0', null);
INSERT INTO `flow_audit_item` VALUES ('df837738e0a74e70af75bcd999440836', '供应商审核', null, '5749500575', '2017-05-11 11:47:54', null, '1', '45001', 'ParamTradeTimeChange', '8739116988', '0', '0', null);
INSERT INTO `flow_audit_item` VALUES ('ed44f27e6aa4436f8aa62e564b30cf66', '供应商投标类别审核', 'SupplierPurchaseTypeApply', '1', '2017-05-11 18:08:55', null, '1', '77505', 'SupplierPurchaseTypeApply', '00001', '0', '0', null);
INSERT INTO `flow_audit_item` VALUES ('f114ea0e96624188a1d7344008196d7a', null, 'ParamTradeTimeChange', '8739116988', '2017-05-11 12:01:44', null, '1', '47501', 'ParamTradeTimeChange', '5749500575', '0', '0', null);

-- ----------------------------
-- Table structure for flow_audit_log
-- ----------------------------
DROP TABLE IF EXISTS `flow_audit_log`;
CREATE TABLE `flow_audit_log` (
  `audit_log_id` varchar(40) NOT NULL,
  `audit_item_id` varchar(40) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  `result` int(11) DEFAULT NULL,
  `remark` text,
  `operate_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`audit_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flow_audit_log
-- ----------------------------
INSERT INTO `flow_audit_log` VALUES ('090f2800c548452da09fbdd29d7a5e26', '932db9cc5fd44e329e7a09c8eddee9d1', '2017-05-11 18:35:40', '1', '0', '111111', '审核');
INSERT INTO `flow_audit_log` VALUES ('1bf9c05f2d284e8f9aedd8a070e76324', '932db9cc5fd44e329e7a09c8eddee9d1', '2017-05-11 18:20:06', '1', '0', '111111', '审核');
INSERT INTO `flow_audit_log` VALUES ('2cdd179661964d699564d3eddc17b8c5', 'cc876f211bce4e10bc34a635deacfc92', '2017-05-11 18:07:20', '1', '1', '111111', '审核');
INSERT INTO `flow_audit_log` VALUES ('2d2fde53ebe54569b6aed1d758b32698', 'df837738e0a74e70af75bcd999440836', '2017-05-11 13:16:03', '1', '1', null, '交易时间审核');
INSERT INTO `flow_audit_log` VALUES ('4d81fbf55b6e4630a78452ab8597f61c', '845893a3a184466ea6588d757a1082db', '2017-05-11 14:28:28', '1', '1', '', '交易时间审核');
INSERT INTO `flow_audit_log` VALUES ('9df291c3dc204b0fb63e0885570915ab', 'b9669eef63cf4254b9c7f094710cdcf9', '2017-05-11 18:18:40', '1', '1', '111111', '审核');
INSERT INTO `flow_audit_log` VALUES ('d2ff05a7e8074e518b8ace543c305983', 'ed44f27e6aa4436f8aa62e564b30cf66', '2017-05-11 18:10:31', '1', '1', '111111', '审核');

-- ----------------------------
-- Table structure for flow_state
-- ----------------------------
DROP TABLE IF EXISTS `flow_state`;
CREATE TABLE `flow_state` (
  `PROCESS_KEY` varchar(200) NOT NULL,
  `PROCESS_STATE` int(11) DEFAULT NULL,
  `UP_TIME` datetime DEFAULT NULL,
  `PROCESS_DEFINITION_ID` varchar(100) DEFAULT NULL,
  `PROCESS_NAME` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`PROCESS_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flow_state
-- ----------------------------

-- ----------------------------
-- Table structure for flow_task_info
-- ----------------------------
DROP TABLE IF EXISTS `flow_task_info`;
CREATE TABLE `flow_task_info` (
  `PROCESS_KEY` varchar(200) NOT NULL,
  `TASK_KEY` varchar(200) NOT NULL,
  `BIND_ROLE` varchar(200) DEFAULT NULL,
  `BIND_FORM` varchar(200) DEFAULT NULL,
  `TASK_NAME` varchar(200) DEFAULT NULL,
  `VALID` int(11) DEFAULT NULL,
  `TASK_DESC` varchar(200) DEFAULT NULL,
  `RANK_NUM` int(11) DEFAULT NULL,
  PRIMARY KEY (`PROCESS_KEY`,`TASK_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flow_task_info
-- ----------------------------

-- ----------------------------
-- Table structure for process_state
-- ----------------------------
DROP TABLE IF EXISTS `process_state`;
CREATE TABLE `process_state` (
  `PK_PROCESS_KEY` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `PROCESS_STATE` int(11) DEFAULT NULL,
  `UP_TIME` datetime DEFAULT NULL,
  `PROCESS_DEFINITION_ID` varchar(64) DEFAULT NULL,
  `PROCESS_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`PK_PROCESS_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of process_state
-- ----------------------------
INSERT INTO `process_state` VALUES ('AgentParamtersChange', '2', '2017-04-17 16:11:38', 'AgentParamtersChange:4:1610169', '经纪会员参数变更');
INSERT INTO `process_state` VALUES ('AgreementTemplateChange', '3', '2017-03-10 18:27:56', 'AgreementTemplateChange:18:277682', '协议转让配售模版审核流程');
INSERT INTO `process_state` VALUES ('ArticleContentOther', '2', '2017-04-01 21:05:04', 'ArticleContentOther:26:1297564', '非交易所公告和产品公告文章审核');
INSERT INTO `process_state` VALUES ('ArticleContentProductAndExchange', '3', '2017-04-01 21:05:11', 'ArticleContentProductAndExchange:24:1297568', '交易所公告和产品公告文章审核');
INSERT INTO `process_state` VALUES ('ArticleGroupAudit', '3', '2017-04-01 21:05:19', 'ArticleGroupAudit:27:1297572', '文章分组审核');
INSERT INTO `process_state` VALUES ('BazaarAccountChange', '3', '2017-03-22 16:00:02', 'BazaarAccountChange:1:1000012', '市场公司变更账户申请');
INSERT INTO `process_state` VALUES ('BazaarApply', '3', '2017-03-22 15:57:09', 'BazaarApply:4:1000008', '市场公司开户申请');
INSERT INTO `process_state` VALUES ('BazaarBankChange', '2', '2017-04-05 14:28:11', 'BazaarBankChange:3:1307691', '市场公司变更银行卡申请');
INSERT INTO `process_state` VALUES ('BazaarInfoChange', '3', '2017-03-22 17:23:26', 'BazaarInfoChange:1:1000065', '市场公司变更资料申请');
INSERT INTO `process_state` VALUES ('BorkerMemberBan', '2', '2017-04-13 09:13:03', 'BorkerMemberBan:10:1517703', '取缔经纪会员');
INSERT INTO `process_state` VALUES ('BorkerMemberPositionsFrozenChange', '2', '2017-03-10 18:27:56', 'BorkerMemberPositionsFrozenChange:99:277656', '经纪会员配售持仓冻结');
INSERT INTO `process_state` VALUES ('BorkerMemberPositionsUnFrozenChange', '2', '2017-03-10 18:27:56', 'BorkerMemberPositionsUnFrozenChange:81:277672', '经纪会员配售持仓冻结');
INSERT INTO `process_state` VALUES ('BorkerMemberStateChange', '2', '2017-03-10 18:27:56', 'BorkerMemberStateChange:80:277681', '经纪会员账户状态变更');
INSERT INTO `process_state` VALUES ('BrokerMemberBelongChange', '2', '2017-03-10 18:27:56', 'BrokerMemberBelongChange:81:277660', '经纪会员归属变更申请');
INSERT INTO `process_state` VALUES ('BrokerMemberFrozenChange', '2', '2017-03-10 18:27:56', 'BrokerMemberFrozenChange:78:277677', '经纪会员冻结状态变更');
INSERT INTO `process_state` VALUES ('BrokerMemberHierarchyChange', '2', '2017-03-10 18:27:56', 'BrokerMemberHierarchyChange:78:277678', '经纪会员层级变更');
INSERT INTO `process_state` VALUES ('BrokerMemberLevelChange', '2', '2017-04-05 15:08:52', 'BrokerMemberLevelChange:179:1307695', '经纪会员等级变更');
INSERT INTO `process_state` VALUES ('BrokerMemberPermissionChange', '2', '2017-03-30 17:21:41', 'BrokerMemberPermissionChange:63:1240055', '经济会员权限变更审核');
INSERT INTO `process_state` VALUES ('ClearTimeParamChange', '3', '2017-04-14 14:16:41', 'ClearTimeParamChange:1:1582508', '清结算时间变更');
INSERT INTO `process_state` VALUES ('GoldTimeConfChange', '3', '2017-04-14 14:16:48', 'GoldTimeConfChange:1:1582512', '出入金时间变更');
INSERT INTO `process_state` VALUES ('IssuerAccountChange', '2', '2017-03-10 18:27:56', 'IssuerAccountChange:37:277674', '发行商账户变更');
INSERT INTO `process_state` VALUES ('IssuerApply', '3', '2017-04-18 17:02:47', 'IssuerApply:182:1700075', ' 委托发售方申请');
INSERT INTO `process_state` VALUES ('IssuerBankChange', '2', '2017-03-10 18:27:56', 'IssuerBankChange:37:277661', '发行商银行卡变更');
INSERT INTO `process_state` VALUES ('IssuerInfoChange', '2', '2017-03-10 18:27:56', 'IssuerInfoChange:82:277680', '发行商资料变更申请');
INSERT INTO `process_state` VALUES ('IssuerPermissionChange', '2', '2017-03-30 16:44:03', 'IssuerPermissionChange:8:1240035', '委托发售方权限变更审核');
INSERT INTO `process_state` VALUES ('IssurePermissionChange', '2', '2017-03-10 18:27:56', 'IssurePermissionChange:59:277676', '发行商权限变更');
INSERT INTO `process_state` VALUES ('IssureRaiseChange', '3', '2017-03-31 11:51:23', 'IssureRaiseChange:1:1260012', '募集资金计划变更');
INSERT INTO `process_state` VALUES ('IssureRaiseUnfreeze', '3', '2017-03-31 11:51:09', 'IssureRaiseUnfreeze:1:1260008', '募集资金计划解冻');
INSERT INTO `process_state` VALUES ('IssureStateChange', '2', '2017-03-10 18:27:56', 'IssureStateChange:59:277659', '发行商状态变更');
INSERT INTO `process_state` VALUES ('MemberApplyChange', '3', '2017-03-10 18:27:56', 'MemberApplyChange:78:277664', '会员实名认证');
INSERT INTO `process_state` VALUES ('MemberFrozenChange', '2', '2017-03-10 18:27:56', 'MemberFrozenChange:79:277687', '会员冻结状态变更');
INSERT INTO `process_state` VALUES ('MemberInfoChange', '3', '2017-04-06 13:12:08', 'MemberInfoChange:177:1350036', '会员信息变更');
INSERT INTO `process_state` VALUES ('MemberLevelChange', '2', '2017-04-01 15:46:56', 'MemberLevelChange:176:1287649', '会员等级变更');
INSERT INTO `process_state` VALUES ('MemberParamtersChange', '3', '2017-04-17 16:12:05', 'MemberParamtersChange:2:1610173', '交易会员参数变更');
INSERT INTO `process_state` VALUES ('MemberPermissionApply', '3', '2017-03-15 14:23:42', 'MemberPermissionApply:2:477508', '前台用户权限申请');
INSERT INTO `process_state` VALUES ('MemberPermissionChange', '2', '2017-03-16 11:46:54', 'MemberPermissionChange:13:590126', '会员权限变更');
INSERT INTO `process_state` VALUES ('MemberPermissionFreeze', '2', '2017-03-30 17:21:34', 'MemberPermissionFreeze:83:1240051', '前台用户权限冻结');
INSERT INTO `process_state` VALUES ('MemberPermissionUnFreeze', '2', '2017-03-30 17:20:15', 'MemberPermissionUnFreeze:69:1240047', '前台用户权限解冻');
INSERT INTO `process_state` VALUES ('MemberPositionsFrozenChange', '2', '2017-03-10 18:27:56', 'MemberPositionsFrozenChange:78:277662', '会员普通持仓冻结');
INSERT INTO `process_state` VALUES ('MemberPositionsUnFrozenChange', '2', '2017-03-10 18:27:56', 'MemberPositionsUnFrozenChange:78:277675', '会员普通持仓解冻');
INSERT INTO `process_state` VALUES ('MemberStateChange', '2', '2017-03-10 18:27:56', 'MemberStateChange:79:277665', '会员销户');
INSERT INTO `process_state` VALUES ('MessageSendApply', '3', '2017-04-19 17:00:43', 'MessageSendApply:2:1735004', '手工发送消息审核');
INSERT INTO `process_state` VALUES ('MessageTemplateChange', '3', '2017-04-19 17:00:51', 'MessageTemplateChange:2:1735008', '消息模板变更审核');
INSERT INTO `process_state` VALUES ('OperationAccountChange', '3', '2017-03-23 14:14:24', 'OperationAccountChange:1:1032504', '运营公司变更账户申请');
INSERT INTO `process_state` VALUES ('OperationApply', '3', '2017-03-23 14:15:58', 'OperationApply:1:1032508', '运营公司开户申请');
INSERT INTO `process_state` VALUES ('OperationBankChange', '3', '2017-03-23 14:16:28', 'OperationBankChange:1:1032512', '运营公司变更银行卡申请');
INSERT INTO `process_state` VALUES ('OperationInfoChange', '3', '2017-03-23 14:17:23', 'OperationInfoChange:1:1032516', '运营公司变更资料申请');
INSERT INTO `process_state` VALUES ('ParamCommissionWithdrawAudit', '3', '2017-04-18 14:25:53', 'ParamCommissionWithdrawAudit:1:1493951', '经纪会员佣金出金');
INSERT INTO `process_state` VALUES ('ParamExchangeWithdrawAudit', '3', '2017-04-18 14:26:05', 'ParamExchangeWithdrawAudit:1:1493955', '交易所出金设置审核');
INSERT INTO `process_state` VALUES ('ParamMemberWithdrawAudit', '3', '2017-04-18 14:26:12', 'ParamMemberWithdrawAudit:1:1493959', '交易会员出金审核');
INSERT INTO `process_state` VALUES ('ParamSellWithdrawAudit', '3', '2017-04-19 13:49:33', 'ParamSellWithdrawAudit:1:1722516', '委托发售方出金审核');
INSERT INTO `process_state` VALUES ('ParamSellWithdrawAuditAudit', '3', '2017-04-18 14:26:20', 'ParamSellWithdrawAuditAudit:1:1493963', '交易会员出金审核');
INSERT INTO `process_state` VALUES ('ParamTempStopTrade', '3', '2017-04-13 18:02:18', 'ParamTempStopTrade:2:1540278', '参数-临时停市审核');
INSERT INTO `process_state` VALUES ('ParamTradeDateChange', '3', '2017-04-17 16:16:48', 'ParamTradeDateChange:2:1645047', '参数-开市日期变更审核');
INSERT INTO `process_state` VALUES ('ParamTradePriceWaveRange', '3', '2017-04-11 18:05:18', 'ParamTradePriceWaveRange:2:1485038', '参数-交易价格变动单位变更审核');
INSERT INTO `process_state` VALUES ('ParamTradeRoutineStop', '1', '2017-04-17 14:16:21', 'ParamTradeRoutineStop:1:1605152', '例行暂停交易审核');
INSERT INTO `process_state` VALUES ('ParamTradeStopService', '2', '2017-04-12 17:23:23', 'ParamTradeStopService:3:1515048', '临时暂停交易审核流程');
INSERT INTO `process_state` VALUES ('ParamTradeTimeChange', '3', '2017-04-13 18:02:28', 'ParamTradeTimeChange:3:1532541', '参数-交易时间变更审核');
INSERT INTO `process_state` VALUES ('ParamTradeTurnoverRate', '1', '2017-04-14 16:23:13', 'ParamTradeTurnoverRate:1:1587521', '换手率暂停交易审核');
INSERT INTO `process_state` VALUES ('PayChannelSettingChange', '3', '2017-04-17 11:01:57', 'PayChannelSettingChange:1:1612508', '支付渠道审核');
INSERT INTO `process_state` VALUES ('PramTradePriceWaveRange', '3', '2017-04-11 13:24:06', 'PramTradePriceWaveRange:2:1457538', '参数-交易价格变动单位变更审核');
INSERT INTO `process_state` VALUES ('ProdFrozenAudit', '3', '2017-03-30 10:12:24', 'ProdFrozenAudit:2:1002542', '人工冻结审核');
INSERT INTO `process_state` VALUES ('ProdUnfrozenAudit', '3', '2017-03-30 10:13:27', 'ProdUnfrozenAudit:2:1002546', '人工解冻审核');
INSERT INTO `process_state` VALUES ('ProductFreezeToIssuer', '2', '2017-03-10 18:27:56', 'ProductFreezeToIssuer:76:277686', '产品配售冻结到发售方账户流程');
INSERT INTO `process_state` VALUES ('ProductGenNumber', '2', '2017-03-10 18:27:56', 'ProductGenNumber:76:277688', '产品配号提醒流程');
INSERT INTO `process_state` VALUES ('ProductLuckyNumber', '2', '2017-03-10 18:27:56', 'ProductLuckyNumber:76:277657', '产品中签提醒流程');
INSERT INTO `process_state` VALUES ('ProductOfflintToOnline', '2', '2017-03-10 18:27:56', 'ProductOfflintToOnline:76:277683', '产品配售线下转线上流程');
INSERT INTO `process_state` VALUES ('ProductPlacing', '2', '2017-03-10 18:27:56', 'ProductPlacing:83:277663', '产品配售流程');
INSERT INTO `process_state` VALUES ('ProductPlacingPlanSet', '2', '2017-03-10 18:27:56', 'ProductPlacingPlanSet:78:277684', '产品发售流程(配售计划变更)');
INSERT INTO `process_state` VALUES ('ProductRePlacing', '2', '2017-03-10 18:27:56', 'ProductRePlacing:76:277667', '产品配售重新指派流程');
INSERT INTO `process_state` VALUES ('ProductSale', '3', '2017-04-10 11:56:34', 'ProductSale:180:1415007', '产品发售流程');
INSERT INTO `process_state` VALUES ('ProductSaleAgainOrModifyBaseInfo', '2', '2017-03-10 18:27:56', 'ProductSaleAgainOrModifyBaseInfo:78:277669', '产品发售流程(增发或者修改基本信息)');
INSERT INTO `process_state` VALUES ('ProductSaleModifyLaunchInfo', '2', '2017-03-10 18:27:56', 'ProductSaleModifyLaunchInfo:76:277685', '产品发售流程(修改公开上市信息)');
INSERT INTO `process_state` VALUES ('ProductSaleModifyOtherInfo', '2', '2017-03-10 18:27:56', 'ProductSaleModifyOtherInfo:78:277666', '产品发售流程(修改其他信息)');
INSERT INTO `process_state` VALUES ('ProductYaoHao', '2', '2017-03-10 18:27:56', 'ProductYaoHao:76:277673', '产品摇号提醒流程');
INSERT INTO `process_state` VALUES ('SystemRoleChange', '2', '2017-03-30 17:18:28', 'SystemRoleChange:50:1240043', '系统后台角色审核');
INSERT INTO `process_state` VALUES ('SystemUserChange', '3', '2017-03-16 13:16:23', 'SystemUserChange:3:635135', '系统后台用户审核');
INSERT INTO `process_state` VALUES ('TradeRoleChange', '3', '2017-03-16 13:16:37', 'TradeRoleChange:4:635139', '前台用户角色审核');
INSERT INTO `process_state` VALUES ('UserApplyBrokerMember', '3', '2017-04-07 14:02:14', 'UserApplyBrokerMember:176:1392519', '经纪会员申请');
INSERT INTO `process_state` VALUES ('myProcess', '2', '2017-03-16 18:29:25', 'myProcess:4:655031', 'My process');

-- ----------------------------
-- Table structure for process_task_info
-- ----------------------------
DROP TABLE IF EXISTS `process_task_info`;
CREATE TABLE `process_task_info` (
  `PK_PROCESS_KEY` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '流程定义KEY',
  `PK_TASK_KEY` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '任务定义KEY',
  `BIND_ROLE` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '绑定角色',
  `BIND_FORM` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '绑定表单访问地址',
  `TASK_NAME` varchar(128) COLLATE utf8_bin NOT NULL,
  `RANK_NUM` int(11) DEFAULT '1',
  `VALID` int(11) NOT NULL DEFAULT '1',
  `TASK_DESC` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`PK_PROCESS_KEY`,`PK_TASK_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='流程任务配置信息表';

-- ----------------------------
-- Records of process_task_info
-- ----------------------------
INSERT INTO `process_task_info` VALUES ('AgentParamtersChange', 'step1', 'testRole', 'memberParamters/memberParamtersInfoAudit', '一级审核', '1', '1', '一级审核');
INSERT INTO `process_task_info` VALUES ('AgentParamtersChange', 'step2', 'testRole', 'memberParamters/memberParamtersInfoAudit', '修改重新提交审核', '2', '1', '修改重新提交审核');
INSERT INTO `process_task_info` VALUES ('AgreementTemplateChange', 'AgreementTemplateMarketAudit', 'testRole', '/agreement/agreementTemplateMarketAudit', '市场公司审核', '1', '1', '协议转让模版市场公司审核');
INSERT INTO `process_task_info` VALUES ('AgreementTemplateChange', 'AgreementTemplateOperateAudit', 'testRole', '/agreement/agreementTemplateOperateAudit', '运营公司审核', '2', '1', '协议转让模版运营公司审核');
INSERT INTO `process_task_info` VALUES ('AgreementTemplateChange', 'AgreementTemplateSubmitAgain', 'testRole', '/agreement/redirectAgreementTemplate', '驳回重新提交', '3', '1', '协议转让模版审核驳回重新填写');
INSERT INTO `process_task_info` VALUES ('ArticleContentOther', 'OperatingCompanyVicePresidentAudit', '100', 'articleAudit/auditInHand/page', '运营公司副总审核', '1', '1', '运营公司副总审核');
INSERT INTO `process_task_info` VALUES ('ArticleContentProductAndExchange', 'ExchangeSaleAuditCommissionerAudit', '100', 'articleAudit/auditInHand/page', '交易所发售审计专员审核', '1', '1', '交易所发售审计专员审核');
INSERT INTO `process_task_info` VALUES ('ArticleGroupAudit', 'OperatingCompanyVicePresidentAudit', '100', 'articleAudit/auditInHand/page', '运营公司副总审核', '1', '1', '运营公司副总审核');
INSERT INTO `process_task_info` VALUES ('BazaarAccountChange', 'exchangeAudit', 'testRole', 'bazaar/bazaarChangeAudit', '交易所负责人审核', '1', '1', '交易所负责人审核');
INSERT INTO `process_task_info` VALUES ('BazaarApply', 'exchangeAudit', 'testRole', 'bazaar/bazaarChangeAudit', '交易所负责人审核', '1', '1', '交易所负责人审核');
INSERT INTO `process_task_info` VALUES ('BazaarBankChange', 'exchangeAudit', 'testRole', 'bazaar/bazaarChangeAudit', '公司负责人审核 ', '1', '1', '公司负责人审核 ');
INSERT INTO `process_task_info` VALUES ('BazaarInfoChange', 'exchangeAudit', 'testRole', 'bazaar/bazaarChangeAudit', '交易所负责人审核', '1', '1', '交易所负责人审核');
INSERT INTO `process_task_info` VALUES ('BorkerMemberBan', 'oneAudit', 'testRole', 'borker/borkerChangeAudit', '一级审核', '1', '1', '一级审核');
INSERT INTO `process_task_info` VALUES ('BorkerMemberBan', 'twoAudit', 'testRole', 'borker/borkerChangeAudit', '二级审核', '2', '1', '二级审核');
INSERT INTO `process_task_info` VALUES ('BorkerMemberPositionsFrozenChange', 'exchangeAudit', null, null, '交易所风控专员审核', '1', '1', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('BorkerMemberPositionsUnFrozenChange', 'exchangeAudit', null, null, '交易所风控专员审核', '1', '1', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('BorkerMemberStateChange', '市场公司副总审核', null, null, '市场公司副总审核', '1', '1', '市场公司副总审核');
INSERT INTO `process_task_info` VALUES ('BrokerMemberBelongChange', 'marketAudit', 'testRole', 'borker/borkerChangeAudit', '市场公司副总审批', '1', '1', '市场公司副总审批');
INSERT INTO `process_task_info` VALUES ('BrokerMemberBelongChange', 'operAudit', 'testRole', 'borker/borkerChangeAudit', '运营公司副总复审', '1', '1', '运营公司副总复审');
INSERT INTO `process_task_info` VALUES ('BrokerMemberFrozenChange', 'exchangeAudit', 'testRole', 'borker/borkerChangeAudit', '交易所风控专员审核', '1', '1', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('BrokerMemberHierarchyChange', 'marketAudit', 'testRole', 'borker/borkerChangeAudit', '市场公司主管副总经理审批', '1', '1', '市场公司主管副总经理审批');
INSERT INTO `process_task_info` VALUES ('BrokerMemberHierarchyChange', 'marketChannelAudit', 'testRole', 'borker/borkerChangeAudit', '市场公司渠道拓展负责人审批', '1', '1', '市场公司渠道拓展负责人审批');
INSERT INTO `process_task_info` VALUES ('BrokerMemberLevelChange', 'channelAudit', 'testRole', 'borker/borkerChangeAudit', '渠道拓展负责人审批', '1', '1', '渠道拓展负责人审批');
INSERT INTO `process_task_info` VALUES ('BrokerMemberLevelChange', 'marketAudit', 'testRole', 'borker/borkerChangeAudit', '市场公司负责人审批', '2', '1', '市场公司负责人审批');
INSERT INTO `process_task_info` VALUES ('BrokerMemberPermissionChange', 'finalAudit', 'testRole', 'member/changeAudit', '运营公司副总审核', '2', '1', '最后一个人审批');
INSERT INTO `process_task_info` VALUES ('BrokerMemberPermissionChange', 'fistAudit', 'testRole', 'member/changeAudit', '市场公司副总审核', '1', '1', '第一个人审批');
INSERT INTO `process_task_info` VALUES ('ClearTimeParamChange', 'exchangeAudit', '100', 'time/changeAudit', '交易所财务经理审核 ', '1', '1', '交易所财务经理审核 ');
INSERT INTO `process_task_info` VALUES ('GoldTimeConfChange', 'exchangeAudit', '100', 'time/changeAudit', '交易所财务经理审核 ', '1', '1', '交易所财务经理审核 ');
INSERT INTO `process_task_info` VALUES ('IssuerAccountChange', 'exchangeAudit', 'testRole', 'issure/changeAudit', '交易所财务专员审核', '1', '1', '交易所审核');
INSERT INTO `process_task_info` VALUES ('IssuerApply', 'exchangeAudit', '100', 'issure/changeAudit', '交易所发售审计专员终审', '2', '1', '交易所审核');
INSERT INTO `process_task_info` VALUES ('IssuerApply', 'marketAudit', '100', 'issure/changeAudit', '市场公司发售审计专员初审', '3', '1', '市场公司');
INSERT INTO `process_task_info` VALUES ('IssuerApply', 'operAudit', '100', 'issure/changeAudit', '运营公司发售审计专员复审', '1', '1', '运营公司发售审计专员初审');
INSERT INTO `process_task_info` VALUES ('IssuerBankChange', 'exchangeAudit', 'testRole', 'issure/changeAudit', '交易所财务专员审核', '1', '1', '交易所审核');
INSERT INTO `process_task_info` VALUES ('IssuerInfoChange', 'exchangeAudit', 'testRole', 'issure/changeAudit', '交易所发售审计专员复审', '1', '1', '交易所审核');
INSERT INTO `process_task_info` VALUES ('IssuerInfoChange', 'marketAudit', 'testRole', 'issure/changeAudit', '市场公司', '1', '1', '市场公司');
INSERT INTO `process_task_info` VALUES ('IssuerInfoChange', 'operAudit', 'testRole', 'issure/changeAudit', '运营公司发售审计专员初审', '1', '1', '运营公司发售审计专员初审');
INSERT INTO `process_task_info` VALUES ('IssuerPermissionChange', 'finalAudit', 'testRole', 'issure/changeAudit', '运营公司副总审核', '2', '1', '运营公司副总审核');
INSERT INTO `process_task_info` VALUES ('IssuerPermissionChange', 'fistAudit', 'testRole', 'issure/changeAudit', '市场公司副总审核', '1', '1', '市场公司副总审核');
INSERT INTO `process_task_info` VALUES ('IssurePermissionChange', 'exchangeAudit', 'testRole', 'issure/changeAudit', '交易所发售审计专员复审', '1', '1', '交易所审核');
INSERT INTO `process_task_info` VALUES ('IssurePermissionChange', 'marketAudit', 'testRole', 'issure/changeAudit', '市场公司', '1', '1', '市场公司');
INSERT INTO `process_task_info` VALUES ('IssurePermissionChange', 'operAudit', 'testRole', 'issure/changeAudit', '运营公司发售审计专员初审', '1', '1', '运营公司发售审计专员初审');
INSERT INTO `process_task_info` VALUES ('IssureRaiseChange', 'exchangeAudit', '100', 'issureRaise/changeAudit', '交易所财务专员复审', '2', '1', '交易所财务专员复审');
INSERT INTO `process_task_info` VALUES ('IssureRaiseChange', 'operateAudit', '100', 'issureRaise/changeAudit', '运营公司财务专员初审', '1', '1', '运营公司财务专员初审');
INSERT INTO `process_task_info` VALUES ('IssureRaiseUnfreeze', 'exchangeAudit', '100', 'issureRaise/changeAudit', '交易所财务专员复审', '2', '1', '交易所财务专员复审');
INSERT INTO `process_task_info` VALUES ('IssureRaiseUnfreeze', 'operateAudit', '100', 'issureRaise/changeAudit', '运营公司财务专员初审', '1', '1', '运营公司财务专员初审');
INSERT INTO `process_task_info` VALUES ('IssureStateChange', 'exchangeAudit', 'testRole', 'issure/changeAudit', '交易所发售审计专员复审', '1', '1', '交易所审核');
INSERT INTO `process_task_info` VALUES ('IssureStateChange', 'marketAudit', 'testRole', 'issure/changeAudit', '市场公司', '1', '1', '市场公司');
INSERT INTO `process_task_info` VALUES ('IssureStateChange', 'operAudit', 'testRole', 'issure/changeAudit', '运营公司发售审计专员初审', '1', '1', '运营公司发售审计专员初审');
INSERT INTO `process_task_info` VALUES ('MemberApplyChange', 'operAudit', '126', 'member/changeAudit', '运营公司运营部经理审核', '1', '1', '运营公司运营部经理审核');
INSERT INTO `process_task_info` VALUES ('MemberFrozenChange', 'exchangeAudit', 'testRole', 'member/changeAudit', '交易所风控专员审核', '1', '1', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('MemberInfoChange', 'marketAudit', 'testRole', 'member/changeAudit', '一级审核', '1', '1', '市场公司客服审核');
INSERT INTO `process_task_info` VALUES ('MemberInfoChange', 'resubmit', 'testRole', 'member/changeAudit', '修改重新提交', '2', '1', '修改重新提交');
INSERT INTO `process_task_info` VALUES ('MemberLevelChange', 'operAudit', 'testRole', 'member/changeAudit', '运营公司运营部经理审核', '1', '1', '运营公司运营部经理审核');
INSERT INTO `process_task_info` VALUES ('MemberParamtersChange', 'step1', 'testRole', 'memberParamters/memberParamtersInfoAudit', '一级审核', '1', '1', '一级审核');
INSERT INTO `process_task_info` VALUES ('MemberParamtersChange', 'step2', 'testRole', 'memberParamters/memberParamtersInfoAudit', '修改重新提交审核', '2', '1', '修改重新提交审核');
INSERT INTO `process_task_info` VALUES ('MemberPermissionApply', 'busiAudit', 'testRole', 'member/changeAudit', '市场公司审核', '1', '1', '市场公司审核');
INSERT INTO `process_task_info` VALUES ('MemberPermissionChange', 'finalAudit', 'testRole', 'member/changeAudit', '交易所风控专员审核', '1', '1', '市场公司审核');
INSERT INTO `process_task_info` VALUES ('MemberPermissionFreeze', 'busiAudit', 'testRole', 'member/changeAudit', '市场公司审核', '1', '1', '市场公司审核');
INSERT INTO `process_task_info` VALUES ('MemberPermissionFreeze', 'exchangeAudit', 'testRole', 'member/changeAudit', '交易所风控专员审核', '1', '0', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('MemberPermissionUnFreeze', 'busiAudit', 'testRole', 'member/changeAudit', '市场公司审核', '1', '1', '市场公司审核');
INSERT INTO `process_task_info` VALUES ('MemberPositionsFrozenChange', 'exchangeAudit', 'testRole', null, '交易所风控专员审核', '1', '1', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('MemberPositionsUnFrozenChange', 'exchangeAudit', null, null, '交易所风控专员审核', '1', '1', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('MemberStateChange', 'busiAudit', 'testRole', 'member/changeAudit', '市场公司审核', '1', '1', '市场公司审核');
INSERT INTO `process_task_info` VALUES ('MessageSendApply', 'finalAudit', 'testRole', 'messageSend/page/messageSendAudit', '交易所风控专员审核', '1', '1', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('MessageSendApply', 'reEdit', 'testRole', 'messageSend/page/messageSendReEdit', '驳回重新修改', '2', '1', '驳回重新修改');
INSERT INTO `process_task_info` VALUES ('MessageTemplateChange', 'finalAudit', 'testRole', 'messageTemplate/page/messageTemplateAudit', '交易所风控专员审核', '1', '1', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('MessageTemplateChange', 'reEdit', 'testRole', 'messageTemplate/page/messageTemplateReEdit', '驳回重新修改', '2', '1', '驳回重新修改');
INSERT INTO `process_task_info` VALUES ('OperationAccountChange', 'exchangeAudit', 'testRole', 'operation/operationChangeAudit', '交易所负责人审核', '1', '1', '交易所负责人审核');
INSERT INTO `process_task_info` VALUES ('OperationApply', 'exchangeAudit', 'testRole', 'operation/operationChangeAudit', '交易所负责人审核', '1', '1', '交易所负责人审核');
INSERT INTO `process_task_info` VALUES ('OperationBankChange', 'exchangeAudit', 'testRole', 'operation/operationChangeAudit', '公司负责人审核 ', '1', '1', '公司负责人审核 ');
INSERT INTO `process_task_info` VALUES ('OperationInfoChange', 'exchangeAudit', 'testRole', 'operation/operationChangeAudit', '交易所负责人审核', '1', '1', '交易所负责人审核');
INSERT INTO `process_task_info` VALUES ('ParamCommissionWithdrawAudit', 'CommissionWithdrawAuditStaffAudit', '100', 'withdrawParamters/processAuditCommission/page', '经纪会员佣金出金审核', '1', '1', '经纪会员佣金出金审核');
INSERT INTO `process_task_info` VALUES ('ParamExchangeWithdrawAudit', 'ExchangeWithdrawStaffAudit', '100', 'withdrawParamters/processAuditExchange/page', '交易所出金审核', '1', '1', '交易所出金审核');
INSERT INTO `process_task_info` VALUES ('ParamMemberWithdrawAudit', 'MemberWithdrawStaffAudit', '100', 'withdrawParamters/processAuditMember/page', '出金审核人员审核', '1', '1', '出金审核人员审核');
INSERT INTO `process_task_info` VALUES ('ParamSellWithdrawAudit', 'SellWithdrawStaffAudit', '100', 'withdrawParamters/processAuditSell/page', '委托发售方出金审核', '1', '1', '委托发售方出金审核');
INSERT INTO `process_task_info` VALUES ('ParamTempStopTrade', 'finalAudit', 'testRole', 'tempStopTrade/page/stopTradeAudit', '交易所风控专员审核', '1', '1', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('ParamTempStopTrade', 'reEdit', 'testRole', 'tempStopTrade/page/stopTradeReEdit', '驳回重新修改', '2', '1', '驳回重新修改');
INSERT INTO `process_task_info` VALUES ('ParamTradeDateChange', 'finalAudit', 'testRole', 'tradeTime/page/tradeTimeAudit', '交易所风控专员审核', '1', '1', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('ParamTradeDateChange', 'reEdit', 'testRole', 'tradeTime/page/tradeTimeReEdit', '驳回重新修改', '2', '1', '驳回重新修改');
INSERT INTO `process_task_info` VALUES ('ParamTradePriceWaveRange', 'finalAudit', 'testRole', 'priceRange/page/tradePriceAudit', '交易所风控专员审核', '1', '1', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('ParamTradePriceWaveRange', 'reEdit', 'testRole', 'priceRange/page/tradePriceReEdit', '驳回重新修改', '2', '1', '驳回重新修改');
INSERT INTO `process_task_info` VALUES ('ParamTradeRoutineStop', 'exchangeSubmitAgain', 'testRole', 'paramStopTradeControl/queryParamTradeRoutineStop', '驳回重新提交', '2', '1', '驳回重新提交');
INSERT INTO `process_task_info` VALUES ('ParamTradeRoutineStop', 'finalAudit', 'testRole', 'paramStopTradeControl/queryParamTradeRoutineStop', '运营公司审核', '1', '1', '运营公司审核');
INSERT INTO `process_task_info` VALUES ('ParamTradeStopService', 'exchangeSubmitAgain', 'testRole', 'paramStopTrade/showViewStopTrade', '驳回重新提交', '2', '1', '驳回重新提交');
INSERT INTO `process_task_info` VALUES ('ParamTradeStopService', 'finalAudit', 'testRole', 'paramStopTrade/showViewStopTrade', '运营公司审核', '1', '1', '运营公司审核');
INSERT INTO `process_task_info` VALUES ('ParamTradeTimeChange', 'finalAudit', 'testRole', 'tradeTime/page/tradeTimeAudit', '交易所风控专员审核', '1', '1', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('ParamTradeTimeChange', 'reEdit', 'testRole', 'tradeTime/page/tradeTimeReEdit', '驳回重新修改', '2', '1', '驳回重新修改');
INSERT INTO `process_task_info` VALUES ('ParamTradeTurnoverRate', 'exchangeSubmitAgain', 'testRole', 'paramStopTradeControl/queryParamTradeTurnoverRate', '驳回重新提交', '2', '1', '驳回重新提交');
INSERT INTO `process_task_info` VALUES ('ParamTradeTurnoverRate', 'finalAudit', 'testRole', 'paramStopTradeControl/queryParamTradeDictionListByParamId', '运营公司审核', '1', '1', '运营公司审核');
INSERT INTO `process_task_info` VALUES ('PayChannelSettingChange', 'exchangeAudit', 'testRole', 'payChannel/changeAudit', '交易所负责人审核', '1', '1', '交易所负责人审核');
INSERT INTO `process_task_info` VALUES ('PramTradePriceWaveRange', 'finalAudit', 'testRole', '1', '交易所风控专员审核', '1', '1', '交易所风控专员审核');
INSERT INTO `process_task_info` VALUES ('ProdFrozenAudit', 'exchangeFrozenAudit', 'testRole', 'memberFrozen/auditMemberPositionFrozenRecords', '交易所审核', '1', '1', '交易所审核');
INSERT INTO `process_task_info` VALUES ('ProdUnfrozenAudit', 'exchangeUnfrozenAudit', 'testRole', 'memberFrozen/auditMemberPositionUnFrozenRecords', '交易所审核', '1', '1', '交易所审核');
INSERT INTO `process_task_info` VALUES ('ProductFreezeToIssuer', 'audit', 'testRole', 'productPlacingStencil/page/placingStencilNotEnoughAuditInfo', '冻结到发售方账户审核', '1', '1', '冻结到发售方账户审核');
INSERT INTO `process_task_info` VALUES ('ProductFreezeToIssuer', 'markerSubmitAgain', 'testRole', '1111111', '冻结到发售方账户重新提交', '1', '1', '冻结到发售方账户重新提交');
INSERT INTO `process_task_info` VALUES ('ProductGenNumber', 'genNumber', 'testRole', '/buy/peihaoInfo', '配号', '1', '1', '配号');
INSERT INTO `process_task_info` VALUES ('ProductLuckyNumber', 'luckyNumber', 'testRole', '/buy/luckyInfo', '中签', '1', '1', '中签');
INSERT INTO `process_task_info` VALUES ('ProductOfflintToOnline', 'audit', 'testRole', 'productPlacingStencil/page/placingStencilNotEnoughAuditInfo', '线下转线上审核', '1', '1', '线下转线上审核');
INSERT INTO `process_task_info` VALUES ('ProductOfflintToOnline', 'markerSubmitAgain', 'testRole', '1111111', '线下转线上重新提交', '1', '1', '线下转线上重新提交');
INSERT INTO `process_task_info` VALUES ('ProductPlacing', 'markerAudit', 'testRole', 'productPlacingStencil/page/placingStencilAuditInfo', '配售模板市场审核', '1', '1', '配售模板市场审核');
INSERT INTO `process_task_info` VALUES ('ProductPlacing', 'markerSubmitAgain', 'testRole', 'productPlacingStencil/page/placingStencilReEdit', '配属模板重新提交', '1', '1', '配属模板重新提交');
INSERT INTO `process_task_info` VALUES ('ProductPlacing', 'operativerAudit', 'testRole', 'productPlacingStencil/page/placingStencilAuditInfo', '配售模板运营审核', '1', '1', '配售模板运营审核');
INSERT INTO `process_task_info` VALUES ('ProductPlacingPlanSet', 'auditLaunchInfo', 'testRole', '/exchangeAuditPublicMarket', '审核公开上市信息', '1', '1', '审核公开上市信息');
INSERT INTO `process_task_info` VALUES ('ProductPlacingPlanSet', 'launchInfoSet', 'testRole', '/operatePublicMarket', '公开上市信息设置', '1', '1', '公开上市信息设置');
INSERT INTO `process_task_info` VALUES ('ProductPlacingPlanSet', 'markerAuditProductPlacingPlanSet', 'testRole', 'productPlacingStencil/page/placingPlanAudit', '市场公司副总审核', '1', '1', '市场公司副总审核');
INSERT INTO `process_task_info` VALUES ('ProductPlacingPlanSet', 'operativerAuditProductPlacingPlanSet', 'testRole', 'productPlacingStencil/page/placingPlanAudit', '运营公司发售审计专员复审', '1', '1', '运营公司发售审计专员复审');
INSERT INTO `process_task_info` VALUES ('ProductPlacingPlanSet', 'productPlacingPlanSetAgain', 'testRole', '/productPlacingStencil/page/placingPlanEdit', '配售计划重新设置', '1', '1', '配售计划重新设置');
INSERT INTO `process_task_info` VALUES ('ProductRePlacing', 'audit', 'testRole', '1111111', '重新指派审核', '1', '1', '重新指派审核');
INSERT INTO `process_task_info` VALUES ('ProductRePlacing', 'markerSubmitAgain', 'testRole', '1111111', '重新指派重新提交', '1', '1', '重新指派重新提交');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'auditLaunchInfo', 'testRole', '/exchangeAuditPublicMarket', '审核公开上市信息', '12', '1', '审核公开上市信息');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'baseInfoSet', 'testRole', '/marketFillBasicInfo', '基本信息设置', '3', '1', '基本信息设置');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'exchangerAuditBaseInfo', 'testRole', '/exchangeOneAudit', '交易所审核', '5', '1', '交易所审核');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'issuerSubmitAgain', 'testRole', '/gotoIssuerNeed', '委托发售方重新提交', '2', '1', '委托发售方重新提交');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'launchInfoSet', 'testRole', '/operatePublicMarket', '公开上市信息设置', '10', '1', '公开上市信息设置');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'markerAudit', 'testRole', '/marketOneAudit', '市场公司审核', '1', '1', '市场公司审核');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'markerAuditProductPlacingPlanSet', 'testRole', 'productPlacingStencil/page/placingPlanAudit', '市场公司副总审核', '14', '1', '市场公司副总审核');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'operativerAuditBaseInfo', 'testRole', '/operateOneAudit', '运营公司审核', '4', '1', '运营公司审核');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'operativerAuditOtherInfo', 'testRole', '/operateAuditOtherInfo', '运营公司审核', '9', '1', '运营公司审核');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'operativerAuditProductPlacingPlanSet', 'testRole', 'productPlacingStencil/page/placingPlanAudit', '运营公司专员审核', '15', '1', '运营公司发售审计专员复审');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'otherInfoSet', 'testRole', '/marketFillOtherInfo', '其他发售信息设置', '8', '1', '其他发售信息设置');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'prodCardRegisterApply', 'testRole', '/operateRegisterDoc', '凭证登记申请', '7', '1', '凭证登记申请');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'prodCardRegisterAudit', 'testRole', '/exchangeConfirmRegister', '凭证登记审核', '13', '1', '凭证登记审核');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'productPlacingPlanSet', 'testRole', '/productPlacingStencil/page/placingPlanEdit', '配售计划设置', '11', '1', '配售计划设置');
INSERT INTO `process_task_info` VALUES ('ProductSale', 'proofProdCard', 'testRole', '/operateGenerateDoc', '生成凭证', '6', '1', '生成凭证');
INSERT INTO `process_task_info` VALUES ('ProductSaleAgainOrModifyBaseInfo', 'auditLaunchInfo', 'testRole', '/exchangeAuditPublicMarket', '审核公开上市信息', '1', '1', '审核公开上市信息');
INSERT INTO `process_task_info` VALUES ('ProductSaleAgainOrModifyBaseInfo', 'baseInfoSetAgain', 'testRole', '/marketFillBasicInfo', '基本信息重新设置', '1', '1', '基本信息重新设置');
INSERT INTO `process_task_info` VALUES ('ProductSaleAgainOrModifyBaseInfo', 'exchangerAuditBaseInfo', 'testRole', '/exchangeOneAudit', '交易所审核', '1', '1', '交易所审核');
INSERT INTO `process_task_info` VALUES ('ProductSaleAgainOrModifyBaseInfo', 'launchInfoSet', 'testRole', '/operatePublicMarket', '公开上市信息设置', '1', '1', '公开上市信息设置');
INSERT INTO `process_task_info` VALUES ('ProductSaleAgainOrModifyBaseInfo', 'markerAuditProductPlacingPlanSet', 'testRole', 'productPlacingStencil/page/placingPlanAudit', '市场公司副总审核', '1', '1', '市场公司副总审核');
INSERT INTO `process_task_info` VALUES ('ProductSaleAgainOrModifyBaseInfo', 'operativerAuditBaseInfo', 'testRole', '/operateOneAudit', '运营公司审核', '1', '1', '运营公司审核');
INSERT INTO `process_task_info` VALUES ('ProductSaleAgainOrModifyBaseInfo', 'operativerAuditOtherInfo', 'testRole', '/operateAuditOtherInfo', '运营公司审核', '1', '1', '运营公司审核');
INSERT INTO `process_task_info` VALUES ('ProductSaleAgainOrModifyBaseInfo', 'operativerAuditProductPlacingPlanSet', 'testRole', 'productPlacingStencil/page/placingPlanAudit', '运营公司发售审计专员复审', '1', '1', '运营公司发售审计专员复审');
INSERT INTO `process_task_info` VALUES ('ProductSaleAgainOrModifyBaseInfo', 'otherInfoSet', 'testRole', '/marketFillOtherInfo', '其他发售信息设置', '1', '1', '其他发售信息设置');
INSERT INTO `process_task_info` VALUES ('ProductSaleAgainOrModifyBaseInfo', 'prodCardRegisterApply', 'testRole', '/operateRegisterDoc', '凭证登记申请', '1', '1', '凭证登记申请');
INSERT INTO `process_task_info` VALUES ('ProductSaleAgainOrModifyBaseInfo', 'prodCardRegisterAudit', 'testRole', '/exchangeConfirmRegister', '凭证登记审核', '1', '1', '凭证登记审核');
INSERT INTO `process_task_info` VALUES ('ProductSaleAgainOrModifyBaseInfo', 'productPlacingPlanSet', 'testRole', '/productPlacingStencil/page/placingPlanEdit', '配售计划设置', '1', '1', '配售计划设置');
INSERT INTO `process_task_info` VALUES ('ProductSaleAgainOrModifyBaseInfo', 'proofProdCard', 'testRole', '/operateGenerateDoc', '生成凭证', '1', '1', '生成凭证');
INSERT INTO `process_task_info` VALUES ('ProductSaleModifyLaunchInfo', 'auditLaunchInfo', 'testRole', '/exchangeAuditPublicMarket', '审核公开上市信息', '1', '1', '审核公开上市信息');
INSERT INTO `process_task_info` VALUES ('ProductSaleModifyLaunchInfo', 'launchInfoSetAgain', 'testRole', '/operatePublicMarket', '公开上市信息重新设置', '1', '1', '公开上市信息重新设置');
INSERT INTO `process_task_info` VALUES ('ProductSaleModifyOtherInfo', 'auditLaunchInfo', 'testRole', '/exchangeAuditPublicMarket', '审核公开上市信息', '1', '1', '审核公开上市信息');
INSERT INTO `process_task_info` VALUES ('ProductSaleModifyOtherInfo', 'launchInfoSet', 'testRole', '/operatePublicMarket', '公开上市信息设置', '1', '1', '公开上市信息设置');
INSERT INTO `process_task_info` VALUES ('ProductSaleModifyOtherInfo', 'markerAuditProductPlacingPlanSet', 'testRole', 'productPlacingStencil/page/placingPlanAudit', '市场公司副总审核', '1', '1', '市场公司副总审核');
INSERT INTO `process_task_info` VALUES ('ProductSaleModifyOtherInfo', 'operativerAuditOtherInfo', 'testRole', '/operateAuditOtherInfo', '运营公司审核', '1', '1', '运营公司审核');
INSERT INTO `process_task_info` VALUES ('ProductSaleModifyOtherInfo', 'operativerAuditProductPlacingPlanSet', 'testRole', 'productPlacingStencil/page/placingPlanAudit', '运营公司发售审计专员复审', '1', '1', '运营公司发售审计专员复审');
INSERT INTO `process_task_info` VALUES ('ProductSaleModifyOtherInfo', 'otherInfoSetAgain', 'testRole', '/marketFillOtherInfo', '其他发售信息重新设置', '1', '1', '其他发售信息重新设置');
INSERT INTO `process_task_info` VALUES ('ProductSaleModifyOtherInfo', 'productPlacingPlanSet', 'testRole', '/productPlacingStencil/page/placingPlanEdit', '配售计划设置', '1', '1', '配售计划设置');
INSERT INTO `process_task_info` VALUES ('ProductYaoHao', 'yaoHao', 'testRole', '/buy/yaohaoInfo', '摇号', '1', '1', '摇号');
INSERT INTO `process_task_info` VALUES ('SaleBaseInfoAudit', '委托发售方重新申请', 'testRole', '17', '委托发售方重新申请', '1', '1', '委托发售方重新申请');
INSERT INTO `process_task_info` VALUES ('SaleBaseInfoAudit', '市场公司审核', 'testRole', '18', '市场公司审核', '1', '1', '市场公司审核');
INSERT INTO `process_task_info` VALUES ('SystemRoleChange', 'finalAudit', 'testRole', 'systemRole/aduitSystemRoleInfo/page', '系统管理员审核', '1', '1', '系统管理员审核');
INSERT INTO `process_task_info` VALUES ('SystemUserChange', 'finalAudit', 'testRole', 'systemRole/aduitUserInfo/page', '系统管理员审核', '1', '1', '系统管理员审核');
INSERT INTO `process_task_info` VALUES ('TradeRoleChange', 'finalAudit', 'testRole', 'tradeRole/aduitTradeRoleInfo/page', '系统管理员审核', '1', '1', '系统管理员审核');
INSERT INTO `process_task_info` VALUES ('UserApplyBrokerMember', 'marketAudit', 'testRole', 'member/changeAudit', '市场公司审核', '1', '1', '市场公司审核');
INSERT INTO `process_task_info` VALUES ('UserApplyBrokerMember', 'operAudit', 'testRole', 'member/changeAudit', '运营公司审核', '2', '1', '运营公司审核');
INSERT INTO `process_task_info` VALUES ('myProcess', 'usertask1', '103', 'test', 'test', '1', '1', 'test');

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `parent_id` varchar(10) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `id` varchar(10) NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  `del_flag` char(18) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_area
-- ----------------------------
INSERT INTO `sys_area` VALUES ('0', '0,', '中国', '1', '100000', '0');
INSERT INTO `sys_area` VALUES ('8', '0,1,2,', '历城区', '10', '370532', '0');
INSERT INTO `sys_area` VALUES ('8', '0,1,2,', '历城区', '11', '370533', '0');
INSERT INTO `sys_area` VALUES ('8', '0,1,2,', '历下区', '12', '370534', '0');
INSERT INTO `sys_area` VALUES ('8', '0,1,2,', '天桥区', '13', '370535', '0');
INSERT INTO `sys_area` VALUES ('8', '0,1,8,', '槐荫区', '14', '370536', '0');
INSERT INTO `sys_area` VALUES ('1', '0,1,', '北京市', '2', '110000', '0');
INSERT INTO `sys_area` VALUES ('2', '0,1,2,', '东城区', '3', '110101', '0');
INSERT INTO `sys_area` VALUES ('2', '0,1,2,', '西城区', '4', '110102', '0');
INSERT INTO `sys_area` VALUES ('2', '0,1,2,', '朝阳区', '5', '110103', '0');
INSERT INTO `sys_area` VALUES ('2', '0,1,2,', '丰台区', '6', '110104', '0');
INSERT INTO `sys_area` VALUES ('2', '0,1,2,', '海淀区', '7', '110105', '0');
INSERT INTO `sys_area` VALUES ('d406d79a-1', '0,1,2,7,d406d79a-1,', '海淀区22233', '9bc7209b-f', '333333', '1');
INSERT INTO `sys_area` VALUES (null, null, '海淀区1', 'a78c1178-b', '11111', '0');
INSERT INTO `sys_area` VALUES ('7', '0,1,2,7,', '海淀区222', 'd406d79a-1', '233333', '1');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(64) NOT NULL,
  `label` varchar(100) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '正常', '0', 'del_flag', '删除标记', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('10', '黄色', 'yellow', 'color', '颜色值', '40', null, '0');
INSERT INTO `sys_dict` VALUES ('11', '橙色', 'orange', 'color', '颜色值', '50', null, '0');
INSERT INTO `sys_dict` VALUES ('12', '默认主题', 'default', 'theme', '主题方案', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('13', '天蓝主题', 'cerulean', 'theme', '主题方案', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('14', '橙色主题', 'readable', 'theme', '主题方案', '30', null, '0');
INSERT INTO `sys_dict` VALUES ('15', '红色主题', 'united', 'theme', '主题方案', '40', null, '0');
INSERT INTO `sys_dict` VALUES ('16', 'Flat主题', 'flat', 'theme', '主题方案', '60', null, '0');
INSERT INTO `sys_dict` VALUES ('17', '国家', '1', 'sys_area_type', '区域类型', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('18', '省份、直辖市', '2', 'sys_area_type', '区域类型', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('19', '地市', '3', 'sys_area_type', '区域类型', '30', null, '0');
INSERT INTO `sys_dict` VALUES ('1d157cf94b7144779113d991aff87625', '签章', '100', 'default_sign_count', '默认签章份数', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('2', '删除', '1', 'del_flag', '删除标记', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('20', '区县', '4', 'sys_area_type', '区域类型', '40', null, '0');
INSERT INTO `sys_dict` VALUES ('21', '公司', '1', 'sys_office_type', '机构类型', '60', null, '0');
INSERT INTO `sys_dict` VALUES ('22', '部门', '2', 'sys_office_type', '机构类型', '70', null, '0');
INSERT INTO `sys_dict` VALUES ('23', '一级', '1', 'sys_office_grade', '机构等级', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('24', '二级', '2', 'sys_office_grade', '机构等级', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('25', '三级', '3', 'sys_office_grade', '机构等级', '30', null, '0');
INSERT INTO `sys_dict` VALUES ('26', '四级', '4', 'sys_office_grade', '机构等级', '40', null, '0');
INSERT INTO `sys_dict` VALUES ('27', '所有数据', '1', 'sys_data_scope', '数据范围', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('28', '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('29', '所在公司数据', '3', 'sys_data_scope', '数据范围', '30', null, '0');
INSERT INTO `sys_dict` VALUES ('2918c2cd29aa4ffd8f3ded4056c0152f', '电子公章', '1', 'esign_seal_type', '印章类型', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('2BSL83Y2MD', '50 - 100人', '3', 'supplier_emploee_count', '从业人数', '3', null, '0');
INSERT INTO `sys_dict` VALUES ('2CAMKUHZYA', '100 - 200人', '4', 'supplier_emploee_count', '从业人数', '3', null, '0');
INSERT INTO `sys_dict` VALUES ('3', '显示', '1', 'show_hide', '显示/隐藏', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('30', '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', '40', null, '0');
INSERT INTO `sys_dict` VALUES ('31', '所在部门数据', '5', 'sys_data_scope', '数据范围', '50', null, '0');
INSERT INTO `sys_dict` VALUES ('32', '仅本人数据', '8', 'sys_data_scope', '数据范围', '90', null, '0');
INSERT INTO `sys_dict` VALUES ('33', '按明细设置', '9', 'sys_data_scope', '数据范围', '100', null, '0');
INSERT INTO `sys_dict` VALUES ('34', '系统管理', '1', 'sys_user_type', '用户类型', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('35', '部门经理', '2', 'sys_user_type', '用户类型', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('36', '普通用户', '3', 'sys_user_type', '用户类型', '30', null, '0');
INSERT INTO `sys_dict` VALUES ('37', '基础主题', 'basic', 'cms_theme', '站点主题', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('38', '蓝色主题', 'blue', 'cms_theme', '站点主题', '20', null, '1');
INSERT INTO `sys_dict` VALUES ('39', '红色主题', 'red', 'cms_theme', '站点主题', '30', null, '1');
INSERT INTO `sys_dict` VALUES ('3edb970d26414713b2185c6a0b347a71', 'test', 'test', 'test', 'test', '1', null, '1');
INSERT INTO `sys_dict` VALUES ('4', '隐藏', '0', 'show_hide', '显示/隐藏', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('40', '文章模型', 'article', 'cms_module', '栏目模型', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('41', '图片模型', 'picture', 'cms_module', '栏目模型', '20', null, '1');
INSERT INTO `sys_dict` VALUES ('42', '下载模型', 'download', 'cms_module', '栏目模型', '30', null, '1');
INSERT INTO `sys_dict` VALUES ('43', '链接模型', 'link', 'cms_module', '栏目模型', '40', null, '0');
INSERT INTO `sys_dict` VALUES ('44', '专题模型', 'special', 'cms_module', '栏目模型', '50', null, '1');
INSERT INTO `sys_dict` VALUES ('45', '默认展现方式', '0', 'cms_show_modes', '展现方式', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('46', '首栏目内容列表', '1', 'cms_show_modes', '展现方式', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('47', '栏目第一条内容', '2', 'cms_show_modes', '展现方式', '30', null, '0');
INSERT INTO `sys_dict` VALUES ('4776d388525d49f88259dde586e4a014', 'test1', '1231', 'test1', 'test1', '1', null, '1');
INSERT INTO `sys_dict` VALUES ('48', '发布2', '0', 'cms_del_flag', '内容状态2', '15', null, '0');
INSERT INTO `sys_dict` VALUES ('49', '删除', '1', 'cms_del_flag', '内容状态', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('5', '是', '1', 'yes_no', '是/否', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('50', '审核1', '2', 'cms_del_flag', '内容状态1', '14', null, '0');
INSERT INTO `sys_dict` VALUES ('51', '首页焦点图', '1', 'cms_posid', '推荐位', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('52', '栏目页文章推荐', '2', 'cms_posid', '推荐位', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('53', '咨询', '1', 'cms_guestbook', '留言板分类', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('54', '建议', '2', 'cms_guestbook', '留言板分类', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('55', '投诉', '3', 'cms_guestbook', '留言板分类', '30', null, '0');
INSERT INTO `sys_dict` VALUES ('56', '其它', '4', 'cms_guestbook', '留言板分类', '40', null, '0');
INSERT INTO `sys_dict` VALUES ('57', '公休', '1', 'oa_leave_type', '请假类型', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('58', '病假', '2', 'oa_leave_type', '请假类型', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('59', '事假', '3', 'oa_leave_type', '请假类型', '30', null, '0');
INSERT INTO `sys_dict` VALUES ('5ab539dcd6924666973187b376e3aab2', '个人私章', '2', 'esign_seal_type', '印章类型', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('6', '否', '0', 'yes_no', '是/否', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('60', '调休', '4', 'oa_leave_type', '请假类型', '40', null, '0');
INSERT INTO `sys_dict` VALUES ('61', '婚假', '5', 'oa_leave_type', '请假类型', '60', null, '0');
INSERT INTO `sys_dict` VALUES ('62', '接入日志', '1', 'sys_log_type', '日志类型', '30', null, '0');
INSERT INTO `sys_dict` VALUES ('625a7663b79b4dff849e16da9e395edd', 'test_user2', 'test_user2', 'test_user', 'test_user2', '3', null, '1');
INSERT INTO `sys_dict` VALUES ('63', '异常日志', '2', 'sys_log_type', '日志类型', '40', null, '0');
INSERT INTO `sys_dict` VALUES ('64', '单表增删改查', 'single', 'prj_template_type', '代码模板', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('65', '所有entity和dao', 'entityAndDao', 'prj_template_type', '代码模板', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('7', '红色', 'red', 'color', '颜色值', '10', null, '0');
INSERT INTO `sys_dict` VALUES ('8', '绿色', 'green', 'color', '颜色值', '20', null, '0');
INSERT INTO `sys_dict` VALUES ('850dde0ae436473e86cba2596a4513a1', 'test', 'test', 'test', 'test', '11', null, '1');
INSERT INTO `sys_dict` VALUES ('9', '蓝色', 'blue', 'color', '颜色值', '30', null, '0');
INSERT INTO `sys_dict` VALUES ('98e5febc55654ed0b9426a1b367aa6c7', '3', '4', '3', '3', '4', null, '1');
INSERT INTO `sys_dict` VALUES ('9dc5a841a4784d39a8a670b57187cb07', '4', '4', '2', '4', '4', null, '1');
INSERT INTO `sys_dict` VALUES ('a2122007c7ba4c26b1e3c67f4da7ac2f', 'test', 'test', 'test', 'test', '1', null, '1');
INSERT INTO `sys_dict` VALUES ('a61d7ea2ab18461b9c8b11eb0379fbc7', '123', '123', '123', '123', '1', null, '1');
INSERT INTO `sys_dict` VALUES ('b45ea42eccc04088beca1ebad9db78a2', '测试用户', 'test_user', 'test_user', '测试用户', '1', null, '1');
INSERT INTO `sys_dict` VALUES ('b9c61f7858ce4a5d87c0f33667340c98', 'test1', 'test1', 'test', 'test1', '2', null, '0');
INSERT INTO `sys_dict` VALUES ('bae2751e29f14c18b1f836ecbf814c81', 'test', '121', 'test', 'test1', '2', null, '1');
INSERT INTO `sys_dict` VALUES ('BHFUVYV9HH', '长沙银行', '3', 'supplier_bank', '开户银行', '1', null, '0');
INSERT INTO `sys_dict` VALUES ('BMUWL87VLT', '10 - 50人', '2', 'supplier_emploee_count', '从业人数', '2', null, '0');
INSERT INTO `sys_dict` VALUES ('C3PNEJSLJP', '1 - 10人', '1', 'supplier_emploee_count', '从业人数', '2', null, '0');
INSERT INTO `sys_dict` VALUES ('c51b59d7de694d7db28dd479ab4b99de', 'test1', 'test1', 'test1', 'test1', '2', null, '1');
INSERT INTO `sys_dict` VALUES ('c8e0c5efa7844ae7abf466c9cb2d1a4d', '测试用户u', 'test_user1', 'test_user', '测试用户1', '1', null, '1');
INSERT INTO `sys_dict` VALUES ('d7e3a194fede4d74bad573205ec8bda4', 'ttt', 'ttt', 'ttt', 'ttt', '1', null, '1');
INSERT INTO `sys_dict` VALUES ('DCGP4HZN6X', '国营企业', '1', 'supplier_office_nature', '单位性质', '1', null, '0');
INSERT INTO `sys_dict` VALUES ('dd131b40572d41b69880ff5213f96050', 'test', 'test', 'test', 'test', '1', null, '1');
INSERT INTO `sys_dict` VALUES ('DVWPEFGXJ8', '软件/硬件', '2', 'supplier_business_type', '所属行业', '2', null, '0');
INSERT INTO `sys_dict` VALUES ('TF9Y3LV3L5', '私营企业', '2', 'supplier_office_nature', '单位性质', '2', null, '0');
INSERT INTO `sys_dict` VALUES ('URUER8YJLC', '建设银行', '1', 'supplier_bank', '开户银行', '1', null, '0');
INSERT INTO `sys_dict` VALUES ('WUM24EZ2R3', '机械', '1', 'supplier_business_type', '所属行业', '2', null, '0');
INSERT INTO `sys_dict` VALUES ('Y99V8YQ7AS', '工商银行', '2', 'supplier_bank', '开户银行', '1', null, '0');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `file_id` varchar(50) NOT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `file_type` varchar(20) DEFAULT NULL,
  `save_path` varchar(200) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `last_update_time` timestamp NULL DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `content_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('34d35a1fc2654214b8f0bbaa20e951b7', '印章图象', 'bmp', '2017\\05\\16\\f91f285c489d4906bd892063fdc835f4.zip', '2017-05-16 19:17:59', null, null, null, null, null);
INSERT INTO `sys_file` VALUES ('3af30510a3bd478990d6c869f8a5fe97', '2M', 'rar', '2017\\05\\16\\481e26fb6d044848a0ad1023473fc160.rar', '2017-05-16 19:21:15', null, null, null, '5749500575', null);
INSERT INTO `sys_file` VALUES ('3d8fa29ff0d0466b932e342d32f8b175', '5M', 'rar', '2017\\05\\16\\fb45839aeff44d56985352880b01d3fe.rar', '2017-05-16 19:22:04', null, null, null, '5749500575', null);
INSERT INTO `sys_file` VALUES ('4b98b39df7774c4e937df1eb1a7c5593', '2M', 'rar', '2017\\05\\16\\b2be68f232194938a416a80773ce7abd.rar', '2017-05-16 21:39:58', null, null, null, null, 'application/octet-stream');
INSERT INTO `sys_file` VALUES ('7c1243472ad2421cae20339283c49b80', '10M', 'rar', '2017\\05\\16\\01088b51ea2f420294514988a6843e6a.rar', '2017-05-16 19:22:09', null, null, null, '5749500575', null);
INSERT INTO `sys_file` VALUES ('7fc4a78e56e545898e84472275da5bf9', '5M', 'rar', '2017\\05\\16\\f37844d2b8fc4671a448d2e42db960a1.rar', '2017-05-16 19:21:42', null, null, null, '5749500575', null);
INSERT INTO `sys_file` VALUES ('a908ebd8bb5f41479bb7a6909938d29b', '30M', 'rar', '2017\\05\\16\\6688d882cf0048a9869110b44dc22b41.rar', '2017-05-16 19:22:19', null, null, null, '5749500575', null);
INSERT INTO `sys_file` VALUES ('bdbccde5195b4f2daeb86dad476c3756', '2M', 'rar', '2017\\05\\16\\8e94d00bf084499f81eedfdb35a2850c.rar', '2017-05-16 19:39:13', null, null, null, null, 'application/octet-stream');
INSERT INTO `sys_file` VALUES ('e32bbf78bc874c6aab0139b10e7b3396', '印章图象', 'bmp', '2017\\05\\16\\96e270224d23433f8f0720ec97c9ffd7.bmp', '2017-05-16 19:20:33', null, null, null, null, null);
INSERT INTO `sys_file` VALUES ('e3677b2a81e9402887246f3edd0b7f87', '印章图象', 'bmp', '2017\\05\\16\\667be6658c224894b9995cf755d760c1.bmp', '2017-05-16 19:37:35', null, null, null, '5749500575', 'image/bmp');
INSERT INTO `sys_file` VALUES ('e53cfbc42cad4b25855f3fc1f0131d00', '印章图象', 'bmp', '2017\\05\\16\\8881c2fc127746c2a7ae3d354a4a9510.zip', '2017-05-16 19:19:55', null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(64) NOT NULL,
  `parent_id` varchar(64) DEFAULT NULL,
  `parent_ids` varchar(2000) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `is_show` int(11) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('03543b49ebbe4fccb5b1e55298027ec2', 'f9f13acd9c6247788520a512f6d5b147', '0,1,60076411112b4dbe93d126ed9865621f,f9f13acd9c6247788520a512f6d5b147,', '电子印章管理', '/esign/seal/list', '', '30', '1', 'esign:seal:view', '1', '');
INSERT INTO `sys_menu` VALUES ('0f1bf417ebb34a88802319c379152622', '60076411112b4dbe93d126ed9865621f', '0,1,60076411112b4dbe93d126ed9865621f,', '123123', '123123', 'fa fa-american-sign-language-interpreting', '11', '1', '1233', '1', '');
INSERT INTO `sys_menu` VALUES ('1', '0', '0,', '顶级菜单', '', '', '0', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('10', '3', '0,1,2,3,', '字典管理', '/system/dict', 'fa fa-book', '60', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('11', '10', '0,1,2,3,10,', '查看', '', '', '30', '0', 'sys:dict:view', '0', '');
INSERT INTO `sys_menu` VALUES ('12', '10', '0,1,2,3,10,', '修改', '', '', '30', '0', 'sys:dict:edit', '0', '');
INSERT INTO `sys_menu` VALUES ('13', '2', '0,1,2,', '机构用户', '', 'fa fa-user', '970', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('13d3119a9d', '6cff3118a9', '0,1,7c5e610086,6cff3118a9,', '供应商黑名单', '/user/supplier/blacklist', '', '0', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('14', '13', '0,1,2,13,', '区域管理', '/system/area/', 'fa fa-globe', '50', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('15', '14', '0,1,2,13,14,', '查看', '', '', '30', '0', 'sys:area:view', '0', '');
INSERT INTO `sys_menu` VALUES ('16', '14', '0,1,2,13,14,', '修改', '', '', '30', '0', 'sys:area:edit', '0', '');
INSERT INTO `sys_menu` VALUES ('17', '13', '0,1,2,13,', '机构管理', '/system/office/', 'fa fa-users', '40', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('18', '17', '0,1,2,13,17,', '查看', '', '', '30', '0', 'sys:office:view', '0', '');
INSERT INTO `sys_menu` VALUES ('19', '17', '0,1,2,13,17,', '修改', '', '', '30', '0', 'sys:office:edit', '0', '');
INSERT INTO `sys_menu` VALUES ('1c3987d505', '27', '0,1,27,', '我的任务', '', '', '1', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('1c61dc1fa7', '3', '0,1,2,3,', '须知文件管理', '/system/notice', 'fa fa-map-o', '3', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('2', '1', '0,1,', '系统设置', '', 'fa fa-gear', '900', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('20', '13', '0,1,2,13,', '用户管理', '/system/user/', 'fa fa-user', '30', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('21', '20', '0,1,2,13,20,', '查看', '', '', '30', '0', 'sys:user:view', '0', '');
INSERT INTO `sys_menu` VALUES ('22', '20', '0,1,2,13,20,', '修改', '', '', '30', '0', 'sys:user:edit', '0', '');
INSERT INTO `sys_menu` VALUES ('23', '2', '0,1,2,', '关于帮助', '', 'fa fa-question-circle-o', '990', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('24', '23', '0,1,2,23,', '项目首页', 'http://jeesite.com', '', '30', '1', '', '0', '333');
INSERT INTO `sys_menu` VALUES ('25', '23', '0,1,2,23,', '项目支持', 'http://jeesite.com/donation.html', '', '50', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('252ecfdfba', '78', '0,1,76,77,78,', '1', '1', '', '0', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('26', '23', '0,1,2,23,', '论坛交流', 'http://bbs.jeesite.com', '', '80', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('27', '1', '0,1,', '我的面板', '', '', '100', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('28', '27', '0,1,27,', '个人信息', '', '', '990', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('286aa89bd5ac4f82a1d4035566a3316d', '60076411112b4dbe93d126ed9865621f', '0,1,60076411112b4dbe93d126ed9865621f,', 'ce', 'ce', '', '1', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('29', '28', '0,1,27,28,', '个人信息', '/personal/user-info', 'fa fa-user', '30', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('2a41260e4a', '68fc127343', '0,1,7c5e610086,68fc127343,', '评标专家管理', '/user/expert/manage', '', '0', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('2bf25dcf7dd6455ea4af547b015f7784', '1', '0,1,', 'test', 'test', 'test', '1', '1', 'test', '1', '');
INSERT INTO `sys_menu` VALUES ('3', '2', '0,1,2,', '系统设置', '', 'fa fa-cog', '980', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('30', '28', '0,1,27,28,', '修改密码', '/personal/password', 'fa fa-asterisk', '40', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('31', '1', '0,1,', '内容管理', '', '', '500', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('318c049c8ba0415194623c851bebd612', '27', '0,1,27,', 'test', 'test', 'test', '1', '1', 'test', '1', '');
INSERT INTO `sys_menu` VALUES ('32', '31', '0,1,31,', '栏目设置', '', '', '990', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('32df3d86a3', '3', '0,1,2,3,', '采购类别管理', '/system/purchaseType', 'fa fa-cog', '1', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('33', '32', '0,1,31,32,', '栏目管理', '/cms/category/', 'align-justify', '30', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('34', '33', '0,1,31,32,33,', '查看', '', '', '30', '0', 'cms:category:view', '1', '');
INSERT INTO `sys_menu` VALUES ('341c52aacbb446189475da029dff106f', '60076411112b4dbe93d126ed9865621f', '0,1,60076411112b4dbe93d126ed9865621f,', '123', '123', '', '123', '1', '123', '1', '');
INSERT INTO `sys_menu` VALUES ('35', '33', '0,1,31,32,33,', '修改', '', '', '30', '0', 'cms:category:edit', '1', '');
INSERT INTO `sys_menu` VALUES ('36', '32', '0,1,31,32,', '站点设置', '/cms/site/', 'certificate', '40', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('37', '36', '0,1,31,32,36,', '查看', '', '', '30', '0', 'cms:site:view', '1', '');
INSERT INTO `sys_menu` VALUES ('38', '36', '0,1,31,32,36,', '修改', '', '', '30', '0', 'cms:site:edit', '1', '');
INSERT INTO `sys_menu` VALUES ('39', '32', '0,1,31,32,', '切换站点', '/cms/site/select', 'retweet', '50', '1', 'cms:site:select', '1', '');
INSERT INTO `sys_menu` VALUES ('3d33c15c65', '78', '0,1,76,77,78,', '3', '3', '', '2', '1', '3', '0', '3');
INSERT INTO `sys_menu` VALUES ('3f4102309ffb4b21b8819bb68411ea29', '56d07f16fc324de98cb6dbe7e3fa1b41', '0,1,60076411112b4dbe93d126ed9865621f,f9f13acd9c6247788520a512f6d5b147,56d07f16fc324de98cb6dbe7e3fa1b41,', '修改', '123', '123', '31', '0', 'esign:seal:edit', '1', '');
INSERT INTO `sys_menu` VALUES ('4', '3', '0,1,2,3,', '菜单管理', '/system/menu', 'fa fa-reorder', '30', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('40', '31', '0,1,31,', '内容管理', '', '', '500', '1', 'cms:view', '1', '');
INSERT INTO `sys_menu` VALUES ('41', '40', '0,1,31,40,', '内容发布', '/cms/', 'briefcase', '30', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('42', '41', '0,1,31,40,41,', '文章模型', '/cms/article/', 'file', '40', '0', '', '1', '');
INSERT INTO `sys_menu` VALUES ('43', '42', '0,1,31,40,41,42,', '查看', '', '', '30', '0', 'cms:article:view', '1', '');
INSERT INTO `sys_menu` VALUES ('44', '42', '0,1,31,40,41,42,', '修改', '', '', '30', '0', 'cms:article:edit', '1', '');
INSERT INTO `sys_menu` VALUES ('447324563e3b42909b55c7ef09ade6e6', 'f3a5bd24a58a4d6c88fab8d777f30775', '0,1,60076411112b4dbe93d126ed9865621f,f3a5bd24a58a4d6c88fab8d777f30775,', '123', '123', '', '1', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('44b175530d', '1c3987d505', '0,1,27,1c3987d505,', '待办任务', '/task/todo', '', '1', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('45', '42', '0,1,31,40,41,42,', '审核', '', '', '30', '0', 'cms:article:audit', '1', '');
INSERT INTO `sys_menu` VALUES ('46', '41', '0,1,31,40,41,', '链接模型', '/cms/link/', 'random', '60', '0', '', '1', '');
INSERT INTO `sys_menu` VALUES ('47', '46', '0,1,31,40,41,46,', '查看', '', '', '30', '0', 'cms:link:view', '1', '');
INSERT INTO `sys_menu` VALUES ('48', '46', '0,1,31,40,41,46,', '修改', '', '', '30', '0', 'cms:link:edit', '1', '');
INSERT INTO `sys_menu` VALUES ('482c5bef18', '3', '0,1,2,3,', '银行账户配置', '/system/setting', 'fa fa-credit-card-alt', '5', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('49', '46', '0,1,31,40,41,46,', '审核', '', '', '30', '0', 'cms:link:audit', '1', '');
INSERT INTO `sys_menu` VALUES ('4a6856890a0c49b7ac197561668b951a', '341c52aacbb446189475da029dff106f', '0,1,60076411112b4dbe93d126ed9865621f,341c52aacbb446189475da029dff106f,', '12344', '12344', '', '12344', '1', '12344', '1', '');
INSERT INTO `sys_menu` VALUES ('5', '4', '0,1,2,3,4,', '查看', '', '', '30', '0', 'sys:menu:view', '0', '');
INSERT INTO `sys_menu` VALUES ('50', '40', '0,1,31,40,', '评论管理', '/cms/comment/?status=2', 'comment', '40', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('51', '50', '0,1,31,40,50,', '查看', '', '', '30', '0', 'cms:comment:view', '1', '');
INSERT INTO `sys_menu` VALUES ('51bf175043c5439b93d242090712742e', '4a6856890a0c49b7ac197561668b951a', '0,1,60076411112b4dbe93d126ed9865621f,341c52aacbb446189475da029dff106f,4a6856890a0c49b7ac197561668b951a,', '1', '1', '', '1', '1', '1', '1', '');
INSERT INTO `sys_menu` VALUES ('52', '50', '0,1,31,40,50,', '审核', '', '', '30', '0', 'cms:comment:edit', '1', '');
INSERT INTO `sys_menu` VALUES ('53', '40', '0,1,31,40,', '公共留言', '/cms/guestbook/?status=2', 'glass', '80', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('54', '53', '0,1,31,40,53,', '查看', '', '', '30', '0', 'cms:guestbook:view', '1', '');
INSERT INTO `sys_menu` VALUES ('55', '53', '0,1,31,40,53,', '审核', '', '', '30', '0', 'cms:guestbook:edit', '1', '');
INSERT INTO `sys_menu` VALUES ('56', '40', '0,1,31,40,', '文件管理', '/../static/ckfinder/ckfinder.html', 'folder-open', '90', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('56d07f16fc324de98cb6dbe7e3fa1b41', 'f9f13acd9c6247788520a512f6d5b147', '0,1,60076411112b4dbe93d126ed9865621f,f9f13acd9c6247788520a512f6d5b147,', '电子印章制作', '/esign/seal/form', '', '30', '1', 'esign:seal:edit', '1', '');
INSERT INTO `sys_menu` VALUES ('57', '56', '0,1,31,40,56,', '查看', '', '', '30', '0', 'cms:ckfinder:view', '1', '');
INSERT INTO `sys_menu` VALUES ('58', '56', '0,1,31,40,56,', '上传', '', '', '30', '0', 'cms:ckfinder:upload', '1', '');
INSERT INTO `sys_menu` VALUES ('59', '56', '0,1,31,40,56,', '修改', '', '', '30', '0', 'cms:ckfinder:edit', '1', '');
INSERT INTO `sys_menu` VALUES ('6', '4', '0,1,2,3,4,', '修改', '', '', '30', '0', 'sys:menu:edit', '0', '');
INSERT INTO `sys_menu` VALUES ('60', '31', '0,1,31,', '统计分析', '', '', '600', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('60076411112b4dbe93d126ed9865621f', '1', '0,1,', '电子签章系统', '', '', '30', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('60c42688c0d44dfa9817cbec2ee9695d', '60076411112b4dbe93d126ed9865621f', '0,1,60076411112b4dbe93d126ed9865621f,', '12345', '', '', '30', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('61', '60', '0,1,31,60,', '信息量统计', '/cms/stats/article', 'tasks', '30', '1', 'cms:stats:article', '1', '');
INSERT INTO `sys_menu` VALUES ('62', '1', '0,1,', '在线办公', '', '', '200', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('63', '62', '0,1,62,', '个人办公', '', '', '30', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('64', '63', '0,1,62,63,', '请假办理', '/oa/leave', 'leaf', '30', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('65', '64', '0,1,62,63,64,', '查看', '', '', '30', '0', 'oa:leave:view', '1', '');
INSERT INTO `sys_menu` VALUES ('66', '64', '0,1,62,63,64,', '修改', '', '', '40', '0', 'oa:leave:edit', '1', '');
INSERT INTO `sys_menu` VALUES ('664aabf317', '28', '0,1,27,28,', '机构信息', '/personal/office-info', '', '1', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('67', '2', '0,1,2,', '日志查询', '', 'fa fa-newspaper-o', '985', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('68', '67', '0,1,2,67,', '日志查询', '/system/log', 'fa fa-newspaper-o', '30', '1', 'sys:log:view', '0', '');
INSERT INTO `sys_menu` VALUES ('68ece5978986451cabde1013b9ddad30', '27', '0,1,27,', 'test', 'test', 'test', '1', '1', 'test', '1', '');
INSERT INTO `sys_menu` VALUES ('68fc127343', '7c5e610086', '0,1,7c5e610086,', '专家管理', '', '', '0', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('69', '2', '0,1,2,', '流程管理', '', '', '983', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('69d3b2438c79417c8679578c958ed508', '27', '0,1,27,', 'test', 'test', 'test', '1', '1', 'test', '1', '');
INSERT INTO `sys_menu` VALUES ('6a06cc8656', '6cff3118a9', '0,1,7c5e610086,6cff3118a9,', '供应商投标类别审核', '/user/supplier/purchase-audit', '', '0', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('6cff3118a9', '7c5e610086', '0,1,7c5e610086,', '供应商管理', '', '', '0', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('6efa109c17db4314b80c7adf26d2f13b', 'f3a5bd24a58a4d6c88fab8d777f30775', '0,1,60076411112b4dbe93d126ed9865621f,f3a5bd24a58a4d6c88fab8d777f30775,', '签章日志审计', '/esign/signlog/list', '', '30', '1', 'esign:signlog:view', '1', '');
INSERT INTO `sys_menu` VALUES ('7', '3', '0,1,2,3,', '角色管理', '/system/role', 'fa fa-child', '50', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('70', '69', '0,1,2,69,', '流程管理', '/sys/workflow/processList', 'road', '300', '1', 'sys:workflow:edit', '1', '');
INSERT INTO `sys_menu` VALUES ('71', '64', '0,1,62,63,64,', '部门领导审批', '', '', '50', '0', 'oa:leave:deptLeaderAudit', '1', '');
INSERT INTO `sys_menu` VALUES ('72', '64', '0,1,62,63,64,', '人事审批', '', '', '60', '0', 'oa:leave:hrAudit', '1', '');
INSERT INTO `sys_menu` VALUES ('72bccbf103bd48e3ab4a1fbd3136e984', '03543b49ebbe4fccb5b1e55298027ec2', '0,1,60076411112b4dbe93d126ed9865621f,f9f13acd9c6247788520a512f6d5b147,03543b49ebbe4fccb5b1e55298027ec2,', '修改', '', '', '30', '0', '', '1', '');
INSERT INTO `sys_menu` VALUES ('73', '32', '0,1,31,32', '模板管理', '/cms/template', 'pencil', '40', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('74', '73', '0,1,31,32,73,', '查看', '', '', '30', '0', 'cms:template:view', '1', '');
INSERT INTO `sys_menu` VALUES ('75', '73', '0,1,31,32,73,', '修改', '', '', '30', '0', 'cms:template:edit', '1', '');
INSERT INTO `sys_menu` VALUES ('76', '1', '0,1,', '项目管理', '', '', '1000', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('77', '76', '0,1,76,', '项目数据', '', '', '30', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('78', '77', '0,1,76,77,', '项目管理', '/prj/project/', 'globe', '30', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('79', '78', '0,1,76,77,78,', '查看', '', '', '30', '0', 'prj:project:view', '0', '');
INSERT INTO `sys_menu` VALUES ('7c5e610086', '1', '0,1,', '用户管理', '', '', '3', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('7dfbfee854', '3', '0,1,2,3,', '专业类别管理', '/system/professionType', 'fa fa-cog', '2', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('8', '7', '0,1,2,3,7,', '查看', '', '', '30', '0', 'sys:role:view', '0', '');
INSERT INTO `sys_menu` VALUES ('80', '78', '0,1,76,77,78,', '修改', '', '', '40', '0', 'prj:project:edit', '0', '');
INSERT INTO `sys_menu` VALUES ('85bc175aac5a4b68b67e441a3839f7b9', '60076411112b4dbe93d126ed9865621f', '0,1,60076411112b4dbe93d126ed9865621f,', '2', '2', '2', '2', '1', '2', '1', '');
INSERT INTO `sys_menu` VALUES ('892986a2c5c342039b0b951dc2504015', 'f3a5bd24a58a4d6c88fab8d777f30775', '0,1,60076411112b4dbe93d126ed9865621f,f3a5bd24a58a4d6c88fab8d777f30775,', '234', '234', '', '22', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('9', '7', '0,1,2,3,7,', '修改', '', '', '30', '0', 'sys:role:edit', '0', '');
INSERT INTO `sys_menu` VALUES ('91e8eaf6-c6b1-4f1c-995d-7c1295f18527', '1', '0,1,', 'Swagger', 'http://127.0.0.1:8000/one/swagger-ui.html', '', '0', '1', '', '0', 'Swagger 文档');
INSERT INTO `sys_menu` VALUES ('98ef6af5b6', '6cff3118a9', '0,1,7c5e610086,6cff3118a9,', '供应商管理', '/user/supplier/manage', '', '0', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('99137a21-9bc9-4808-a137-0cdca9697441', '1', '0,1,', '12322222', '12322222', '', '0', '1', '12322222', '1', '12322222');
INSERT INTO `sys_menu` VALUES ('9ab49ea28af04689aeb0018397a74650', '60076411112b4dbe93d126ed9865621f', '0,1,60076411112b4dbe93d126ed9865621f,', '123', '123', '123', '123', '0', '123', '1', '');
INSERT INTO `sys_menu` VALUES ('a2e8c9e87a444be9b00238e5fdb317f8', '17', '0,1,2,13,17,', '删除', '', '', '30', '0', 'sys:office:delete', '0', '');
INSERT INTO `sys_menu` VALUES ('ad7a8ca34e', '1c3987d505', '0,1,27,1c3987d505,', '已办任务', '/task/done', '', '2', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('adc17a5d16', '6cff3118a9', '0,1,7c5e610086,6cff3118a9,', '供应商审核', '/user/supplier/audit', '', '0', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('b08cf3b87d794b49907357e8783f56fc', '60076411112b4dbe93d126ed9865621f', '0,1,60076411112b4dbe93d126ed9865621f,', '123', '123', '123', '123', '0', '123', '1', '');
INSERT INTO `sys_menu` VALUES ('b7f383368de742ae883d296e1533084d', '60076411112b4dbe93d126ed9865621f', '0,1,60076411112b4dbe93d126ed9865621f,', '123', '123', '123', '123', '0', '123', '1', '');
INSERT INTO `sys_menu` VALUES ('c4644eba6c', '3', '0,1,2,3,', '评标室管理', '/system/room', 'fa fa-bank', '4', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('c90b9d3a9eb449c7a81fa7dbb80a6b8f', null, 'nullnull,', '', '', '', '1', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('d697a8cb6b8c4e15a1d095ac3df4f299', '27', '0,1,27,', 'test', 'test', 'test', '1', '1', 'test', '1', '');
INSERT INTO `sys_menu` VALUES ('d9c1dd3d2f874205b0e9e67e3014ecee', '60076411112b4dbe93d126ed9865621f', '0,1,60076411112b4dbe93d126ed9865621f,', '测试1', '', '', '30', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('de586f6b4f584045820262c4b2eb4519', '341c52aacbb446189475da029dff106f', '0,1,60076411112b4dbe93d126ed9865621f,341c52aacbb446189475da029dff106f,', '1234', '1234', '', '1234', '1', '1234', '1', '');
INSERT INTO `sys_menu` VALUES ('dfc523e954', '6cff3118a9', '0,1,7c5e610086,6cff3118a9,', '供应商调查', '/user/supplier/questionnaire', '', '0', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('e29fdbff526d45dcaecab4489a79264b', '14', '0,1,2,13,14,', '删除', '', '', '3', '0', 'sys:area:delete', '0', '');
INSERT INTO `sys_menu` VALUES ('ec347ab221', '6cff3118a9', '0,1,7c5e610086,6cff3118a9,', '供应商等级管理', '/user/supplier/level', '', '0', '1', '', '0', '');
INSERT INTO `sys_menu` VALUES ('ef2331fc79', '78', '0,1,76,77,78,', '2', '2', '', '1', '1', '2', '0', '2');
INSERT INTO `sys_menu` VALUES ('f13419c5ab3848bab83e69fba5aa264b', '20', '0,1,2,13,20,', '删除', '', '', '30', '1', 'sys:user:delete', '0', '');
INSERT INTO `sys_menu` VALUES ('f3a5bd24a58a4d6c88fab8d777f30775', '60076411112b4dbe93d126ed9865621f', '0,1,60076411112b4dbe93d126ed9865621f,', '日志审计', '', '', '30', '1', '', '1', '');
INSERT INTO `sys_menu` VALUES ('f9f13acd9c6247788520a512f6d5b147', '60076411112b4dbe93d126ed9865621f', '0,1,60076411112b4dbe93d126ed9865621f,', '印章管理', '', '', '30', '1', '', '1', '');

-- ----------------------------
-- Table structure for sys_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office` (
  `id` varchar(64) NOT NULL,
  `area_id` varchar(10) DEFAULT NULL,
  `parent_id` varchar(64) DEFAULT NULL,
  `parent_ids` varchar(2000) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zip_code` varchar(10) DEFAULT NULL,
  `master_id` varchar(10) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_office
-- ----------------------------
INSERT INTO `sys_office` VALUES ('1', '2', '0', '0,', '100000', '北京市总公司', '1', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('10', '8', '7', '0,1,7,', '200003', '市场部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('11', '8', '7', '0,1,7,', '200004', '技术部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('12', '9', '7', '0,1,7,', '201000', '济南市分公司', '1', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('13', '9', '12', '0,1,7,12,', '201001', '公司领导', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('14', '9', '12', '0,1,7,12,', '201002', '综合部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('15', '9', '12', '0,1,7,12,', '201003', '市场部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('16', '9', '12', '0,1,7,12,', '201004', '技术部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('17', '11', '12', '0,1,7,12,', '2010101', '济南市历城区分公司1', '1', '0', '11', '22', null, null, '43', '54', '65', '76');
INSERT INTO `sys_office` VALUES ('18', '11', '17', '0,1,7,12,17,', '201011', '公司领导', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('19', '11', '17', '0,1,7,12,17,', '201012', '综合部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('2', '2', '1', '0,1,', '100001', '公司领导', '1', '0', '1', '2', null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('20', '11', '17', '0,1,7,12,17,', '201013', '市场部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('21', '11', '17', '0,1,7,12,17,', '201014', '技术部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('22', '12', '12', '0,1,7,12,', '201020', '济南市历下区分公司', '1', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('23', '12', '22', '0,1,7,12,22,', '201021', '公司领导', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('24', '12', '22', '0,1,7,12,22,', '201022', '综合部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('25', '12', '22', '0,1,7,12,22,', '201023', '市场部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('26', '12', '22', '0,1,7,12,22,', '201024', '技术部', '2', '1', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('3', '2', '1', '0,1,', '100002', '人力部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('4', '2', '1', '0,1,', '100003', '市场部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('5', '2', '1', '0,1,', '100004', '技术部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('5a45032753', '7', '9', '0,1,7,9,', '111111', '综合部1', '1', '0', '1', '2', null, null, '3', '4', '5', '6');
INSERT INTO `sys_office` VALUES ('6', '2', '1', '0,1,', '100005', '研发部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('6671f053-4', null, '9', '0,1,7,9,', '1111111111', '综合部111', '1', '1', '1', '2', null, null, '3', '4', '5', '6');
INSERT INTO `sys_office` VALUES ('7', '8', '1', '0,1,', '200000', '山东省分公司', '1', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('7dc2461f0f9b4e4bb37824ca331b0b9a', '3', '12', '0,1,7,12,', '123', '123', '2', '1', '123', '123', null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('8', '8', '7', '0,1,7,', '200001', '公司领导', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('9', '8', '7', '0,1,7,', '200002', '综合部', '2', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_office` VALUES ('96f63ff396cf4e1cb3a98c4e1faa5149', '2', '1', '0,1,', '1234', '1234', '1', '1', '12314', '12312', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_org_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_role`;
CREATE TABLE `sys_org_role` (
  `office_id` varchar(64) NOT NULL,
  `role_id` varchar(64) NOT NULL,
  PRIMARY KEY (`office_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_org_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `data_scope` int(11) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `is_default` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '1', '222', '0', '1');
INSERT INTO `sys_role` VALUES ('20', '评标专家', '8', '评标专家', '0', '1');
INSERT INTO `sys_role` VALUES ('30', '供应商', '8', '供应商', '0', '1');
INSERT INTO `sys_role` VALUES ('PEN6WCTJJE', '测试角色', '1', '测试', '1', '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `menu_id` varchar(64) NOT NULL,
  `role_id` varchar(64) NOT NULL,
  PRIMARY KEY (`menu_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('10', '1');
INSERT INTO `sys_role_menu` VALUES ('10', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('11', '1');
INSERT INTO `sys_role_menu` VALUES ('11', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('12', '1');
INSERT INTO `sys_role_menu` VALUES ('12', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('13', '1');
INSERT INTO `sys_role_menu` VALUES ('13', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('13d3119a9d', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('14', '1');
INSERT INTO `sys_role_menu` VALUES ('14', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('15', '1');
INSERT INTO `sys_role_menu` VALUES ('15', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('16', '1');
INSERT INTO `sys_role_menu` VALUES ('16', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('17', '1');
INSERT INTO `sys_role_menu` VALUES ('17', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('18', '1');
INSERT INTO `sys_role_menu` VALUES ('18', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('19', '1');
INSERT INTO `sys_role_menu` VALUES ('19', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('1c3987d505', '1');
INSERT INTO `sys_role_menu` VALUES ('1c61dc1fa7', '1');
INSERT INTO `sys_role_menu` VALUES ('1c61dc1fa7', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('20', '1');
INSERT INTO `sys_role_menu` VALUES ('20', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('21', '1');
INSERT INTO `sys_role_menu` VALUES ('21', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('22', '1');
INSERT INTO `sys_role_menu` VALUES ('22', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('23', '1');
INSERT INTO `sys_role_menu` VALUES ('23', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('24', '1');
INSERT INTO `sys_role_menu` VALUES ('24', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('25', '1');
INSERT INTO `sys_role_menu` VALUES ('25', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('252ecfdfba', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('26', '1');
INSERT INTO `sys_role_menu` VALUES ('26', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('27', '1');
INSERT INTO `sys_role_menu` VALUES ('27', '20');
INSERT INTO `sys_role_menu` VALUES ('27', '30');
INSERT INTO `sys_role_menu` VALUES ('27', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('28', '1');
INSERT INTO `sys_role_menu` VALUES ('28', '20');
INSERT INTO `sys_role_menu` VALUES ('28', '30');
INSERT INTO `sys_role_menu` VALUES ('28', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('29', '1');
INSERT INTO `sys_role_menu` VALUES ('29', '20');
INSERT INTO `sys_role_menu` VALUES ('29', '30');
INSERT INTO `sys_role_menu` VALUES ('29', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('2a41260e4a', '1');
INSERT INTO `sys_role_menu` VALUES ('2a41260e4a', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('3', '1');
INSERT INTO `sys_role_menu` VALUES ('3', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('30', '1');
INSERT INTO `sys_role_menu` VALUES ('30', '20');
INSERT INTO `sys_role_menu` VALUES ('30', '30');
INSERT INTO `sys_role_menu` VALUES ('30', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('32df3d86a3', '1');
INSERT INTO `sys_role_menu` VALUES ('32df3d86a3', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('3d33c15c65', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('4', '1');
INSERT INTO `sys_role_menu` VALUES ('4', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('44b175530d', '1');
INSERT INTO `sys_role_menu` VALUES ('482c5bef18', '1');
INSERT INTO `sys_role_menu` VALUES ('482c5bef18', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('5', '1');
INSERT INTO `sys_role_menu` VALUES ('5', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('6', '1');
INSERT INTO `sys_role_menu` VALUES ('6', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('664aabf317', '30');
INSERT INTO `sys_role_menu` VALUES ('67', '1');
INSERT INTO `sys_role_menu` VALUES ('67', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('68', '1');
INSERT INTO `sys_role_menu` VALUES ('68', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('68fc127343', '1');
INSERT INTO `sys_role_menu` VALUES ('68fc127343', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('6a06cc8656', '1');
INSERT INTO `sys_role_menu` VALUES ('6a06cc8656', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('6cff3118a9', '1');
INSERT INTO `sys_role_menu` VALUES ('6cff3118a9', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('7', '1');
INSERT INTO `sys_role_menu` VALUES ('7', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('76', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('77', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('78', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('79', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('7c5e610086', '1');
INSERT INTO `sys_role_menu` VALUES ('7c5e610086', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('7dfbfee854', '1');
INSERT INTO `sys_role_menu` VALUES ('7dfbfee854', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('8', '1');
INSERT INTO `sys_role_menu` VALUES ('8', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('80', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('9', '1');
INSERT INTO `sys_role_menu` VALUES ('9', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('91e8eaf6-c6b1-4f1c-995d-7c1295f18527', '1');
INSERT INTO `sys_role_menu` VALUES ('91e8eaf6-c6b1-4f1c-995d-7c1295f18527', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('98ef6af5b6', '1');
INSERT INTO `sys_role_menu` VALUES ('98ef6af5b6', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('a2e8c9e87a444be9b00238e5fdb317f8', '1');
INSERT INTO `sys_role_menu` VALUES ('a2e8c9e87a444be9b00238e5fdb317f8', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('ad7a8ca34e', '1');
INSERT INTO `sys_role_menu` VALUES ('adc17a5d16', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('c4644eba6c', '1');
INSERT INTO `sys_role_menu` VALUES ('c4644eba6c', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('dfc523e954', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('e29fdbff526d45dcaecab4489a79264b', '1');
INSERT INTO `sys_role_menu` VALUES ('e29fdbff526d45dcaecab4489a79264b', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('ec347ab221', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('ef2331fc79', 'PEN6WCTJJE');
INSERT INTO `sys_role_menu` VALUES ('f13419c5ab3848bab83e69fba5aa264b', '1');
INSERT INTO `sys_role_menu` VALUES ('f13419c5ab3848bab83e69fba5aa264b', 'PEN6WCTJJE');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(10) NOT NULL,
  `role_id` varchar(64) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1');
INSERT INTO `sys_user_role` VALUES ('5749500575', '30');
INSERT INTO `sys_user_role` VALUES ('9772282019', '30');

-- ----------------------------
-- Table structure for user_base_info
-- ----------------------------
DROP TABLE IF EXISTS `user_base_info`;
CREATE TABLE `user_base_info` (
  `user_id` varchar(10) NOT NULL COMMENT '用户ID',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '出生年月',
  `identity_number` varchar(10) DEFAULT NULL COMMENT '身份证号码',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基础信息';

-- ----------------------------
-- Records of user_base_info
-- ----------------------------
INSERT INTO `user_base_info` VALUES ('1', '1', '2017-04-30', '1');

-- ----------------------------
-- Table structure for user_contact_info
-- ----------------------------
DROP TABLE IF EXISTS `user_contact_info`;
CREATE TABLE `user_contact_info` (
  `user_id` varchar(10) NOT NULL COMMENT '用户ID',
  `area_code` varchar(10) DEFAULT NULL COMMENT '区域编码',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '座机号码',
  `wechat_id` varchar(50) DEFAULT NULL COMMENT '微信OpenID',
  `address` varchar(200) DEFAULT NULL COMMENT '用户地址',
  `zip_code` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户联系方式';

-- ----------------------------
-- Records of user_contact_info
-- ----------------------------
INSERT INTO `user_contact_info` VALUES ('0607154600', null, '5', '5@qq.com', null, null, null, null);
INSERT INTO `user_contact_info` VALUES ('0884034869', null, '18229779123', 'lcw2004@163.com', '21', null, null, null);
INSERT INTO `user_contact_info` VALUES ('1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `user_contact_info` VALUES ('1286461960', null, '6', '5@qq.com', null, null, null, null);
INSERT INTO `user_contact_info` VALUES ('1292028639', null, '4', '5@qq.com', null, null, null, null);
INSERT INTO `user_contact_info` VALUES ('1389657075', null, '13908888888', '888888888@88.com', '888888888888', null, null, null);
INSERT INTO `user_contact_info` VALUES ('1568196648', null, '18229779123', 'lcw@qq.com', '1234567890', null, null, null);
INSERT INTO `user_contact_info` VALUES ('2', null, '44441441443', null, '222144144144', null, '3444144144144144144144144144144144144', null);
INSERT INTO `user_contact_info` VALUES ('4750583434', null, '18229779123', 'lcw2004@163.com', '18229779123', null, null, null);
INSERT INTO `user_contact_info` VALUES ('5024478887', null, '4', '5@qq.com', null, null, null, null);
INSERT INTO `user_contact_info` VALUES ('5483409971', null, '1', '292232611@qq.com', '', null, null, null);
INSERT INTO `user_contact_info` VALUES ('5577778120', null, '4', '5@qq.com', null, null, null, null);
INSERT INTO `user_contact_info` VALUES ('5749500575', null, '66666666677', '6666@66.com', '666666666667', null, '2222', null);
INSERT INTO `user_contact_info` VALUES ('6674408480', null, '12311', '123@qq.com', '123456111', null, null, null);
INSERT INTO `user_contact_info` VALUES ('8791146057', null, '4', '4@qq.com', null, null, null, null);
INSERT INTO `user_contact_info` VALUES ('9266848058', null, '77777777777', '7777@77.com', '777777777777', null, null, null);
INSERT INTO `user_contact_info` VALUES ('9772282019', null, '88888888888', '88@88.com', '888888888888', null, '8888888888888888', null);

-- ----------------------------
-- Table structure for user_education_info
-- ----------------------------
DROP TABLE IF EXISTS `user_education_info`;
CREATE TABLE `user_education_info` (
  `user_id` varchar(10) NOT NULL COMMENT '用户ID',
  `last_education` varchar(50) DEFAULT NULL COMMENT '最高学历',
  `last_school` varchar(50) DEFAULT NULL COMMENT '最后毕业院校',
  `study_profession` varchar(50) DEFAULT NULL COMMENT '学校专业',
  `graduation_time` date DEFAULT NULL COMMENT '毕业时间',
  `work_start_time` date DEFAULT NULL COMMENT '开始工作时间',
  `work_profession` varchar(50) DEFAULT NULL COMMENT '从事专业',
  `work_resume` blob COMMENT '工作简历',
  `graduation_certificate` blob COMMENT '毕业证书影印件',
  `political_status` int(11) DEFAULT NULL COMMENT '政治面貌',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学历信息';

-- ----------------------------
-- Records of user_education_info
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(10) NOT NULL COMMENT '用户ID',
  `account` varchar(50) DEFAULT NULL COMMENT '用户帐号',
  `name` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '用户密码',
  `type` int(11) DEFAULT NULL COMMENT '用户类型',
  `create_ip` varchar(15) DEFAULT NULL COMMENT '注册IP',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `last_login_ip` varchar(15) DEFAULT NULL COMMENT '最后登录IP',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` int(11) DEFAULT NULL COMMENT '用户状态',
  `gender` int(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `identity_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础用户表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'superadmin', '超级管理员', '998d7349b60628a06c6445d655e8bc50632a4fdd616733750b2c083e', '1', null, '2017-04-21 22:15:02', '127.0.0.1', '2017-05-12 15:33:13', '1', null, null, null);
INSERT INTO `user_info` VALUES ('2', 'admin', '管理员', '373a3f5bbe6d14f52741bea6861d8672e14b07f81bcd0087d77a40b0', '1', null, '2017-04-17 21:04:43', '0:0:0:0:0:0:0:1', '2017-05-18 10:01:31', '1', null, null, '111111111111111111');

-- ----------------------------
-- Table structure for user_manager
-- ----------------------------
DROP TABLE IF EXISTS `user_manager`;
CREATE TABLE `user_manager` (
  `user_id` varchar(10) NOT NULL COMMENT '用户ID',
  `office_id` varchar(10) DEFAULT NULL COMMENT '所属机构ID',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理用户表';

-- ----------------------------
-- Records of user_manager
-- ----------------------------

-- ----------------------------
-- Table structure for user_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `user_operation_log`;
CREATE TABLE `user_operation_log` (
  `user_id` varchar(10) NOT NULL COMMENT '用户ID',
  `operate_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `result` int(11) DEFAULT NULL COMMENT '操作结果',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `log_id` varchar(64) NOT NULL COMMENT '日志ID',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `operate_code` varchar(10) NOT NULL COMMENT '类型编码',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户操作日志表';

-- ----------------------------
-- Records of user_operation_log
-- ----------------------------
