<?php
require"database.php"; 
// $ID=$_POST["ID"];
// $DBTABLE = "Lights"

// Login Query
$query ="SELECT ID, Level FROM Lights";
$result=mysqli_query($conn,$query);

if(mysqli_num_rows($result)>0){
	echo "There are $result->num_rows conncted lights.<br>";
	while($row = mysqli_fetch_assoc($result))
		echo "Light: ".$row["ID"]. " - " .$row["Level"]. "<br>";
		
	// $row = mysqli_fetch_assoc($result);
	// $level = $row["Level"];
	// echo "$level";
}
else{
	echo "0 results";
}
?>