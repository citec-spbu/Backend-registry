--liquibase formatted sql
--changeset Andrey Butusov:49
insert into roles_in_projects (role, project_id, student_id)
values ('Team Lead', 1, 1),
       ('Tester', 1, 2),
       ('Team Lead', 2, 3),
       ('Frontend', 2, 4);
--rollback delete from roles_in_project;