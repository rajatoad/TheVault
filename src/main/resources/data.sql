-- This code is left for creating H2 database
-- Use this for reference

-- DROP TABLES BEFORE CREATION
--========================================================================================
--                                  DROP TABLES
--========================================================================================

DROP TABLE logincredentialtable CASCADE;
DROP TABLE accountprofiletable CASCADE;
-- DON'T NEED THIS BUT USEFUL FOR RDS DATABASE IN FUTURE

--DROP TABLE deposittypetable;
--DROP TABLE requesttypetable;
--DROP TABLE requeststatustable;
--DROP TABLE deposittable;
DROP TABLE accounttypetable CASCADE;
DROP TABLE accounttable CASCADE;

--========================================================================================
--                                  LOOK UP TABLES
--========================================================================================

CREATE TABLE deposittypetable(
    pkdeposittypeid SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE requesttypetable(
    pkrequesttypeid SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE requeststatustable(
    pkrequeststatusid SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE accounttypetable(
    pkaccounttypeid SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

--========================================================================================
--                               BRIDGE TABLES FOR USE
--========================================================================================

--CREATE TABLE accounttypebridgetable(
--    fkaccountid INTEGER,
--    fkaccounttypeid INTEGER,
--    FOREIGN KEY (fkaccountid) REFERENCES accounttable(pkaccountid),
--    FOREIGN KEY (fkaccounttypeid) REFERENCES accounttypetable(pkaccounttypeid),
--    PRIMARY KEY (fkaccountid, fkaccounttypeid)
--);

--========================================================================================
--                               NORMAL TABLES FOR USE
--========================================================================================

CREATE TABLE logincredentialtable (
    pkuserid SERIAL PRIMARY KEY,
    username VARCHAR(15) UNIQUE NOT NULL,
    password VARCHAR(15) NOT NULL
);

CREATE TABLE accountprofiletable (
    pkprofileid SERIAL PRIMARY KEY,
    fkuserid INTEGER,
    firstname VARCHAR NOT NULL,
    lastname VARCHAR NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    phonenumber BIGINT UNIQUE NOT NULL,
    address VARCHAR NOT NULL,
    FOREIGN KEY (fkuserid) REFERENCES logincredentialtable(pkuserid)
);

CREATE TABLE accounttable(
    pkaccountid SERIAL PRIMARY KEY,
    fkuserid INTEGER,
    fkaccounttypeid INTEGER,
    availablebalance INTEGER,
    pendingbalance INTEGER,
    FOREIGN KEY (fkuserid) REFERENCES logincredentialtable(pkuserid),
    FOREIGN KEY (fkaccounttypeid) REFERENCES accounttypetable(pkaccounttypeid)
);

CREATE TABLE deposittable(
    pkdepositid SERIAL PRIMARY KEY,
    fkaccountid INTEGER,
    fkdeposittypeid INTEGER,
    reference VARCHAR(25),
    datedeposit DATE,
    amount DECIMAL(10, 2),
    FOREIGN KEY (fkaccountid) REFERENCES accounttable(pkaccountid),
    FOREIGN KEY (fkdeposittypeid) REFERENCES deposittypetable(pkdeposittypeid)
);

CREATE TABLE withdrawtable(
    pkwithdrawid SERIAL PRIMARY KEY,
    fkaccountid INTEGER,
    fkrequesttypeid INTEGER,
    fkrequeststatusid INTEGER,
    reference VARCHAR(25),
    datewithdraw DATE,
    amount DECIMAL(10, 2),
    FOREIGN KEY (fkaccountid) REFERENCES accounttable(pkaccountid),
    FOREIGN KEY (fkrequesttypeid) REFERENCES requesttypetable(pkrequesttypeid),
    FOREIGN KEY (fkrequeststatusid) REFERENCES requeststatustable(pkrequeststatusid)
);

--========================================================================================
--                                  POPULATE TABLES
--========================================================================================

INSERT INTO logincredentialtable
VALUES
    (default, 'username1', 'password1'),
    (default, 'username2', 'password2'),
    (default, 'username3', 'password3'),
    (default, 'username4', 'password4');

INSERT INTO accountprofiletable
VALUES
    (default, 1, 'Tom', 'Cat', 'email@email.com', 5559991234, '1 lane'),
    (default, 2, 'Joe', 'Shmo', 'yahoo@yahoo.com', 9995554323, '2 st');

INSERT INTO deposittypetable
VALUES
    (default, 'Cash'),
    (default, 'Cheque'),
    (default, 'Direct Deposit');

INSERT INTO requeststatustable
VALUES
    (default, 'Pending'),
    (default, 'Completed'),
    (default, 'Failed');

INSERT INTO requesttypetable
VALUES
    (default, 'Retail'),
    (default, 'Tech'),
    (default, 'Transfer');

INSERT INTO accounttypetable
VALUES
    (default, 'Checking'),
    (default, 'Savings');