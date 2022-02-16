-- This code is left for creating H2 database
-- Use this for reference

-- DROP TABLES BEFORE CREATION
--========================================================================================
--                                  DROP TABLES
--========================================================================================

 DROP TABLE login_credential_table CASCADE;
DROP TABLE account_profile_table CASCADE;
-- DON'T NEED THIS BUT USEFUL FOR RDS DATABASE IN FUTURE

--DROP TABLE deposit_type_table;
DROP TABLE request_type_table CASCADE;
DROP TABLE request_status_table CASCADE;
DROP TABLE withdraw_table CASCADE;
--DROP TABLE deposit_table;
DROP TABLE account_type_table CASCADE;
DROP TABLE account_table CASCADE;

--========================================================================================
--                                  LOOK UP TABLES
--========================================================================================

CREATE TABLE deposit_type_table(
    pk_deposit_type_id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE request_type_table(
    pk_request_type_id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE request_status_table(
    pk_request_status_id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE account_type_table(
    pk_account_type_id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

--========================================================================================
--                               BRIDGE TABLES FOR USE
--========================================================================================

--CREATE TABLE account_type_bridge_table(
--    fk_account_id INTEGER,
--    fk_account_type_id INTEGER,
--    FOREIGN KEY (fk_account_id) REFERENCES account_table(pk_account_id),
--    FOREIGN KEY (fk_account_type_id) REFERENCES account_type_table(pk_account_type_id),
--    PRIMARY KEY (fk_account_id, fk_account_type_id)
--);

--========================================================================================
--                               NORMAL TABLES FOR USE
--========================================================================================

CREATE TABLE login_credential_table (
    pk_user_id SERIAL PRIMARY KEY,
    username VARCHAR(15) UNIQUE NOT NULL,
    password VARCHAR(15) NOT NULL
);

CREATE TABLE account_profile_table (
    pk_profile_id SERIAL PRIMARY KEY,
    fk_user_id INTEGER,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    phone_number VARCHAR UNIQUE NOT NULL,
    address VARCHAR NOT NULL,
    FOREIGN KEY (fk_user_id) REFERENCES login_credential_table(pk_user_id)
);

CREATE TABLE account_table(
    pk_account_id SERIAL PRIMARY KEY,
    fk_user_id INTEGER,
    fk_account_type_id INTEGER,
    available_balance BIGINT,
    pending_balance BIGINT,
    FOREIGN KEY (fk_user_id) REFERENCES login_credential_table(pk_user_id),
    FOREIGN KEY (fk_account_type_id) REFERENCES account_type_table(pk_account_type_id)
);

CREATE TABLE deposit_table(
    pk_deposit_id SERIAL PRIMARY KEY,
    fk_account_id INTEGER,
    fk_deposit_type_id INTEGER,
    reference VARCHAR(25),
    date_deposit DATE,
    amount DECIMAL(10, 2),
    FOREIGN KEY (fk_account_id) REFERENCES account_table(pk_account_id),
    FOREIGN KEY (fk_deposit_type_id) REFERENCES deposit_type_table(pk_deposit_type_id)
);

CREATE TABLE withdraw_table(
    pk_withdraw_id SERIAL PRIMARY KEY,
    fk_account_id INTEGER,
    fk_request_type_id INTEGER,
    fk_request_status_id INTEGER,
    reference VARCHAR(25),
    date_withdraw DATE,
    amount BIGINT,
    FOREIGN KEY (fk_account_id) REFERENCES account_table(pk_account_id),
    FOREIGN KEY (fk_request_type_id) REFERENCES request_type_table(pk_request_type_id),
    FOREIGN KEY (fk_request_status_id) REFERENCES request_status_table(pk_request_status_id)
);

--========================================================================================
--                                  POPULATE TABLES
--========================================================================================

--INSERT INTO login_credential_table
--VALUES
----    (default, 'username1', 'password1'),
----    (default, 'username2', 'password2'),
----    (default, 'username3', 'password3'),
----    (default, 'username4', 'password4');

INSERT INTO account_profile_table
VALUES
    (default, 1, 'Tom', 'Cat', 'email@email.com', "555-999-1234", '1 lane'),
    (default, 2, 'Joe', 'Shmo', 'yahoo@yahoo.com', "999-555-4323", '2 st');

INSERT INTO deposit_type_table
VALUES
    (default, 'Cash'),
    (default, 'Cheque'),
    (default, 'Direct Deposit');

INSERT INTO request_status_table
VALUES
    (default, 'Pending'),
    (default, 'Completed'),
    (default, 'Failed');

INSERT INTO request_type_table
VALUES
    (default, 'Retail'),
    (default, 'Tech'),
    (default, 'Transfer');

INSERT INTO account_type_table
VALUES
    (default, 'Checking'),
    (default, 'Savings');

INSERT INTO account_table
VALUES
    (default, 1, 1, 100, 130),
    (default, 2, 2, 199, 19);