--liquibase formatted sql
--changeset Sergey Vankovich:32
insert into users (creation_time, last_update_time, name, role, email)
values (now(), now(), 'Сидоров Петр Иванович',      'USER', 'sidorov@student.spbu.ru'),
       (now(), now(), 'Петров Иван Васильевич',     'USER', 'petrov@student.spbu.ru'),
       (now(), now(), 'Иванова Анастасия Павловна', 'USER', 'ivanova@student.spbu.ru'),
       (now(), now(), 'Пупкин Аркадий Петрович',    'USER', 'pupkin@student.spbu.ru'),
       (now(), now(), 'Газпром',                    'USER', 'https://www.gazprom.ru'),
       (now(), now(), 'Huawei',                     'USER', 'https://www.huawei.ru'),
       (now(), now(), 'SPBU',                       'USER', 'https://spbu.ru'),
       (now(), now(), 'ЦИТИК',                      'USER', 'https://spbu.ru/studentam/praktika/praktika-po-modeli-kliniki-v-spbgu/centr-it-i-inzhenernyh-kompetenciy?ysclid=lfpaz385hh389711213'),
       (now(), now(), 'Коровкин Максим Васильевич', 'USER', 'https://spbu.ru'),
       (now(), now(), 'Блеканов Иван Станиславович', 'USER', 'https://spbu.ru'),
       (now(), now(), 'Давыденко Александр Александрович', 'USER', 'https://spbu.ru'),
       (now(), now(), 'Александров Александр Юрьевич', 'USER', 'https://spbu.ru');
--rollback delete from users;