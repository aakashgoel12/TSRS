<?php
$zip = new ZipArchive;
if ($zip->open('test.zip') === TRUE) {
    $zip->addFile('/var/www/index.html', 'index.html');
    $zip->close();
    echo 'ok';
} else {
    echo 'failed';
}
?>