<?php
require"database.php"; 
$DBTABLE = "Doors";
$COLNUM = isset($_GET['col']) ? $_GET['col'] : '';
$VAL = isset($_GET['val']) ? $_GET['val'] : '';
$ID = isset($_GET['ID']) ? $_GET['ID'] : '';


if($conn){
    $exec ="UPDATE $DBTABLE SET `$COLNUM` = '$VAL' WHERE (`ID` = '$ID');";
    $statment = mysqli_prepare($conn,$exec);
    mysqli_stmt_execute($statment);
    if($statment){
        echo "$ID state updated successfully!";
    } else {
        echo "Couldn't update";
    }
} else {
    echo "No Connection";
}
?> 