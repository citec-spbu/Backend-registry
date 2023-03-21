--liquibase formatted sql
--changeset Andrey Butusov:1
create table
    faculties (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        link varchar(255) not null,
        name varchar(255) not null,
        primary key (id)
    );
--rollback drop table faculties; 