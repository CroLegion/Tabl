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

	$tasks="<ul>";
	$taskList=get_tasks($node["jobID"]);

	while($curtask=$taskList->fetch_assoc())
	{
		$tasks=$tasks."<li>".$curtask["taskname"]." - ".$curtask["taskdesc"]."</li>";
	}
	$tasks=$tasks."</ul>";

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
							</br></br>
							<label>Tasks:</label></br>
							{$tasks}
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
		$workerSQL=get_workers_on_id($node['jobID']);
		$workerArray=[];
		$index=0;
		while($curWorker=$workerSQL->fetch_assoc()['userID'])
		{
			$workerArray[$index]=$curWorker;
			$index=$index+1;
		}

		$assignedQual=get_job_reqs($node['jobID']);
		while($curQual=$assignedQual->fetch_assoc()["qualID"])
		{
			$workerBoxes=$workerBoxes."<label>".get_qual_by_id($curQual)->fetch_assoc()["qualname"].":</label>";
			$usersWithQual=get_active_users_by_qualid($curQual);
			$workerBoxes=$workerBoxes."<br>";
			while($curUser=$usersWithQual->fetch_assoc())
			{
				$workerBoxes=$workerBoxes."<Input type=\"checkbox\" name=\"users[]\" value=\"".$curUser["userID"]."\"";
				if(in_array($curUser['userID'],$workerArray))
				{
					$workerBoxes=$workerBoxes."checked";
				}
				

				$workerBoxes=$workerBoxes.">".$curUser["firstname"]." ".$curUser["lastname"]."<br>";	
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
							
							<label>Tasks:</label></br>
							$tasks
							</br></br>


							<label>Qualifications:</label>
							<br>
							{$qualBoxes}
							</br></br>
							
							{$workerBoxes}
						

							
							<input type='hidden' name='nodeID' value="{$node['jobID']}">
							<input type='hidden' name='action' value='edit_job'>
							<input type='submit' value='Save'>
						</fieldset>
					</form>
				</body>
			</html>
	


HTML;
	}
	$title=$node['jobname']." Details";
	require 'frame.php';
	echo $frame;
	
