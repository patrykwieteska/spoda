--liquibase formatted sql

--changeset db:1 endDelimiter:;
insert into PARTICIPANT ("NAME","DISPLAY_NAME","CREATION_DATE","LAST_MODIFICATION_DATE")

values ('Patryk', 'Wietek','2022-12-02 12:12:12','2022-12-02 12:12:12');