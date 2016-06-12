


<?php
$con=mysqli_connect("localhost","root","lbs2015","project");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$email=$_GET['email'];
$pass=$_GET['pass'];

$sql="select count(*) from registration where Email='$email' and password='$pass'";
//echo $sql;
$result = mysqli_query($con,$sql);
$row=mysqli_fetch_array($result);
$count=$row[0];
//echo $count;
if($count!=0)
{
echo "1"; 
//echo "Email & Password Match Successfull";
}
else
{
echo "0";
}
mysqli_close($con);

?>

