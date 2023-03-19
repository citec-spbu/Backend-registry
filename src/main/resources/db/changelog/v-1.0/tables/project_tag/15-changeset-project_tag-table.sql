--liquibase formatted sql
--changeset Andrey Butusov:15
create table
    project_tag (
        project_id bigint not null,
        tag_id bigint not null,
        primary key (project_id, tag_id)
);
alter table
    if exists project_tag
add
    constraint FKtdpca6u6cxadjbie0iyuyt324 foreign key (tag_id) references tags;
alter table
    if exists project_tag
add
    constraint FK7arll7vppdy1j61xrux7nyy11 foreign key (project_id) references projects;
--rollback alter table project_tag drop constraint FKtdpca6u6cxadjbie0iyuyt324;
--rollback alter table project_tag drop constraint FK7arll7vppdy1j61xrux7nyy11;
--rollback drop table project_tag;