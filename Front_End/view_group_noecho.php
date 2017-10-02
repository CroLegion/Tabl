<?php
	//Import Statments
	require 'database.php';
	require	'util.php';

	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";
	data_set($servername, $username, $password, $database);

	//Get company data
	$companydetails = get_company_details();
	$company_name = $companydetails->fetch_assoc()["companyname"];
	$company_email = $companydetails->fetch_assoc()["email"];
	$company_phone = $companydetails->fetch_assoc()["phone"];

	//Get project data
	$projects=get_roots();
	$numProj=$projects->num_rows;
	$projectlist = "";
	while($curProj = $projects->fetch_assoc()["jobname"])
	{
		$projectlist = $projectlist . $curProj;
		$numProj = $numProj - 1;
		($numProj > 0) ? $projectlist = $projectlist . ", " : $projectlist = $projectlist . "<br/>";
	}
	$projectlist = $projectlist . "<br/>";

	//Get qualification data
	$qualList = data_qual_List();
	$qualificationlist = "";
	while($curQual = $qualList->fetch_assoc()["qualname"])
	{
		$userByQualList = users_with_qualifications($curQual);
		if($userByQualList->num_rows > 0)
		{
			$qualificationlist = $qualificationlist . "<h3>" . $curQual . ":</h3>";	
			$rowsleft = $userByQualList->num_rows;
			while($curUser = $userByQualList->fetch_assoc())
			{
				$qualificationlist = $qualificationlist . $curUser["firstname"] . " " . $curUser["lastname"];
				$rowsleft--;
				if($rowsleft)
				{
					$qualificationlist = $qualificationlist . ", ";
				}
			}
			$qualificationlist = $qualificationlist . "<br/><br/>";
		}
	}

	//Build navbar pane in HTML
	$navbar = <<< HTML
		//TODO
HTML;

	//Build content pane in HTML
	$content = <<< HTML
		<h2> $company_name </h2>
			Email: $company_email <br>
			Phone: $company_phone <br>
		<br>
		<h3>Projects: </h3>
		<br>
		$projectlist;
		<br>
		$qualificationlist;
HTML;

	//Insert page elements into frame
	require 'frame.php';

	//Echo page to client browsers
	echo $frame;
?>
