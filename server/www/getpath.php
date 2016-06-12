<?php
$con=mysqli_connect("localhost","root","lbs2015","tns");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$lat = (float)$_GET['lat'];
$lng = (float)$_GET['lng'];
$imag=$_GET['img'];
//$password = $_GET['password'];

$R = 6371;  // earth's mean radius, km
$rad=.250;
    // first-cut bounding box (in degrees)
    $maxLat = $lat + rad2deg($rad/$R);
    $minLat = $lat - rad2deg($rad/$R);
    // compensate for degrees longitude getting smaller with increasing latitude
    $maxLon = $lng + rad2deg($rad/$R/cos(deg2rad($lat)));
    $minLon = $lng - rad2deg($rad/$R/cos(deg2rad($lat)));
//echo $lat; " , " $minLat " ; " $maxLon " , " $minLon;
    

if($imag=="true")
$sql="SELECT count(*) FROM image where lat='$lat' and lng='$lng'";
else
$sql="SELECT pathaud,time FROM audio Where lat Between '$minLat' And '$maxLat' And lng Between '$minLon' And '$maxLon' order by time desc";
$result = mysqli_query($con,$sql);// where username='$username' and password='$password'");
$row = mysqli_fetch_array($result);
$data = $row[0];
//echo $data;
$date = new DateTime();
$ts=$date->getTimestamp();
$t2=(int)$ts;
//echo $imag;
if($imag=="false")
{
//echo "hey";
$data2=$row[1];
$t1=(int)$data2;
//echo $t2-$t1;
if($t2-$t1<=7200)
{
//echo "coming";
echo $data;
}
}
else
{
/*$sql = "Select path,time,convert(float,lat),convert(float,lng)
                acos(sin('$lat')*sin(radians(lat)) + cos('$lat')*cos(radians(lat))*cos(radians(lng)-'$lng')) * '$R' As D
            From (
                Select path,time,convert(float,lat),convert(float,lng)
                From image Where lat Between '$minLat' And '$maxLat' And lng Between '$minLon' And '$maxLon'
            ) As FirstCut
            acos(sin('$lat')*sin(radians(lat)) + cos('$lat')*cos(radians(lat))*cos(radians(lng)-'$lng')) * '$R' < '$rad'
            Order by D";
	*/	
//$sql="SELECT path,time FROM image where lat='$lat' and lng='$lng' order by time desc";
$sql= "Select path,time,lat,lng From image Where lat Between '$minLat' And '$maxLat' And lng Between '$minLon' And '$maxLon' order by time desc";
$result = mysqli_query($con,$sql);// where username='$username' and password='$password'");
//echo $data;
while($row = mysqli_fetch_assoc($result)) 
{

	//echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
	$path=$row["path"];
	$time=$row["time"];

	$t1=(int)$time;
	if($t2-$t1<=7200)
	{
		$paths .=$path . ",";
	}
	else
	{
		//echo "breaking";
		break;
	}
}
echo $paths;
}
mysqli_close($con);
?>
