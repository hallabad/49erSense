<?php
require"database.php"; 
$DBTABLE = "System";

if($conn){
	$query ="SELECT * FROM $DBTABLE";
	$result=mysqli_query($conn,$query);
	$out = "";
	if(mysqli_num_rows($result)>0){
		for ($i = 0 ; $i < mysqli_num_rows($result) ; $i++) {
			$row = mysqli_fetch_assoc($result);
			$id = $row["SysID"];
			$armed = $row["Armed"];
			if ($armed==0)
				$armed = "Disarmed";
			elseif ($armed == 1)
				$armed = "Armed Stay";
			else
				$armed = "Armed Away";
			$out = "$out $armed";
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