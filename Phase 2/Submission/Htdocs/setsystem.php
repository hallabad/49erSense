<?php
require"database.php"; 
$DBTABLE = "system";
$COLNUM = isset($_GET['col']) ? $_GET['col'] : '';
$VAL = isset($_GET['val']) ? $_GET['val'] : '';
$sysID = isset($_GET['sysID']) ? $_GET['sysID'] : '';


if($conn){
    $exec ="UPDATE $DBTABLE SET `$COLNUM` = '$VAL' WHERE (`sysID` = '$sysID');";
    $statment = mysqli_prepare($conn,$exec);
    mysqli_stmt_execute($statment);
    if($statment){
        echo "$sysID state updated successfully!";
    } else {
        echo "Couldn't update";
    }
} else {
    echo "No Connection";
}
?> 