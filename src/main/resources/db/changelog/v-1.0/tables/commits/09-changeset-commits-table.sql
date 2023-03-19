--liquibase formatted sql
--changeset Andrey Butusov:9
create table
    commits (
        id bigserial not null,
        creation_time timestamp(6),
        last_update_time timestamp(6),
        commit_date date not null,
        num_differences integer not null,
        project_id bigint not null,
        student_id bigint not null,
        primary key (id)
);
alter table if exists commits
add
    constraint FK4bmx2s5jfc072em7w0boss13t foreign key (project_id) references projects;
alter table if exists commits
add
    constraint FKjs9x65x1qrw60in148autnp92 foreign key (student_id) references students;
--rollback alter table commits drop constraint FK4bmx2s5jfc072em7w0boss13t;
--rollback alter table commits drop constraint FKjs9x65x1qrw60in148autnp92;
--rollback drop table commits;