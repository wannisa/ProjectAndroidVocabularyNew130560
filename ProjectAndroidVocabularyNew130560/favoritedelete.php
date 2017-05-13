<?php
header('Content-Type: application/json');

/*****************************************************************************/
// $host = "ap-cdbr-azure-east-c.cloudapp.net";
// $user = "b5d011ad4841d1";
// $pass = "2320a017";
// $db = "studentactivity";

// mysql_connect($host,$user,$pass);
// mysql_query("SET NAMES UTF8");
// mysql_query("USE $db");
include("../b_connectDB.php");
/*****************************************************************************/

		// GET PARAMETER FROM ANDROID
		$get_ID = $_POST["ID"];
	

			//IT WAS ZERO
			$sql = "DELETE FROM favorite WHERE id = '$get_ID'";
			mysql_query("set names 'utf8'");
			$result = mysql_query($sql);

		if($result){
			$json = array('SQL' => $sql);
		} else {
			$json = array('SQL' => $sql);
		}
		print(json_encode($json));
		mysql_close();
?>
