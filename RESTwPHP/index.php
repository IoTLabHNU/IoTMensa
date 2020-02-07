<?php

//Check the REST-method
if($_SERVER['REQUEST_METHOD'] == "POST") {
    
    //Saves the passed message variables 
    $title = @$_POST['title'];
    $content = @$_POST['content'];
    
    // Get connection paramenters
    require_once("connect.php");
    
    $query = "INSERT INTO `mySQLtable` (title, content) VALUES ('$title', '$content')";
    
    //Check if connection & query is successful
    if (mysqli_query($con, $query)) {
        $response['success'] = true;
        $response['message'] = "Successfully";
    
        
    } else {
        $response['success'] = false;
        $response['message'] = "Failed";
       
    }
    
} else {
     $response['success'] = false;
     $response['message'] = "Error!";
}

//Close database connection
mysqli_close($con);

//Gives an respoonse in JSON-format
echo json_encode($response);

?>