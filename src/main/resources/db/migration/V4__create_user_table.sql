CREATE TABLE "user" (
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
last_name VARCHAR(200) NOT NULL,
first_name VARCHAR(200) NOT NULL,
email VARCHAR(200),
password VARCHAR(200)
);