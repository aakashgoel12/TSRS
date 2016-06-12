<?php
$x=$_GET["security"];
$y=$_GET["traffic"];
$z=$_GET["weather"];
$command="sh mat.sh " . $x . " " . $y . " " . $z;
passthru($command, $var);
echo $var;
?>
