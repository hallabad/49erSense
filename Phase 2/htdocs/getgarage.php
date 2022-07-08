<?php
require"database.php"; 
$DBTABLE = "Doors";

if($conn){
	$query ="SELECT * FROM $DBTABLE where ID like 'GarageD%'";
	$result=mysqli_query($conn,$query);
	$out = "";
	if(mysqli_num_rows($result)>0){
		for ($i = 0 ; $i < mysqli_num_rows($result) ; $i++) {
			$row = mysqli_fetch_assoc($result);
			$open = $row["Open"];
			if ($open==0)
				$open = "Open";
			else
				$open = "Closed";
			$out = "$out $open,";
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