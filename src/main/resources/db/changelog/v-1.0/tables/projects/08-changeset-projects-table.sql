--liquibase formatted sql
--changeset Andrey Butusov:8
create table
    projects (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        description text not null,
        end_time timestamp(6),
        max_students integer,
        name varchar(255) not null,
        requirements text,
        result_link varchar(255),
        scientific_supervisor varchar(255),
        start_time timestamp(6),
        work_format varchar(255) not null,
        client_id bigint not null,
        primary key (id)
);
alter table
    if exists projects
add
    constraint FKksdiyuily2f4ca2y53k07pmq foreign key (client_id) references clients;
--rollback alter table projects drop constraint FKksdiyuily2f4ca2y53k07pmq;
--rollback drop table projects;