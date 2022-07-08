<?php
require"database.php"; 
$DBTABLE = "HVAC";

if($conn){
	$query ="SELECT * FROM $DBTABLE";
	$result=mysqli_query($conn,$query);
	$out = "";
	if(mysqli_num_rows($result)>0){
		for ($i = 0 ; $i < mysqli_num_rows($result) ; $i++) {
			$row = mysqli_fetch_assoc($result);
			$id = $row["ID"];
			$mode = $row["Mode"];
			$fan = $row["Fan"];
			$temp = $row["Temp"];
			$target = $row["Target"];
			$out = "$out $id,$mode,$fan,$temp,$target |";
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