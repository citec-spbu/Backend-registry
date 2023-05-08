--liquibase formatted sql
--changeset Andrey Butusov:39
insert into supervisors (creation_time, last_update_time, link, phone, user_id)
values (now(), now(), 'asfaasff@gmail.com', '+3333333333', 11),
       (now(), now(), 'ggggggg@gmail.com', '+8734566666', 12);
--rollback delete from supervisors;