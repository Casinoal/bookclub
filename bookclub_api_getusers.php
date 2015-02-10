<?php
$username = "alexwhya_book";
$password = "alex123";
$hostname = "alexwhyatt.com"; 

//connection to the database
$dbhandle = mysql_connect($hostname, $username, $password) 
 or die("Unable to connect to MySQL");


//select a database to work with
$selected = mysql_select_db("alexwhya_bookclub",$dbhandle) 
  or die("Could not select examples");

  
  //$myname = ($_POST['username']); // $foo = bar
//$clubcode = ($_POST['clubcode']); // $foo = bar
//$myname = "Alex";
$clubcode = "4321";

//execute the SQL query and return records
$sql = mysql_query("SELECT DISTINCT username FROM users WHERE clubcode='".($clubcode)."'");

//fetch the data from the database 
 
$values = array();
while ($row = mysql_fetch_array($sql)) {
    $values[] = $row['username'];
}

echo implode(',', $values);


  mysql_close();


?>