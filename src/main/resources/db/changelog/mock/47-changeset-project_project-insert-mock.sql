--liquibase formatted sql
--changeset Andrey Butusov:47
insert into project_project (first_project_id, second_project_id)
values (1, 2);
--rollback delete from project_project;