--liquibase formatted sql
--changeset Sergey Vankovich:35
insert into students (creation_time, last_update_time, sex, degree, grade, educational_program_id, user_id)
values (now(), now(), 'MALE', 'BACHELOR', 3, 1, 1),
       (now(), now(), 'MALE', 'BACHELOR', 2, 1, 2),
       (now(), now(), 'FEMALE', 'MASTER', 1, 2, 3),
       (now(), now(), 'MALE', 'MASTER', 2, 2, 4);
--rollback delete from students;