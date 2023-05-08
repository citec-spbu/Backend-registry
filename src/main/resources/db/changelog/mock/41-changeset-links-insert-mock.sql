--liquibase formatted sql
--changeset Andrey Butusov:41
insert into links (creation_time, last_update_time, name, link, project_id)
values (now(), now(), 'git', 'https://github.com/spbu-registry/Backend-registry', 1),
       (now(), now(), 'git', '+https://github.com/spbu-registry/Frontend-registry', 2);
--rollback delete from curators;