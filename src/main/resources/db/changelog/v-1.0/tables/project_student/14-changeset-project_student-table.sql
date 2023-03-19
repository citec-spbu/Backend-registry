--liquibase formatted sql
--changeset Andrey Butusov:14
create table
    project_student (
        project_id bigint not null,
        student_id bigint not null,
        primary key (project_id, student_id)
);
alter table
    if exists project_student
add
    constraint FKkf2jffas838k4wxd9kc7xyne0 foreign key (student_id) references students;
alter table
    if exists project_student
add
    constraint FKthoas902afruk0t7q9df8qpre foreign key (project_id) references projects;
--rollback alter table project_student drop constraint FKkf2jffas838k4wxd9kc7xyne0;
--rollback alter table project_student drop constraint FKthoas902afruk0t7q9df8qpre;
--rollback drop table project_student;