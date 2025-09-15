


--用户表（User）
CREATE TABLE "User" (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL UNIQUE CHECK (email LIKE '%@%'),
    role_id INT NOT NULL DEFAULT 4,
    FOREIGN KEY (role_id) REFERENCES "Role"(role_id)
);


--权限表（Permission）
CREATE TABLE "Permission" (
    permission_id VARCHAR(20) PRIMARY KEY,
    permission_desc VARCHAR(500)
);

(1,'查看SensitiveData表data_id的权限'),
(2,'查看SensitiveData表的id_number权限'),


-- --角色权限关系表（Role_Permission）
-- CREATE TABLE "Role_Permission" (
--     role_id INT,
--     permission_id VARCHAR(20),
--     PRIMARY KEY (role_id, permission_id),
--     FOREIGN KEY (role_id) REFERENCES "Role"(role_id),
--     FOREIGN KEY (permission_id) REFERENCES "Permission"(permission_id)
-- );

--角色表（Role）
CREATE TABLE "Role" (
    role_id INT PRIMARY KEY CHECK (role_id BETWEEN 1 AND 4),
    role_name VARCHAR(20) NOT NULL UNIQUE,
    role_desc VARCHAR(200)
);

INSERT INTO "Role" (role_id, role_name, role_desc) VALUES
(1, '系统管理员', '~'),
(2, '数据安全管理人员', '~'),
(3, '日志审核人员', '~'),
(4, '数据使用人员', '~');

--权限表（Permission）
CREATE TABLE "Permission" (
    permission_id VARCHAR(20) PRIMARY KEY,
    permission_desc VARCHAR(500)
);


--角色权限关系表（Role_Permission）
CREATE TABLE "Role_Permission" (
    id SERIAL PRIMARY KEY,
    role_id INT,
    permission_id VARCHAR(20),
    FOREIGN KEY (role_id) REFERENCES "Role"(role_id),
    FOREIGN KEY (permission_id) REFERENCES "Permission"(permission_id)
);

--脱敏策略

--敏感数据表（SensitiveData）
CREATE TABLE "SensitiveData" (
    data_id SERIAL PRIMARY KEY,
    id_number VARCHAR(18),
    name VARCHAR(20),
    gender VARCHAR(10),
    phone_number VARCHAR(20),
    email VARCHAR(100) CHECK (email LIKE '%@%'),
    bank_card VARCHAR(30),
    upload_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    uploader_id INT NOT NULL,
    FOREIGN KEY (uploader_id) REFERENCES "User"(user_id)
);


-- 脱敏策略表（MaskingPolicy）
CREATE TABLE "MaskingPolicy" (
    policy_id SERIAL PRIMARY KEY,
    policy_name VARCHAR(20) NOT NULL UNIQUE,
    target_field VARCHAR(50) NOT NULL,
    rule VARCHAR(200) NOT NULL,
    policy_desc VARCHAR(500)
);


--脱敏任务表（MaskingTask）
CREATE TABLE "MaskingTask" (
    task_id SERIAL PRIMARY KEY,
    task_status VARCHAR(20) NOT NULL CHECK (task_status IN ('待处理', '完成', '失败')) DEFAULT '待处理',
    execute_time TIMESTAMP,
    policy_id INT NOT NULL,
    data_id INT NOT NULL,
    FOREIGN KEY (policy_id) REFERENCES "MaskingPolicy"(policy_id),
    FOREIGN KEY (data_id) REFERENCES "SensitiveData"(data_id)
);


--操作日志表（OperationLog）
CREATE TABLE "OperationLog" (
    log_id SERIAL PRIMARY KEY,
    action_type VARCHAR(50) NOT NULL,
    user_id INT NOT NULL,
    action_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    action_detail VARCHAR(500),
    FOREIGN KEY (user_id) REFERENCES "User"(user_id)
);


--审计日志表（AuditLog）
CREATE TABLE "AuditLog" (
    audit_id SERIAL PRIMARY KEY,
    auditor_id INT NOT NULL,
    audit_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    report_summary VARCHAR(500),
    log_id INT NOT NULL,
    FOREIGN KEY (auditor_id) REFERENCES "User"(user_id),
    FOREIGN KEY (log_id) REFERENCES "OperationLog"(log_id)
);

