--liquibase formatted sql
--changeset Sergey Vankovich:31
insert into project_tag (project_id, tag_id)
values (1, 1),
       (1, 2),
       (1, 5),
       (2, 1),
       (2, 2),
       (2, 3);
--rollback delete from project_tag;