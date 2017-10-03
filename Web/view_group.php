<head><link rel="stylesheet" href="styles.css"></head>
<div class ="view_group">
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

	$companydetails = get_company_details();
		
	echo "<h2>".$companydetails->fetch_assoc()["companyname"]."</h2>";

	$companydetails = get_company_details();
	echo "Email: ". $companydetails->fetch_assoc()["email"];
	echo "<br/>";
	$companydetails = get_company_details();
	echo "Phone:". $companydetails->fetch_assoc()["phone"];
	echo "<br/>";
	
	echo "<h3>Projects:<br/></h3>";
	$projects=get_roots();
	$numProj=$projects->num_rows;
	while($curProj=$projects->fetch_assoc()["jobname"])
	{
		echo $curProj;
		$numProj=$numProj-1;
		if($numProj>0){echo", ";}
		else{echo "<br/>";}
	}	
	echo "<br/>";
	
	$qualList = data_qual_List();
	
	while($curQual=$qualList->fetch_assoc()["qualname"])
	{
		$userByQualList = users_with_qualifications($curQual);
		if($userByQualList->num_rows>0)
		{
			echo "<h3>".$curQual.":</h3>";	
		$rowsleft = $userByQualList->num_rows;
		while($curUser=$userByQualList->fetch_assoc())
		{
			echo $curUser["firstname"]." ".$curUser["lastname"];
			$rowsleft--;
			if($rowsleft){echo ", ";}
		}
		echo "<br/><br/>";
		}
	}	
?>
