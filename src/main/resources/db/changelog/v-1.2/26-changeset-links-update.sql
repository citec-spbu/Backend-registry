--liquibase formatted sql
--changeset Andrey Butusov:26
alter table if exists links
    add column name varchar(255) not null;

--rollback alter table if exists links drop column name; 