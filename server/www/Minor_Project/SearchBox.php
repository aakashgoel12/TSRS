<?php

if(isset($_GET['query']) && !empty($_GET['query'])) //isset — Determine if a variable is set and is not NULL
{
	$search_keyword=$_GET['query'];
	require 'testcode3.php';
}

?>

<html>
<head>
    <title>MINOR PROJECT</title>

<style type="text/css">
        h1{
            font-size: 3.0em;
            text-align: center;
        }
        p{
            text-align: center;
        }
        body{
            }
	#tfheader{
		background-color:grey;
	}
	#tfnewsearch{
		text-align: center;
		padding:20px;
	}
	.tftextinput2{
		margin: 0;
		padding: 10px 30px;
		font-family: Arial, Helvetica, sans-serif;
		font-size:18px;
		color:#666;
		border:1px solid #0076a3; border-right:0px;
		border-top-left-radius: 5px 5px;
		border-bottom-left-radius: 10px 10px;
	}
	.tfbutton2 {
		margin: 0;
		padding: 7px 9px;
		font-family: Arial, Helvetica, sans-serif;
		font-size:20px;
		font-weight:bold;
		outline: none;
		cursor: pointer;
		text-align: center;
		text-decoration: none;
		color: #ffffff;
		border: solid 1px #0076a3; border-right:0px;
		background: #0095cf;
		background: -webkit-gradient(linear, left top, left bottom, from(#00adee), to(#0078a5));
		background: -moz-linear-gradient(top,  #00adee,  #0078a5);
		border-top-right-radius: 10px 10px;
		border-bottom-right-radius: 10px 10px;
	}
	.tfbutton2:hover {
		text-decoration: none;
		background: #007ead;
		background: -webkit-gradient(linear, left top, left bottom, from(#0095cc), to(#00678e));
		background: -moz-linear-gradient(top,  #0095cc,  #00678e);
	}
	/* Fixes submit button height problem in Firefox */
	.tfbutton2::-moz-focus-inner {
	  border: 0;
	}
	.tfclear{
		clear:both;
	}
        .bg{
            background-image:url("bckgrnd1.jpg");
            height: 260px;
        
        
        }
</style>

<body>
    <h1>Expert Finding Portal</h1>
    <div class="bg">
    </div>
    <p><font size="6">Hello users!!</font> <br></br> Type any research area below and search for the experts</p>

<!-- HTML for SEARCH BAR -->
    <div id="tfheader">
        <form  id="tfnewsearch" method="get" action="SearchBox.php">
            <input type="text" id="tfq" class="tftextinput2" name="query" size="21" maxlength="120" value="Parallel">
            <input type="submit" value=">" class="tfbutton2">
        </form>
		<div class="tfclear"></div>
    </div>
	
<!-- HTML for Get Profile Button -->
    <p>Want to know more about Experts?</p>
    <div style="text-align: center">
        <input  type=button onClick=window.open("https://scholar.google.com/citations?view_op=search_authors","width=550,height=300,left=150,top=200,toolbar=0,status=0") value="Get Profile Details on Google Scholar">
        <input type=button onClick=window.open("http://dblp.uni-trier.de/pers/","width=550,height=300,left=150,top=200,toolbar=0,status=0")  value="Get Profile Details on DBLP"> 
    </div>

<!-- HTML for Footer -->
    <br></br><br></br><br></br><br></br>
    <footer><center>Copyright2015 @ Amit,Aastha,Sumit,Subash <br><img src="projectunder.gif" alt="Angry face" width="32" height="32" />Project under:Sweta Jain <img src="contact.gif" alt="Angry face" width="32" height="32" /> <br> Contact: subash.shrestha262@gmail.com / asthabanthia@gmail.com </footer>
</body>
</head>
</html>
