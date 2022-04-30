-- ----------------------------
-- Table structure for oauth_client_details 将请求的路径存在数据表
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
`client_id` varchar(48) NOT NULL COMMENT '主键,clientID和clientSecret搭配(必须)',
`resource_ids` varchar(256) DEFAULT NULL COMMENT '权限控制,控制访问的服务器ID',
`client_secret` varchar(256) DEFAULT NULL COMMENT 'clientID和clientSecret搭配(必须)',
`scope` varchar(256) DEFAULT NULL COMMENT '权限控制2,主要增删读写类',
`authorized_grant_types` varchar(256) DEFAULT NULL COMMENT 'client类别password/client/简化/code/refreshtoken',
`web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT 'url验证,只有简化和code才会用到,即redirect_uri和这个不匹配就会报错',
`authorities` varchar(256) DEFAULT NULL COMMENT '权限控制3,主要是角色控制,必须ROLE_开头',
`access_token_validity` int(11) DEFAULT NULL COMMENT 'token时效',
`refresh_token_validity` int(11) DEFAULT NULL COMMENT 'refreshtoken时效',
`additional_information` varchar(4096) DEFAULT NULL COMMENT '预留字段',
`autoapprove` varchar(256) DEFAULT NULL COMMENT '用户是否自动Approval操作,只有code模式才用到,自动跳过用户approval页面',
PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='oauth2_client表';


-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('authorization_code', 'user', 'authorization_code', 'read', 'authorization_code,refresh_token', NULL, NULL, 2592000, 2592000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('client_credentials', 'user', 'client_credentials', 'read', 'client_credentials', NULL, 'ROLE_USER', 2592000, 2592000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('implicit', 'user', 'implicit', 'read', 'implicit,refresh_token', NULL, NULL, 2592000, 2592000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('password_common', 'common', 'password_common', 'read', 'password,refresh_token', NULL, NULL, 2592000, 2592000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('password_file', 'file', 'password_file', 'read', 'password,refresh_token', NULL, NULL, 2592000, 2592000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('password_pay', 'pay', 'password_pay', 'read', 'password,refresh_token', NULL, NULL, 2592000, 2592000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('password_spider', 'spider', 'password_spider', 'read', 'password,refresh_token', NULL, NULL, 2592000, 2592000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('password_user', 'user', 'password_user', 'read', 'password,refresh_token', NULL, NULL, 2592000, 2592000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('password_wx', 'wx', 'password_wx', 'read', 'password,refresh_token', NULL, NULL, 2592000, 2592000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('qizuo', NULL, 'qizuo', 'all', 'client_credentials,password,authorization_code,implicit,refresh_token', NULL, NULL, 2592000, 2592000, NULL, 'true');