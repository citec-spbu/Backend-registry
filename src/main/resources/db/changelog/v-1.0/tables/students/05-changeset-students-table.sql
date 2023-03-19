--liquibase formatted sql
--changeset Andrey Butusov:5
create table
    students (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        degree varchar(255) not null,
        email varchar(255) not null,
        grade integer not null,
        name varchar(255) not null,
        sex varchar(255) not null,
        educational_program_id bigint not null,
        primary key (id)
    );
alter table if exists students
add
    constraint FKp65plgmefx811xau7o93nw12p foreign key (educational_program_id) references educational_programs;
--rollback alter table students drop constraint FKp65plgmefx811xau7o93nw12p;
--rollback drop table students;