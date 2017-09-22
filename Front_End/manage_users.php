<?php
	require 'database.php';
	require 'util.php';

	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";
	data_set($servername, $username, $password, $database);
	$result = data_usersList();

	//Build list of users
	while($user = $result->fetch_assoc())
	{
		$user_rows = $user_rows . <<< HTML
			<tr>
				<td><a href="#" class="w3-bar-item w3-button">{$user["firstname"]}</a></td>
				<td><a href="#" class="w3-bar-item w3-button">{$user["lastname"]}</a></td>
			<tr>
HTML;
	}


			

	$users_page = <<< HTML
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<center>
		<body>
		<!-- Sidebar -->
		<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:25%">
  			<h3 class="w3-bar-item">Users</h3>
  			<a>$user_rows</a>
			</div>

		<div class="w3-container">
		<h1>Buddy Guy</h1>
		<form>
		<table width="99%">
			<tbody>
			<tr>
				<td colspan="2"></td>
			</tr>
				<table cellspacing="0" cellpadding="2">
					<tbody>
						
						<tr>
							<td>
								<p>First Name:<br><br>Last Name:<br><br>Username<br><br>Email Address:<br><br>Phone Number:</p>
							</td>
							<td>
								<p>     </p>
							</td>
							<td style="padding-left:50px;padding-top:20px">
								
									<input  id="firstname" type="text" name="firstname" value="Buddy"><br><br>
									<input  id="lastname" type="text" name="lastname" value="Guy"><br><br>
									<input  id="username" type="text" name="username" value="buddy_guy"><br><br>
									<input  id="email" type="text" name="email" value="guy@email.com"><br><br>
									<input  id="phone" type="text" name="Phone" value="123-454-4567"><br><br>
									
								
							</td>
						</tr>
					</tbody>
				</table> 
			</tbody>
		</table>
		<input  type="submit" value="SAVE"><br>
		</form>

		<hr>

		<h2>Permissons</h2>

		<table width="99%">
			<tbody>
			<tr>
				<td colspan="2"></td>
			</tr>
				<table cellspacing="0" cellpadding="2">
					<tbody>
						<tr>
							<td><b>Available</b></td>
							<td></td>
							<td><b><a id="assigned">Assigned</a></b></td>
						</tr>
						<tr>
							<td>
								<select name="unassigned_permissions" id="unassigned_permissions" size="16" multiple="multiple" style="width:180px">
									<option value="716571">Electrical</option>
									<option value="663392">Plumbing</option>
									<option value="664069">Welding</option>
									<option value="445926">Brick Laying</option>
								</select>
							</td>
							<td>
								<input type="submit" name="permission_assign" value=" -> " onclick="" id="permission_assign" style="width:60px;">
								<br>
								<input type="submit" name="permisson_remove" value=" <- " onclick="" id="permisson_remove" style="width:60px;">
							</td>
							<td>
								<select name="current_permissons" id="current_permissons" size="16" multiple="multiple" style="width:180px">
									<option value="421560">Stone Work</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table> 
			</tbody>
		</table>

		<hr>

		<button id="button" type="button">DELETE USER</button>
		<button id="button" type="button">CHANGE PASSWORD</button>

		</center>

		</body>
		</div>
HTML;
	echo $users_page;


?>