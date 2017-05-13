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
		$get_THAI = $_POST["THAI"];
		$get_ENG = $_POST["ENG"];
		$get_FAVO = $_POST["FAVO"];

			//IT WAS ZERO
			$sql = "INSERT INTO favorite (id, thai, eng, favo) VALUES ('$get_ID', '$get_THAI', '$get_ENG', '$get_FAVO')";
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
