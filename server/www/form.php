<?php
error_reporting( E_ALL | E_STRICT ); 
ini_set( 'display_errors', 1 );
$con=mysqli_connect("localhost","root","lbs2015","tns");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$lat = $_GET['lat'];
$lng = $_GET['lng'];
$traffic=$_GET['traffic'];
$weather=$_GET['weather'];
$safe=$_GET['safe'];
$nr=$_GET['rating'];
//$password = $_GET['password'];

$sql="SELECT count(*) FROM `feedback` WHERE lat='$lat' AND lng='$lng'";
$result=mysqli_query($con,$sql);
$row = mysqli_fetch_array($result);
$data = $row[0];
echo $data;
$date = new DateTime();
$ts=$date->getTimestamp();

if($data==0)
{
$sql = "INSERT INTO feedback(traffic,weather,safe,saferating,nop,lat,lng,time) VALUES('$traffic','$weather','$safe','$nr',1,'$lat','$lng','$ts')";
$result=mysqli_query($con,$sql);
}
else
{
$sql="SELECT saferating,nop FROM `feedback` WHERE lat='$lat' AND lng='$lng'";
$result=mysqli_query($con,$sql);
$row = mysqli_fetch_array($result);
$cr = $row[0];
$nop=$row[1];
$temp=$cr*$nop + $nr;
$temp2=$temp/($nop +1);
$nop2=$nop+1;
$sql = "update feedback set `traffic`='$traffic' , `weather`='$weather' , `safe`='$safe', `time`='$ts', `saferating`='$temp2' , `nop`='$nop2'  where `lat`='$lat' and `lng`='$lng'";
$result=mysqli_query($con,$sql);
}
echo "hello";
echo $result;
mysqli_close($con);
?>
