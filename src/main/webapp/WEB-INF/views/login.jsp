<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
 <script src="<c:url value='/resources/js/vendor/jquery.min.js'/>"></script>
  <script src="<c:url value='/resources/js/vendor/bootstrap.min.js'/>"></script>
  <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
  <link rel="stylesheet" href="<c:url value='/resources/css/vendor/bootstrap.min.css' />">
</head>
<body onload='document.loginForm.j_username.focus();'>
<h3>Custom Login Page</h3>
 
<%
 
String errorString = (String)request.getAttribute("error");
if(errorString != null && errorString.trim().equals("true")){
out.println("Incorrect login name or password. Please retry using correct login name and password.");
}
%>
<div class="col-md-offset-3">
<div class="col-md-6">
       <div class="generic-container">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Sign In </span></div>
                            <div class="formcontainer">
              
<form name='loginForm' action="<c:url value='j_spring_security_check' />"
method='POST'>
 						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="file">User Name</label>
								<div class="col-md-7">
								<input type='text' name='j_username' value='' placeholder="Enter username" required ng-minlength="3" >
								</div>
								</div>
								</div>
								
								<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="file">Password</label>
								<div class="col-md-7">
								<input type='text' name='j_password' value='' placeholder="Enter password" required ng-minlength="3" >
								</div>
								</div>
								</div>
								
								<div class="row">
							<div class="form-actions">
								<input type="submit" value="submit" class="btn btn-primary btn-sm" >
								<input name="reset" type="reset" class="btn btn-primary btn-sm"/>			
												</div>
						</div>
					
						
						
<!-- <table>
<tr>
<td>Username :</td>
<td><input type='text' name='j_username' value=''>
</td>
</tr>
<tr>
<td>Password:</td>
<td><input type='password' name='j_password' />
</td>
</tr>
<tr>
<td><input name="submit" type="submit"
value="submit" />
</td>
<td><input name="reset" type="reset" />
</td>
</tr>
</table> -->
 
</form>
</div>
</div>
</div>
</div>
</div>
</body>
</html>