<?php
	//Import required PHP files
	require 'database.php';
	require 'util.php';
	
	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";
	data_set($servername, $username, $password, $database);
	
	$node=get_job($_POST['node']);
	$node=$node->fetch_assoc();
	require 'navbar.php';
	if($_SESSION['usertype'] == 0)
	{
		$content =<<<HTML
			<!DOCTYPE html>
			<html>
				<head>
					<title>Details</title>
					<link rel='stylesheet' type='text/css' href='styles.css'>
				</head>
				<body>
					<form action='index.php' method='post' id='edit_task'>
						<fieldset>
							<legend>Task Details</legend>
							<label>Job Name:</label></br>
							{$node['jobname']}
							</br></br>	
							<label>Job Description:</label></br>
							{$node['jobdesc']}
						</fieldset>
					</form>
				</body>
			</html>
	


HTML;
	}
	else
	{

		$allQual=data_qual_List();
		$assignedQual=get_job_reqs($node['jobID']);
		$qualBoxes="";
		$even=0;	
		$qualArray=[];
		$index=0;
		while($row=$assignedQual->fetch_assoc()["qualID"])
		{
			$qualArray[$index]=$row;
			$index=$index+1;
		}

		while($curQual=$allQual->fetch_assoc())
		{
			$qualBoxes=$qualBoxes."<input type=\"checkbox\" name = \"quals[]\" value=\"".$curQual["qualID"]."\"";

			if(in_array($curQual["qualID"], $qualArray))
			{
				$qualBoxes=$qualBoxes."checked";
			}	
			
			$qualBoxes=$qualBoxes."	> ".$curQual["qualname"]."&#09;&#09;";
			if($even==2){$qualBoxes=$qualBoxes."<br>";$even=0;}
			else{$even=$even+1;}
		}

		$workerBoxes="";
		$assignedQual=get_job_reqs($node['jobID']);
		while($curQual=$assignedQual->fetch_assoc()["qualID"])
		{
			$workerBoxes=$workerBoxes."<label>".get_qual_by_id($curQual)->fetch_assoc()["qualname"]."</label>";
			$usersWithQual=get_active_users_by_qualid($curQual);
			$workerBoxes=$workerBoxes."<br>";
			while($curUser=$usersWithQual->fetch_assoc())
			{
				$workerBoxes=$workerBoxes."<Input type=\"checkbox\" name=\"users[]\" value=\"".$curUser["userID"]."\">";

				//Add in code for checking if assigned, also add in database code for getting
				//list of assigned workers, as well as code for removing and adding in workers based on one
				//that are checked here.  

				$workerBoxes=$workerBoxes.$curUser["firstname"]." ".$curUser["lastname"]."<br>";	
			}
			$workerBoxes=$workerBoxes."<br><br>";
	
		}

		$content =<<<HTML
			<!DOCTYPE html>
			<html>
				<head>
					<title>Details</title>
					<link rel='stylesheet' type='text/css' href='styles.css'>
				</head>
				<body>
					<form action='index.php' method='post' id='edit_task'>
						<fieldset>
							<legend>Task Details</legend>
							<label>Job Name:</label></br>
							<input type='text' name='job_name' value="{$node['jobname']}">
							</br></br>	
							<label>Job Description:</label></br>
							<textarea rows="4" cols="50" name="job_desc" form="edit_task">{$node['jobdesc']}</textarea>
							</br></br>
							<label>Qualifications:</label>
							{$qualBoxes}
							</br></br>
							
							{$workerBoxes}
							</br></br>

							</br></br>
							<input type='hidden' name='nodeID' value="{$node['jobID']}">
							<input type='hidden' name='action' value='edit_job'>
							<input type='submit' value='Save'>
						</fieldset>
					</form>
				</body>
			</html>
	


HTML;
	}
	$title="moo";
	require 'frame.php';
	echo $frame;
	
