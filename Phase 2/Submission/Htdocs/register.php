<?php
require"database.php";
$username=$_POST["username"];
$password=$_POST["password"];
$email=$_POST["email"];
$phone=$_POST["phone"];

// $username="demo3";
// $password="demo3";
// $email="a@a.com";
// $phone="123456789";

$pw= password_hash($password,PASSWORD_DEFAULT);
// Query
$query="INSERT INTO details(username,password,email,phone) VALUES('$username','$pw','$email','$phone')";
if(mysqli_query($conn,$query)){
    echo"Data inserted";
}
else{
    echo"error data not inserted";
}


?>