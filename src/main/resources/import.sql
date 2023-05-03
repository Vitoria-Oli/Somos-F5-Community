INSERT INTO profiles VALUES (1, 'MIMIMI', 'LinkedIn', 'Gijon', 'Mari Carmen', 'Apellido')
INSERT INTO profiles VALUES (2, 'MIMIMI', 'LinkedIn', 'Murcia', 'Dieguuuu', 'Apellido')
INSERT INTO contacts VALUES (default,2)

INSERT INTO users VALUES (default, null, '$2a$10$GesR/t2ldE7wtXwh93Brd.txPEvLVbtE02LOwWmI9Rq40YWKWi4Ra', 'saul@gmail.com', 1)
INSERT INTO users VALUES (default, null, '$2a$10$GesR/t2ldE7wtXwh93Brd.txPEvLVbtE02LOwWmI9Rq40YWKWi4Ra', 'sa', 2)

-- INSERT INTO profiles VALUES (default, 'Giiiii', 'Liiiii', 'Ubiiiii', 'Nomiiiii', 'Apiiiiii')
-- INSERT INTO posts VALUES (default, '2023-02-01','Recurso de prueba', 'Titulo de prueba', 1)
-- INSERT INTO users (id, username, password, profile_id) VALUES (default, "enol", "1234", 1)

INSERT INTO roles (id, role_name) VALUES (default, "ROLE_ADMIN")
INSERT INTO roles (id, role_name) VALUES (default, "ROLE_USER")

INSERT INTO user_roles (user_id, role_id) VALUES (1,1)
INSERT INTO profiles_contacts (profile_id, contact_id) VALUES (1,1)

INSERT INTO posts (id_post, title, date, description, profile_id) VALUES (default, 'publicacion', null, 'aplicacion java', 1)
INSERT INTO posts (id_post, title, date, description, profile_id) VALUES (default, 'Titulo', null, 'Descripcion', 2)
INSERT INTO posts (id_post, title, date, description, profile_id) VALUES (default, 'Titulo', null, 'Descripcion', 1)


