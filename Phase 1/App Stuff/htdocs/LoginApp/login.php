<?php
require"database.php"; 
$username=$_POST["username"];
$password=$_POST["password"];
// $username="demo";
// $password="demo";

// Login Query
$query ="SELECT * FROM details where username ='$username'";
$result=mysqli_query($conn,$query);

if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	$username=$row["username"];
    $password1=$row["password"];
    $email=$row["email"];
    $phone=$row["phone"];
    $verify=password_verify($password,$password1);
    if ($verify){

        //Here is the specially formatted string response.. ie: "ServerResponse".
        //It is of the form: "boolean,email,name"
        echo "true,{$username},{$password},{$email},{$phone}";
    }
	else{
        echo "false,0,0";
    }
}
else{
	echo "false,0,0";
}

?>