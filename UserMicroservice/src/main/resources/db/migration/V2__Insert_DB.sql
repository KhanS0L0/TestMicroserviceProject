insert into roles(name) values ('ROLE_USER'), ('ROLE_ADMIN');

insert into users(email, password, username) values ('email@email.com', 'password', 'test_user'), ('email@email.com', 'password', 'test_admin');

insert into users_roles(user_id, role_id) values (1, 1), (2, 2);