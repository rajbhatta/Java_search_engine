<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            
            
        <table id="resultTable" class="table">
            
            <thead>
            <tr><th>Results</th>
           </tr>
            </thead>
            
            <tfoot>
            
            <tr><th>Developed By: Raj Bhatta</th>
            </tr>
            </tfoot>
            
            <tbody>
            
            <c:forEach items="${result}" var="resultSet" varStatus="i">
            	<tr class="active"><td><a href="#"> 
				                  <span class="search-title"><span style="display:none;">${i.index}</span>  ${resultSet.resultTitle} </span></a></td>
            	</tr>
            </c:forEach>
            
			</tbody>
			
		</table>
            
            
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
	
	$('#resultTable').dataTable({
        "order": [[ 0, "desc" ]]
    });
});
</script>