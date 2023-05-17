--liquibase formatted sql
--changeset Andrey Butusov:28
alter table if exists requirements drop constraint FKqmqh3kwx05h7h413yfyaq0x1p;
drop table requirements;
--rollback create table
--rollback     requirements (
--rollback         id bigserial not null,
--rollback         creation_time timestamp(6),
--rollback         last_update_time timestamp(6),
--rollback         requirement varchar(255),
--rollback         project_id bigint not null,
--rollback         primary key (id)
--rollback );
--rollback alter table
--rollback     if exists requirements
--rollback add
--rollback     constraint FKqmqh3kwx05h7h413yfyaq0x1p foreign key (project_id) references projects;