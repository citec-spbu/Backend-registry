--liquibase formatted sql
--changeset Sergey Vankovich:26
insert into clients (creation_time, last_update_time, org_name, phone, user_id)
        values (now(), now(), 'Газпром', '+78124137444', 5),
               (now(), now(), 'Huawei',  '+74952340686', 6),
               (now(), now(), 'SPBU',    '+78129146443', 7),
               (now(), now(), 'ЦИТИК',   '+78005553535', 8);
--rollback delete from clients;