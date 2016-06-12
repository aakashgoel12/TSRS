<?php
$con=mysqli_connect("localhost","root","lbs2015","tns");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$lat = $_GET['lat'];
$lng = $_GET['lng'];
$R = 6371;  // earth's mean radius, km
$rad=.250;
    // first-cut bounding box (in degrees)
    $maxLat = $lat + rad2deg($rad/$R);
    $minLat = $lat - rad2deg($rad/$R);
    // compensate for degrees longitude getting smaller with increasing latitude
    $maxLon = $lng + rad2deg($rad/$R/cos(deg2rad($lat)));
    $minLon = $lng - rad2deg($rad/$R/cos(deg2rad($lat)));

    
//$password = $_GET['password'];

$sql="SELECT email FROM register Where lat Between '$minLat' And '$maxLat' And lng Between '$minLon' And '$maxLon'";
$result = mysqli_query($con,$sql);
while($row = mysqli_fetch_assoc($result)) 
{
	$name=$row["email"];
	$names .=$name . ",";
}
echo $names;

mysqli_close($con);
?>