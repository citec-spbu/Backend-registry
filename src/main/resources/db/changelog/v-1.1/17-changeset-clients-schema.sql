--liquibase formatted sql
--changeset Sergey Vankovich:17
alter table if exists clients
    add column user_id bigint not null;

alter table if exists clients
    drop column name;

alter table if exists clients
    drop column email;

alter table if exists clients
    add
        constraint FKqvykjc6027qa8n5es37omu5cf foreign key (user_id) references users;
--rollback alter table if exists clients drop constraint FKqvykjc6027qa8n5es37omu5cf;
--rollback alter table if exists clients drop column user_id;
--rollback alter table if exists clients add column name varchar(255);
--rollback alter table if exists clients add column email varchar(255);
