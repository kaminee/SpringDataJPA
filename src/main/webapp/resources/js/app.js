'use strict';

var App = angular.module('myUserApp', ['ngRoute'])

.config(function($routeProvider){
 $routeProvider
 	.when('/emp', {
		templateUrl: 'resources/views/employees.html',
//		template:'views/UserGroupManagement.jsp',
//	   	template: '<div class="tab tab1"><p>Caerphilly fromage cheeseburger. Goat fromage frais halloumi melted cheese cheese and biscuits macaroni cheese babybel ricotta. Roquefort croque monsieur babybel fromage frais chalk and cheese bavarian bergkase cream cheese emmental. When the cheese comes out everybody\'s happy camembert de normandie fromage frais ricotta.</p></div>',
		controller: 'EmployeeController as ectrl'
	})
	.when('/showOrders', {
//		templateUrl: 'views/show_orders.jsp',
	   	template: '<div class="tab tab1"><p>Caerphilly fromage cheeseburger. Goat fromage frais halloumi melted cheese cheese and biscuits macaroni cheese babybel ricotta. Roquefort croque monsieur babybel fromage frais chalk and cheese bavarian bergkase cream cheese emmental. When the cheese comes out everybody\'s happy camembert de normandie fromage frais ricotta.</p></div>',

		controller: 'todoCtrl'
      })
     .when('/shop', {
		templateUrl: 'resources/views/company-list.html',
//	   	template: '<div class="tab tab1"><p>Caerphilly fromage cheeseburger. Goat fromage frais halloumi melted cheese cheese and biscuits macaroni cheese babybel ricotta. Roquefort croque monsieur babybel fromage frais chalk and cheese bavarian bergkase cream cheese emmental. When the cheese comes out everybody\'s happy camembert de normandie fromage frais ricotta.</p></div>',
	
//		template:'views/UserGroupManagement.jsp',
		controller: 'CompanyController as sctrl'
	})
    .otherwise({
		redirectTo: '/showUsers'
      });
})

//var appHome = angular.module('myApp',['ngRoute']);


