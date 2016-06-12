<?php
//mysqli_connect :connect to MySqlServer=localhost where username=root and pwd 
$db=mysqli_connect("localhost","root","lbs2015");
$db_select=mysqli_select_db($db,"checking_project"); //select database 
if (!$db_select)
 {
    die("Database selection failed: " . mysqli_error($db));
}

	 
$query="select author,count(author),sum(number),sum(score) from article where title like \"%$search_keyword%\" group by author order by sum(score) DESC LIMIT 0,20";
	echo "<font color='red'><b>Research Area/Topic::</b></font>"."<font color='blue'><u>$search_keyword </u> </font>"."<br>";
	
	echo "<br>"; //break line
		$retval = mysqli_query($db,$query);      //mysqli_query() - Send a MySQL query through a variable i.e. retval=retrieve value
		if($query_run=$retval)       //if query runs correctly
		{               //mysqli_num_rows — Gets the number of rows in a result
			if(mysqli_num_rows($query_run)!=null) //Keyword Found:rows in result of query is not null		
			{
        $rank=1;
				//mysqli_fetch_array — Fetch a result row as an associative array, a numeric array, or both
			while($row = mysqli_fetch_array($retval, MYSQL_ASSOC)) 
              {
$query1="select title,number from article where author like \"%{$row['author']}%\" AND title like \"%$search_keyword%\" AND number=(select max(number) from article where author like \"%{$row['author']}%\" AND title like \"%$search_keyword%\" ) "; 
            $retval1=mysqli_query($db,$query1);
            $row1 = mysqli_fetch_array($retval1, MYSQL_ASSOC);
        echo "Rank:"."<font color='magneta'>$rank</font>"."<br>".
	         "Author :{$row['author']}  <br> ".
		     "Total Citations: {$row['sum(number)']} <br> ".
		     "PaperCount : {$row['count(author)']} <br> ".
             "Highest Cited Paper : {$row1['title']}-<font color='purple'>citation({$row1['number']})</font><br> ".
		     "<font color='green'>--------------------------------------</font><br>";
		     $rank++;
              }  
        mysqli_free_result($retval);
            }
		
		    else
			 {
			 echo "Keyword Not Found.<br>".mysqli_error(); //the keyword didnt match i.e. 0 rows in result of query
			 }
		}
		else
		{
			echo "QueryError :Please Check Your Query.<br>".mysqli_error($db); //wrong query
		}
		
?>
<html>
<head>
<b><br><br><br>
<!-- HTML for Get Profile Button -->
<p><b>Copy the Expert Name and click on:</b></p>
<body>
<input type=button onClick=window.open("https://scholar.google.com/citations?view_op=search_authors","width=550,height=300,left=150,top=200,toolbar=0,status=0"); value="Get Profile Details on Google Scholar">
<input type=button onClick=window.open("http://dblp.uni-trier.de/pers/","width=550,height=300,left=150,top=200,toolbar=0,status=0"); value="Get Profile Details on DBLP">
</body>
</head>
</html>
