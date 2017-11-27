<?php

	//Get active conversations
	$id = $_SESSION['userID'];
	$convos = get_convos($id);

	$list = "";
	foreach($convos as $item)
	{
		// $list = $list . $item['conversationName'] . "<br>";
		$name = $item['conversationName'];
		$cid = $item['conversationID'];
		$list = $list . <<< HTML
			<form action='index.php' method='post'>
				<input type='hidden' name='action' value='view_convo'>
				<input type='hidden' name='convo_id' value='$cid'>
				<input type='submit' value='$name' class='navbutton'>
			</form>
HTML;
	}

	//Get messages for current conversation
	$thread_id = 0;
	if(isset($_SESSION['current_convo']))
	{
		$thread_id = $_SESSION['current_convo'];
	}

	$thread = <<< HTML
HTML;

	if($thread_id != 0)
	{
		$messages = get_thread($thread_id);
		foreach($messages as $msg)
		{

			$content = $msg['msgcontent'];
			$sender = $msg['firstname'] . " " . $msg['lastname'];
			$time = $msg['timesent'];
			$thread = $thread . <<< HTML
				<br>
				<b> $sender </b> - <i> $time </i>
				<p> $content </p>
				<br>
				<hr>
HTML;
		}
	}

	$title = "Messages";

	$content =  <<< HTML
		<!DOCTYPE html>
		<html>
			<head>
				<title>Messages</title>
				<link rel='stylesheet' type='text/css' href='styles.css'>
			</head>
			<body>
				<div class='conversations'>
					<h3> Conversations : $id</h3>
					<br>
					$list
				</div>

				<div class='messages'>
					<h3> Beginning of thread </h3>
					$thread
				</div>

				<div class='messagebox'>
					<form action='index.php' method='post'>
						<input type='hidden' name='action' value='send_message'>
						<input type='hidden' name='msg_sender' value='$id'>
						<input type='hidden' name='msg_dest' value='$thread_id'>
						<textarea rows='4' cols='150' class='messageinput' name='msg_content'></textarea>
						<br>
						<input type='submit' class='actionbutton' value='Send'>
					</form>
				</div>
				
				
			</body>
		</html>
HTML;

	require 'navbar.php';
	require 'frame.php';
	echo $frame;
?>
