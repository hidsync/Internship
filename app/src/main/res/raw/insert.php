<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
    //GETTING POST DATA
    $name=$_POST['name']
    $address=$_POST['address']
    $email=$_POST['email']
    $phone=$_POST['phone']
    $pwd=$_POST['pwd']
           
    if($name=='' || $address=='' || $email=='' || $phone=='' || $pwd==''){
        echo('Please fill all details');
    }else{
        require_once('dbConnect.php');
        
        $sql="SELECT * FROM lattice_user WHERE name='$name' or email='$email'";
        
        $check=mysqli_fetch_array(mysqli_query($con,$sql));
        
        if(isset($check)){
            echo('User already exists')
        }else{
            $sql="INSERT INTO lattice_user(name,address,email,phone,pwd) VALUES('$name','$address','$email','$phone','$pwd')";
            
            if(mysqli_query($con,$sql)){
                echo('Successfully registered');
            }else{
                echo('Please try again');
            }
        }
        mysqli_close($con);
    }          
}else{
        echo('Error');
    } 