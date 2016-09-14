<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
   <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.28//angular-route.min.js"></script>
     <script>
var app = angular.module('myApp', []);

app.controller('todoCtrl', function($scope,$http) {
	console.log("data===");
	$http.get('http://localhost:8080/SpringMvcHibernateXML/mobile').
        success(function(data) {
         $scope.usersdata = data[0];
        	console.log("data==="+data[0].username+"\t object=="+data);
//            $scope.username = data[0].username;
  				$scope.email = data[0].email;
        });
        
        
         $scope.testAccounts = [];
    $http({
            method: 'GET',
            url: 'http://localhost:8080/SpringMvcHibernateXML/users'
           
        }).success(function (result) {
        $scope.testAccounts = result;
    });
    
    $scope.todoList = [{todoText:'Clean House', done:false}];

    $scope.todoAdd = function() {
        $scope.todoList.push({todoText:$scope.todoInput, done:false});
        $scope.todoInput = "";
    };

    $scope.remove = function() {
        var oldList = $scope.todoList;
        $scope.todoList = [];
        angular.forEach(oldList, function(x) {
            if (!x.done) $scope.todoList.push(x);
        });
    };
});
</script>
    </head>
    <body>
    
		<div ng-app="myApp" ng-controller="todoCtrl">
		 Hi {{usersdata.username}}
		 <select ng-model="selectedTestAccount" ng-options="item.id as item.username for item in testAccounts">
    <option value="">Select User</option>
</select>

<a ng-href="">link1</a>

<br>

<a href="#/viewStudents"> View Students List</a>
		</div>

        <div align="center">
	        <h1>Contact List</h1>
        	<table border="1">
	        	<th>No</th>
	        	<th>Username</th>
	        	<th>Email</th>
	        	
				<c:forEach var="user" items="${userList}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${user.username}</td>
					<td>${user.email}</td>
							
	        	</tr>
				</c:forEach>	        	
        	</table>
        </div>
    </body>
</html>
