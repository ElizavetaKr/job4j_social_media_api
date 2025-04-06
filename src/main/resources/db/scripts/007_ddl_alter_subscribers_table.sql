ALTER TABLE subscribers ADD COLUMN target_user_id int references users(id);
ALTER TABLE subscribers DROP COLUMN exist;