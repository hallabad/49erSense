<?php
require"database.php"; 
$DBTABLE = "Doors";

if($conn){
	$query ="SELECT * FROM $DBTABLE";
	$result=mysqli_query($conn,$query);
	$out = "";
	if(mysqli_num_rows($result)>0){
		for ($i = 0 ; $i < mysqli_num_rows($result) ; $i++) {
			$row = mysqli_fetch_assoc($result);
			$id = $row["Id"];
			$open = $row["Open"];
			$Locked = $row["Locked"];
			$out = "$out $id,$open,$Locked |";
		}
		echo $out;
	}
	else{
		echo "-1";
	}
} else {
	echo "-2";
}
?>