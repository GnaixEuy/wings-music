SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`              varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '用户ID',
    `username`        varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
    `nickname`        varchar(64) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '用户昵称',
    `password`        varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '加密后的密码',
    `gender`          varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性别',
    `locked`          tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否锁定，1-是，0-否',
    `enabled`         tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用，1-是，0-否',
    `last_login_ip`   varchar(64) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '最后登录IP',
    `last_login_time` datetime(6) DEFAULT NULL COMMENT '最后登录IP',
    `created_time`    datetime(6) NOT NULL COMMENT '创建时间',
    `updated_time`    datetime(6) NOT NULL COMMENT '更新时间',
    `open_id`         varchar(32) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT 'open_id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';

SET
FOREIGN_KEY_CHECKS = 1;
