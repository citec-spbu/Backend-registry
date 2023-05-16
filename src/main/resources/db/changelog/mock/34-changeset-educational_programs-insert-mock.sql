--liquibase formatted sql
--changeset Sergey Vankovich:34
insert into educational_programs (creation_time, last_update_time, code, name, faculty_id)
        values (now(), now(), '01.03.02', 'Прикладная математика, фундаментальная информатика и программирование', 1),
               (now(), now(), '02.03.02', 'Программирование и информационные технологии', 1);
--rollback delete from educational_programs;