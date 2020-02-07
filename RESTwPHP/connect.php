<?php
//set connection parameters
$con = mysqli_connect("servername", "username", "password", "dbname");

//test conection
if ($con) {
    echo "success";
} else {
    echo "error";
}



