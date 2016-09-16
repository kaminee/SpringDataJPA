'use strict';

var App = angular.module('myUserApp', ['ngRoute'])

.config(function($routeProvider){
 $routeProvider
 	.when('/emp', {
		templateUrl: 'resources/views/employees.html',
		controller: 'EmployeeController as ectrl'
	})
	.when('/meeting', {
		templateUrl: 'resources/views/meeting.html',
		controller: 'MeetingController as  mctrl'
	})
	.when('/showOrders', {
	   	template: '<div class="tab tab1"><p>Caerphilly fromage cheeseburger. Goat fromage frais halloumi melted cheese cheese and biscuits macaroni cheese babybel ricotta. Roquefort croque monsieur babybel fromage frais chalk and cheese bavarian bergkase cream cheese emmental. When the cheese comes out everybody\'s happy camembert de normandie fromage frais ricotta.</p></div>',

		controller: 'todoCtrl'
      })
     .when('/shop', {
		templateUrl: 'resources/views/company-list.html',
		controller: 'CompanyController as sctrl'
	})
    .otherwise({
		redirectTo: '/showUsers'
      });
})

//var appHome = angular.module('myApp',['ngRoute']);


