SELECT * FROM users;

UPDATE users
SET usertype = 1, passhash = 'pass' 
WHERE userID = 4352;

SELECT * FROM conversations;
INSERT INTO conversations (conversationName) VALUES ('Team Tabl');