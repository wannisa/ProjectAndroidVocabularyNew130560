
<?php
header('Content-Type: application/json');

include("../b_connectDB.php");





$sqlAll = "SELECT id,eng,thai,favo FROM favorite ";

$resutAll = mysql_query($sqlAll);

while ($rowAll = mysql_fetch_array($resutAll)) {

	$data[] = array("id" => " ".$rowAll['id'],"thai" => "  ".$rowAll['thai'],"eng" => "  ".$rowAll['eng'],"favo" => " ".$rowAll['favo']);

}

$json = array('StatusCode' => $StatusCodeIs200, 'SQL' => $sqlAll, 'Data' => $data);

print(json_encode($json));
mysql_close();
?>
