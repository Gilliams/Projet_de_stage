<?php

//$recu = $_POST['choix'];
$my_upload_path = $_POST['choix'];


error_reporting(E_ALL | E_STRICT);
require('UploadHandler.php');

//$options = array ('upload_dir' => dirname(__FILE__) . $recu);
//$upload_handler = new UploadHandler($options);




$options = array ('upload_dir' => $my_upload_path , 'upload_url' =>  $my_upload_path);
$upload_handler = new UploadHandler($options);
