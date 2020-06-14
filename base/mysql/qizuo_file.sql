/*
Navicat MySQL Data Transfer

Source Server         : fanglu
Source Server Version : 50162
Source Host           : 127.0.0.1:3306
Source Database       : qizuo_file

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2020-06-14 11:08:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_upload_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_upload_log`;
CREATE TABLE `sys_upload_log` (
  `BASE_ID` varchar(255) NOT NULL COMMENT '标识',
  `DATA_TYPE` varchar(100) NOT NULL,
  `DATA_ID` varchar(255) NOT NULL COMMENT '数据关联ID',
  `DATA_COLUMN` varchar(255) DEFAULT '' COMMENT '关联字段类别',
  `RESOURCE_NAME` varchar(500) NOT NULL COMMENT '上传路径名称',
  `NAME` varchar(500) NOT NULL COMMENT '文件名',
  `UPLOAD_RESULT` varchar(20) NOT NULL COMMENT '上传结果',
  `BASE_CREATE_USER_ID` varchar(255) DEFAULT NULL COMMENT '创建者',
  `BASE_CREATE_TM` datetime NOT NULL COMMENT '创建时间',
  `BASE_UPDATE_USER_ID` varchar(255) DEFAULT NULL COMMENT '更新者',
  `BASE_UPDATE_TM` datetime DEFAULT NULL COMMENT '更新时间',
  `BASE_STATUS` varchar(255) NOT NULL COMMENT '状态',
  `BASE_REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `BASE_CREATE_IP` varchar(255) DEFAULT NULL COMMENT '创建IP',
  `BASE_UPDATE_IP` varchar(255) DEFAULT NULL COMMENT '更新IP',
  PRIMARY KEY (`BASE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上传资源';

-- ----------------------------
-- Table structure for sys_upload_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_upload_resource`;
CREATE TABLE `sys_upload_resource` (
  `BASE_ID` varchar(255) NOT NULL COMMENT '标识',
  `DATA_TYPE` varchar(100) NOT NULL COMMENT '数据类别',
  `DATA_ID` varchar(255) NOT NULL COMMENT '数据关联ID',
  `DATA_COLUMN` varchar(255) DEFAULT '' COMMENT '关联字段类别',
  `RESOURCE_NAME` varchar(500) NOT NULL COMMENT '上传资源名称',
  `NAME` varchar(500) NOT NULL COMMENT '文件名称',
  `BASE_CREATE_USER_ID` varchar(255) DEFAULT NULL COMMENT '创建者',
  `BASE_CREATE_TM` datetime NOT NULL COMMENT '创建时间',
  `BASE_UPDATE_USER_ID` varchar(255) DEFAULT NULL COMMENT '更新者',
  `BASE_UPDATE_TM` datetime DEFAULT NULL COMMENT '更新时间',
  `BASE_STATUS` varchar(255) NOT NULL COMMENT '状态',
  `BASE_REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `BASE_CREATE_IP` varchar(255) DEFAULT NULL COMMENT '创建IP',
  `BASE_UPDATE_IP` varchar(255) DEFAULT NULL COMMENT '更新IP',
  PRIMARY KEY (`BASE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上传资源';
