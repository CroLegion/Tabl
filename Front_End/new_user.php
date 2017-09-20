<!DOCTYPE HTML>  
<html>
<head>
 <style>
 .error {color: #FF0000;}
</style>
</head>
<body>  

<?php
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
}

function user_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}
?>

<h2>Enter User Information</h2>
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">  
  First Name: <input type="text" name="first_name">
  <br><br>
  Last Name: <input type="text" name="last_name">
  <br><br>
  Username: <input type="text" name="username">
  <br><br>
  Email Address: <input type="text" name="email">
  <br><br>
  Phone Number: <input type="text" name="phone">
  <br><br>
  <input type="submit" name="submit" value="Submit">  
</form>

<?php
// validate user information, send information to server and then return success if saved to SQL server
if ($first_name != "Devin") {
  $success = "New User created";
} elseif ($first_name == "John") {
  $success = "User info did not save";
}


echo $success;
?>

</body>
</html>