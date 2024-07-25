/* Create Tables */

CREATE TABLE IF NOT EXISTS administrator_key_code
(
    id uuid NOT NULL,
    user_id uuid NULL,
    key_code_value varchar(32) NOT NULL,
    CONSTRAINT PK_administrator_key_code PRIMARY KEY (id)
)
;

CREATE TABLE IF NOT EXISTS secret
(
    id uuid NOT NULL,
    login varchar(20) NOT NULL,
    password varchar(120) NOT NULL,
    CONSTRAINT PK_secret PRIMARY KEY (id)
)
;

CREATE TABLE IF NOT EXISTS block_reasons
(
    id varchar(10) NOT NULL,
    description text NOT NULL,
    CONSTRAINT PK_block_reasons PRIMARY KEY (id)
)
;

CREATE TABLE IF NOT EXISTS operation_permissions
(
    id uuid NOT NULL,
    permission_name text NOT NULL,
    operation_endpoint text NOT NULL,
    is_critical boolean NOT NULL,
    CONSTRAINT PK_operation_permissions PRIMARY KEY (id)
)
;

CREATE TABLE IF NOT EXISTS roles_operations
(
    id uuid NOT NULL,
    role_id uuid NOT NULL,
    permission_id uuid NOT NULL,
    CONSTRAINT PK_roles_operations PRIMARY KEY (id)
)
;

CREATE TABLE IF NOT EXISTS universal_user
(
    id uuid NOT NULL,
    user_info JSONB NULL,
    block_reason_id varchar(10) NULL,
    secret_id uuid NOT NULL,
    login varchar(20),
    CONSTRAINT PK_UniversalUser PRIMARY KEY (id)
)
;

CREATE TABLE IF NOT EXISTS user_group_roles
(
    id uuid NOT NULL,
    role_name text NOT NULL,
    description text NULL,
    criticality boolean NOT NULL,
    CONSTRAINT PK_user_group_roles PRIMARY KEY (id)
)
;

CREATE TABLE IF NOT EXISTS users_roles
(
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    role_id uuid NOT NULL,
    assign_date varchar(24) NOT NULL,
    CONSTRAINT PK_users_roles PRIMARY KEY (id)
)
;

CREATE TABLE IF NOT EXISTS user_permission
(
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    permission_id uuid NOT NULL,
    create_date varchar(24) NOT NULL,
    CONSTRAINT PK_user_permission PRIMARY KEY (id)
)
;

/* Drop Foreign Key Constraints if they exist*/

ALTER TABLE administrator_key_code DROP CONSTRAINT IF EXISTS FK_administrator_key_code_universal_user;

ALTER TABLE roles_operations DROP CONSTRAINT IF EXISTS FK_roles_operations_operation_permissions;

ALTER TABLE roles_operations DROP CONSTRAINT IF EXISTS FK_roles_operations_user_group_roles;

ALTER TABLE universal_user DROP CONSTRAINT IF EXISTS FK_universal_user_block_reasons;

ALTER TABLE users_roles DROP CONSTRAINT IF EXISTS FK_users_roles_universal_user;

ALTER TABLE users_roles DROP CONSTRAINT IF EXISTS FK_users_roles_user_group_roles;

ALTER TABLE user_permission DROP CONSTRAINT IF EXISTS FK_user_permission_operation_permissions;

ALTER TABLE user_permission DROP CONSTRAINT IF EXISTS FK_user_permission_universal_user;

ALTER TABLE universal_user DROP CONSTRAINT IF EXISTS FK_universal_user_secret;

/* Create Foreign Key Constraints */

