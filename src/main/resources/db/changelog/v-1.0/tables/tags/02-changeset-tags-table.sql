--liquibase formatted sql
--changeset Andrey Butusov:2
create table
    tags (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        name varchar(255) not null,
        primary key (id)
    );
--rollback drop table tags;