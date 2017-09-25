<?php

	//required imports
	require 'database.php';
	require 'util.php';
	
	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";
	data_set($servername, $username, $password, $database);

	$root = get_root_node();
	$depth = get_tree_depth();
	$children = get_children_in_order();

/*
<html>
	<head><link rel="stylesheet" href="../styles.css"></head>
	<header><title>project name from db</title></header>
	<div class="tree">
		<ul>
			<li>
				<a href="link">master name</a>
				<ul>
					<li>
						<a href="link">left child 1?</a>

					</li>
					<li>
						<a href="link">right child 1?</a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</html>
*/
?>
