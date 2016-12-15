'use strict';

angular.module('myUserApp').controller('EmployeeController', ['$scope', 'EmployeeService','$http', function($scope, EmployeeService,$http) {
	console.log("\n\t stareted "+name);

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

        if(self.emp.employeeId===null){
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
            if(self.emps[i].employeeId == id) {
            	console.log("active==="+self.emps[i].firstname);

                self.emp=angular.copy(self.emps[i]);
                console.log(self.emps[i].company);
                	var id=self.emps[i].company.id;
                		self.emp.company=angular.copy(self.emps[i].company);
                for (i = 0; i < $scope.compArray.length; ++i) {
                	if($scope.compArray[i].id==id){
                		$scope.compArray[i]=self.emp.company;
                	}
                }
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.emp.employeeId === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteCompany(id);
    }
}]);
