--liquibase formatted sql
--changeset Andrey Butusov:4
create table
    educational_programs (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        code varchar(255) not null,
        name varchar(255) not null,
        faculty_id bigint not null,
        primary key (id)
);
alter table
    if exists educational_programs
add
    constraint FKqaos5vh58hxb7sguhy66qnpj3 foreign key (faculty_id) references faculties;
--rollback alter table educational_programs drop constraint FKqaos5vh58hxb7sguhy66qnpj3;
--rollback drop table educational_programs;