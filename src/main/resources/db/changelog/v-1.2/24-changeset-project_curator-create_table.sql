--liquibase formatted sql
--changeset Andrey Butusov:24
create table
    project_curator (
        project_id bigint not null,
        curator_id bigint not null,
        primary key (project_id, curator_id)
);
alter table
    if exists project_curator
add
    constraint FKm9sgo980w9yfdarbejblhaqol foreign key (curator_id) references curators;
alter table
    if exists project_curator
add
    constraint FKglfpnd731kp15j4w3txe2q75u foreign key (project_id) references projects;
--rollback alter table project_curator drop constraint FKm9sgo980w9yfdarbejblhaqol;
--rollback alter table project_curator drop constraint FKglfpnd731kp15j4w3txe2q75u;
--rollback drop table project_curator;