<!DOCTYPE HTML>  
<html>
<head>
</head>
<body>  

<?php
// define variables and set to empty values
$first_name = $last_name = $username = $email = $phone = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  $first_name = test_input($_POST["first_name"]);
  $last_name = test_input($_POST["last_name"]);
  $username = test_input($_POST["username"]);
  $email = test_input($_POST["email"]);
  $phone = test_input($_POST["phone"]);
}

function test_input($data) {
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
  Phone Number: <input type="text" name="pone">
  <br><br>
  <input type="submit" name="submit" value="Submit">  
</form>

<?php
// send information to server
?>

</body>
</html>