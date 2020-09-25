/* Populate tabla clientes  datos base */
INSERT INTO regiones (id, nombre, create_at) VALUES (1, 'No Especifica', '2020-01-01');
INSERT INTO regiones (id, nombre, create_at) VALUES (2, 'La Ceiba', '2020-01-01');
INSERT INTO regiones (id, nombre, create_at) VALUES (3, 'Comuneros', '2020-01-01');




INSERT INTO sucursales (id, nombre, nit, direccion, telefono, celular1, celular2, propietario, sede, regimen, facebook, instagram, geoposicion, create_at) VALUES (1, 'Bulevar', '1.090.363.231-0', 'Calle 11 Av.0 Centro Comercial Gran Bulevar. Local 143', '5833938', '300 392 2227', '55555555', 'Joiner Obando', 'CLUB GALLERY AIRGUNS COLOMBIA', 'Simplificado', 'Joiner Candado', 'Joiner Candado', 'Cucuta - Colombia', '2018-01-01');


/* Creamos algunos usuarios con sus roles */
INSERT INTO usuarios (documento, region_id,  username, password, enabled, nombre, apellido, email, fecha, create_at, direccion, celular1) VALUES ('0123456789', 1,    'admin1', '$2a$10$0mE/eqTisi6Gqr0falzenOd3klFZ9TGUJUAOFiiF37F6W4w2SZko6', 1, 'Administrador', 'admin','administrador@bolsadeideas.com', '2018-03-05', '2019-01-01', 'calle alguna # alguno', '3194335815');
INSERT INTO usuarios (documento, region_id,  username, password, enabled, nombre, apellido, email, fecha, create_at, direccion, celular1) VALUES ('0123456788', 1,    'admin2', '$2a$10$2ZdRmg7MtYWhgD0ZZYlXFeEtCLa17fi9z/zdAffEk.Szski7yBsze', 1, 'Usuario1', 'usuario1','usuario1@bolsadeideas.com', '2018-03-05', '2019-01-01', 'calle alguna # alguno', '3194335819');
INSERT INTO usuarios (documento, region_id,  username, password, enabled, nombre, apellido, email, fecha, create_at, direccion, celular1) VALUES ('0123456787', 1,    'usuario1', '$2a$10$2ZdRmg7MtYWhgD0ZZYlXFeEtCLa17fi9z/zdAffEk.Szski7yBsze', 1, 'Usuario2', 'usuario2','usuario2@bolsadeideas.com', '2018-03-05', '2019-01-01', 'calle alguna # alguno', '3194335818');
INSERT INTO usuarios (documento, region_id,  username, password, enabled, nombre, apellido, email, fecha, create_at, direccion, celular1) VALUES ('0123456786', 1,    'usuario2', '$2a$10$2ZdRmg7MtYWhgD0ZZYlXFeEtCLa17fi9z/zdAffEk.Szski7yBsze', 1, 'Usuario3', 'usuario3','usuario3@bolsadeideas.com', '2018-03-05', '2019-01-01', 'calle alguna # alguno', '3194335817');
INSERT INTO usuarios (documento, region_id,  username, password, enabled, nombre, apellido, email, fecha, create_at, direccion, celular1) VALUES ('0123456785', 1,    'usuario3', '$2a$10$2ZdRmg7MtYWhgD0ZZYlXFeEtCLa17fi9z/zdAffEk.Szski7yBsze', 1, 'Usuario4', 'usuario4','usuario4@bolsadeideas.com', '2018-03-05', '2019-01-01', 'calle alguna # alguno', '3194335816');

/* Populate tabla roles */
INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

/* Populate tabla usuarios roles */
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (3, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (4, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (5, 1);

