<?php
require"database.php"; 
$username=$_POST["username"];
$password=$_POST["password"];
// $username="demo";
// $password="demo";

// Login Query
$mysql_qry = mysqli_query($conn,"SELECT * FROM table1 where 
   Username='$username' and Password='$password'");
   $result=mysqli_query($conn ,$mysql_qry);
   if(mysqli_num_rows($result) > 0){
	   echo "login success";
   }
   else {
	   echo "login not success"
   }
   // $row = mysqli_fetch_array($result);
   // $data = $row[0];

   // if($data){
      // echo $data;
   // }
	
   mysqli_close($conn);

?>