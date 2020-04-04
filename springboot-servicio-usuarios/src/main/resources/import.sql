insert into usuarios (username, password, enabled, nombre, apellido, email) values ('andres', '$2a$10$3ZnIJjkC7KZUdgFikBpHP.3toJwdj1Ewcp86nm4IJWsFwR5awZC5e', true, 'Andres', 'Guzman', 'profesor@bolsadeideas.com');
insert into usuarios (username, password, enabled, nombre, apellido, email) values ('admin', '$2a$10$QOTPcMqAdx3GB5j4O8WsCuJqyYypOQMOEKdPFxxdqTE6ECi8j8C1q', true, 'Jonh', 'Doe', 'john.doe@bolsadeideas.com');

insert into roles (nombre) values ('ROLE_USER');
insert into roles (nombre) values ('ROLE_ADMIN');
insert into roles (nombre) values ('ROLE_OTRO');

insert into usuarios_roles (usuario_id, rol_id) values (1, 1);
insert into usuarios_roles (usuario_id, rol_id) values (2, 2);
insert into usuarios_roles (usuario_id, rol_id) values (2, 1);