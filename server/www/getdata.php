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
$R = 6371;  // earth's mean radius, km
$rad=.100;
    // first-cut bounding box (in degrees)
    $maxLat = $lat + rad2deg($rad/$R);
    $minLat = $lat - rad2deg($rad/$R);
    // compensate for degrees longitude getting smaller with increasing latitude
    $maxLon = $lng + rad2deg($rad/$R/cos(deg2rad($lat)));
    $minLon = $lng - rad2deg($rad/$R/cos(deg2rad($lat)));


$sql="SELECT count(*) FROM feedback Where lat Between '$minLat' And '$maxLat' And lng Between '$minLon' And '$maxLon'";
$result = mysqli_query($con,$sql);// where username='$username' and password='$password'");
$row = mysqli_fetch_array($result);
$count = $row[0];
$sql="SELECT traffic,weather,safe,time,saferating,nop FROM feedback Where lat Between '$minLat' And '$maxLat' And lng Between '$minLon' And '$maxLon'";
$result = mysqli_query($con,$sql);// where username='$username' and password='$password'");
$people=0;
$rat2=0;

while ($row = mysqli_fetch_assoc($result))
{
$tr = $row['traffic'];
$we = $row['weather'];
$sa = $row['safe'];
$data2=$row['time'];
$rat=$row['saferating'];
$noo=$row['nop'];
$rat2+=$rat*$noo;
$people+=$noo;
}
$rat=$rat2/$people;
$date = new DateTime();
$ts=$date->getTimestamp();
$t1=(int)$data2;
$t2=(int)$ts;
if(true)//$t2-$t1<=7200)
{
echo $rat . "," . $tr . "," . $we . "," . $sa;
}
else if($count!=0)
{
echo $rat . "," . "hey";
}
mysqli_close($con);
?>
