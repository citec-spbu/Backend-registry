--liquibase formatted sql
--changeset Andrey Butusov:22
create table
    supervisors (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        link varchar(255),
        phone varchar(255),
        user_id bigint not null,
        primary key (id)
);

alter table if exists supervisors
    add
        constraint FKqahpoq6417qa8n5es35omu5cf foreign key (user_id) references users;
--rollback alter table if exists supervisors drop constraint FKqahpoq6417qa8n5es35omu5cf;
--rollback drop table supervisors;