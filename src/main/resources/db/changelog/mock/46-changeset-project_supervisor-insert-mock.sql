--liquibase formatted sql
--changeset Andrey Butusov:46
insert into project_supervisor (project_id, supervisor_id)
values (1, 1),
       (2, 2);
--rollback delete from project_supervisor;