<?php

	require 'test.php';

	$page = <<<HTML
	<html>
		<body>
			<p>This is some text from the primary page</p>
			<p> $test </p>
		</body>
	</html>
HTML;

	echo $page;
?>