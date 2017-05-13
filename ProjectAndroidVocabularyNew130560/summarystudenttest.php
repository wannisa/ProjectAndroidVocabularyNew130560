
<?php
header('Content-Type: application/json');

include("../b_connectDB.php");





$sqlAll = "SELECT id,english,thai FROM vocabulary";

$resutAll = mysql_query($sqlAll);

while ($rowAll = mysql_fetch_array($resutAll)) {

	$id = $rowAll['id'];

	$sqlAllFavo = "SELECT favo FROM favorite WHERE id = '$id'";
	$resutAllFavo = mysql_query($sqlAllFavo);
	$rowAllFavo = mysql_fetch_array($resutAllFavo);

	$data[] = array("id" => $rowAll['id'],"thai" => $rowAll['thai'],"english" => $rowAll['english'], "favo" => $rowAllFavo['favo']);

}

$json = array('StatusCode' => $StatusCodeIs200, 'SQL' => $sqlAllFavo, 'Data' => $data);

print(json_encode($json));
mysql_close();
?>
