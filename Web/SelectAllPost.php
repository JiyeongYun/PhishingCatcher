<?php
$conn = mysqli_connect("aigoodwill.godohosting.com", "root", "autoset", "simpleboard");
 
$query = "select seq, author, title, content from board";
 
 
if($result = mysqli_query($conn, $query)){
    $row_num = mysqli_num_rows($result);
    
    echo "{";
    
        echo "\"status\":\"OK\",";
        
        echo "\"rownum\":\"$row_num\",";
    
        echo "\"result\":";
        
            echo "[";
            
                for($i = 0; $i < $row_num; $i++){
                    $row = mysqli_fetch_array($result);
                    echo "{";
                    
                    echo "\"seq\":\"$row[seq]\", \"author\":\"$row[author]\", \"title\":\"$row[title]\", \"content\":\"$row[content]\"";
                    
                    echo "}";
                    if($i<$row_num-1){
                        echo ",";
                    }
                }
 
                        
                
            echo "]";
            
    echo "}";
}
 
else{
    echo "failed to get data from database.";
}
 
?>