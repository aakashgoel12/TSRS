<?php
echo "hey";
$res = mail ( "princi.chhavi@gmail.com", "Test from de lappie..." , "Hello World!" ,"From: android<tns.iit2015@gmail.com>");
echo "checking";
var_export( $res );
echo "finiish";

?>