-- 菜单
CREATE TABLE sys_menu (
  menu_id bigserial,
  parent_id int8,
  name varchar(50),
  url varchar(200),
  perms varchar(500),
  type int,
  icon varchar(50),
  order_num int,
  PRIMARY KEY (menu_id)
);


-- 系统用户
CREATE TABLE sys_user (
  user_id bigserial,
  username varchar(50) NOT NULL,
  password varchar(100),
  salt varchar(20),
  email varchar(100),
  mobile varchar(100),
  status int,
  create_user_id int8,
  create_time timestamp,
  PRIMARY KEY (user_id),
  UNIQUE (username)
);


-- 系统用户Token
CREATE TABLE sys_user_token (
  user_id bigserial,
  token varchar(100) NOT NULL,
  expire_time timestamp,
  update_time timestamp,
  PRIMARY KEY (user_id),
  UNIQUE (token)
);

-- 系统验证码
CREATE TABLE sys_captcha (
  uuid varchar(36) NOT NULL,
  code varchar(6) NOT NULL,
  expire_time timestamp,
  PRIMARY KEY (uuid)
);

-- 角色
CREATE TABLE sys_role (
  role_id bigserial,
  role_name varchar(100),
  remark varchar(100),
  create_user_id int8,
  create_time timestamp,
  PRIMARY KEY (role_id)
);

-- 用户与角色对应关系
CREATE TABLE sys_user_role (
  id bigserial,
  user_id int8,
  role_id int8,
  PRIMARY KEY (id)
);

-- 角色与菜单对应关系
CREATE TABLE sys_role_menu (
  id bigserial,
  role_id int8,
  menu_id int8,
  PRIMARY KEY (id)
);



-- 系统日志
CREATE TABLE sys_log (
  id bigserial,
  username varchar(50),
  operation varchar(50),
  method varchar(200),
  params varchar(5000),
  time int8 NOT NULL,
  ip varchar(64),
  create_date timestamp,
  PRIMARY KEY (id)
);


INSERT INTO sys_user (user_id, username, password, salt, email, mobile, status, create_user_id, create_time) VALUES ('1', 'admin', 'cdac762d0ba79875489f6a8b430fa8b5dfe0cdd81da38b80f02f33328af7fd4a', 'YzcmCZNvbXocrsz9dm8e', 'root@fast.io', '13612345678', '1', '1', '2016-11-11 11:11:11');

INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (1, 0, '系统管理', NULL, NULL, 0, 'system', 0);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (2, 1, '管理员列表', 'sys/user', NULL, 1, 'admin', 1);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (5, 1, 'SQL监控', 'http://localhost:1030/vue-fast/druid/sql.html', NULL, 1, 'sql', 4);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0);
INSERT INTO sys_menu(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES (29, 1, '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7);






alter sequence sys_menu_menu_id_seq restart with 31;
alter sequence sys_user_user_id_seq restart with 2;

