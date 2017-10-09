<?php
	$frame = <<< HTML
	<html>
		<head>
			<title>Tabl</title>
			<link rel='stylesheet' type='text/css' href='styles.css'>
		</head>
		<body onload='initialize();'>
			<div class='header'>
				<h1> $title </h1>
				<form action='index.php' method='post'>
					<input type='hidden' name='action' value='notifications'>
					<input type='submit' value='Notifications' class='topButton'>
				</form>
				<form action='index.php' method='post'>
					<input type='hidden' name='action' value='settings'>
					<input type='submit' value='Settings' class='topButton'>
				</form>
			</div>
			<div class='page'>
				<div class='navbar'> $navbar </div>
				<div class='content' id='contentpane'> $content </div>
				<div class='clear'><!-- --></div>
			</div>
		</body>
	</html>
HTML;
?>
