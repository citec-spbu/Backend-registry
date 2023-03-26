--liquibase formatted sql
--changeset Sergey Vankovich:18
insert into clients (creation_time, last_update_time, org_name, email, phone, link)
values (now(), now(), 'Газпром', 'hotline@ss.gazprom.ru', '+78124137444', 'https://www.gazprom.ru'),
       (now(), now(), 'Huawei', 'Moscow.Reception@huawei.com', '+74952340686', 'https://www.huawei.ru'),
       (now(), now(), 'SPBU', 'pressa@spbu.ru', '+78129146443', 'https://spbu.ru'),
       (now(), now(), 'ЦИТИК', 'citic@spbu.ru', '+7800553535', 'https://spbu.ru/studentam/praktika/praktika-po-modeli-kliniki-v-spbgu/centr-it-i-inzhenernyh-kompetenciy?ysclid=lfpaz385hh389711213');
--rollback delete from clients;