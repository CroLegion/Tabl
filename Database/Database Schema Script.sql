/*Setup and test scripts for CS 309 project*/
/*Last modified 08/28/2017 by Mason Wray*/

/*Creates a new schema within the current database to hold tables*/
CREATE SCHEMA `project` ;

/*Creates a table to store a list of users*/
/*NOTE: 'usertype' field is intended to represent admin = 1, manager = 2 (but 0 for now), or worker = 3 numerically*/
/*NOTE: 'pashash' field is intended to contain a SHA256 hash of the user's password to facilitate (relatively) secure logins*/
/*NOTE: SHA256 hash must be executed server-side, before insertion into database*/
CREATE TABLE users
(
	userID int auto_increment not null,
    usertype tinyint not null,
    username varchar(32) unique not null,
    firstname varchar(32) not null,
    lastname varchar(32) not null,
    email varchar(64),
    phone varchar(11),
    passhash varchar(32) not null,
    isActive boolean not null,
    PRIMARY KEY (userID)
);

/*Creates a table to store a list of jobs*/
/*NOTE: 'jobtype' field is intended to represent project = 1, or job = 2 numerrically*/
/*NOTE: 'parentID' creates a circular reference back to the 'jobs' table, facilitating an arbitrary-depth task heirarchy*/
/*NOTE: 'parentID' can be NULL, indicating that a job is root/top level (is a project)*/
CREATE TABLE jobs
(
	jobID int auto_increment not null,
    jobname varchar(32) not null,
    jobtype tinyint not null,
    jobdesc text,
    parentID int,
    taskID int,	/*should be removed, tasks->jobs is a many-to-one relation*/
    PRIMARY KEY (jobID),
    FOREIGN KEY (parentID) REFERENCES jobs(jobID),
    FOREIGN KEY (taskID) REFERENCES tasks(taskID)
);

CREATE TABLE tasks
(
    taskID int auto_increment not null,
    taskname varchar(32) not null,
    taskdesc text,
    parentID int not null,
    PRIMARY KEY (taskID),
    FOREIGN KEY (parentID) REFERENCES jobs(jobID)
);

/*Creates a table to store data about who is qualified to accept each task*/
CREATE TABLE qualifications
(
	qualID int auto_increment not null,
    qualname varchar(32) not null,
    qualdescription text,
    PRIMARY KEY (qualID)
);

CREATE TABLE qualification_requirements
(
	qualID int not null,
    taskID int not null,
    PRIMARY KEY (qualID,taskID),
    FOREIGN KEY (taskID) REFERENCES tasks(taskID)
);
CREATE TABLE job_assignments
(
    jobID int not null,
    userID int not null,
    PRIMARY KEY (jobID,userID),
    FOREIGN KEY (userID) REFERENCES users(userID),
    FOREIGN KEY (jobID) REFERENCES jobs(jobID)
);
CREATE TABLE task_assignments
(
    taskID int not null,
    userID int not null,
    PRIMARY KEY (taskID,userID),
    FOREIGN KEY (userID) REFERENCES users(userID),
    FOREIGN KEY (taskID) REFERENCES tasks(taskID)
);
CREATE TABLE job_requirements
(
	jobID int not null,
    qualID int not null,
    PRIMARY KEY (jobID, qualID),
    FOREIGN KEY (jobID) REFERENCES jobs(jobID)
);
CREATE TABLE tickets
(
	ticketID int not null,
    title text not null,
    message text,
    submittedBy int not null,
    isDone boolean,
    submittedDate varchar(32) not null,
    PRIMARY KEY (ticketID),
    FOREIGN KEY (submittedBy) REFERENCES users(userID)
);
CREATE TABLE manager_assignments
(
    jobID int not null,
    userID int not null,
    PRIMARY KEY (jobID,userID),
    FOREIGN KEY (userID) REFERENCES users(userID),
    FOREIGN KEY (jobID) REFERENCES jobs(jobID)
);
CREATE TABLE qualification_assignments
(
    qualID int not null,
    userID int not null,
    PRIMARY KEY (qualID,userID),
    FOREIGN KEY (userID) REFERENCES users(userID)
);

/*Creates a table to record user-defined groups*/
CREATE TABLE groups
(
	groupID int auto_increment not null,
    groupname varchar(32) not null,
    groupdesc text,
    PRIMARY KEY (groupID)
);

/*Creates a table to record who belongs to each group*/
CREATE TABLE placements
(
	placeID int auto_increment not null,
    userID int not null,
    groupID int not null,
    PRIMARY KEY (placeID),
    FOREIGN KEY (userID) REFERENCES users(userID),
    FOREIGN KEY (groupID) REFERENCES groups(groupID)
);

/*Creates a table to store chat messages to groups*/
CREATE TABLE messages
(
	msgID int auto_increment not null,
    msgsubject varchar(32),
    msgcontent text not null,
    msgsender int not null,
    msgdest int not null,
    timesent timestamp not null,
    PRIMARY KEY (msgID),
    FOREIGN KEY (msgsender) REFERENCES users(userID),
    FOREIGN KEY (msgdest) REFERENCES conversations(conversationID)
);

CREATE TABLE conversations
(
	conversationID int auto_increment not null,
	conversationName varchar(32),
	PRIMARY KEY (conversationID)
);

CREATE TABLE conversation_assignments
(
	conversationID int not null,
	memberID int not null,
	FOREIGN KEY (conversationID) REFERENCES conversations(conversationID),
	FOREIGN KEY (memberID) REFERENCES users(userID)
);

/*Creates a table to store message attachment locations*/
CREATE TABLE attachments
(
	attID int auto_increment not null,
    att_message int not null,
    att_location varchar(64) not null,
    att_name varchar(32) not null,
    att_data longtext,
    att_type tinyint,
    PRIMARY KEY (attID),
    FOREIGN KEY (att_message) REFERENCES messages(msgID)
);

/*Creates a table to store system variables*/
/*NOTE: Storing preferences in a database eliminates the need to implement file-parsing, offers greater interoperability between applications (PHP and JVM), and enforces data integrity*/
CREATE TABLE sysprefs
(
    varname varchar(64)not null,
    varvalue varchar(64),
    PRIMARY KEY (varname)
);

/*Creates a table to store user variables*/
/*NOTE: Storing preferences in a database eliminates the need to implement file-parsing, offers greater interoperability between applications (PHP and JVM), and enforces data integrity*/
CREATE TABLE userprefs
(
    varname varchar(64) not null,
    varuser int not null,
    varvalue varchar(64),
    PRIMARY KEY (varname, varuser),
    FOREIGN KEY (varuser) REFERENCES users(userID)
);
CREATE TABLE companydetails
(
    companyname varchar(64) not null,
    email varchar(64),
    phone varchar(11),
    PRIMARY KEY (companyname)
);

/*Statements for dropping tables*/
DROP TABLE users;
DROP TABLE jobs;
DROP TABLE qualifications;
DROP TABLE groups;
DROP TABLE placements;
DROP TABLE group_messages;
DROP TABLE direct_messages;
DROP TABLE attachments;
DROP TABLE sysprefs;
DROP TABLE userprefs;
