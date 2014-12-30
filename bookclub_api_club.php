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

$sql="INSERT INTO lending (bookname, username, lenddate)
VALUES
('$_POST[bookname]','$_POST[username]',curdate())";
 
if (!mysql_query($sql,$dbhandle))
  {
  die('Error: ' . mysql_error());
  }
echo "1 record added";
 
mysql_close($dbhandle)
?>
