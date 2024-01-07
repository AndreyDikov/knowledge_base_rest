insert into security_user (username, password, active)
values ('user', '123', true),
       ('admin', '123', true),
       ('userAndAdmin', '123', true);

insert into user_role
values (1, 'BASIC_STATE'),
       (2, 'ADMINISTRATION_STATE'),
       (3, 'BASIC_STATE'),
       (3, 'ADMINISTRATION_STATE');

insert into departments (name) values ('Департамент нижнего интернета 1');

insert into posts (name) values ('Разумист');

insert into users (post_id, department_id, date_employment)
values (1, 1, '2010-12-11');

insert into article_types (id, name) values (1, 'тип статьи 1');

insert into articles (type_id, author_id, name, text, status_id)
       values (1, 1, 'статья 1', 'Какой-то текст статьи 1', 'TO_BE_AGREED');