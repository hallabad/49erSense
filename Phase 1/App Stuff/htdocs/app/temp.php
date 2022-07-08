<?php
require"database.php"; 
$ID=$_POST["ID"];
$DBTABLE = "iotsec."

// Login Query
$query ="SELECT * FROM $DBTABLE where ID ='$ID'";
$result=mysqli_query($conn,$query);

if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	
	echo "$ID,$Open,$Locked"
}
else{
	echo "$ID,-1,-1";
}
?>