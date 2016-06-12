<?php
$con=mysqli_connect("localhost","root","lbs2015","tns");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$name=$_GET['name'];
$email=$_GET['email'];
$pass=$_GET['password'];

$sql="select count(*) from register where email='$email'";
$result = mysqli_query($con,$sql);
$row=mysqli_fetch_array($result);
$count=$row[0];
echo "hello";
if($count==0)
{
echo "hey";
$sql="INSERT INTO register(name, email, password)VALUES('$name', '$email', '$pass')";
$result = mysqli_query($con,$sql);
echo $result;
}
else
{
echo "0";
}
mysqli_close($con);

?>