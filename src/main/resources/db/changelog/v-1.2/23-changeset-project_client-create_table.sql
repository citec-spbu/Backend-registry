--liquibase formatted sql
--changeset Andrey Butusov:23
create table
    project_client (
        project_id bigint not null,
        client_id bigint not null,
        primary key (project_id, client_id)
);
alter table
    if exists project_client
add
    constraint FKm4lmk528w9yfdarbejbcqklpm foreign key (client_id) references clients;
alter table
    if exists project_client
add
    constraint FKgxolmd632kp15j4w3fkd2q63u foreign key (project_id) references projects;
--rollback alter table project_client drop constraint FKm4lmk528w9yfdarbejbcqklpm;
--rollback alter table project_client drop constraint FKgxolmd632kp15j4w3fkd2q63u;
--rollback drop table project_client;