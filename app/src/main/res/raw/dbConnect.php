<?php

    define('HOST','localhost');
    define('USER','lattice');
    define('PASS','sneha');
    define('DB','lattice_user');
    
    $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to connect');