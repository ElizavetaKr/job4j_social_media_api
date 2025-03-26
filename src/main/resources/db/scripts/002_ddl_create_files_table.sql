CREATE TABLE files (
id SERIAL PRIMARY KEY,
name varchar not null,
path varchar unique not null
);