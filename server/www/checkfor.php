<?php
$con=mysqli_connect("localhost","root","lbs2015","tns");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$email=$_GET['email'];

$sql="select checkin,lat,lng from register where email='$email'";
$result = mysqli_query($con,$sql);
$row=mysqli_fetch_array($result);
$checkin=$row[0];
$lat=$row[1];
$lng=$row[2];


if($checkin=="yes")
{echo $lat . "," . $lng;
}
else
{
echo "no";
}
mysqli_close($con);

?>