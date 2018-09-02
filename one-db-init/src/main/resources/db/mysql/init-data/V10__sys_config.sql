INSERT INTO `sys_config_type`(`code`, `name`) VALUES ('000', '系统相关配置');
INSERT INTO `sys_config`(`config_id`, `type_code`, `config_key`, `config_value`, `remark`, `config_name`) VALUES ('00001', '000', 'system_cookie_expire_time', '30', 'Cookie失效时间，以分为单位', '系统 - Cookie失效时间');
INSERT INTO `sys_config`(`config_id`, `type_code`, `config_key`, `config_value`, `remark`, `config_name`) VALUES ('00002', '000', 'system_registry_code_expire_time', '30', '注册验证码失效时间，以分为单位', '系统 - 注册验证码失效时间');
INSERT INTO `sys_config`(`config_id`, `type_code`, `config_key`, `config_value`, `remark`, `config_name`) VALUES ('00003', '000', 'system_max_login_error_count', '3', '登录失败次数限制', '系统 - 登录失败次数限制');
INSERT INTO `sys_config`(`config_id`, `type_code`, `config_key`, `config_value`, `remark`, `config_name`) VALUES ('00004', '000', 'system_default_password', 'q111111', '系统用户的默认密码', '系统 - 系统用户的默认密码');
