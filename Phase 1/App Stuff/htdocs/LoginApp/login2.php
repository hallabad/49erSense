<?php
require"database.php"; 
$username=$_POST["username"];
$password=$_POST["password"];
// $username="demo";
// $password="demo";

// Login Query
$result = mysqli_query($conn,"SELECT * FROM table1 where 
   Username='$username' and Password='$password'");
   $row = mysqli_fetch_array($result);
   $data = $row[0];

   if($data){
      echo $data;
   }
	
   mysqli_close($conn);

?>