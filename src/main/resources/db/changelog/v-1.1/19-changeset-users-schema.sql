--liquibase formatted sql
--changeset Sergey Vankovich:19
alter table if exists users
    drop constraint FKqvykjc6027qa8n5es37omu3xs;

alter table if exists users
    drop constraint FKc8nfkx91xbh5fv7a02092q1ip;

alter table if exists users
    drop column student_id;

alter table if exists users
    drop column client_id;
--rollback alter table if exists users add column student_id bigint;
--rollback alter table if exists users add column client_id bigint;
--rollback alter table if exists users add constraint FKqvykjc6027qa8n5es37omu3xs foreign key (client_id) references clients;
--rollback alter table if exists users add constraint FKc8nfkx91xbh5fv7a02092q1ip foreign key (student_id) references students;
