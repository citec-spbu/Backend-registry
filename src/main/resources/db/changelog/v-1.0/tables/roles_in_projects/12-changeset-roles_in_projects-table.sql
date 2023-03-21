--liquibase formatted sql
--changeset Andrey Butusov:12
create table
    roles_in_projects (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        role varchar(255) not null,
        project_id bigint not null,
        student_id bigint not null,
        primary key (id)
);
alter table if exists roles_in_projects
add
    constraint FKn2r9lxwnpqo2elh5qlj3dpuhx foreign key (project_id) references projects;
alter table if exists roles_in_projects
add
    constraint FK54b75pf8n6ker6tp996u4nxvo foreign key (student_id) references students;
--rollback alter table roles_in_projects drop constraint FKn2r9lxwnpqo2elh5qlj3dpuhx;
--rollback alter table roles_in_projects drop constraint FK54b75pf8n6ker6tp996u4nxvo;
--rollback drop table roles_in_projects;