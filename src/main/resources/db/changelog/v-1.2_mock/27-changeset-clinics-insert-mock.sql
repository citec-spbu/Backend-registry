--liquibase formatted sql
--changeset Sergey Vankovich:27
insert into clinics (creation_time, last_update_time, name, faculty_id)
        values (now(), now(), 'IT', 1);
--rollback delete from clinics;