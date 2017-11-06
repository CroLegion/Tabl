SELECT * FROM users;

UPDATE users
SET usertype = 1, passhash = 'pass' 
WHERE userID = 4352;

SELECT * FROM conversations;
INSERT INTO conversations (conversationName) VALUES ('Team Tabl');
INSERT INTO conversations (conversationName) VALUES ('Test');

INSERT INTO conversation_assignments (conversationID, memberID) values (1, 1112);
INSERT INTO conversation_assignments (conversationID, memberID) values (2, 1112);
INSERT INTO conversation_assignments (conversationID, memberID) values (1, 630128640);

SELECT * FROM conversation_assignments;

SELECT * FROM conversations JOIN conversation_assignments ON conversations.conversationID = conversation_assignments.conversationID WHERE memberID = 1112;

INSERT INTO messages (msgsender, msgcontent, msgdest) VALUES (1112, 'This is a second test message to thread 1.', 1);

SELECT * FROM messages WHERE msgdest = 2;