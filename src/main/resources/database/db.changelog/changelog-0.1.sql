--liquibase formatted sql

--changeset db:1 endDelimiter:;
CREATE TABLE "PARTICIPANT" (
    "ID" number(10) PRIMARY KEY NOT NULL,
    "USER_NAME" varchar(50) NOT NULL,
    "ALIAS" varchar(50) NOT NULL,
    "CREATION_DATE" DATETIME NULL,
    "LAST_MODIFICATION_DATE" DATETIME  NULL,
    "CREATED_BY" varchar(50) NULL
);


INSERT INTO PARTICIPANT
VALUES (1, 'Patryk', 'Wietek','2022-12-02 12:12:12','2022-12-02 12:12:12','Wietek');