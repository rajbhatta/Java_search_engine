<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome To Information Retrieval Class</title>
    <link href="<c:url value="/native-theme/css/bootstrap.min.css"/>" rel="stylesheet">
  </head>
  <body>
  

		  <nav class="navbar  navbar-inverse  navbar-fixed-top" style="background-color:#5F5F5F;">
		  <div class="container">
		  <button type="button" class="navbar-toggle"
		  data-toggle="collapse"
		  data-target=".navbar-collapse"
		  >
		  <span class="sr-only"> Toggle navigation</span>
		  <span class="icon-bar"> </span>
		  <span class="icon-bar"> </span>
		  <span class="icon-bar"> </span>
		  </button>
		  
		   <a class="navbar-brand" href="#" style="color:white;"> IR-Search Engine</a>
		   
		      
		  </div>
		</nav>
	



 <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>Contact Me</h2>
                    <hr class="star-primary">
                </div>
            </div>
            <div class="row" style="margin-top:100px;">
            
            	
                <div class="col-lg-8 col-sm-offset-2" >
                    
                	
               		
               		<div style="margin-top:5px; margin-left:100px; margin-right:100px;">
                        <a href="<c:url value="/citeseer-search.jsp"/>" class="btn btn-success btn-block" >CiteSeer Search</a>
               		</div>
               		
               		<div style="margin-top:5px; margin-left:100px; margin-right:100px;">
                         <a href="<c:url value="/pagerank-search.jsp"/>" class="btn btn-success btn-block" >Cigmod Page Rank Search</a>
               		</div>
               		
               		<div style="margin-top:5px; margin-left:100px; margin-right:100px;">
                         <a href="<c:url value="/vldb-search.jsp"/>" class="btn btn-success btn-block" >VLDB Search</a>
               		</div>
               		
               		<div style="margin-top:5px; margin-left:100px; margin-right:100px;">
                         <a href="<c:url value="/icse-search.jsp"/>" class="btn btn-success btn-block" >ICSE Search</a>
               		</div>
               		
               		<div style="margin-top:5px; margin-left:100px; margin-right:100px;">
                         <a href="<c:url value="/naivebayes-search.jsp"/>" class="btn btn-success btn-block" >Naive Bayes Classification</a>
               		</div>
               
                </div>
                
               
          
            </div>
           </div>
           
           
           <div class="container">
            <div class="row">
               
            </div> 
         </div>
    </section>


    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/native-theme/js/bootstrap.min.js"/>"></script>
  </body>
</html>


