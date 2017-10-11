<?php
	$message;
	($_SESSION['attempts'] > 0) ? $message = "Please try again." : $message = '';

	echo <<< HTML
		<!DOCTYPE html>
		<html>
			<head>
				<title>Login</title>
				<script src="processing.js"></script>
				<link rel='stylesheet' type='text/css' href='styles.css'>
			</head>
			<body>
				<canvas data-processing-sources="background.pde" class='background'></canvas>
				<div class='overlay'>
					<h1 class='title'> Tabl </h1>
					<form action='index.php' method='post' class='login'>
						<p> $message </p>
						<label class='loginlabel'>Username:</label>
						<br>
						<input type='text' name='login_user' class='loginfield'>
						<br><br>
						<label class='loginlabel'>Password:</label>
						<br>
						<input type='password' name='login_pass' class='loginfield'>
						<br><br>
						<input type='submit' value='Sign In' class='loginbutton'>
					</form>
					<footer>
						<div class="footerholder">
   							<div class="footer">
								<p> Scalable Workforce Management Solution </p>
								<p> Devin Johnson &#183; Jacob Stilwell &#183; Vincent Waters &#183; Mason Wray</p>
								<p><b> Tabl &#183; Version a0.2 &#183; 2017 </b></p>
							</div>
						</div>
					</footer>
				</div>
			</body>
		</html>
HTML;
?>
