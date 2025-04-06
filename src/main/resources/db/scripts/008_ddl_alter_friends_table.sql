ALTER TABLE friends ADD COLUMN target_user_id int references users(id);
ALTER TABLE friends DROP COLUMN exist;