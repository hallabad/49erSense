<?php
require"database.php"; 
$DBTABLE = "Weathers";

if($conn){
	$query ="SELECT * FROM $DBTABLE";
	$result=mysqli_query($conn,$query);
	$out = "";
	if(mysqli_num_rows($result)>0){
		for ($i = 0 ; $i < mysqli_num_rows($result) ; $i++) {
			$row = mysqli_fetch_assoc($result);
			$id = $row["ID"];
			$sr = $row["Sunrise"];
			$ss = $row["Sunset"];
			$tmax = $row["TempMax"];
			$tmin = $row["TempMin"];
			$fmax = $row["FeelsLikeMax"];
			$fmin = $row["FeelsLikeMin"];
			$p = $row["Pressure"];
			$h = $row["Humidity"];
			$uvi = $row["UVIndex"];
			$cc = $row["CloudCover"];
			$ws = $row["WindSpeed"];
			$wd = $row["WindDirection"];
			$wt = $row["WeatherType"];
			$cop = $row["ChanceOfRain"];
			$v = $row["Visability"];

			$out = "$out $id,$sr,$ss,$tmax,$tmin,$fmax,$fmin,$p,$h,$uvi,$cc,$ws,$wd,$wt,$cop,$v |";
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