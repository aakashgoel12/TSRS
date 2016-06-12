<?php
$con=mysqli_connect("localhost","root","lbs2015","tns");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$email=$_GET['email'];
$pass=$_GET['password'];

$sql="select count(*) from register where email='$email' and password='$pass'";
$result = mysqli_query($con,$sql);
$row=mysqli_fetch_array($result);
$count=$row[0];
if($count!=0)
{
echo "1";
}
else
{
echo "0";
}
mysqli_close($con);

?>
