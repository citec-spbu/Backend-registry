--liquibase formatted sql
--changeset Andrey Butusov:38
insert into curators (creation_time, last_update_time, link, phone, user_id)
values (now(), now(), 'sdlkjgf@gmail.com', '+10978513689', 9),
       (now(), now(), 'sdgsdgsdg@gmail.com', '+1235624635', 10);
--rollback delete from curators;