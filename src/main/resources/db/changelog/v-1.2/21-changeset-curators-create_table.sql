--liquibase formatted sql
--changeset Andrey Butusov:21
create table
    curators (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        link varchar(255),
        phone varchar(255),
        user_id bigint not null,
        primary key (id)
);

alter table if exists curators
    add
        constraint FKqasdbc5217qa8n5es37omu5cf foreign key (user_id) references users;
--rollback alter table if exists curators drop constraint FKqasdbc5217qa8n5es37omu5cf;
--rollback drop table curators;