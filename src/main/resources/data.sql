insert into departments (name) values ('Департамент нижнего интернета 1');

insert into posts (name) values ('Разумист');

insert into users (post_id, department_id, date_employment)
values (1, 1, '2010-12-11');

insert into article_types (id, name) values (1, 'тип статьи 1');

insert into articles (type_id, author_id, name, text, status_id)
       values (1, 1, 'статья 1', 'Какой-то текст статьи 1', 'TO_BE_AGREED');