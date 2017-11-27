<?php

	//This file provides a 'connection' class for interacting with the MySQL database. This class should abstract database interactions to the level of 'getters' and 'setters'.
	//By: Mason Wray - 09/20/2017

	//Returns the properly hashed digest of the given username, password, and salt.
	function hash_login($username, $password, $salt)
	{
		return $password;
		//return hash("sha256", $username . $password . $salt, FALSE);
	}

	//Sets the global database connection parameters. Use this to specify which database you want to connect to at the beginning of a script.
	function data_set($servername, $username, $password, $database)
	{
		$GLOBALS['servername'] = $servername;
		$GLOBALS['usernamea'] = $username;
		$GLOBALS['password'] = $password;
		$GLOBALS['database'] = $database;
	}

	//Opens a new database connection to the globally specified database and returns a MySQLI object.
	function data_open()
	{
		return new mysqli($GLOBALS['servername'], $GLOBALS['usernamea'], $GLOBALS['password'], $GLOBALS['database']);
	}

	//Performs a query on the users table, and returns the userID and usertype of the user if username and password are valid, -1 if they are not.
	function data_validUser($username, $password)
	{
		$hash = hash_login($username, $password, "");
		$stmt = <<< SQL
				SELECT userID, usertype
				FROM users
				WHERE username = '{$username}'
				AND passhash = '{$hash}';
SQL;
		$conn = data_open();
		$result = $conn->query($stmt);
		$conn->close();

		if($result->num_rows == 1)
		{
			return $result->fetch_assoc();
		}
		else
		{
			return -1;
		}
	}

		//Performs a query on the users table and returns all users first and last names.
	function data_usersList()
	{
		$sql = "SELECT firstname, lastname, userID FROM users";
		$conn = data_open();
		$result = $conn->query($sql);
		$conn->close();

		if($result->num_rows >= 1)
		{
			return $result;
		}
		else
		{
			return -1;
		}
	}

	//Selects a user by their id number
	function data_specificUser($userID)
	{
		$sql = "SELECT * FROM users WHERE userID={$userID}";
		$conn = data_open();
		$result = $conn->query($sql);
		$conn->close();
		return $result;
	}

	function data_usernameExists($username)
	{
		$sql = "SELECT COUNT(*) FROM users WHERE username = '{$username}'";
		$conn = data_open();
		$result = $conn->query($sql)->fetch_row()[0];
		$conn->close();
		if($result > 0)
		{
			return TRUE;
		}
		else
		{
			return FALSE;
		}
	}

	//Performs a query on the qualifications table and returns all the qualifications.
	//added for job creation and project creation
	function data_qual_List()
	{
		$sql = "SELECT qualname, qualID FROM qualifications";
		$conn = data_open();
		$result = $conn->query($sql);
		$conn->close();

		if($result->num_rows >= 1)
		{
			return $result;
		}
		else
		{
			return -1;
		}
	}
	
	//Adds a new user to the database
	function data_add_new_user($usertype, $firstname, $lastname, $username, $email, $phone, $password)
	{
		$pass = hash_login($username, $password, "");
		$userID = get_and_create_new_user_id();
		$sql = "INSERT INTO users VALUES('{$userID}', '{$username}', '{$usertype}', '{$firstname}', '{$lastname}', '{$email}', '{$phone}', '{$pass}')";
		$conn = data_open();
		$conn->query($sql);
		$conn->close();
	}

	function get_and_create_new_user_id()
	{
		$conn = data_open();
		$result = 1;

		while ($result != 0) {
			$val = rand(0, PHP_INT_MAX);
			$sql = "SELECT COUNT(userID) FROM users WHERE userID = {$val}";
			$result = $conn->query($sql)->fetch_row()[0];
		}
		$conn->close();
		return $val;
	}

	
	//function that returns users with a qualification
	function users_with_qualifications($qualification)
	{
	
		$sql = "SELECT firstname, lastname
				FROM ((db309amc2.users
				INNER join db309amc2.qualification_assignments on users.userID=qualification_assignments.userID)
				INNER join db309amc2.qualifications on qualification_assignments.qualID=qualifications.qualID)
				WHERE db309amc2.qualifications.qualname= (\"$qualification\")" ;
		$conn = data_open();
		$result=$conn->query($sql);
		$conn->close();
	
		return $result;
	
	}


	//Function that gets qualifications for a user by id
	function qualifications_for_user($userID)
	{
		$sql = "select qualID from qualification_assignments where userID={$userID};";
		$conn=data_open();
		$result=$conn->query($sql);
		$conn->close();
		return $result;
	}




	//function that gets company details for view group
	function get_company_details()
	{
		$sql = "SELECT * FROM companydetails";
		$conn = data_open();
		$result = $conn->query($sql);
		$conn->close();

		return $result;

	}

	//Get project root from jobs table
	function get_root_of_tree($project_name)
	{
		$sql = "Select jobID, jobname, jobdesc from jobs where jobs.jobname=\"$project_name\";";
		
		$conn = data_open();
		$result = $conn->query($sql);
		$conn->close();
		//echo $result->num_rows."<br/>";
		return $result;
	}

	//Get children of node by jobID
	function get_children($jobID)
	{	
		$sql="Select jobID, jobname, jobdesc from jobs where jobs.parentID=\"$jobID\";";
		
		$conn = data_open();
		$result = $conn->query($sql);
		$conn->close();
		//echo $result->num_rows."<br/>";
		return $result;
	}
	//Get all root nodes in database
	function get_roots()
	{
		$sql="Select jobID, jobname, jobdesc from jobs where jobs.jobtype=1;";
		
		$conn = data_open();
		$result = $conn->query($sql);
		$conn->close();
		//echo $result->num_rows."<br/>";
		return $result;
	}
	
	//Get task details for task details page
	function get_task_by_id($taskID)
	{
		$sql="Select * from tasks where taskID ='{$taskID}';";
		
		$conn = data_open();
		$result = $conn->query($sql);
		$conn->close();
		//echo $result->num_rows."<br/>";
		return $result;
	}
	
	//Insert new project ino database
	function insert_project($name,$desc)
	{
		$minQ="SELECT max(jobID) from db309amc2.jobs;";
		$conn = data_open();
		$lowest_avail=$conn->query($minQ);
		$lowest_avail=$lowest_avail->fetch_assoc()["max(jobID)"]+1;
		$sql="INSERT INTO jobs VALUES(".$lowest_avail.",\"".$name."\",1,\"".$desc."\",NULL);";
		$conn->query($sql);
		$conn->close();
	}
	
	//Insert new job ino database
	function insert_job($name,$desc,$parent)
	{
		$minQ="SELECT max(jobID) from db309amc2.jobs;";
		$conn = data_open();
		$lowest_avail=$conn->query($minQ);
		$lowest_avail=$lowest_avail->fetch_assoc()["max(jobID)"]+1;
		$sql="INSERT INTO jobs VALUES(".$lowest_avail.",\"".$name."\",2,\"".$desc."\",\"".$parent."\");";
		$conn->query($sql);
		$conn->close();
	}
	
	//Temporary function for create project
	function all_jobs()
	{

		$sql="Select jobname from jobs;";
		
		$conn = data_open();
		$result = $conn->query($sql);
		$conn->close();
		return $result;
	}

	//Modify a user in the database
	function update_user($newID,$username,$usertype,$firstname,$lastname,$email,$phone,$passhash,$oldID,$active)
	{
		$sql="UPDATE db309amc2.users SET userID={$newID}, username =\"{$username}\", usertype=\"{$usertype}\", firstname=\"{$firstname}\", lastname=\"{$lastname}\", email=\"{$email}\", phone=\"{$phone}\",passhash=\"{$passhash}\",isActive=\"{$active}\" WHERE userID={$oldID};"; 
		
		$conn = data_open();
		$conn->query($sql);
		$conn->close();

	}

	//Removes all qualifitcations for auser, then adds in new ones
	function update_user_quals($userID,$qualList)
	{
		$sql="Delete from qualification_assignments where userID={$userID};";
		$conn=data_open();
		$conn->query($sql);

		foreach($qualList as $qual)
		{
			$sqlb="insert into qualification_assignments values({$qual},{$userID});";
			$conn->query($sqlb);
		}	
		$conn->close();
	}

	//Adds qualifications to job
	function add_job_qual($jobName,$qualList)
	{
		$conn=data_open();
		$getID="Select jobID from jobs where jobname=\"{$jobName}\";";
		$jobID=$conn->query($getID)->fetch_assoc()['jobID'];
		echo $jobID;
		foreach($qualList as $qual)
		{
		
			$sql="insert into job_requirements values({$jobID},{$qual});";
			$conn->query($sql);
		}
		$conn->close();
	}

	//Gets job related info by ID
	function get_job($jobID)
	{
		$conn=data_open();
		$sql="Select * from jobs where jobID={$jobID}";
		$result=$conn->query($sql);
		$conn->close();
		return $result;
	}

	//Gets qualification requirements for a job
	function get_job_reqs($id)
	{
		$sql="select * from job_requirements join qualifications on job_requirements.qualID=qualifications.qualID and job_requirements.jobID={$id};";
		$conn=data_open();
		$result=$conn->query($sql);
		$conn->close();
		return $result;
	}

	//Gets users with quallification by id
	function get_active_users_by_qualid($id)
	{
		$sql="select * from qualification_assignments join users on qualification_assignments.userID=users.userID and qualID={$id} and isActive=1;";
		$conn=data_open();
		$result=$conn->query($sql);
		$conn->close();
		return $result;
	}

	//Gets qualification information from its ID
	function get_qual_by_id($id)
	{
		$sql="select * from qualifications where qualID={$id};";
		$conn=data_open();
		$result=$conn->query($sql);
		$conn->close();
		return $result;
	}

	//Removes all qualification assignemnts, then assigns new ones
	function update_job_reqs($jobID,$qualList)
	{
		$sql="Delete from job_requirements where jobID={$jobID};";
		$conn=data_open();
		$conn->query($sql);

		foreach($qualList as $qual)
		{
			$sqlb="insert into job_requirements values({$jobID},{$qual});";
			$conn->query($sqlb);
		}	
		$conn->close();
	}

	//Gets a list of users that are on a job
	function get_workers_on_id($jobID)
	{
		$sql="Select * from job_assignments where jobID={$jobID};";

		$conn=data_open();
		$result=$conn->query($sql);
		$conn->close();
		return $result;

	}	

	//Removes all job assignemnts, then assigns new ones
	function update_job_assignments($jobID,$userList)
	{
		$sql="Delete from job_assignments where jobID={$jobID};";
		$conn=data_open();
		$conn->query($sql);
		
		foreach($userList as $user)
		{
			$sqlb="insert into job_assignments values({$jobID},{$user});";
			$conn->query($sqlb);
		}	
		$conn->close();
	}

	//Gets all conversations that a given userID is a member of
	function get_convos($userID)
	{
		$sql = "SELECT * FROM conversations JOIN conversation_assignments ON conversations.conversationID = conversation_assignments.conversationID WHERE memberID = $userID";
		$conn=data_open();
		$result = $conn->query($sql);
		$conn->close();
		return $result;
	}

	//Gets all messages in the specified conversation thread
	function get_thread($conversationID)
	{
		// $sql = "SELECT * FROM messages WHERE msgdest = $conversationID";
		$sql = "SELECT * FROM messages JOIN users ON messages.msgsender = users.userID WHERE msgdest = $conversationID";
		$conn=data_open();
		$result = $conn->query($sql);
		$conn->close();
		return $result;
	}

	//Sends a message to the specified message thread
	function add_message($sender, $destination, $content)
	{
		$sql = "INSERT INTO messages (msgsender, msgdest, msgcontent) VALUES ($sender, $destination, '$content')";
		$conn=data_open();
		$result = $conn->query($sql);
		$conn->close();
	}

	//Gets basic ticket information
	function get_tickets()
	{
		$sql = "SELECT * from tickets";
		$conn=data_open();
		$result = $conn->query($sql);
		$conn->close();
		return $result;
	}
		
	//Gets info for one ticket by ID
	function get_ticket_by_ID($id)
	{
		$sql = "Select * from tickets where ticketID=$id";
		$conn=data_open();
		$result = $conn->query($sql);
		$conn->close();
		return $result;
	}
	
?>
