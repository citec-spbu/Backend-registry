--liquibase formatted sql
--changeset Andrey Butusov:13
create table
    project_clinic (
        project_id bigint not null,
        clinic_id bigint not null,
        primary key (project_id, clinic_id)
);
alter table
    if exists project_clinic
add
    constraint FKm9sgo980w9yfdarbejbcqklpm foreign key (clinic_id) references clinics;
alter table
    if exists project_clinic
add
    constraint FKgxojnd462kp15j4w3txe2q75u foreign key (project_id) references projects;
--rollback alter table project_clinic drop constraint FKm9sgo980w9yfdarbejbcqklpm;
--rollback alter table project_clinic drop constraint FKgxojnd462kp15j4w3txe2q75u;
--rollback drop table project_clinic;