--liquibase formatted sql
--changeset Andrey Butusov:48
insert into project_client (project_id, client_id)
values (1, 2),
       (1, 3),
       (2, 4),
       (2, 1);
--rollback delete from project_client;