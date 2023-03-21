--liquibase formatted sql
--changeset Andrey Butusov:6
create table
    users (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        email varchar(255),
        name varchar(255) not null,
        role varchar(255) not null,
        client_id bigint,
        student_id bigint,
        primary key (id)
    );
alter table if exists users
add
    constraint FKqvykjc6027qa8n5es37omu3xs foreign key (client_id) references clients;
alter table if exists users
add
    constraint FKc8nfkx91xbh5fv7a02092q1ip foreign key (student_id) references students;
--rollback alter table users drop constraint FKqvykjc6027qa8n5es37omu3xs;
--rollback alter table users drop constraint FKc8nfkx91xbh5fv7a02092q1ip;
--rollback drop table users;