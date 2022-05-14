INSERT INTO `user` (id, username, nickname, password, created_time, updated_time)
VALUES ('1', 'admin', 'GnaixEuy', '$2a$10$bfkPnyeQXamlx/h48Zt0Oe4t40MSJaH1EvqX7U/LmhV6NHIYgIM6G',
        '2022-5-14 21:16:12.260000',
        '2022-5-14 21:16:12.260000');
INSERT INTO `role` (id, name, title, created_time, updated_time)
VALUES ('1', 'ROLE_USER', '普通用户', '2022-5-14 21:16:12.260000', '2022-5-14 21:16:12.260000');
INSERT INTO `role` (id, name, title, created_time, updated_time)
VALUES ('2', 'ROLE_ADMIN', '超级管理员', '2022-5-14 21:16:12.260000', '2022-5-14 21:16:12.260000');
INSERT INTO `user_role` (user_id, role_id)
VALUES ('1', '1');
INSERT INTO `user_role` (user_id, role_id)
VALUES ('1', '2');