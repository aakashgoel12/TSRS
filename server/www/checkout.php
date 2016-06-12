<?php
$con=mysqli_connect("localhost","root","lbs2015","tns");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$email=$_GET['email'];


$sql="update register set  checkin='no' where email='$email'";
$result = mysqli_query($con,$sql);
$row=mysqli_fetch_array($result);
$count=$row[0];
echo $count;
mysqli_close($con);

?>