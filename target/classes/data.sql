INSERT INTO roles (id, name) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_ACTUATOR'),
(3, 'ROLE_USER'),
(4, 'ADMIN'),
(5, 'USER');



INSERT INTO users (id,is_account_non_expired,is_account_non_locked,is_credentials_non_expired,is_enabled, username, password) VALUES
(1,1,1,1,1,'admin', '$2a$10$dmX1kGhxHQzbSl0tEgNbCOzTdKuDGaZU3LY1NEP8VKCtNUbZB.ulO');



insert into users_roles(user_id, role_id) values
(1,1),
(1,2),
(1,3),
(1,4),
(1,5)
;

insert into categories (name,description)
values ('Custom Made','This guitar has been custom made');

insert into craftsmen(name)
value ('Crafter');

insert into guitars(id, price, image, name, samples, category_id)
value (1,100,null,'Guitar 100',10,1);
