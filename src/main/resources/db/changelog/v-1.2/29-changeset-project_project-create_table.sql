--liquibase formatted sql
--changeset Andrey Butusov:29
create table
    project_project (
        first_project_id bigint not null,
        second_project_id bigint not null,
        primary key (first_project_id, second_project_id)
);
alter table
    if exists project_project
add
    constraint FKm9sgo953w9asfkdfejnralsdg foreign key (first_project_id) references projects;
alter table
    if exists project_project
add
    constraint FKjtojnd652kp73j4w6yer2q75u foreign key (second_project_id) references projects;
--rollback alter table project_project drop constraint FKm9sgo953w9asfkdfejnralsdg;
--rollback alter table project_project drop constraint FKjtojnd652kp73j4w6yer2q75u;
--rollback drop table project_project;