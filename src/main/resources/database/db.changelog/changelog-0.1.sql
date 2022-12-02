--liquibase formatted sql

--changeset db:1 endDelimiter:;
create TABLE "PARTICIPANT" (
    "ID" number(10) PRIMARY KEY NOT NULL,
    "USER_NAME" VARCHAR(50) NOT NULL,
    "ALIAS" VARCHAR(50) NOT NULL,
    "CREATION_DATE" DATETIME NULL,
    "LAST_MODIFICATION_DATE" DATETIME  NULL,
    "CREATED_BY" VARCHAR(50) NULL
);

insert into PARTICIPANT
values (22, 'Patryk', 'Wietek','2022-12-02 12:12:12','2022-12-02 12:12:12','Wietek');