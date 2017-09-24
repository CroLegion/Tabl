<?php
	//Import Statments
	require 'database.php'
	require	'util.php'

	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";
	data_set($servername, $username, $password, $database);

	$companydetails = get_company_details();
	$staff=users_by_qualifications();

	echo $companydetails;
	
	foreach($staff as $qualification)
	{
		foreach($qualification as $member)
		{
			echo $member;
		}
		echo <br/>;
	}

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
