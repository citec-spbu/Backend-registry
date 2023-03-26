--liquibase formatted sql
--changeset Sergey Vankovich:28
insert into projects (creation_time, last_update_time, name, description, max_students, scientific_supervisor, work_format, client_id)
values (now(), now(), 'Анализатор маршрутной сети города Петрозаводска',
        'Создание анализатора маршрутной сети города Петрозаводска для оценки качества спроектированной модели бла бла бла...',
        2, 'Профессоркин Профессор Профессорович', 'HYBRID', 2),

       (now(), now(), 'Нейросеть для распознования грибов',
        'Создание нейросети? которая будет распознавать 1000 видов съедобный и несъедобных грибов для помощи грибникам бла бла бла...',
        2, 'Докторкин Доктор Докторович', 'DISTANT', 3);
--rollback delete from projects;