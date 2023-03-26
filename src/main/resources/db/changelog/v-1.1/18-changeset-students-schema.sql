--liquibase formatted sql
--changeset Sergey Vankovich:18
alter table if exists students
    add column user_id bigint not null;

alter table if exists students
    drop column name;

alter table if exists students
    drop column email;

alter table if exists students
    add
        constraint FKqvykjc6027qa8n5es37osi7il foreign key (user_id) references users;

--rollback alter table if exists students drop constraint FKqvykjc6027qa8n5es37osi7il;
--rollback alter table if exists students drop column user_id;
--rollback alter table if exists students add column name varchar(255) not null;
--rollback alter table if exists students add column email varchar(255);