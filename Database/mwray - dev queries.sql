SELECT * FROM users;

UPDATE users
SET usertype = 1, passhash = 'pass' 
WHERE userID = 4352;