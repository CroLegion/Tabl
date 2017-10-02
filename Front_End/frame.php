<?php
	$frame = <<< HTML 
	<html>
		<head>
			<title>Tabl</title>
			<script src='script.js'></script>
			<link rel="stylesheet" type="text/css" href="styles.css">
		</head>
		<body onload='initialize();'>
			<div class='header'>
				<h1>Page Header</h1>
				<input class='topButton' type='button' value='Notifications' onclick='clickNotifications();'>
				<input class='topButton' type='button' value='Settings' onclick='clickSettings();'>
			</div>
			<div class='page'>
				<div class='navbar'> {$navbar} </div>
				<div class='content' id='contentpane'> {$content} </div>
				<div class='clear'><!-- --></div>
			</div>
		</body>
	</html>
HTML;
?>
