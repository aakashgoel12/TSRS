#!/bin/sh
x=$1
y=$2
z=$3
#echo $x
matlab -nodisplay -nojvm -logfile out.txt -r "m=$x;n=$y;o=$z;a=readfis('fuzzy_lbs');evalfis([m,n,o],a), quit" | tail -0
wait $(ps | grep matlab | awk '{print $2}') 
tail -2 out.txt | head -1 | cut -d " " -f5


