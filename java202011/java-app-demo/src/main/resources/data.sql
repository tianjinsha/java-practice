-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`(`username`,`phone`,`email`,`password`,`is_expired`,`is_locked`,`is_enabled`,`create_time`)
VALUES ('user1','18780183730','18780183730@163.com','$2a$10$uN9jRysMRF3RMEtXiPQ5fuhuKSjrjxfYJb/1rBaXtnyzkgjEI4H8u','0','0','0','2017-06-05 18:30:30');
INSERT INTO `user`(`username`,`phone`,`email`,`password`,`is_expired`,`is_locked`,`is_enabled`,`create_time`)
VALUES ('user2','18780183731','18780183731@163.com','$2a$10$Rt9E.VoFHijHLHv2ieq79u/PWjMWzM0iRIxpQxABuLULUnCxx87Qy','0','0','0','2017-06-05 18:30:30');
INSERT INTO `user`(`username`,`phone`,`email`,`password`,`is_expired`,`is_locked`,`is_enabled`,`create_time`)
VALUES ('user3','18780183732','18780183732@163.com','$2a$10$vC8R86x4sHkQdpn0Grq/Aevx4Wl2hcNz8qLIA9Uw7mJo/a8mH.Xhe','0','0','0','2017-06-05 18:30:30');
-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`(`code`,`name`,`desc`) VALUES ('ROLE_ADMIN','超级管理员','拥有最大的权限');
INSERT INTO `role`(`code`,`name`,`desc`) VALUES ('ROLE_SYSTEM','系统管理员管理员','拥有系统设置的权限');
INSERT INTO `role`(`code`,`name`,`desc`) VALUES ('ROLE_H2','h2数据库管理员','拥有h2数据库的权限');

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role`(`user_id`,`role_code`) VALUES ('1','ROLE_ADMIN');
INSERT INTO `user_role`(`user_id`,`role_code`) VALUES ('1','ROLE_SYSTEM');
INSERT INTO `user_role`(`user_id`,`role_code`) VALUES ('1','ROLE_H2');
INSERT INTO `user_role`(`user_id`,`role_code`) VALUES ('2','ROLE_SYSTEM');
INSERT INTO `user_role`(`user_id`,`role_code`) VALUES ('3','ROLE_H2');









