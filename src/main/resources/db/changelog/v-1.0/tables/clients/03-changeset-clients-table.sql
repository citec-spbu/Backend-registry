--liquibase formatted sql
--changeset Andrey Butusov:3
create table
    clients (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        email varchar(255),
        link varchar(255),
        name varchar(255),
        org_name varchar(255) not null,
        phone varchar(255),
        primary key (id)
);
--rollback drop table clients;