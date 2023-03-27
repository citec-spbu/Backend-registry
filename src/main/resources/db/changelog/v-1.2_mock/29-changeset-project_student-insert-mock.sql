--liquibase formatted sql
--changeset Sergey Vankovich:29
insert into project_student (project_id, student_id)
values (1, 1),
       (1, 2),
       (2, 3),
       (2, 4);
--rollback delete from project_student;