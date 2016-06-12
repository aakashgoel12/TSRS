<?php
$lat=28.543843;
$lng=77.192152;
$R = 6371;  // earth's mean radius, km
$rad=.250;
    // first-cut bounding box (in degrees)
    $maxLat = $lat + rad2deg($rad/$R);
    $minLat = $lat - rad2deg($rad/$R);
    // compensate for degrees longitude getting smaller with increasing latitude
    $maxLon = $lng + rad2deg($rad/$R/cos(deg2rad($lat)));
    $minLon = $lng - rad2deg($rad/$R/cos(deg2rad($lat)));
echo $maxLat . " , " . $minLat;
echo " : " . $maxLon . " , " . $minLon;
?>
