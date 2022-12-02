CREATE TABLE product (
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
name VARCHAR(200) NOT NULL,
price BIGINT,
manufacture_id UUID,
FOREIGN KEY (manufacture_id) REFERENCES manufacture (id)
);