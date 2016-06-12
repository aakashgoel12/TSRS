<?php
/**
* Copyright (c) 2009, Nitin Kumar Gupta, http://publicmind.in.
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions
* are met:
*
*   * Redistributions of source code must retain the above copyright
*     notice, this list of conditions and the following disclaimer.
*
*   * Redistributions in binary form must reproduce the above
*     copyright notice, this list of conditions and the following
*     disclaimer in the documentation and/or other materials provided
*     with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
* "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
* LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
* FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
* COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
* INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
* BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
* CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
* LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
* WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
* OF SUCH DAMAGE.
*/

/*
* This is a BSD License approved by the Open Source Initiative (OSI).
* See:  http://www.opensource.org/licenses/bsd-license.php
*/

/**
* Directory to archive.
* example: '.' - root directory
*          'folder/sub-folder/sub-folder' - for an internal folder.
*
* Note: Do not use trailing slashes.
*/
$directory = '/var/www/uploads';

// File name for the backup along with timestamp. If the filename exists,
// They are numbered as backup_1, backup_2,...
$time = date("d_m_y");;
$zipname = 'backup_'.$time;
$count = 1;

$maxFiles = 500;    //Number of files to be added to each archive.
//Varies with your PHP Memory limit and execution time.
//After a certain limit, the zipArchive is unable to archive.

$fileNums = 0;
$filenames = array();

parse($directory);
zipFiles();
echo "Done";
return;

/**
*  Archives the files present in the global $filename array.
*
*  @return:
*
*  0: fail
*  1: Success
*/
function zipFiles() {
global $count;
global $zipname;
global $filenames;
$zipfile = $zipname . '.zip';
while(file_exists($zipfile)) {
$zipfile = $zipname.'_'.$count.'.zip';
$count++;
}
$zip = new ZipArchive();

if ($zip->open($zipfile, ZIPARCHIVE::CREATE)!==TRUE) {
exit("Error opening , Make sure you have write permissionsn");
}
foreach ($filenames as $filename) {
$zip->addFile($filename,$filename);
echo "Adding " . $filename . " to <strong>".$zipfile."</strong>";
}

echo "Files Packed = " . $zip->numFiles . "<br />";
if(!$zip->status) {
echo "Success";
return 1;
}
else {
echo "Fail";
return 0;
}
$zip->close();
}

/**
* parses the whole directory and archives it in small parts, each
* containg $maxFiles files each.
*
* @param:
*
* $dir: Directory to archive recursively.
*/
function parse($dir) {
global $filenames;
global $fileNums;
global $maxFiles;
if ($handle = opendir($dir)) {
while (false !== ($file = readdir($handle))) {
if ($file != "." && $file != ".." && is_file($dir.'/'.$file)) {
$filenames[] = $dir.'/'.$file;
$fileNums++;

if($fileNums >= $maxFiles) {
$fileNums=0;
if(!zipFiles()) return;
$filenames = array();
}
}

else if ($file != "." && $file != ".." && is_dir($dir.'/'.$file)) {
parse($dir.'/'.$file);
}
}
closedir($handle);
}
}
?>