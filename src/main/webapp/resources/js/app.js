'use strict';


var App = angular.module('myUserApp', ["ui.router"])

.config(function($stateProvider, $urlRouterProvider){
	$stateProvider
 	.state('emp', {
 		 url: "emp",
		templateUrl: 'resources/views/employees.html',
		controller: 'EmployeeController as ectrl'
	})
	.state('meeting', {
		 url: "meeting",
		templateUrl: 'resources/views/meeting.html',
		controller: 'MeetingController as  mctrl'
	})
	.state('guiForm', {
		 url: "guiForm",
		templateUrl: 'resources/views/guiForm.html',
		controller: 'GuiFormController as  gctrl'
	})
	.state('booking', {
		 url: "booking",
		templateUrl: 'resources/views/booking.html',
		controller: 'BookingController as  bookctrl'
	})
	.state('showOrders', {
		 url: "showOrders",
	   	template: '<div class="tab tab1"><p>Caerphilly fromage cheeseburger. Goat fromage frais halloumi melted cheese cheese and biscuits macaroni cheese babybel ricotta. Roquefort croque monsieur babybel fromage frais chalk and cheese bavarian bergkase cream cheese emmental. When the cheese comes out everybody\'s happy camembert de normandie fromage frais ricotta.</p></div>',
		controller: 'todoCtrl'
      })
      .state('home', {
 		 url: "home",
 		templateUrl: 'resources/views/home.html',
// 	   	template: '<div class="tab tab1"><p>Caerphilly fromage cheeseburger. Goat fromage frais halloumi melted cheese cheese and biscuits macaroni cheese babybel ricotta. Roquefort croque monsieur babybel fromage frais chalk and cheese bavarian bergkase cream cheese emmental. When the cheese comes out everybody\'s happy camembert de normandie fromage frais ricotta.</p></div>',
 		controller: 'homeCtrl'
       })
       .state('/', {
 		 url: "/",
 		templateUrl: 'resources/views/home.html',
// 	   	template: '<div class="tab tab1"><p>Caerphilly fromage cheeseburger. Goat fromage frais halloumi melted cheese cheese and biscuits macaroni cheese babybel ricotta. Roquefort croque monsieur babybel fromage frais chalk and cheese bavarian bergkase cream cheese emmental. When the cheese comes out everybody\'s happy camembert de normandie fromage frais ricotta.</p></div>',
 		controller: 'homeCtrl'
       })
     .state('shop', {
 		 url: "shop",
		templateUrl: 'resources/views/company-list.html',
		controller: 'CompanyController as sctrl'
	});
   /* .otherwise({
		redirectTo: '/showUsers'
      });*/
})
.run( function run($rootScope) {
//	$rootScope.data = {};
	console.log("\n\t ------rootScope----------"+$rootScope);
//    console.log("LOGIN user: " + $rootScope.data.username + " - PW: " + $rootScope.data.password);
})

//var appHome = angular.module('myApp',['ngRoute']);


