--liquibase formatted sql
--changeset Andrey Butusov:45
insert into project_curator (project_id, curator_id)
values (1, 2),
       (2, 1);
--rollback delete from project_curator;