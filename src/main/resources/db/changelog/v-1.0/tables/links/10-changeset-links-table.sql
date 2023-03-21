--liquibase formatted sql
--changeset Andrey Butusov:10
create table
    links (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        link varchar(255) not null,
        project_id bigint not null,
        primary key (id)
);
alter table if exists links
add
    constraint FKoryhxcrkx9ok9oq2m2l94hhhw foreign key (project_id) references projects;
--rollback alter table links drop constraint FKoryhxcrkx9ok9oq2m2l94hhhw;
--rollback drop table links;