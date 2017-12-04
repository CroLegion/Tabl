<?php
	//Acquire data
	session_start();

	//Create new zip archive in 'reports' folder
	$zip = new ZipArchive();

	$filename = "./reports/" . $_SESSION['project'] . " - " . session_id() . ".zip";
	if ($zip->open($filename, ZipArchive::CREATE)!==TRUE) 
	{
    	exit("cannot open <$filename>\n");
	}

	//Add the HTML report to the ZIP file
	$zip->addFromString("Project Report.htm", $_SESSION['report']);
	$zip->close();

	//Send the zip file for download      
	header('Pragma: public');
	header('Expires: 0');
	header('Cache-Control: must-revalidate, post-check=0, pre-check=0');
	header('Cache-Control: private', false); // required for certain browsers 
	header('Content-Type: application/pdf');

	header('Content-Disposition: attachment; filename="'. basename($filename) . '";');
	header('Content-Transfer-Encoding: binary');
	header('Content-Length: ' . filesize($filename));

	readfile($filename);

	exit;
?>
