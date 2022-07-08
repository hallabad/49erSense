<?php
require"database.php"; 
$DBTABLE = "System";
$COLNUM = isset($_GET['col']) ? $_GET['col'] : '';
$VAL = isset($_GET['val']) ? $_GET['val'] : '';
$ID = isset($_GET['id']) ? $_GET['id'] : '';


if($conn){
    $exec ="UPDATE $DBTABLE SET `$COLNUM` = '$VAL' WHERE (`ID` = '$ID');";
    $statment = mysqli_prepare($conn,$exec);
    mysqli_stmt_execute($statment);
    if($statment){
        echo "t";
    } else {
        echo "f";
    }
    echo $statment;
} else {
    echo "-2";
}
?> 