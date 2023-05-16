--liquibase formatted sql
--changeset Andrey Butusov:25
create table
    project_supervisor (
        project_id bigint not null,
        supervisor_id bigint not null,
        primary key (project_id, supervisor_id)
);
alter table
    if exists project_supervisor
add
    constraint FKm9sgo953w9yfdsdfejbcqlsdg foreign key (supervisor_id) references supervisors;
alter table
    if exists project_supervisor
add
    constraint FKjtojnd632kp73j4w3txe2q75u foreign key (project_id) references projects;
--rollback alter table project_supervisor drop constraint FKm9sgo953w9yfdsdfejbcqlsdg;
--rollback alter table project_supervisor drop constraint FKjtojnd632kp73j4w3txe2q75u;
--rollback drop table project_supervisor;