<?php
$username = "alexwhya_book";
$password = "alex123";
$hostname = "alexwhyatt.com"; 

//connection to the database
$dbhandle = mysql_connect($hostname, $username, $password) 
 or die("Unable to connect to MySQL");
echo "Connected to MySQL<br>";

//select a database to work with
$selected = mysql_select_db("alexwhya_bookclub",$dbhandle) 
  or die("Could not select examples");

$myname = ($_POST['username']); // $foo = bar
$clubcode = ($_POST['clubcode']); // $foo = bar
$email = ($_POST['email']); // $foo = bar
//$myname = "Jo";
//$clubcode = "4321";
//$email = "test@test.com";
  
$sql = "UPDATE users SET email='$email' WHERE clubcode='$clubcode' AND username='$myname'";

 
if (!mysql_query($sql,$dbhandle))
  {
  die('Error: ' . mysql_error());
  }
echo "1 record added";
 
mysql_close($dbhandle)
?>
