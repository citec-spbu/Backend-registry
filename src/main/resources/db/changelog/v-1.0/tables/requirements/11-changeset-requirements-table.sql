--liquibase formatted sql
--changeset Andrey Butusov:11
create table
    requirements (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        requirement varchar(255),
        project_id bigint not null,
        primary key (id)
);
alter table
    if exists requirements
add
    constraint FKqmqh3kwx05h7h413yfyaq0x1p foreign key (project_id) references projects;
--rollback alter table requirements drop constraint FKqmqh3kwx05h7h413yfyaq0x1p;
--rollback drop table requirements;