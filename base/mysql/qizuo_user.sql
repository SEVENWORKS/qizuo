/*
Navicat MySQL Data Transfer

Source Server         : fanglu
Source Server Version : 50162
Source Host           : 127.0.0.1:3306
Source Database       : qizuo_user

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2020-06-13 21:10:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `BASE_ID` varchar(225) NOT NULL COMMENT '编号',
  `NAME` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `URL` varchar(200) DEFAULT NULL COMMENT '资源路径',
  `PARENT_ID` varchar(255) DEFAULT NULL COMMENT '父编号',
  `THEME` varchar(255) DEFAULT NULL COMMENT '主题',
  `BASE_CREATE_USER_ID` varchar(225) DEFAULT NULL COMMENT '创建者',
  `BASE_CREATE_TM` datetime NOT NULL COMMENT '创建时间',
  `BASE_UPDATE_USER_ID` varchar(225) DEFAULT NULL COMMENT '修改者',
  `BASE_UPDATE_TM` datetime DEFAULT NULL COMMENT '修改时间',
  `BASE_STATUS` varchar(225) NOT NULL DEFAULT '0' COMMENT '状态',
  `BASE_REMARKS` varchar(225) DEFAULT '' COMMENT '备注',
  `BASE_CREATE_IP` varchar(255) DEFAULT NULL COMMENT '创建ip',
  `BASE_UPDATE_IP` varchar(255) DEFAULT NULL COMMENT '更新ip',
  PRIMARY KEY (`BASE_ID`),
  KEY `idx_sys_resource_parent_id` (`PARENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='资源';

-- ----------------------------
-- Table structure for sys_msg
-- ----------------------------
DROP TABLE IF EXISTS `sys_msg`;
CREATE TABLE `sys_msg` (
  `BASE_ID` varchar(255) NOT NULL COMMENT '编号',
  `TITLE` varchar(200) DEFAULT NULL COMMENT '消息名称',
  `TYPE` varchar(100) DEFAULT NULL COMMENT '消息类型',
  `CONTENT` varchar(1000) DEFAULT NULL COMMENT '消息内容',
  `SEND_USER_ID` varchar(100) DEFAULT NULL COMMENT '接收人id(一般对应人员id)',
  `SEND_TYPE` varchar(100) DEFAULT NULL COMMENT '接收人TYPE(一般对应权限表)',
  `IS_SEND` bigint(1) DEFAULT '0' COMMENT '是否发送',
  `BASE_CREATE_USER_ID` varchar(255) DEFAULT NULL COMMENT '创建人',
  `BASE_CREATE_TM` datetime NOT NULL COMMENT '创建时间',
  `BASE_UPDATE_USER_ID` varchar(255) DEFAULT NULL COMMENT '修改人',
  `BASE_UPDATE_TM` datetime DEFAULT NULL COMMENT '修改时间',
  `BASE_STATUS` varchar(255) NOT NULL DEFAULT '0' COMMENT '状态',
  `BASE_REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `BASE_CREATE_IP` varchar(255) DEFAULT NULL COMMENT '创建ip',
  `BASE_UPDATE_IP` varchar(255) DEFAULT NULL COMMENT '更新ip',
  PRIMARY KEY (`BASE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `BASE_ID` varchar(255) NOT NULL COMMENT '权限编号',
  `NAME` varchar(100) DEFAULT NULL COMMENT '权限名称',
  `MENU_IDS` varchar(5000) DEFAULT NULL COMMENT '资源ID集合',
  `DATA_SCOPE_CDS` varchar(50) DEFAULT '' COMMENT '数据操作能力（增删查改）',
  `BASE_CREATE_USER_ID` varchar(255) DEFAULT NULL COMMENT '创建者',
  `BASE_CREATE_TM` datetime NOT NULL COMMENT '创建时间',
  `BASE_UPDATE_USER_ID` varchar(255) DEFAULT NULL COMMENT '修改者',
  `BASE_UPDATE_TM` datetime DEFAULT NULL COMMENT '修改时间',
  `BASE_STATUS` varchar(225) NOT NULL DEFAULT '0' COMMENT '状态',
  `BASE_REMARKS` varchar(255) DEFAULT '' COMMENT '备注',
  `BASE_CREATE_IP` varchar(255) DEFAULT NULL COMMENT '创建ip',
  `BASE_UPDATE_IP` varchar(255) DEFAULT NULL COMMENT '更新ip',
  PRIMARY KEY (`BASE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='权限';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `BASE_ID` varchar(255) NOT NULL COMMENT '主键',
  `USERNAME` varchar(100) DEFAULT NULL COMMENT '账号',
  `PASSWORD` varchar(100) DEFAULT NULL COMMENT '密码',
  `SALT` varchar(100) DEFAULT NULL COMMENT '加密盐',
  `ROLE_IDS` varchar(100) DEFAULT NULL COMMENT '权限编号集合',
  `NAME` varchar(100) DEFAULT NULL COMMENT '姓名',
  `SEX_CD` char(2) DEFAULT NULL COMMENT '性别',
  `IDCARD` varchar(100) DEFAULT NULL COMMENT '用户编号（身份证）',
  `PHONE` varchar(45) DEFAULT NULL COMMENT '电话号码',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `PHOTO` varchar(200) DEFAULT NULL COMMENT '头像',
  `ADDRESS` varchar(500) DEFAULT NULL COMMENT '地址',
  `LOGIN_DATE` datetime DEFAULT NULL COMMENT '登录时间',
  `OUTMUTUAL_KEY` varchar(255) DEFAULT NULL COMMENT '第三方key',
  `BASE_CREATE_USER_ID` varchar(255) DEFAULT NULL COMMENT '创建者',
  `BASE_CREATE_TM` datetime NOT NULL COMMENT '创建时间',
  `BASE_UPDATE_USER_ID` varchar(255) DEFAULT NULL COMMENT '修改者',
  `BASE_UPDATE_TM` datetime DEFAULT NULL COMMENT '修改时间',
  `BASE_STATUS` varchar(255) NOT NULL DEFAULT '0' COMMENT '状态',
  `BASE_REMARKS` varchar(255) DEFAULT NULL COMMENT '数据备注',
  `BASE_CREATE_IP` varchar(255) DEFAULT NULL,
  `BASE_UPDATE_IP` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BASE_ID`),
  UNIQUE KEY `idx_sys_user_username` (`USERNAME`),
  KEY `idx_sys_user_organization_id` (`ADDRESS`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';


-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_COMMON', NULL, '', '1', '2022-03-21 21:20:39', NULL, NULL, '0', '', NULL, NULL);
INSERT INTO `sys_role` VALUES ('2', 'ROLE_FILE', NULL, '', '1', '2022-03-21 21:22:47', NULL, NULL, '0', '', NULL, NULL);
INSERT INTO `sys_role` VALUES ('3', 'ROLE_PAY', NULL, '', '1', '2022-03-21 21:23:00', NULL, NULL, '0', '', NULL, NULL);
INSERT INTO `sys_role` VALUES ('4', 'ROLE_SPIDER', NULL, '', '1', '2022-03-21 21:23:15', NULL, NULL, '0', '', NULL, NULL);
INSERT INTO `sys_role` VALUES ('5', 'ROLE_USER', NULL, '', '1', '2022-03-21 21:23:26', NULL, NULL, '0', '', NULL, NULL);
INSERT INTO `sys_role` VALUES ('6', 'ROLE_WX', NULL, '', '1', '2022-03-21 21:23:39', NULL, NULL, '0', '', NULL, NULL);


-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'qizuo', 'qizuo', 'qizuo', '1,2,3,4,5,6', 'qizuo', '1', 'qizuo', 'qizuo', 'qizuo', 'qizuo', 'qizuo', '2022-03-21 21:26:21', 'qizuo', '1', '2022-03-21 21:26:32', NULL, NULL, '0', NULL, NULL, NULL);