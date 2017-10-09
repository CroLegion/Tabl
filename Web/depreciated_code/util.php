<?php
	//This file contains a set of general utility methods
	//By: Mason Wray - 09/20/2017

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