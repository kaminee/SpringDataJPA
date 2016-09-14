'use strict';

angular.module('myUserApp').controller('EmployeeController', ['$scope', 'EmployeeService','$http', function($scope, EmployeeService,$http) {
	console.log("\n\t stareted ");

	var self = this;
   self.emp={employeeId:null,firstname:'',lastname:'',cellphone:'',company:[]};
   self.emps=[];
//   self.emp.active='true';
   self.submit = submit;
   self.edit = edit;
   self.remove = remove;
   self.reset = reset;
   
	console.log("\n\t *****");
//	$scope.users=[];
	 $scope.compArray = [];
	    $http({
	        method: 'GET',
	        url: 'http://localhost:8080/SpringMvcHibernateXML/company/list'
	       
	    }).success(function (result) {
//	    	console.log("\n\t resule"+result);
	    $scope.compArray = result;
	});


	fetchEmployees();
    
    $scope.GetValue = function (fruit) {/*
        var fruitId = $scope.ddlFruits;
        var fruitName = $.grep($scope.Fruits, function (fruit) {
            return fruit.Id == fruitId;
        })[0].Name;
        $window.alert("Selected Value: " + fruitId + "\nSelected Text: " + fruitName);
    */}
    function fetchEmployees(){
    	console.log("\n\t fetchEmployees");
    	EmployeeService.fetchEmployees()
            .then(
            function(d) {
            	console.log("\n\t fetchEmployees=="+d.length);
             	self.emps = d;
            	$scope.employeeList=angular.copy(d);
            },
            function(errResponse){
                console.error('Error while fetching Employees');
            }
        );
    }
    
    
    function submit() {
        console.log('company C with id ', self.emp.employeeId+"\t name==>"+self.emp.firstname);

        if(self.company.id===null){
            console.log('Saving New company', self.emp);
            createEmployee(self.emp);
        }else{
        	updateEmployee(self.emp, self.emp.employeeId);
            console.log('company updated with id ', self.emp.employeeId);
        }
        reset();
    }

    function createEmployee(emp){
    	EmployeeService.createEmployee(emp)
            .then(
            		fetchEmployees,
            function(errResponse){
                console.error('Error while creating Employee');
            }
        );
    }
    function reset(){
        self.company={id:null,name:'',panNumber:'',active:''};
        $scope.myForm.$setPristine(); //reset Form
    }
    
    
    function updateEmployee(emp, id){
    	EmployeeService.updateEmployee(emp, id)
            .then(
            		fetchEmployees,
            function(errResponse){
                console.error('Error while updating Employee');
            }
        );
    }

    function deleteEmployee(id){
    	CompanyService.deleteEmployee(id)
            .then(
            		fetchEmployees,
            function(errResponse){
                console.error('Error while deleting Employee');
            }
        );
    }

    
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.emps.length; i++){
        	console.log("active==="+self.emps[i].firstname);
            if(self.emps[i].id === id) {
                self.emp= angular.copy(self.emps[i]);
//                self.company.active=self.emps[i].active+'';
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.emp.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteCompany(id);
    }
}]);
