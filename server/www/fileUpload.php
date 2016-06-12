
<?php
echo $_FILES['image']['name'] . '<br/>';
$lat = $_POST['lat'];
$lng = $_POST['lng'];
$isImage=$_POST['isimage'];
//ini_set('upload_max_filesize', '10M');
//ini_set('post_max_size', '10M');
ini_set('max_input_time', 1000);
ini_set('max_execution_time', 1000);
error_reporting( E_ALL | E_STRICT ); 
ini_set( 'display_errors', 1 );

$target_path = "uploads/";

$target_path = $target_path . basename($_FILES['image']['name']);
echo $target_path;
try {
    //throw exception if can't move the file
    if (!move_uploaded_file($_FILES['image']['tmp_name'], $target_path)) {
        throw new Exception('Could not move file');
		
    }

   // echo "The file " . basename($_FILES['image']['name']) .
   // " has been uploaded";
} catch (Exception $e) {
    die('File did not upload: ' . $e->getMessage());
}
$con=mysqli_connect("localhost","root","lbs2015","tns");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
if($isImage=="true")
$sql="SELECT count(*) FROM `image` WHERE lat='$lat' AND lng='$lng'";
else
$sql="SELECT count(*) FROM `audio` WHERE lat='$lat' AND lng='$lng'";
$result=mysqli_query($con,$sql);
$row = mysqli_fetch_array($result);
$data = $row[0];
echo $data;
$date = new DateTime();
$ts=$date->getTimestamp();

if($data==0)
{
if($isImage=="true")
$sql = "INSERT INTO image(path,lat,lng,time) VALUES('$target_path','$lat','$lng','$ts')";
else
$sql = "INSERT INTO audio(pathaud,lat,lng,time) VALUES('$target_path','$lat','$lng','$ts')";
$result=mysqli_query($con,$sql);
}
else
{
if($isImage=="true")
$sql = "INSERT INTO image(path,lat,lng,time) VALUES('$target_path','$lat','$lng','$ts')";
//$sql = "update image set `path`='$target_path' , `time`='$ts' where `lat`='$lat' and `lng`='$lng'";
else
$sql = "update audio set `pathaud`='$target_path' , `time`='$ts' where `lat`='$lat' and `lng`='$lng'";
$result=mysqli_query($con,$sql);
}

echo "hello";
echo $result;
mysqli_close($con);
?>