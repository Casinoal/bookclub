<?php
$username = "alexwhya_book";
$password = "alex123";
$hostname = "alexwhyatt.com"; 

//connection to the database
$dbhandle = mysql_connect($hostname, $username, $password) 
 or die("Unable to connect to MySQL");
echo "Connected to MySQL<br>";

$sql = "SELECT bookname, username FROM lending";
$res = mysql_query($sql);

$xml = new XMLWriter();

$xml->openURI("php://output");
$xml->startDocument();
$xml->setIndent(true);

$xml->startElement('books');

while ($row = mysql_fetch_assoc($res)) {
  $xml->startElement("bookname");

  $xml->writeAttribute('username', $row['username']);
  $xml->writeRaw($row['bookname']);

  $xml->endElement();
}

$xml->endElement();

header('Content-type: text/xml');
$xml->flush();


mysql_close($dbhandle)



?>






