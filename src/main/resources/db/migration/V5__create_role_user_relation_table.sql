CREATE TABLE role_user (
role_id UUID NOT NULL,
user_id UUID NOT NULL,
PRIMARY KEY (role_id, user_id),
FOREIGN KEY (role_id ) REFERENCES role (id),
FOREIGN KEY (user_id) REFERENCES "user" (id)
);