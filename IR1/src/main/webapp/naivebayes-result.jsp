<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome To Information Retrieval Class</title>
    <link href="<c:url value="/native-theme/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="/native-theme/css/jquery.dataTables.css"/>">
  </head>
  
  <style type="text/css">
  
  .search-title{
    font-weight:normal;
    color:#1a0dab;
    font-size:18px;
}
.search-link{
    color:#008443;
}

.search-link a{
    color:#008443;
    text-decoration:none
}


.search-para{
    display:block;
    color:#6a6a6a;
}

.btn-group i{ cursor:pointer; text-decoration:none}

  </style>
  
  <body>
  
<br/>
 <section id="contact">
        <div class="container">
            <div class="row">
            	
            </div>
           </div>
           
           
           <div class="container">
            <div class="row">
            
                
<h5>Naive Bayes Classification Results:</h5>
            	 <a href="#" style="color:#2F6646"> 
				                  <span class="search-title">Word Count:&#9755; ${naiveResult.totalCount} </span></a><br>
				                  <span class="search-title">Precision:&#9755; ${naiveResult.precision} </span></a><br>
				                  <span class="search-title">F1Value:&#9755; ${naiveResult.f1Value} </span></a><br>
				                  <span class="search-title">Error:&#9755; ${naiveResult.error} </span></a><br>
								   <span class="search-title">Accuracy:&#9755; ${naiveResult.accuracy} </span></a><br>
            	</tr>
           
            
            
            
            </div> 
         </div>
    </section>

    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" language="javascript" src="<c:url value="/native-theme/js/jquery.dataTables.js"/>"></script>
    <script src="<c:url value="/native-theme/js/bootstrap.min.js"/>"></script>

  </body>
</html>


<script type="text/javascript">
$(document).ready(function(){
	
	$('#resultTable').dataTable();
});
</script>