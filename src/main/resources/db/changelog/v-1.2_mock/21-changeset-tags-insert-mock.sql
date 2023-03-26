--liquibase formatted sql
--changeset Sergey Vankovich:21
insert into tags (creation_time, last_update_time, name)
          values (now(), now(), 'machine learning'),
                 (now(), now(), 'AI'),
                 (now(), now(), 'neural networks'),
                 (now(), now(), 'agricultural'),
                 (now(), now(), 'physics');
--rollback delete from tags;