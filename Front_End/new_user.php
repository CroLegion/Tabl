<?php
  //Import required PHP files
  require 'database.php';
  require 'util.php';

// define variables and set to empty values
$first_name_err = $last_name_err = $username_err = "";
$first_name = $last_name = $username = $email = $phone = "";
$success = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  if (empty($_POST["first_name"])) {
    $first_name_err = "First Name is required";
  } else {
    $first_name = user_input($_POST["first_name"]);
  }

  if (empty($_POST["last_name"])) {
    $last_name_err = "Last Name is required";
  } else {
    $last_name = user_input($_POST["last_name"]);
  }

  if (empty($_POST["username"])) {
    $username_err = "Username is required";
  } else {
    $username = user_input($_POST["username"]);
  }
  $email = user_input($_POST["email"]);
  $phone = user_input($_POST["phone"]);
  $password = user_input($_POST["password"]);
}

function user_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}



// Send information to server and then return success if saved to SQL server
$success data_add_new_user()

if ($success == 1)
{
  echo "<p>Added user to database.</p>";
} else {
  echo "<p>User not added to database.</p>";
}

?>