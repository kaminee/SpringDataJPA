<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"   "http://www.w3.org/TR/html4/loose.dtd">
   <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<html ng-app="myUserApp">

<head>
  <script src="<c:url value='/resources/js/vendor/jquery.min.js'/>"></script>
  <script src="<c:url value='/resources/js/vendor/bootstrap.min.js'/>"></script>
  <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
  <link rel="stylesheet" href="<c:url value='/resources/css/vendor/bootstrap.min.css' />">
    
</head>
  
<body>
    <br />

<br />
<%-- <a href="logout"><spring:message code="user.logout"/></a> --%>

			<nav class="navbar navbar-default">
			  <div class="container-fluid">
			  
			    <div class="col-md-8">
			      <div class="navbar-header">
			      <a class="navbar-brand" href="#">Test Application</a>
			    </div>
			     <ul class="nav navbar-nav">
<!-- 			      <li class="active"><a ui-sref="#">Home</a></li> -->
			     	<li><a ui-sref="emp">Employees</a></li>
					<li><a ui-sref="meeting">Meetings</a></li>
			      <li><a ui-sref="shop">Shop List</a></li>
			      
			    </ul>
			    </div>
			   <div class="col-md-4">
			 <div class="pull-right">
			   <spring:message code="user.logged"/>: <sec:authentication property="name"/> 
				<sec:authentication property="authorities"/>
			  			  <a href="logout"><spring:message code="user.logout"/></a>
			  			  </div>
			  
			
			  
			   </div>
			  </div>
			  
			</nav>
     
              	<script src="<c:url value='/resources/js/vendor/angular.js'/>"></script>
              	<script src="<c:url value='/resources/js/vendor/angular-route.min.js'/>"></script>
              <script src="http://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.10/angular-ui-router.min.js"></script>
              	
                <script data-require="angular-animate@1.2.11" data-semver="1.2.11" src="<c:url value='/resources/js/vendor/angular-animate.min.js'/>"></script>
<%--                 <script src="<c:url value="/resources/js/controller/home.js" />"></script> --%>
        
				<script src="<c:url value="/resources/js/app.js" />"></script>
				
				<script src="<c:url value="/resources/js/controller/company_controller.js" />"></script>
				<script src="<c:url value="/resources/js/controller/emp_controller.js" />"></script>
				<script src="<c:url value="/resources/js/controller/meetingController.js" />"></script>
				
				
				<script src="<c:url value="/resources/js/service/company_service.js" />"></script>
				<script src="<c:url value="/resources/js/service/emp_service.js" />"></script>
				<script src="<c:url value="/resources/js/service/meetingService.js" />"></script>
				
				
		

   <div ui-view></div>
    </body>
</html>
