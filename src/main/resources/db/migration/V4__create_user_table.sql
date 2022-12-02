CREATE TABLE "user" (
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
lastName VARCHAR(200) NOT NULL,
firstName VARCHAR(200) NOT NULL,
email VARCHAR(200),
password VARCHAR(200)
);