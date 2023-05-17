--liquibase formatted sql
--changeset Sergey Vankovich:40
insert into projects (creation_time, last_update_time, name, description, max_students, work_format, requirements_for_performers, requirements, status, start_time)
values (now(), now(), 'Анализатор маршрутной сети города Петрозаводска',
        'Создание анализатора маршрутной сети города Петрозаводска для оценки качества спроектированной модели бла бла бла...',
        2, 'HYBRID', 'Что-то нереальное', 'Прям крутое', 'ACTIVE', '08.05.2023'),

       (now(), now(), 'Нейросеть для распознования грибов',
        'Создание нейросети, которая будет распознавать 1000 видов съедобный и несъедобных грибов для помощи грибникам бла бла бла...',
        2, 'DISTANT', 'Что-то крутое', 'Прям нереальное', 'TEST', '05.04.2022');
--rollback delete from projects;