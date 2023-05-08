--liquibase formatted sql
--changeset Sergey Vankovich:33
insert into faculties (creation_time, last_update_time, name, link)
        values (now(), now(), 'ПМ-ПУ', 'https://apmath.spbu.ru');
--rollback delete from faculties;