CREATE TABLE subscribers (
id SERIAL PRIMARY KEY,
exist BOOLEAN,
user_id int references users(id)
);