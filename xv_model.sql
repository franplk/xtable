/*
Navicat MySQL Data Transfer

Source Server         : app_db
Source Server Version : 50534
Source Host           : 123.59.18.238:3306
Source Database       : xview_model

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2016-10-10 11:20:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xv_chart
-- ----------------------------
DROP TABLE IF EXISTS `xv_chart`;
CREATE TABLE `xv_chart` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) NOT NULL DEFAULT 'line',
  `title` varchar(128) DEFAULT NULL,
  `sub_title` varchar(120) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_chart
-- ----------------------------

-- ----------------------------
-- Table structure for xv_chart_axis
-- ----------------------------
DROP TABLE IF EXISTS `xv_chart_axis`;
CREATE TABLE `xv_chart_axis` (
  `chart_id` int(8) NOT NULL,
  `chart_axis_col` varchar(128) NOT NULL,
  `chart_axis_flag` varchar(128) NOT NULL,
  `chart_axis_code` int(8) NOT NULL COMMENT 'col order in axis',
  `chart_axis_name` varchar(128) DEFAULT NULL COMMENT 'axis name'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_chart_axis
-- ----------------------------

-- ----------------------------
-- Table structure for xv_chart_style
-- ----------------------------
DROP TABLE IF EXISTS `xv_chart_style`;
CREATE TABLE `xv_chart_style` (
  `id` int(8) NOT NULL,
  `chart_id` int(8) NOT NULL,
  `fontFamily` varchar(32) DEFAULT NULL,
  `fontSize` int(4) DEFAULT NULL,
  `fontWeight` varchar(16) DEFAULT NULL,
  `color` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_chart_style
-- ----------------------------

-- ----------------------------
-- Table structure for xv_dashboard
-- ----------------------------
DROP TABLE IF EXISTS `xv_dashboard`;
CREATE TABLE `xv_dashboard` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `dim_id` int(4) DEFAULT NULL,
  `board` varchar(32) DEFAULT NULL,
  `datespan` varchar(16) DEFAULT 'before',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=503 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_dashboard
-- ----------------------------
INSERT INTO `xv_dashboard` VALUES ('1', 'assets_cm', '102', null, 'prev');
INSERT INTO `xv_dashboard` VALUES ('2', 'assets_bid', '101', null, 'prev');
INSERT INTO `xv_dashboard` VALUES ('3', 'assets_user', null, null, 'prev');
INSERT INTO `xv_dashboard` VALUES ('4', 'assets_other', null, null, 'prev');
INSERT INTO `xv_dashboard` VALUES ('5', 'platform_all', '106', 'fullplatform', 'prev');
INSERT INTO `xv_dashboard` VALUES ('6', 'business_day', '101', null, 'week');
INSERT INTO `xv_dashboard` VALUES ('7', 'business_hour', '104', null, 'curr');
INSERT INTO `xv_dashboard` VALUES ('8', 'business_industry', '107', null, 'curr');
INSERT INTO `xv_dashboard` VALUES ('9', 'business_project', '106', null, 'curr');
INSERT INTO `xv_dashboard` VALUES ('10', 'business_camp', '108', null, 'curr');
INSERT INTO `xv_dashboard` VALUES ('11', 'flow_summary', '101', null, 'week');
INSERT INTO `xv_dashboard` VALUES ('12', 'flow_trade', '111', null, 'curr');
INSERT INTO `xv_dashboard` VALUES ('13', 'flow_type', '112', null, 'curr');
INSERT INTO `xv_dashboard` VALUES ('14', 'flow_channel', '103', null, 'curr');
INSERT INTO `xv_dashboard` VALUES ('16', 'flow_domain', '117', null, 'curr');
INSERT INTO `xv_dashboard` VALUES ('17', 'flow_adslot', '119', null, 'curr');
INSERT INTO `xv_dashboard` VALUES ('31', 'creative_sum', '121', null, 'curr');
INSERT INTO `xv_dashboard` VALUES ('32', 'creative_template', '121', null, 'curr');
INSERT INTO `xv_dashboard` VALUES ('501', 'rule_request', '103', 'rule', 'prev');
INSERT INTO `xv_dashboard` VALUES ('502', 'rule_camp', '502', 'rule', 'prev');

-- ----------------------------
-- Table structure for xv_dashboard_drill
-- ----------------------------
DROP TABLE IF EXISTS `xv_dashboard_drill`;
CREATE TABLE `xv_dashboard_drill` (
  `board_id` int(8) NOT NULL,
  `drill_id` int(8) NOT NULL,
  `drill_name` varchar(16) NOT NULL,
  `drill_label` varchar(16) NOT NULL,
  `drill_title` varchar(16) NOT NULL DEFAULT 'New Tab'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_dashboard_drill
-- ----------------------------
INSERT INTO `xv_dashboard_drill` VALUES ('6', '7', 'hour_id', '时段', '时段统计');
INSERT INTO `xv_dashboard_drill` VALUES ('6', '8', 'category_id', '行业', '行业统计');
INSERT INTO `xv_dashboard_drill` VALUES ('6', '9', 'project_id', '项目', '项目统计');
INSERT INTO `xv_dashboard_drill` VALUES ('6', '10', 'campaign_id', '活动', '活动统计');
INSERT INTO `xv_dashboard_drill` VALUES ('6', '14', 'channel_id', '渠道', '渠道分析');
INSERT INTO `xv_dashboard_drill` VALUES ('6', '16', 'domain', '域名', '域名(PC/APP)');
INSERT INTO `xv_dashboard_drill` VALUES ('6', '17', 'adslot_id', '广告位', '广告位分析');
INSERT INTO `xv_dashboard_drill` VALUES ('7', '8', 'category_id', '行业', '行业统计');
INSERT INTO `xv_dashboard_drill` VALUES ('7', '9', 'project_id', '项目', '项目统计');
INSERT INTO `xv_dashboard_drill` VALUES ('7', '10', 'campaign_id', '活动', '活动统计');
INSERT INTO `xv_dashboard_drill` VALUES ('8', '6', 'rep_date', '日期', '日统计');
INSERT INTO `xv_dashboard_drill` VALUES ('8', '7', 'hour_id', '时段', '时段统计');
INSERT INTO `xv_dashboard_drill` VALUES ('8', '9', 'project_id', '项目', '项目统计');
INSERT INTO `xv_dashboard_drill` VALUES ('8', '10', 'campaign_id', '活动', '活动统计');
INSERT INTO `xv_dashboard_drill` VALUES ('8', '14', 'channel_id', '渠道', '渠道分析');
INSERT INTO `xv_dashboard_drill` VALUES ('8', '16', 'domain', '域名', '域名(PC/APP)');
INSERT INTO `xv_dashboard_drill` VALUES ('8', '17', 'adslot_id', '广告位', '广告位分析');
INSERT INTO `xv_dashboard_drill` VALUES ('9', '7', 'hour_id', '时段', '时段统计');
INSERT INTO `xv_dashboard_drill` VALUES ('9', '10', 'campaign_id', '活动', '活动统计');
INSERT INTO `xv_dashboard_drill` VALUES ('9', '14', 'channel_id', '渠道', '渠道分析');
INSERT INTO `xv_dashboard_drill` VALUES ('9', '16', 'domain', '域名', '域名(PC/APP)');
INSERT INTO `xv_dashboard_drill` VALUES ('9', '17', 'adslot_id', '广告位', '广告位分析');
INSERT INTO `xv_dashboard_drill` VALUES ('10', '6', 'rep_date', '日期', '日统计');
INSERT INTO `xv_dashboard_drill` VALUES ('10', '7', 'hour_id', '时段', '时段统计');
INSERT INTO `xv_dashboard_drill` VALUES ('10', '14', 'channel_id', '渠道', '渠道分析');
INSERT INTO `xv_dashboard_drill` VALUES ('10', '16', 'domain', '域名', '域名(PC/APP)');
INSERT INTO `xv_dashboard_drill` VALUES ('10', '17', 'adslot_id', '广告位', '广告位分析');
INSERT INTO `xv_dashboard_drill` VALUES ('11', '12', 'trade_type', '交易方式', '交易方式');
INSERT INTO `xv_dashboard_drill` VALUES ('11', '13', 'flow_type', '流量类型', '流量类型');
INSERT INTO `xv_dashboard_drill` VALUES ('11', '14', 'channel_id', '渠道', '渠道分析');
INSERT INTO `xv_dashboard_drill` VALUES ('11', '16', 'domain', '域名', '域名(PC/APP)');
INSERT INTO `xv_dashboard_drill` VALUES ('11', '17', 'adslot_id', '广告位', '广告位分析');
INSERT INTO `xv_dashboard_drill` VALUES ('12', '13', 'flow_type', '流量类型', '流量类型');
INSERT INTO `xv_dashboard_drill` VALUES ('12', '14', 'channel_id', '渠道', '渠道分析');
INSERT INTO `xv_dashboard_drill` VALUES ('12', '16', 'domain', '域名', '域名(PC/APP)');
INSERT INTO `xv_dashboard_drill` VALUES ('12', '17', 'adslot_id', '广告位', '广告位分析');
INSERT INTO `xv_dashboard_drill` VALUES ('13', '12', 'trade_type', '交易方式', '交易方式');
INSERT INTO `xv_dashboard_drill` VALUES ('13', '14', 'channel_id', '渠道', '渠道分析');
INSERT INTO `xv_dashboard_drill` VALUES ('13', '16', 'domain', '域名', '域名(PC/APP)');
INSERT INTO `xv_dashboard_drill` VALUES ('13', '17', 'adslot_id', '广告位', '广告位分析');
INSERT INTO `xv_dashboard_drill` VALUES ('14', '16', 'domain', '域名', '域名(PC/APP)');
INSERT INTO `xv_dashboard_drill` VALUES ('14', '17', 'adslot_id', '广告位', '广告位分析');
INSERT INTO `xv_dashboard_drill` VALUES ('16', '17', 'adslot_id', '广告位', '广告位分析');
INSERT INTO `xv_dashboard_drill` VALUES ('16', '14', 'channel_id', '渠道', '渠道分析');
INSERT INTO `xv_dashboard_drill` VALUES ('17', '16', 'domain', '域名', '域名(PC/APP)');
INSERT INTO `xv_dashboard_drill` VALUES ('9', '6', 'rep_date', '日期', '日统计');
INSERT INTO `xv_dashboard_drill` VALUES ('31', '16', 'domain', '域名', '域名(PC/APP)');
INSERT INTO `xv_dashboard_drill` VALUES ('31', '17', 'adslot_id', '广告位', '广告位分析');
INSERT INTO `xv_dashboard_drill` VALUES ('31', '6', 'rep_date', '日期', '日统计');
INSERT INTO `xv_dashboard_drill` VALUES ('31', '7', 'hour_id', '时段', '时段统计');
INSERT INTO `xv_dashboard_drill` VALUES ('31', '9', 'project_id', '项目', '项目统计');
INSERT INTO `xv_dashboard_drill` VALUES ('31', '10', 'campaign_id', '活动', '活动统计');
INSERT INTO `xv_dashboard_drill` VALUES ('31', '14', 'channel_id', '渠道', '渠道分析');
INSERT INTO `xv_dashboard_drill` VALUES ('14', '10', 'campaign_id', '活动', '活动统计');
INSERT INTO `xv_dashboard_drill` VALUES ('12', '11', 'rep_date', '日期', '流量概况');
INSERT INTO `xv_dashboard_drill` VALUES ('13', '11', 'rep_date', '日期', '流量概况');
INSERT INTO `xv_dashboard_drill` VALUES ('14', '11', 'rep_date', '日期', '流量概况');
INSERT INTO `xv_dashboard_drill` VALUES ('16', '11', 'rep_date', '日期', '流量概况');
INSERT INTO `xv_dashboard_drill` VALUES ('17', '11', 'rep_date', '日期', '流量概况');
INSERT INTO `xv_dashboard_drill` VALUES ('6', '31', 'creative_id', '创意', '创意统计');
INSERT INTO `xv_dashboard_drill` VALUES ('7', '31', 'creative_id', '创意', '创意统计');
INSERT INTO `xv_dashboard_drill` VALUES ('8', '31', 'creative_id', '创意', '创意统计');
INSERT INTO `xv_dashboard_drill` VALUES ('9', '31', 'creative_id', '创意', '创意统计');
INSERT INTO `xv_dashboard_drill` VALUES ('10', '31', 'creative_id', '创意', '创意统计');
INSERT INTO `xv_dashboard_drill` VALUES ('11', '31', 'creative_id', '创意', '创意统计');
INSERT INTO `xv_dashboard_drill` VALUES ('12', '31', 'creative_id', '创意', '创意统计');
INSERT INTO `xv_dashboard_drill` VALUES ('13', '31', 'creative_id', '创意', '创意统计');
INSERT INTO `xv_dashboard_drill` VALUES ('14', '31', 'creative_id', '创意', '创意统计');
INSERT INTO `xv_dashboard_drill` VALUES ('16', '31', 'creative_id', '创意', '创意统计');
INSERT INTO `xv_dashboard_drill` VALUES ('17', '31', 'creative_id', '创意', '创意统计');
INSERT INTO `xv_dashboard_drill` VALUES ('14', '9', 'project_id', '项目', '项目统计');
INSERT INTO `xv_dashboard_drill` VALUES ('16', '9', 'project_id', '项目', '项目统计');
INSERT INTO `xv_dashboard_drill` VALUES ('16', '10', 'campaign_id', '活动', '活动统计');
INSERT INTO `xv_dashboard_drill` VALUES ('17', '9', 'project_id', '项目', '项目统计');
INSERT INTO `xv_dashboard_drill` VALUES ('17', '10', 'campaign_id', '活动', '活动统计');

-- ----------------------------
-- Table structure for xv_dashboard_rel
-- ----------------------------
DROP TABLE IF EXISTS `xv_dashboard_rel`;
CREATE TABLE `xv_dashboard_rel` (
  `dashboard_id` int(4) NOT NULL,
  `data_model_id` int(4) NOT NULL,
  `chart_id` int(4) DEFAULT NULL,
  `filter_id` int(4) NOT NULL DEFAULT '1',
  KEY `dashbaord` (`dashboard_id`),
  KEY `datamodel` (`data_model_id`),
  KEY `chart` (`chart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_dashboard_rel
-- ----------------------------
INSERT INTO `xv_dashboard_rel` VALUES ('1', '1', null, '1');
INSERT INTO `xv_dashboard_rel` VALUES ('2', '2', null, '1');
INSERT INTO `xv_dashboard_rel` VALUES ('5', '5', null, '1');
INSERT INTO `xv_dashboard_rel` VALUES ('6', '6', null, '2');
INSERT INTO `xv_dashboard_rel` VALUES ('7', '7', null, '2');
INSERT INTO `xv_dashboard_rel` VALUES ('8', '8', null, '2');
INSERT INTO `xv_dashboard_rel` VALUES ('9', '9', null, '2');
INSERT INTO `xv_dashboard_rel` VALUES ('10', '10', null, '2');
INSERT INTO `xv_dashboard_rel` VALUES ('11', '11', null, '3');
INSERT INTO `xv_dashboard_rel` VALUES ('12', '12', null, '3');
INSERT INTO `xv_dashboard_rel` VALUES ('13', '13', null, '3');
INSERT INTO `xv_dashboard_rel` VALUES ('14', '14', null, '3');
INSERT INTO `xv_dashboard_rel` VALUES ('16', '16', null, '4');
INSERT INTO `xv_dashboard_rel` VALUES ('17', '17', null, '4');
INSERT INTO `xv_dashboard_rel` VALUES ('501', '501', null, '501');
INSERT INTO `xv_dashboard_rel` VALUES ('502', '502', null, '502');
INSERT INTO `xv_dashboard_rel` VALUES ('31', '31', null, '5');
INSERT INTO `xv_dashboard_rel` VALUES ('32', '32', null, '5');

-- ----------------------------
-- Table structure for xv_datamodel
-- ----------------------------
DROP TABLE IF EXISTS `xv_datamodel`;
CREATE TABLE `xv_datamodel` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `ord_id` int(4) DEFAULT NULL COMMENT 'OrderField',
  `is_sum` int(2) NOT NULL DEFAULT '1',
  `theme_id` int(4) NOT NULL COMMENT 'ThemeID',
  `description` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `datatheme` (`theme_id`)
) ENGINE=InnoDB AUTO_INCREMENT=503 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_datamodel
-- ----------------------------
INSERT INTO `xv_datamodel` VALUES ('1', null, '1', '1', 'Assets_CM');
INSERT INTO `xv_datamodel` VALUES ('2', null, '1', '2', 'Assets_bid');
INSERT INTO `xv_datamodel` VALUES ('3', null, '1', '3', 'Assets_user');
INSERT INTO `xv_datamodel` VALUES ('4', null, '1', '4', 'Assets_other');
INSERT INTO `xv_datamodel` VALUES ('5', '231', '1', '5', 'Platform_full');
INSERT INTO `xv_datamodel` VALUES ('6', '101', '1', '6', 'Platform_day');
INSERT INTO `xv_datamodel` VALUES ('7', '104', '1', '6', 'Platform_hour');
INSERT INTO `xv_datamodel` VALUES ('8', '205', '1', '6', 'PLatform_industry');
INSERT INTO `xv_datamodel` VALUES ('9', '205', '1', '6', 'Platform_project');
INSERT INTO `xv_datamodel` VALUES ('10', '205', '1', '6', 'Platform_campaign');
INSERT INTO `xv_datamodel` VALUES ('11', '101', '1', '7', 'flow_summary');
INSERT INTO `xv_datamodel` VALUES ('12', '205', '1', '7', 'flow_trade_type');
INSERT INTO `xv_datamodel` VALUES ('13', '205', '1', '7', 'flow_type');
INSERT INTO `xv_datamodel` VALUES ('14', '205', '1', '7', 'flow_channel');
INSERT INTO `xv_datamodel` VALUES ('16', '205', '1', '7', 'flow_domain');
INSERT INTO `xv_datamodel` VALUES ('17', '205', '1', '7', 'flow_adsolt');
INSERT INTO `xv_datamodel` VALUES ('31', '205', '1', '6', 'creative_sum');
INSERT INTO `xv_datamodel` VALUES ('32', '205', '1', '6', 'creative_template');
INSERT INTO `xv_datamodel` VALUES ('501', '503', '0', '501', 'rule_request');
INSERT INTO `xv_datamodel` VALUES ('502', '503', '0', '502', 'rule_camp');

-- ----------------------------
-- Table structure for xv_filter_condition
-- ----------------------------
DROP TABLE IF EXISTS `xv_filter_condition`;
CREATE TABLE `xv_filter_condition` (
  `filter_id` int(8) NOT NULL,
  `col_id` int(8) NOT NULL,
  `level` int(2) NOT NULL DEFAULT '1',
  `code` int(4) NOT NULL DEFAULT '1',
  `type_id` int(8) NOT NULL DEFAULT '1',
  `value_id` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_filter_condition
-- ----------------------------
INSERT INTO `xv_filter_condition` VALUES ('1', '106', '1', '1', '4', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '106', '1', '1', '4', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '108', '1', '2', '4', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '103', '1', '3', '4', '2');
INSERT INTO `xv_filter_condition` VALUES ('2', '111', '1', '4', '4', '3');
INSERT INTO `xv_filter_condition` VALUES ('2', '112', '1', '5', '5', '4');
INSERT INTO `xv_filter_condition` VALUES ('2', '113', '1', '6', '4', '1');
INSERT INTO `xv_filter_condition` VALUES ('1', '208', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('1', '209', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('1', '221', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('1', '222', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('1', '213', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('1', '211', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('1', '212', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('1', '216', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('1', '214', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('1', '215', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('1', '218', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('1', '227', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('1', '228', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('1', '229', '2', '1', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '205', '2', '110', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '231', '2', '140', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '206', '2', '120', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '232', '2', '130', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '208', '2', '150', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '234', '2', '170', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '235', '2', '180', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '236', '2', '190', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '209', '2', '200', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '237', '2', '210', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '238', '2', '220', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '210', '2', '230', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '239', '2', '240', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '240', '2', '250', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '242', '2', '280', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '241', '2', '260', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '243', '2', '290', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '244', '2', '300', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '245', '2', '270', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '246', '2', '272', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '248', '2', '330', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '249', '2', '340', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '250', '2', '350', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '251', '2', '360', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '252', '2', '370', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '213', '2', '380', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '253', '2', '390', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '216', '2', '400', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '219', '2', '410', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '218', '2', '420', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '220', '2', '145', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('2', '207', '2', '189', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('502', '513', '1', '20', '4', '2');
INSERT INTO `xv_filter_condition` VALUES ('502', '502', '1', '30', '4', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '103', '1', '100', '4', '2');
INSERT INTO `xv_filter_condition` VALUES ('3', '111', '1', '110', '4', '3');
INSERT INTO `xv_filter_condition` VALUES ('3', '112', '1', '120', '5', '4');
INSERT INTO `xv_filter_condition` VALUES ('3', '113', '1', '130', '4', '1');
INSERT INTO `xv_filter_condition` VALUES ('3', '117', '1', '140', '2', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '119', '1', '150', '4', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '205', '2', '110', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '231', '2', '140', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '206', '2', '120', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '232', '2', '130', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '208', '2', '150', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '234', '2', '170', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '235', '2', '180', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '236', '2', '190', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '209', '2', '200', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '237', '2', '210', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '238', '2', '220', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '210', '2', '230', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '239', '2', '240', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '240', '2', '250', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '242', '2', '280', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '241', '2', '260', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '243', '2', '290', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '244', '2', '300', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '245', '2', '270', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '246', '2', '272', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '248', '2', '330', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '218', '2', '420', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '204', '2', '100', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '217', '2', '142', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '220', '2', '145', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '249', '2', '340', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '207', '2', '189', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '250', '2', '350', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '251', '2', '360', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '252', '2', '370', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '213', '2', '380', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '253', '2', '390', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '216', '2', '400', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('3', '219', '2', '410', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('501', '513', '1', '10', '4', '2');
INSERT INTO `xv_filter_condition` VALUES ('501', '509', '1', '30', '4', '51');
INSERT INTO `xv_filter_condition` VALUES ('501', '510', '1', '40', '4', '52');
INSERT INTO `xv_filter_condition` VALUES ('502', '512', '1', '10', '4', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '241', '2', '260', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '243', '2', '290', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '244', '2', '300', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '245', '2', '270', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '246', '2', '272', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '248', '2', '330', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '218', '2', '420', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '300', '2', '100', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '217', '2', '142', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '220', '2', '145', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '249', '2', '340', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '207', '2', '189', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '250', '2', '350', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '251', '2', '360', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '252', '2', '370', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '213', '2', '380', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '253', '2', '390', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '216', '2', '400', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '219', '2', '410', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '103', '1', '100', '4', '2');
INSERT INTO `xv_filter_condition` VALUES ('4', '111', '1', '110', '4', '3');
INSERT INTO `xv_filter_condition` VALUES ('4', '112', '1', '120', '5', '4');
INSERT INTO `xv_filter_condition` VALUES ('4', '113', '1', '130', '4', '1');
INSERT INTO `xv_filter_condition` VALUES ('4', '117', '1', '140', '2', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '119', '1', '150', '4', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '205', '2', '110', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '231', '2', '140', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '206', '2', '120', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '232', '2', '130', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '208', '2', '150', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '234', '2', '170', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '235', '2', '180', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '236', '2', '190', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '209', '2', '200', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '237', '2', '210', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '238', '2', '220', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '210', '2', '230', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '239', '2', '240', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '240', '2', '250', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('4', '242', '2', '280', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '106', '1', '1', '4', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '108', '1', '2', '4', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '103', '1', '3', '4', '2');
INSERT INTO `xv_filter_condition` VALUES ('5', '111', '1', '4', '4', '3');
INSERT INTO `xv_filter_condition` VALUES ('5', '112', '1', '5', '5', '4');
INSERT INTO `xv_filter_condition` VALUES ('5', '113', '1', '6', '4', '1');
INSERT INTO `xv_filter_condition` VALUES ('5', '205', '2', '110', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '231', '2', '140', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '206', '2', '120', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '232', '2', '130', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '208', '2', '150', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '234', '2', '170', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '235', '2', '180', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '236', '2', '190', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '209', '2', '200', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '237', '2', '210', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '238', '2', '220', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '210', '2', '230', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '239', '2', '240', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '240', '2', '250', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '242', '2', '280', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '241', '2', '260', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '243', '2', '290', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '244', '2', '300', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '245', '2', '270', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '246', '2', '272', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '248', '2', '330', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '249', '2', '340', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '250', '2', '350', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '251', '2', '360', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '252', '2', '370', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '213', '2', '380', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '253', '2', '390', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '216', '2', '400', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '219', '2', '410', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '218', '2', '420', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '220', '2', '145', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '207', '2', '189', '1', null);
INSERT INTO `xv_filter_condition` VALUES ('5', '121', '1', '7', '4', null);

-- ----------------------------
-- Table structure for xv_filter_type
-- ----------------------------
DROP TABLE IF EXISTS `xv_filter_type`;
CREATE TABLE `xv_filter_type` (
  `id` int(4) NOT NULL,
  `value` varchar(32) NOT NULL,
  `text` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_filter_type
-- ----------------------------
INSERT INTO `xv_filter_type` VALUES ('1', 'gte', '大于等于');
INSERT INTO `xv_filter_type` VALUES ('1', 'gt', '大于');
INSERT INTO `xv_filter_type` VALUES ('1', 'eq', '等于');
INSERT INTO `xv_filter_type` VALUES ('1', 'lte', '小于等于');
INSERT INTO `xv_filter_type` VALUES ('1', 'lt', '小于');
INSERT INTO `xv_filter_type` VALUES ('2', 'inc', '包含');
INSERT INTO `xv_filter_type` VALUES ('3', 'span', '区间为');
INSERT INTO `xv_filter_type` VALUES ('2', 'eq', '等于');
INSERT INTO `xv_filter_type` VALUES ('4', 'eq', '等于');
INSERT INTO `xv_filter_type` VALUES ('5', 'inc', '等于');

-- ----------------------------
-- Table structure for xv_filter_value
-- ----------------------------
DROP TABLE IF EXISTS `xv_filter_value`;
CREATE TABLE `xv_filter_value` (
  `id` int(8) NOT NULL,
  `value` varchar(32) NOT NULL,
  `text` varchar(32) NOT NULL,
  `code` int(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_filter_value
-- ----------------------------
INSERT INTO `xv_filter_value` VALUES ('2', '1001', 'egou(1001)', '1001');
INSERT INTO `xv_filter_value` VALUES ('2', '1002', 'yiqifa(1002)', '1002');
INSERT INTO `xv_filter_value` VALUES ('2', '1003', 'yigao直采(1003)', '1003');
INSERT INTO `xv_filter_value` VALUES ('2', '1004', '其它直采(1004)', '1004');
INSERT INTO `xv_filter_value` VALUES ('2', '5010', 'google adx(5010)', '5010');
INSERT INTO `xv_filter_value` VALUES ('2', '5011', 'google_app(5011)', '5011');
INSERT INTO `xv_filter_value` VALUES ('2', '5020', 'taobao tanx(5020)', '5020');
INSERT INTO `xv_filter_value` VALUES ('2', '5030', 'tencent adx(5030)', '5030');
INSERT INTO `xv_filter_value` VALUES ('2', '5040', 'yigao SSP(5040)', '5040');
INSERT INTO `xv_filter_value` VALUES ('2', '5041', '亿告PMP(5041)', '5041');
INSERT INTO `xv_filter_value` VALUES ('2', '5050', 'baidu adx(5050)', '5050');
INSERT INTO `xv_filter_value` VALUES ('2', '5060', 'allyes(5060)', '5060');
INSERT INTO `xv_filter_value` VALUES ('2', '5080', 'sina adx(5080)', '5080');
INSERT INTO `xv_filter_value` VALUES ('2', '5090', '芒果APP(5090)', '5090');
INSERT INTO `xv_filter_value` VALUES ('2', '5110', '秒针(5110)', '5110');
INSERT INTO `xv_filter_value` VALUES ('2', '5120', '广点通(5120)', '5120');
INSERT INTO `xv_filter_value` VALUES ('2', '5130', '互众(5130)', '5130');
INSERT INTO `xv_filter_value` VALUES ('2', '5140', '360(5140)', '5140');
INSERT INTO `xv_filter_value` VALUES ('2', '5150', '万流客(5150)', '5150');
INSERT INTO `xv_filter_value` VALUES ('2', '5160', '优酷(5160)', '5160');
INSERT INTO `xv_filter_value` VALUES ('1', '2', 'RD', '1');
INSERT INTO `xv_filter_value` VALUES ('1', '0,1', '非RD', '2');
INSERT INTO `xv_filter_value` VALUES ('3', '1001', '程序化购买(RTB)', '1');
INSERT INTO `xv_filter_value` VALUES ('3', '2001', '非程序化购买', '2');
INSERT INTO `xv_filter_value` VALUES ('4', '10', 'PC流量', '1');
INSERT INTO `xv_filter_value` VALUES ('4', '1001', 'PC Banner', '2');
INSERT INTO `xv_filter_value` VALUES ('4', '1003', 'PC Text', '3');
INSERT INTO `xv_filter_value` VALUES ('4', '1002', 'PC Video', '4');
INSERT INTO `xv_filter_value` VALUES ('4', '20', '移动流量', '5');
INSERT INTO `xv_filter_value` VALUES ('4', '2001', '移动WEB', '6');
INSERT INTO `xv_filter_value` VALUES ('4', '2002', '移动APP', '7');
INSERT INTO `xv_filter_value` VALUES ('4', '2003', '移动原生', '8');
INSERT INTO `xv_filter_value` VALUES ('2', '1011', '移动WEB直采(1011)', '1011');
INSERT INTO `xv_filter_value` VALUES ('2', '1012', '移动APP直采(1012)', '1012');
INSERT INTO `xv_filter_value` VALUES ('2', '5180', '易积分(5180)', '5180');
INSERT INTO `xv_filter_value` VALUES ('2', '5190', '凤凰(5190)', '5190');
INSERT INTO `xv_filter_value` VALUES ('2', '5021', 'taobao PMP(5021)', '5021');
INSERT INTO `xv_filter_value` VALUES ('2', '5210', '新浪微博(5210)', '5210');
INSERT INTO `xv_filter_value` VALUES ('2', '5220', 'inmobi(5220)', '5220');
INSERT INTO `xv_filter_value` VALUES ('51', 'MOBILE', 'MOBILE', '10');
INSERT INTO `xv_filter_value` VALUES ('51', 'PC', 'PC', '20');
INSERT INTO `xv_filter_value` VALUES ('52', 'BANNER', 'BANNER', '10');
INSERT INTO `xv_filter_value` VALUES ('52', 'APP', 'APP', '20');
INSERT INTO `xv_filter_value` VALUES ('52', 'NATIVE', 'NATIVE', '30');
INSERT INTO `xv_filter_value` VALUES ('4', '3001', '社交广告', '9');
INSERT INTO `xv_filter_value` VALUES ('2', '5230', 'adview(5230)', '5230');
INSERT INTO `xv_filter_value` VALUES ('3', '-1', '社交广告', '3');

-- ----------------------------
-- Table structure for xv_menu
-- ----------------------------
DROP TABLE IF EXISTS `xv_menu`;
CREATE TABLE `xv_menu` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `url` varchar(256) DEFAULT NULL,
  `subject` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `code` int(11) NOT NULL COMMENT 'order code',
  `valid` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_menu
-- ----------------------------
INSERT INTO `xv_menu` VALUES ('10', '资产报表', null, '10', '1', '1', '0');
INSERT INTO `xv_menu` VALUES ('11', '用户概况', '/board/3', '10', '2', '1', '0');
INSERT INTO `xv_menu` VALUES ('12', '竞价概况', '/board/2', '10', '2', '2', '0');
INSERT INTO `xv_menu` VALUES ('13', 'CM识别率', '/board/1', '10', '2', '3', '0');
INSERT INTO `xv_menu` VALUES ('20', '运营分析', null, '20', '1', '1', '1');
INSERT INTO `xv_menu` VALUES ('21', '全平台统计', '/board/5', '20', '2', '1', '1');
INSERT INTO `xv_menu` VALUES ('22', '日统计', '/board/6', '20', '2', '2', '1');
INSERT INTO `xv_menu` VALUES ('23', '时段统计', '/board/7', '20', '2', '3', '1');
INSERT INTO `xv_menu` VALUES ('24', '行业统计', '/board/8', '20', '2', '4', '1');
INSERT INTO `xv_menu` VALUES ('25', '项目统计', '/board/9', '20', '2', '5', '1');
INSERT INTO `xv_menu` VALUES ('26', '活动统计', '/board/10', '20', '2', '6', '1');
INSERT INTO `xv_menu` VALUES ('30', '流量分析', null, '30', '1', '1', '1');
INSERT INTO `xv_menu` VALUES ('31', '流量概况', '/board/11', '30', '2', '1', '1');
INSERT INTO `xv_menu` VALUES ('32', '交易方式', '/board/12', '30', '2', '2', '1');
INSERT INTO `xv_menu` VALUES ('33', '流量类型', '/board/13', '30', '2', '3', '1');
INSERT INTO `xv_menu` VALUES ('34', '渠道分析', '/board/14', '30', '2', '4', '1');
INSERT INTO `xv_menu` VALUES ('35', '域名(PC/APP)', '/board/16', '30', '2', '5', '1');
INSERT INTO `xv_menu` VALUES ('36', '广告位分析', '/board/17', '30', '2', '6', '1');
INSERT INTO `xv_menu` VALUES ('40', '创意分析', null, '40', '1', '1', '1');
INSERT INTO `xv_menu` VALUES ('41', '创意统计', '/board/31', '40', '2', '1', '1');
INSERT INTO `xv_menu` VALUES ('42', '模板统计', '/board/32', '40', '2', '2', '0');
INSERT INTO `xv_menu` VALUES ('50', '竞价分析', '', '300', '1', '1', '1');
INSERT INTO `xv_menu` VALUES ('51', '请求过滤', '/rule/501', '300', '2', '1', '1');
INSERT INTO `xv_menu` VALUES ('52', '活动匹配', '/rule/502', '300', '2', '2', '1');
INSERT INTO `xv_menu` VALUES ('1000', '我的模版', null, '1000', '1', '1', '0');

-- ----------------------------
-- Table structure for xv_metadata_column
-- ----------------------------
DROP TABLE IF EXISTS `xv_metadata_column`;
CREATE TABLE `xv_metadata_column` (
  `id` int(4) NOT NULL,
  `name` varchar(32) NOT NULL,
  `label` varchar(32) NOT NULL,
  `dim` int(2) NOT NULL,
  `datatype` varchar(16) DEFAULT NULL,
  `formula` varchar(64) DEFAULT NULL,
  `relyon` varchar(32) DEFAULT NULL COMMENT 'Display Together',
  `ex_value` varchar(16) DEFAULT NULL,
  `mapname` varchar(32) DEFAULT NULL,
  `maptitle` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_metadata_column
-- ----------------------------
INSERT INTO `xv_metadata_column` VALUES ('101', 'rep_date', '日期', '1', 'map', 'date_format_day', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('102', 'channel_id', '平台', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('103', 'channel_id', '渠道ID', '1', 'sig', null, '', null, 'channel_name', '渠道名称');
INSERT INTO `xv_metadata_column` VALUES ('104', 'hour_id', '时段', '1', 'map', 'date_format_hour', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('105', 'report_month', '月份', '1', 'map', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('106', 'project_id', '项目ID', '1', 'sig', null, '', '-1', 'project_name', '项目名称');
INSERT INTO `xv_metadata_column` VALUES ('107', 'category_id', '行业类型', '1', 'map', 'industry', null, '-1', null, null);
INSERT INTO `xv_metadata_column` VALUES ('108', 'campaign_id', '活动ID', '1', 'sig', null, null, '-1', 'campaign_name', '活动名称');
INSERT INTO `xv_metadata_column` VALUES ('111', 'trade_type', '交易方式', '1', 'map', 'trade_type', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('112', 'flow_type', '流量类型', '1', 'map', 'flow_convert', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('113', 'is_rd', 'RD类型', '1', 'map', 'is_rd', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('114', 'adslot_type', '广告位类型', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('115', 'is_native', '是否原生', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('116', 'media_type', '媒体类型', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('117', 'domain', '域名/APPID', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('118', 'subdomain', '子域名', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('119', 'adslot_id', '广告位ID', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('120', 'adslot_position', '广告位位置', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('121', 'creative_id', '创意ID', '1', 'sig', null, null, '-1', null, null);
INSERT INTO `xv_metadata_column` VALUES ('150', 'platform', '移动平台', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('151', 'brand', '设备品牌', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('152', 'device_type', '设备类型', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('153', 'device_model', '设备型号', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('154', 'os_version', '操作系统版本', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('155', 'ip', 'IP地址', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('156', 'is_app', '是否APP', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('157', 'app_category_ids', 'APP分类', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('158', 'app_name', 'APP名称', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('159', 'mobile_carrier_id', '运营商ID', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('160', 'wireless_network_type', '无线网络类型', '1', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('201', 'req_num', '总流量', '0', 'int', 'sum(req_num)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('202', 'match_num', '可识别流量', '0', 'int', 'sum(match_num)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('203', 'match_rate', '识别率(%)', '0', 'digit', '100*sum(match_num)/sum(req_num)', 'match_num,req_num', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('204', 'req_num', '请求量', '0', 'int', 'sum(req_num)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('205', 'bid', '投放量', '0', 'int', 'sum(bid)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('206', 'imp', '展现量', '0', 'int', 'sum(imp)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('207', 'clk', '点击量', '0', 'int', 'sum(clk)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('208', 'rtb_cost', '竞价花费', '0', 'digit', 'sum(rtb_cost)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('209', 'bid_cost', '投放花费', '0', 'digit', 'sum(bid_cost)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('210', 'profit', '毛利', '0', 'digit', 'sum(bid_cost)-sum(rtb_cost)', 'bid_cost,rtb_cost', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('211', 'order_num_rd', '订单量(RD)', '0', 'int', 'sum(order_num_rd)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('212', 'order_num_nrd', '订单量(非RD)', '0', 'int', 'sum(order_num_nrd)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('213', 'order_num', '订单量', '0', 'int', 'sum(order_num)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('214', 'order_price_rd', '订单金额(RD)', '0', 'digit', 'sum(order_price_rd)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('215', 'order_price_nrd', '订单金额(非RD)', '0', 'digit', 'sum(order_price_nrd)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('216', 'order_price', '订单金额', '0', 'digit', 'sum(order_price)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('217', 'rtb_join_rate', '竞价参与率(%)', '0', 'digit', '100*sum(bid)/sum(req_num)', 'bid,req_num', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('218', 'bid_roi', '投放ROI', '0', 'digit', 'sum(order_price)/sum(bid_cost)', 'order_price,bid_cost', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('219', 'rtb_roi', '竞价ROI', '0', 'digit', 'sum(order_price)/sum(rtb_cost)', 'order_price,rtb_cost', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('220', 'rtb_suc_rate', '竞价成功率(%)', '0', 'digit', '100*sum(imp)/sum(bid)', 'imp,bid', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('221', 'bid_cost_rtb', '投放花费(RTB)', '0', 'digit', 'sum(bid_cost_rtb)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('222', 'bid_cost_nrtb', '投放花费(非RTB)', '0', 'digit', 'sum(bid_cost_nrtb)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('223', 'order_num_rtb', '订单量(RTB)', '0', 'int', 'sum(order_num_rtb)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('224', 'order_num_nrtb', '订单量(非RTB)', '0', 'int', 'sum(order_num_nrtb)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('225', 'order_price_rtb', '订单金额(RTB)', '0', 'digit', 'sum(order_price_rtb)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('226', 'order_price_nrtb', '订单金额(非RTB)', '0', 'digit', 'sum(order_price_nrtb)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('227', 'bid_roi_rtb', 'ROI(RTB)', '0', 'digit', 'sum(order_price_rtb)/sum(bid_cost_rtb)', 'order_price_rtb,bid_cost_rtb', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('228', 'bid_roi_nrtb', 'ROI(非RTB)', '0', 'digit', 'sum(order_price_nrtb)/sum(bid_cost_nrtb)', 'order_price_nrtb,bid_cost_nrtb', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('229', 'profit_rtb', 'RTB毛利', '0', 'digit', 'sum(bid_cost_rtb)-sum(rtb_cost)', 'bid_cost_rtb,rtb_cost', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('230', 'project_name', '项目名称', '0', 'string', 'PROJECT2NAME(project_id)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('231', 'rtb_price', '竞价出价', '0', 'digit', 'sum(rtb_price)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('232', 'imp_rate', '展现率(%)', '0', 'digit', '100*sum(imp)/sum(bid)', 'imp,bid', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('233', 'rtb_over_rate', '竞价溢出率(%)', '0', 'digit', '100*(sum(rtb_price)-sum(rtb_cost))/sum(rtb_cost)', 'rtb_price,rtb_cost', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('234', 'rtb_cpm', '竞价CPM', '0', 'digit', '1000*sum(rtb_cost)/sum(imp)', 'rtb_cost,imp', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('235', 'rtb_cpc', '竞价CPC', '0', 'digit', 'sum(rtb_cost)/sum(clk)', 'rtb_cost,clk', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('236', 'clk_rate', '点击率(%)', '0', 'digit', '100*sum(clk)/sum(imp)', 'clk,imp', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('237', 'bid_cpm', '投放CPM', '0', 'digit', '1000*sum(bid_cost)/sum(imp)', 'bid_cost,imp', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('238', 'bid_cpc', '投放CPC', '0', 'digit', 'sum(bid_cost)/sum(clk)', 'bid_cost,clk', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('239', 'profit_rate', '毛利率(%)', '0', 'digit', '100*(sum(bid_cost)-sum(rtb_cost))/sum(rtb_cost)', 'bid_cost,rtb_cost', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('240', 'arrival_num', '到达量', '0', 'int', 'sum(arrival_num)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('241', 'arrival_rate', '到达率(%)', '0', 'digit', '100*sum(arrival_num)/sum(clk)', 'arrival_num,clk', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('242', 'pv', 'PV量', '0', 'int', 'sum(pv)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('243', 'avg_view', '平均浏览量', '0', 'digit', 'sum(pv)/sum(arrival_num)', 'pv,arrival_num', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('244', 'avg_stop_time', '平均停留时间(s)', '0', 'digit', 'sum(stop_time)/sum(arrival_num)', 'stop_time,arrival_num', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('245', 'jump_num', '跳出数', '0', 'int', 'sum(jump_num)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('246', 'twojump_num', '二跳数', '0', 'int', 'sum(arrival_num)-sum(jump_num)', 'arrival_num,jump_num', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('247', 'twojump_rate', '二跳率(%)', '0', 'digit', '100*(sum(arrival_num)-sum(jump_num))/sum(arrival_num)', 'arrival_num,jump_num', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('248', 'reg_num', '注册量', '0', 'int', 'sum(reg_num)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('249', 'reg_rate', '注册转化率', '0', 'digit', 'sum(reg_num)/sum(arrival_num)', 'reg_num,arrival_num', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('250', 'reg_cost', '注册成本', '0', 'digit', 'sum(bid_cost)/sum(reg_num)', 'bid_cost,reg_num', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('251', 'shopcart_num', '加入购物车次数', '0', 'int', 'sum(shop_cart)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('252', 'shopcart_price', '加入购物车金额', '0', 'digit', 'sum(shop_cart_price)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('253', 'order_rate', '订单转化率', '0', 'digit', 'sum(order_num)/sum(arrival_num)', 'order_num,arrival_num', null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('254', 'activate_num', '激活量', '0', 'int', 'sum(activate_num)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('300', 'req_count', '请求量', '0', 'int', 'sum(req_count)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('501', 'day_id', '日期', '0', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('502', 'camp_id', '活动ID', '0', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('503', 'begin_step', '过滤规则ID', '0', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('504', 'begin_name', '过滤规则名称', '0', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('505', 'begin_count', '过滤前请求数', '0', 'int', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('506', 'next_step', '下一步ID', '0', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('507', 'next_name', '下一步名称', '0', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('508', 'end_count', '过滤后请求数', '0', 'int', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('509', 'device_type', '设备类型', '0', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('510', 'view_type', '展示类型', '0', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('511', 'pass_rate', '通过率(%)', '0', 'digit', 'ROUND(100*end_count/begin_count,2)', null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('512', 'project_id', '项目ID', '0', 'sig', null, null, null, null, null);
INSERT INTO `xv_metadata_column` VALUES ('513', 'channel_id', '渠道ID', '0', 'sig', null, null, null, null, null);

-- ----------------------------
-- Table structure for xv_template
-- ----------------------------
DROP TABLE IF EXISTS `xv_template`;
CREATE TABLE `xv_template` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `title` varchar(32) CHARACTER SET gbk NOT NULL,
  `board` varchar(16) NOT NULL,
  `ord_idx` varchar(16) DEFAULT NULL,
  `rel_idx` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_template
-- ----------------------------
INSERT INTO `xv_template` VALUES ('1', '我的测试', '6', 'clk', 'bid');
INSERT INTO `xv_template` VALUES ('2', '我的测试', '6', 'clk', 'bid');
INSERT INTO `xv_template` VALUES ('3', '我的测试', '6', 'clk', 'bid');

-- ----------------------------
-- Table structure for xv_template_filter
-- ----------------------------
DROP TABLE IF EXISTS `xv_template_filter`;
CREATE TABLE `xv_template_filter` (
  `tid` int(8) NOT NULL,
  `field` varchar(32) NOT NULL,
  `fieldName` varchar(32) NOT NULL,
  `type` varchar(32) NOT NULL,
  `typeName` varchar(32) NOT NULL,
  `value` varchar(32) NOT NULL,
  `valueName` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_template_filter
-- ----------------------------

-- ----------------------------
-- Table structure for xv_theme
-- ----------------------------
DROP TABLE IF EXISTS `xv_theme`;
CREATE TABLE `xv_theme` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT 'table name',
  `date_key` varchar(32) NOT NULL DEFAULT 'rep_date',
  `table_strategy` int(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=503 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_theme
-- ----------------------------
INSERT INTO `xv_theme` VALUES ('1', 'assets_cm', 'rep_date', '1');
INSERT INTO `xv_theme` VALUES ('2', 'assets_bid', 'rep_date', '1');
INSERT INTO `xv_theme` VALUES ('3', 'assets_user', 'rep_date', '1');
INSERT INTO `xv_theme` VALUES ('4', 'assets_other', 'rep_date', '1');
INSERT INTO `xv_theme` VALUES ('5', 'platform_full', 'rep_date', '1');
INSERT INTO `xv_theme` VALUES ('6', 'bid', 'rep_date', '1');
INSERT INTO `xv_theme` VALUES ('7', 'bid', 'rep_date', '1');
INSERT INTO `xv_theme` VALUES ('10', 'media_adslot', 'rep_date', '1');
INSERT INTO `xv_theme` VALUES ('501', 'bid_match_request', 'day_id', '2');
INSERT INTO `xv_theme` VALUES ('502', 'bid_match_camp', 'day_id', '2');

-- ----------------------------
-- Table structure for xv_theme_column
-- ----------------------------
DROP TABLE IF EXISTS `xv_theme_column`;
CREATE TABLE `xv_theme_column` (
  `theme_id` int(4) NOT NULL,
  `col_id` int(4) NOT NULL,
  `flag` int(2) NOT NULL DEFAULT '1',
  `sorting` int(2) DEFAULT NULL,
  `code` int(2) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_theme_column
-- ----------------------------
INSERT INTO `xv_theme_column` VALUES ('1', '102', '0', '0', '101');
INSERT INTO `xv_theme_column` VALUES ('2', '101', '0', '0', '101');
INSERT INTO `xv_theme_column` VALUES ('5', '106', '0', '0', '101');
INSERT INTO `xv_theme_column` VALUES ('7', '111', '0', '1', '130');
INSERT INTO `xv_theme_column` VALUES ('7', '112', '0', '1', '160');
INSERT INTO `xv_theme_column` VALUES ('7', '103', '0', '1', '100');
INSERT INTO `xv_theme_column` VALUES ('7', '119', '0', '1', '150');
INSERT INTO `xv_theme_column` VALUES ('7', '101', '0', '1', '110');
INSERT INTO `xv_theme_column` VALUES ('1', '201', '1', '1', '101');
INSERT INTO `xv_theme_column` VALUES ('1', '202', '1', '1', '102');
INSERT INTO `xv_theme_column` VALUES ('1', '203', '1', '1', '103');
INSERT INTO `xv_theme_column` VALUES ('2', '204', '1', '1', '101');
INSERT INTO `xv_theme_column` VALUES ('2', '205', '0', '1', '102');
INSERT INTO `xv_theme_column` VALUES ('2', '206', '0', '1', '103');
INSERT INTO `xv_theme_column` VALUES ('2', '207', '0', '1', '104');
INSERT INTO `xv_theme_column` VALUES ('2', '208', '0', '1', '105');
INSERT INTO `xv_theme_column` VALUES ('2', '209', '0', '1', '106');
INSERT INTO `xv_theme_column` VALUES ('2', '217', '1', '1', '107');
INSERT INTO `xv_theme_column` VALUES ('2', '220', '1', '1', '108');
INSERT INTO `xv_theme_column` VALUES ('2', '211', '1', '1', '109');
INSERT INTO `xv_theme_column` VALUES ('2', '212', '1', '1', '110');
INSERT INTO `xv_theme_column` VALUES ('2', '216', '1', '1', '111');
INSERT INTO `xv_theme_column` VALUES ('2', '210', '1', '1', '112');
INSERT INTO `xv_theme_column` VALUES ('2', '218', '0', '1', '113');
INSERT INTO `xv_theme_column` VALUES ('2', '219', '1', '1', '114');
INSERT INTO `xv_theme_column` VALUES ('5', '208', '1', '1', '102');
INSERT INTO `xv_theme_column` VALUES ('5', '209', '1', '1', '103');
INSERT INTO `xv_theme_column` VALUES ('5', '221', '1', '1', '104');
INSERT INTO `xv_theme_column` VALUES ('5', '222', '1', '1', '105');
INSERT INTO `xv_theme_column` VALUES ('5', '213', '1', '1', '106');
INSERT INTO `xv_theme_column` VALUES ('5', '223', '1', '1', '107');
INSERT INTO `xv_theme_column` VALUES ('5', '224', '1', '1', '108');
INSERT INTO `xv_theme_column` VALUES ('5', '216', '1', '1', '109');
INSERT INTO `xv_theme_column` VALUES ('5', '225', '1', '1', '110');
INSERT INTO `xv_theme_column` VALUES ('5', '226', '1', '1', '111');
INSERT INTO `xv_theme_column` VALUES ('5', '218', '1', '1', '112');
INSERT INTO `xv_theme_column` VALUES ('5', '227', '1', '1', '113');
INSERT INTO `xv_theme_column` VALUES ('5', '228', '1', '1', '114');
INSERT INTO `xv_theme_column` VALUES ('5', '229', '1', '1', '115');
INSERT INTO `xv_theme_column` VALUES ('7', '205', '1', '1', '110');
INSERT INTO `xv_theme_column` VALUES ('7', '231', '0', '1', '120');
INSERT INTO `xv_theme_column` VALUES ('7', '206', '1', '1', '130');
INSERT INTO `xv_theme_column` VALUES ('7', '232', '1', '0', '140');
INSERT INTO `xv_theme_column` VALUES ('7', '208', '1', '1', '150');
INSERT INTO `xv_theme_column` VALUES ('7', '234', '0', '0', '180');
INSERT INTO `xv_theme_column` VALUES ('7', '235', '0', '0', '200');
INSERT INTO `xv_theme_column` VALUES ('7', '236', '1', '0', '210');
INSERT INTO `xv_theme_column` VALUES ('7', '209', '1', '1', '220');
INSERT INTO `xv_theme_column` VALUES ('7', '237', '1', '0', '230');
INSERT INTO `xv_theme_column` VALUES ('7', '238', '1', '0', '240');
INSERT INTO `xv_theme_column` VALUES ('7', '210', '1', '0', '250');
INSERT INTO `xv_theme_column` VALUES ('7', '239', '0', '0', '260');
INSERT INTO `xv_theme_column` VALUES ('7', '240', '0', '1', '270');
INSERT INTO `xv_theme_column` VALUES ('7', '241', '0', '0', '280');
INSERT INTO `xv_theme_column` VALUES ('7', '242', '0', '1', '290');
INSERT INTO `xv_theme_column` VALUES ('7', '243', '0', '0', '300');
INSERT INTO `xv_theme_column` VALUES ('7', '244', '0', '0', '310');
INSERT INTO `xv_theme_column` VALUES ('7', '245', '0', '1', '320');
INSERT INTO `xv_theme_column` VALUES ('7', '246', '0', '0', '330');
INSERT INTO `xv_theme_column` VALUES ('7', '248', '0', '1', '340');
INSERT INTO `xv_theme_column` VALUES ('7', '249', '0', '0', '350');
INSERT INTO `xv_theme_column` VALUES ('7', '250', '0', '0', '360');
INSERT INTO `xv_theme_column` VALUES ('7', '251', '0', '1', '370');
INSERT INTO `xv_theme_column` VALUES ('7', '252', '0', '1', '380');
INSERT INTO `xv_theme_column` VALUES ('7', '213', '0', '1', '400');
INSERT INTO `xv_theme_column` VALUES ('7', '253', '0', '0', '410');
INSERT INTO `xv_theme_column` VALUES ('7', '216', '0', '1', '420');
INSERT INTO `xv_theme_column` VALUES ('7', '219', '0', '0', '430');
INSERT INTO `xv_theme_column` VALUES ('7', '218', '0', '0', '440');
INSERT INTO `xv_theme_column` VALUES ('7', '217', '0', '0', '190');
INSERT INTO `xv_theme_column` VALUES ('7', '233', '0', '0', '170');
INSERT INTO `xv_theme_column` VALUES ('7', '254', '0', '1', '390');
INSERT INTO `xv_theme_column` VALUES ('7', '204', '0', '1', '100');
INSERT INTO `xv_theme_column` VALUES ('7', '207', '1', '1', '149');
INSERT INTO `xv_theme_column` VALUES ('7', '247', '0', '0', '335');
INSERT INTO `xv_theme_column` VALUES ('7', '113', '0', '1', '170');
INSERT INTO `xv_theme_column` VALUES ('7', '106', '2', '0', '180');
INSERT INTO `xv_theme_column` VALUES ('7', '108', '2', '0', '190');
INSERT INTO `xv_theme_column` VALUES ('6', '101', '0', '1', '110');
INSERT INTO `xv_theme_column` VALUES ('6', '103', '0', '0', '100');
INSERT INTO `xv_theme_column` VALUES ('6', '104', '0', '0', '120');
INSERT INTO `xv_theme_column` VALUES ('6', '106', '0', '1', '140');
INSERT INTO `xv_theme_column` VALUES ('6', '107', '0', '1', '130');
INSERT INTO `xv_theme_column` VALUES ('6', '108', '0', '1', '160');
INSERT INTO `xv_theme_column` VALUES ('6', '111', '0', '1', '180');
INSERT INTO `xv_theme_column` VALUES ('6', '112', '0', '1', '200');
INSERT INTO `xv_theme_column` VALUES ('6', '113', '0', '1', '190');
INSERT INTO `xv_theme_column` VALUES ('6', '205', '1', '1', '110');
INSERT INTO `xv_theme_column` VALUES ('6', '206', '1', '1', '130');
INSERT INTO `xv_theme_column` VALUES ('6', '207', '1', '1', '150');
INSERT INTO `xv_theme_column` VALUES ('6', '208', '1', '1', '160');
INSERT INTO `xv_theme_column` VALUES ('6', '209', '1', '1', '220');
INSERT INTO `xv_theme_column` VALUES ('6', '210', '1', '0', '250');
INSERT INTO `xv_theme_column` VALUES ('6', '213', '0', '1', '390');
INSERT INTO `xv_theme_column` VALUES ('6', '216', '0', '1', '410');
INSERT INTO `xv_theme_column` VALUES ('6', '218', '0', '0', '430');
INSERT INTO `xv_theme_column` VALUES ('6', '219', '0', '0', '420');
INSERT INTO `xv_theme_column` VALUES ('6', '231', '0', '1', '120');
INSERT INTO `xv_theme_column` VALUES ('6', '232', '1', '0', '140');
INSERT INTO `xv_theme_column` VALUES ('6', '233', '0', '0', '170');
INSERT INTO `xv_theme_column` VALUES ('6', '234', '0', '0', '180');
INSERT INTO `xv_theme_column` VALUES ('6', '235', '0', '0', '190');
INSERT INTO `xv_theme_column` VALUES ('6', '236', '1', '0', '210');
INSERT INTO `xv_theme_column` VALUES ('6', '237', '1', '0', '230');
INSERT INTO `xv_theme_column` VALUES ('6', '238', '1', '0', '240');
INSERT INTO `xv_theme_column` VALUES ('6', '239', '0', '0', '260');
INSERT INTO `xv_theme_column` VALUES ('6', '240', '0', '1', '270');
INSERT INTO `xv_theme_column` VALUES ('6', '241', '0', '0', '280');
INSERT INTO `xv_theme_column` VALUES ('6', '242', '0', '1', '290');
INSERT INTO `xv_theme_column` VALUES ('6', '243', '0', '0', '300');
INSERT INTO `xv_theme_column` VALUES ('6', '244', '0', '0', '310');
INSERT INTO `xv_theme_column` VALUES ('6', '245', '0', '1', '320');
INSERT INTO `xv_theme_column` VALUES ('6', '246', '0', '0', '330');
INSERT INTO `xv_theme_column` VALUES ('6', '247', '0', '0', '335');
INSERT INTO `xv_theme_column` VALUES ('6', '248', '0', '1', '340');
INSERT INTO `xv_theme_column` VALUES ('6', '249', '0', '0', '350');
INSERT INTO `xv_theme_column` VALUES ('6', '250', '0', '0', '360');
INSERT INTO `xv_theme_column` VALUES ('6', '251', '0', '1', '370');
INSERT INTO `xv_theme_column` VALUES ('6', '252', '0', '1', '380');
INSERT INTO `xv_theme_column` VALUES ('6', '253', '0', '0', '400');
INSERT INTO `xv_theme_column` VALUES ('6', '254', '0', '1', '385');
INSERT INTO `xv_theme_column` VALUES ('7', '117', '0', '1', '140');
INSERT INTO `xv_theme_column` VALUES ('502', '502', '1', '0', '20');
INSERT INTO `xv_theme_column` VALUES ('502', '503', '1', '0', '40');
INSERT INTO `xv_theme_column` VALUES ('502', '504', '1', '0', '50');
INSERT INTO `xv_theme_column` VALUES ('502', '505', '1', '0', '60');
INSERT INTO `xv_theme_column` VALUES ('502', '508', '1', '0', '90');
INSERT INTO `xv_theme_column` VALUES ('502', '511', '1', '0', '100');
INSERT INTO `xv_theme_column` VALUES ('501', '513', '1', '0', '10');
INSERT INTO `xv_theme_column` VALUES ('501', '509', '1', '0', '20');
INSERT INTO `xv_theme_column` VALUES ('501', '510', '1', '0', '30');
INSERT INTO `xv_theme_column` VALUES ('501', '503', '1', '0', '40');
INSERT INTO `xv_theme_column` VALUES ('501', '504', '1', '0', '50');
INSERT INTO `xv_theme_column` VALUES ('501', '505', '1', '0', '60');
INSERT INTO `xv_theme_column` VALUES ('501', '508', '1', '0', '90');
INSERT INTO `xv_theme_column` VALUES ('501', '511', '1', '0', '100');
INSERT INTO `xv_theme_column` VALUES ('502', '512', '1', '0', '10');
INSERT INTO `xv_theme_column` VALUES ('502', '513', '1', '0', '15');
INSERT INTO `xv_theme_column` VALUES ('7', '107', '2', '0', '200');
INSERT INTO `xv_theme_column` VALUES ('6', '121', '0', '1', '170');
INSERT INTO `xv_theme_column` VALUES ('7', '121', '2', '0', '210');

-- ----------------------------
-- Table structure for xv_theme_drill
-- ----------------------------
DROP TABLE IF EXISTS `xv_theme_drill`;
CREATE TABLE `xv_theme_drill` (
  `theme_id` int(4) NOT NULL,
  `drill_id` int(4) NOT NULL,
  `drill_name` varchar(32) NOT NULL,
  `drill_label` varchar(32) NOT NULL,
  `drill_title` varchar(32) NOT NULL,
  `code` int(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_theme_drill
-- ----------------------------
INSERT INTO `xv_theme_drill` VALUES ('6', '6', 'rep_date', '日期', '日统计', '10');
INSERT INTO `xv_theme_drill` VALUES ('6', '7', 'hour_id', '时段', '时段统计', '20');
INSERT INTO `xv_theme_drill` VALUES ('6', '9', 'project_id', '项目', '项目统计', '40');
INSERT INTO `xv_theme_drill` VALUES ('6', '10', 'campaign_id', '活动', '活动统计', '50');
INSERT INTO `xv_theme_drill` VALUES ('6', '14', 'channel_id', '渠道', '渠道分析', '60');
INSERT INTO `xv_theme_drill` VALUES ('6', '8', 'category_id', '行业', '行业统计', '30');
INSERT INTO `xv_theme_drill` VALUES ('6', '12', 'trade_type', '交易方式', '交易方式', '80');
INSERT INTO `xv_theme_drill` VALUES ('6', '13', 'flow_type', '流量类型', '流量类型', '90');
INSERT INTO `xv_theme_drill` VALUES ('6', '17', 'adslot_id', '广告位', '广告位分析', '110');
INSERT INTO `xv_theme_drill` VALUES ('6', '16', 'domain', '域名', '域名(PC/APP)', '100');
INSERT INTO `xv_theme_drill` VALUES ('6', '31', 'creative_id', '创意', '创意统计', '70');
INSERT INTO `xv_theme_drill` VALUES ('7', '6', 'rep_date', '日期', '日统计', '10');
INSERT INTO `xv_theme_drill` VALUES ('7', '7', 'hour_id', '时段', '时段统计', '20');
INSERT INTO `xv_theme_drill` VALUES ('7', '8', 'category_id', '行业', '行业统计', '30');
INSERT INTO `xv_theme_drill` VALUES ('7', '9', 'project_id', '项目', '项目统计', '40');
INSERT INTO `xv_theme_drill` VALUES ('7', '10', 'campaign_id', '活动', '活动统计', '50');
INSERT INTO `xv_theme_drill` VALUES ('7', '12', 'trade_type', '交易方式', '交易方式', '80');
INSERT INTO `xv_theme_drill` VALUES ('7', '13', 'flow_type', '流量类型', '流量类型', '90');
INSERT INTO `xv_theme_drill` VALUES ('7', '14', 'channel_id', '渠道', '渠道分析', '60');
INSERT INTO `xv_theme_drill` VALUES ('7', '16', 'domain', '域名', '域名(PC/APP)', '100');
INSERT INTO `xv_theme_drill` VALUES ('7', '17', 'adslot_id', '广告位', '广告位分析', '110');
INSERT INTO `xv_theme_drill` VALUES ('7', '31', 'creative_id', '创意', '创意统计', '70');

-- ----------------------------
-- Table structure for xv_user_index
-- ----------------------------
DROP TABLE IF EXISTS `xv_user_index`;
CREATE TABLE `xv_user_index` (
  `uid` int(8) NOT NULL,
  `board` varchar(16) NOT NULL,
  `idxes` varchar(512) NOT NULL,
  PRIMARY KEY (`uid`,`board`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_user_index
-- ----------------------------
INSERT INTO `xv_user_index` VALUES ('1850', '10', 'bid;imp;imp_rate;clk;rtb_cost;clk_rate;bid_cost;bid_cpm;bid_cpc;profit;arrival_num;twojump_num');
INSERT INTO `xv_user_index` VALUES ('100077', '9', 'bid;rtb_price;imp;imp_rate;clk;rtb_cost;rtb_over_rate;rtb_cpm;rtb_cpc;clk_rate;bid_cost;bid_cpm;bid_cpc;profit;profit_rate;arrival_num;arrival_rate;pv;avg_view;avg_stop_time;jump_num;twojump_num;twojump_rate;reg_num;reg_rate;reg_cost;shopcart_num;shopcart_price;activate_num;order_num;order_rate;order_price;rtb_roi;bid_roi');
INSERT INTO `xv_user_index` VALUES ('100911', '6', 'bid;imp;imp_rate;clk;rtb_cost;clk_rate;bid_cost;bid_cpc;profit');
INSERT INTO `xv_user_index` VALUES ('100916', '10', 'bid;imp;imp_rate;clk;rtb_cost');
INSERT INTO `xv_user_index` VALUES ('100916', '14', 'req_num;bid;rtb_price;imp;clk;rtb_cost;bid_cost');
INSERT INTO `xv_user_index` VALUES ('100916', '6', 'bid;imp;clk;rtb_cost;bid_cost;profit');
INSERT INTO `xv_user_index` VALUES ('100916', '9', 'bid;rtb_price;imp;clk;rtb_cost;rtb_cpm;bid_cost;profit;order_num');
INSERT INTO `xv_user_index` VALUES ('101185', '10', 'bid;imp;imp_rate;clk;rtb_cost;clk_rate;bid_cost;bid_cpm;bid_cpc;profit');
INSERT INTO `xv_user_index` VALUES ('101392', '31', 'bid;rtb_price;imp;imp_rate;clk;rtb_cost;rtb_over_rate;rtb_cpm;rtb_cpc;clk_rate;bid_cost;bid_cpm;bid_cpc;profit;profit_rate;arrival_num;arrival_rate;pv;avg_view;avg_stop_time;jump_num;twojump_num;twojump_rate;reg_num;reg_rate;reg_cost;shopcart_num;shopcart_price;activate_num;order_num;order_rate;order_price;rtb_roi;bid_roi');
INSERT INTO `xv_user_index` VALUES ('101392', '9', 'bid;imp;clk;clk_rate;reg_num;bid_roi');
INSERT INTO `xv_user_index` VALUES ('101394', '10', 'imp_rate;clk_rate');
INSERT INTO `xv_user_index` VALUES ('101394', '9', 'bid;imp;imp_rate');
INSERT INTO `xv_user_index` VALUES ('101423', '10', 'bid;imp;imp_rate;clk;rtb_cost;rtb_cpc;clk_rate;bid_cost;profit;profit_rate');
INSERT INTO `xv_user_index` VALUES ('101423', '31', 'bid;rtb_price;imp;imp_rate;clk;rtb_cost;rtb_over_rate;rtb_cpm;rtb_cpc;clk_rate;bid_cost;bid_cpm;bid_cpc;profit;profit_rate;arrival_num;arrival_rate;pv;avg_view;avg_stop_time;jump_num;twojump_num;twojump_rate;reg_num;reg_rate;reg_cost;shopcart_num;shopcart_price;activate_num;order_num;order_rate;order_price;rtb_roi;bid_roi');
INSERT INTO `xv_user_index` VALUES ('101423', '6', 'bid;rtb_price;imp;clk;bid_cost');
INSERT INTO `xv_user_index` VALUES ('101423', '8', 'bid;imp;imp_rate');
INSERT INTO `xv_user_index` VALUES ('101423', '9', 'bid;rtb_price;imp;imp_rate;clk;rtb_cost;rtb_over_rate;rtb_cpm;rtb_cpc;clk_rate;bid_cost;bid_cpm;bid_cpc;profit;profit_rate;arrival_num;arrival_rate;pv;avg_view;avg_stop_time;jump_num;twojump_num;twojump_rate;reg_num;reg_rate;reg_cost;shopcart_num;shopcart_price;activate_num;order_num;order_rate;order_price;rtb_roi;bid_roi');

-- ----------------------------
-- Table structure for xv_user_template
-- ----------------------------
DROP TABLE IF EXISTS `xv_user_template`;
CREATE TABLE `xv_user_template` (
  `uid` int(8) NOT NULL,
  `tid` int(8) NOT NULL,
  `code` int(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xv_user_template
-- ----------------------------
INSERT INTO `xv_user_template` VALUES ('1', '1', '1');
INSERT INTO `xv_user_template` VALUES ('1', '2', '2');
INSERT INTO `xv_user_template` VALUES ('1', '3', '3');
INSERT INTO `xv_user_template` VALUES ('2', '4', '1');
