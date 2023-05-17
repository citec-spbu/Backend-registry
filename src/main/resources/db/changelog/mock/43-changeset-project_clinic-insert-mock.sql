--liquibase formatted sql
--changeset Sergey Vankovich:43
insert into project_clinic (project_id, clinic_id)
values (1, 1),
       (2, 1);
--rollback delete from project_clinic;