ALTER TABLE administrator_key_code ADD CONSTRAINT FK_administrator_key_code_universal_user
    FOREIGN KEY (user_id) REFERENCES universal_user (id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE roles_operations
    ADD CONSTRAINT FK_roles_operations_operation_permissions
    FOREIGN KEY (permission_id)
    REFERENCES operation_permissions (id)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE roles_operations ADD CONSTRAINT FK_roles_operations_user_group_roles
    FOREIGN KEY (role_id) REFERENCES user_group_roles (id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE universal_user ADD CONSTRAINT FK_universal_user_block_reasons
    FOREIGN KEY (block_reason_id) REFERENCES block_reasons (id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE users_roles ADD CONSTRAINT FK_users_roles_universal_user
    FOREIGN KEY (user_id) REFERENCES universal_user (id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE users_roles ADD CONSTRAINT FK_users_roles_user_group_roles
    FOREIGN KEY (role_id) REFERENCES user_group_roles (id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE user_permission
    ADD CONSTRAINT FK_user_permission_operation_permissions
    FOREIGN KEY (permission_id)
    REFERENCES operation_permissions (id)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE user_permission
    ADD CONSTRAINT FK_user_permission_universal_user
    FOREIGN KEY (user_id)
    REFERENCES universal_user (id)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE universal_user
    ADD CONSTRAINT FK_universal_user_secret
    FOREIGN KEY (secret_id)
    REFERENCES secret (id)
    ON DELETE No Action ON UPDATE No Action
;

/* CREATE OR REPLACE FUNCTIONS */

CREATE OR REPLACE FUNCTION public.find_a_key_code_by_value(p_key_code_value character varying)
    RETURNS TABLE(id uuid, user_id uuid, key_code_value character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            akc.id as id,
            akc.user_id as user_id,
            akc.key_code_value as key_code_value
        from
            administrator_key_code akc
        where
            akc.key_code_value = p_key_code_value;
end;
';

CREATE OR REPLACE FUNCTION public.find_by_age(p_age integer)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.user_info is not NULL
          and us.user_info ->> ''age'' = p_age::text;
end;'
;

CREATE OR REPLACE FUNCTION public.find_by_block_reason_id_and_age(p_block_reason_id uuid, p_age integer)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.block_reason_id=p_block_reason_id
          and us.user_info is not NULL
          and us.user_info ->> ''age'' = p_age::text;
end;'
;

CREATE OR REPLACE FUNCTION public.find_by_block_reason_id_and_first_name(p_block_reason_id uuid, p_f_name character varying)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.block_reason_id=p_block_reason_id
          and us.user_info is not NULL
          and us.user_info ->> ''firstName'' = p_f_name;
end;'
;

CREATE OR REPLACE FUNCTION public.find_by_block_reason_id_and_first_name_and_age(p_block_reason_id uuid, p_f_name character varying, p_age integer)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.block_reason_id=p_block_reason_id
          and us.user_info is not NULL
          and us.user_info ->> ''firstName'' = p_f_name
          and us.user_info ->> ''age'' = p_age::text;
end;'
;

CREATE OR REPLACE FUNCTION public.find_by_block_reason_id_and_first_name_and_second_name(p_block_reason_id uuid, p_f_name character varying, p_s_name character varying)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.block_reason_id = p_block_reason_id
          and us.user_info is not NULL
          and us.user_info ->> ''firstName'' = p_f_name
          and us.user_info ->> ''secondName'' = p_s_name;
end;'
;

CREATE OR REPLACE FUNCTION public.find_by_block_reason_id_and_first_name_and_second_name_and_age(p_block_reason_id uuid, p_f_name character varying, p_s_name character varying, p_age integer)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.block_reason_id=p_block_reason_id
          and us.user_info is not NULL
          and us.user_info ->> ''firstName'' = p_f_name
          and us.user_info ->> ''secondName'' = p_s_name
          and us.user_info ->> ''age'' = p_age::text;
end;'
;

CREATE OR REPLACE FUNCTION public.find_by_block_reason_id_and_second_name(p_block_reason_id uuid, p_s_name character varying)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.block_reason_id=p_block_reason_id
          and us.user_info is not NULL
          and us.user_info ->> ''secondName'' = p_s_name;
end;'
;

CREATE OR REPLACE FUNCTION public.find_by_block_reason_id_and_second_name_and_age(p_block_reason_id uuid, p_s_name character varying, p_age integer)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.block_reason_id=p_block_reason_id
          and us.user_info is not NULL
          and us.user_info ->> ''secondName'' = p_s_name
          and us.user_info ->> ''age'' = p_age::text;
end;
'
;

CREATE OR REPLACE FUNCTION public.find_by_first_name(p_f_name character varying)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.user_info is not NULL
          and us.user_info ->> ''firstName'' = p_f_name;
end;'
;


CREATE OR REPLACE FUNCTION public.find_by_first_name_and_age(p_f_name character varying, p_age integer)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.user_info is not NULL
          and us.user_info ->> ''firstName'' = p_f_name
          and us.user_info ->> ''age'' = p_age::text;
end;'
;

CREATE OR REPLACE FUNCTION public.find_by_first_name_and_second_name(p_f_name character varying, p_s_name character varying)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.user_info is not NULL
          and us.user_info ->> ''firstName'' = p_f_name
          and us.user_info ->> ''secondName'' = p_s_name;
end;'
;

CREATE OR REPLACE FUNCTION public.find_by_first_name_ans_second_name_and_age(p_f_name character varying, p_s_name character varying, p_age integer)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.user_info is not NULL
          and us.user_info ->> ''firstName'' = p_f_name
          and us.user_info ->> ''secondName'' = p_s_name
          and us.user_info ->> ''age'' = p_age::text;
end;'
;

CREATE OR REPLACE FUNCTION public.find_by_second_name(p_s_name character varying)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.user_info is not NULL
          and us.user_info ->> ''secondName'' = p_s_name;
end;'
;

CREATE OR REPLACE FUNCTION public.find_by_second_name_and_age(p_s_name character varying, p_age integer)
    RETURNS TABLE(id uuid, secret_id uuid, user_info jsonb, block_reason_id character varying)
    LANGUAGE plpgsql
AS '
begin
    return query
        select
            us.id as id,
            us.secret_id as secret_id,
            us.user_info as user_info,
            us.block_reason_id as block_reason_id
        from
            universal_user us
        where
            us.user_info is not NULL
          and us.user_info ->> ''secondName'' = p_s_name
          and us.user_info ->> ''age'' = p_age::text;
end;'
;

CREATE OR REPLACE FUNCTION secret_login_update_trigger_fnc()
   RETURNS trigger
   LANGUAGE plpgsql 
AS '
BEGIN
   update public.universal_user 
   set login = (
 	  select login
 	  from public.secret s
 	  where secret_id = s.id
   );
   RETURN NEW;
END;'
;

CREATE OR REPLACE FUNCTION public.universal_user_login_update_trigger_fnc()
    RETURNS trigger
    LANGUAGE plpgsql
AS '
BEGIN
    if not new.login = (select login from public.secret where old.secret_id = id) then
        raise ''Changes of column login in universal_user table allowed only by changing the login value in table secret'';
    end if;
    RETURN NEW;
END;'
;

/* CREATE OR REPLACE TRIGGERS */

CREATE OR REPLACE TRIGGER secret_login_update_trigger
  AFTER UPDATE OF login
  ON public.secret
  FOR EACH ROW
  EXECUTE PROCEDURE secret_login_update_trigger_fnc();

CREATE OR REPLACE TRIGGER universal_user_login_update_trigger
    BEFORE UPDATE OF login
    ON public.universal_user
    FOR EACH ROW
    EXECUTE PROCEDURE universal_user_login_update_trigger_fnc();