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
  
$result = mysql_query("SELECT * FROM lending");
//fetch the data from the database 
$entries = 'Entries: '; 

while ($row = mysql_fetch_array($result)) {
    $entries .= $row['bookname'] . "\r\n";
}
//while ($row = mysql_fetch_array($result)) {
//    $entries .= $row['bookname'] . ', '"\r\n";
//}

 mysql_close($dbhandle);

// In case any of our lines are larger than 70 characters, we should use wordwrap()
//$entries = wordwrap($entries, 70, "\r\n");

// Send
mail('alexwhyatt@gmail.com', 'My Subject', $entries);
?>