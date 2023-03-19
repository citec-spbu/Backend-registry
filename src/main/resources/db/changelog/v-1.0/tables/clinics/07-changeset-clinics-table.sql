--liquibase formatted sql
--changeset Andrey Butusov:7
create table
    clinics (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        link varchar(255),
        name varchar(255) not null,
        faculty_id bigint not null,
        primary key (id)
);
--rollback drop table clinics;