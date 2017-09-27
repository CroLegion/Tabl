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
		
	echo $companydetails->fetch_assoc()["companyname"];
	echo" </br>" ;
	$companydetails = get_company_details();
	echo "Email: ". $companydetails->fetch_assoc()["email"];
	echo "<br/>";
	$companydetails = get_company_details();
	echo "Phone: ". $companydetails->fetch_assoc()["phone"];
	echo "<br/>";
	$qualList = data_qual_List();
	
	while($curQual=$qualList->fetch_assoc()["qualname"])
	{
		$userByQualList = data_usersList();
		echo $curQual;
		echo "<br/>";
		
		while($curUser=$userByQualList->fetch_assoc())
		{
			echo $curUser["firstname"]." ".$curUser["lastname"];
			echo ", ";
		}
		echo "<br/>";
	}	

	/*
	$state = 0;	
	foreach($staff as $qualification)
	{
		foreach($qualification as $member)
		{
			echo $member;
			if($state=0)
			{
				$state=1;
				echo <br/>;
			}

		}
		$state=0;
		echo <br/>;
	}
*/

//Code below this is for reference in how page should look	
/*
<html>
	<header><title>View Group</title></header>
	<body>
		<h1>Company name</h1>
		<h2>Active Projects</h2>
		<ul>
			<li><a href="link to project details">Church</a></li>
			<li><a href="link to project details">Upgrade School</a></li>
			<li><a href="link to project details">Other queried elements</a></li>
		</ul>
		<h2>Current Staff</h2>
		<h3>Drivers</h3>
		<ul>
			<li>Tom Toyota</li>
			<li>Frank Ford</li>
			<li>Perry Pontiac</li>
			<li>John Jalopey</li>
			<li>Dan Dodge</li>
		</ul>
		<h3>Construction</h3>
		<ul>
			<li>Henry Hammer</li>
			<li>Frank Ford</li>
			<li>Perry Pontiac</li>
			<li>John Jalopey</li>
			<li>Dan Dodge</li>
		</ul>
		<h3>Electricians</h3>
		<ul>
			<li>Tom Toyota</li>
			<li>Frank Ford</li>
			<li>Perry Pontiac</li>
			<li>John Jalopey</li>
			<li>Dan Dodge</li>
		</ul>
	</body>
</html>
 */
?>
