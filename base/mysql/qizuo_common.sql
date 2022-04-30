-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_common`;
CREATE TABLE `sys_common` (
`BASE_ID` varchar(225) NOT NULL COMMENT '编号',
`CONTENT` varchar(100) DEFAULT NULL COMMENT '内容',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='常用库';