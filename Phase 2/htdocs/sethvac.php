<?php
require"database.php";
   $DBTABLE = "hvac";
   $COLNUM = $_GET['col'];
   $VAL = $_GET['val'];
   $ID = $_GET['ID'];
   $statment = mysqli_query($conn,"UPDATE $DBTABLE SET `$COLNUM` = '$VAL' where `ID` = '$ID'");
   if($statment){
        echo "t";
    } else {
        echo "f";
    }
   mysqli_close($conn);
?>