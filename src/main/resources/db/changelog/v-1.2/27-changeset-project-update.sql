--liquibase formatted sql
--changeset Andrey Butusov:27
alter table if exists projects
    drop constraint FKksdiyuily2f4ca2y53k07pmq;

alter table if exists projects
    drop column client_id;
    
alter table if exists projects
    drop column end_time;

alter table if exists projects
    add column start_filing timestamp(6);
    
alter table if exists projects
    add column end_filing timestamp(6);
    
alter table if exists projects
    add column start_implementation timestamp(6);

alter table if exists projects
    add column end_implementation timestamp(6);

alter table if exists projects
    add column start_defense timestamp(6);

alter table if exists projects
    add column end_defense timestamp(6);

alter table if exists projects
    add column status varchar(255);

alter table if exists projects
    add column requirements_for_performers text;

alter table if exists projects
    drop column scientific_supervisor;
--rollback alter table if exists projects add column end_time timestamp(6);
--rollback alter table if exists projects add column scientific_supervisor varchar(255);
--rollback alter table if exists projects drop column start_filing;
--rollback alter table if exists projects drop column end_filing;
--rollback alter table if exists projects drop column start_implementation;
--rollback alter table if exists projects drop column end_implementation;
--rollback alter table if exists projects drop column start_defense;
--rollback alter table if exists projects drop column end_defense;
--rollback alter table if exists projects drop column status;
--rollback alter table if exists projects drop column requirements_for_performers;
