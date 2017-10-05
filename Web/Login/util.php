<?php
	//This file contains a set of general utility methods
	//By: Mason Wray - 09/20/2017

	function processLogin($username, $password)
	{
		$user = data_validUser($username, $password);
		if($user == -1)
		{
			$_SESSION['attempts'] = $_SESSION['attempts'] + 1;
			$_SESSION['auth'] = FALSE;
			return FALSE;
		}
		else
		{
			$_SESSION['userID'] = $user['userID'];
			$_SESSION['usertype'] = $user['usertype'];
			$_SESSION['page'] = 'default';
			$_SESSION['auth'] = TRUE;
			return TRUE;
		}
	}

	function logout()
	{
		
	}
	
	//Loads an HTML document from the specified filename, and returns the file's contents as HTML.
	function loadHTML($filename)
	{
		$doc = new DOMDocument();
		$doc->loadHTMLFile($filename);
		return $doc->saveHTML();
	}

	//Loads an HTML document from the specified filename, and returns the file as a DOMDocument.
	function loadHTMLasDOM($filename)
	{
		$doc = new DOMDocument();
		$doc->loadHTMLFile($filename);
		return $doc;
	}
?>