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
  ?>


<html>
<head>
</head>

<body>
<table border=1>
<tr>
<th>ID</th>
<th>LendDate</th>
<th>Bookname</th>
<th>Current Reader</th>
</tr

<?php
$myname = ($_POST['username']); // $foo = bar
//$myname = "Alex";
//execute the SQL query and return records
$result = mysql_query("SELECT * FROM lending WHERE username='".($myname)."'");

//fetch the data from the database 

while ($row = mysql_fetch_array($result))
{
?>
 <tr> 
 <td> <?php echo $row['lendid'];?></td>
 <td> <?php echo $row['lenddate'];?></td>
 <td> <?php echo $row['bookname'];?></td>
 <td> <?php echo $row['username'];?></td>
 
 </tr>
 
<?php
}
 mysql_close($dbhandle)
?>
</table>


</body>

</html>





