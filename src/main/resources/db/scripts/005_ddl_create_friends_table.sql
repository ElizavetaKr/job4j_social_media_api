CREATE TABLE friends (
id SERIAL PRIMARY KEY,
exist BOOLEAN,
user_id int references users(id)
);