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
		<footer>
			<div class="footerholder">
   				<div class="footer">
					<p> Scalable Workforce Management Solution </p>
					<p> Devin Johnson &#183; Jacob Stilwell &#183; Vincent Waters &#183; Mason Wray</p>
					<p><b> Tabl &#183; Version a0.2 &#183; 2017 </b></p>
				</div>
			</div>
		</footer>
	</html>
HTML;
?>
