insert into "user" (id, last_name, first_name, email, password)
values('a6192263-d70f-4a15-960f-074975e3063a', 'admin', 'admin',
	   'admin@admin.com', '$2a$10$sq.p6upRUoqIGvFUMjy5netCffCYMr2t0Troca/w7EZVYiaOOnRpy');

insert into role_user (role_id, user_id) VALUES
('fcf12a8d-91e9-4bb6-b60b-53d6d97a9f44', 'a6192263-d70f-4a15-960f-074975e3063a');