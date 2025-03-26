CREATE TABLE posts (
id SERIAL PRIMARY KEY,
name varchar not null,
description text,
file_id int references files(id),
user_id int references users(id)
);