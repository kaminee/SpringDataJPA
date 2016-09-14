'use strict';

angular.module('myUserApp').controller('CompanyController', ['$scope', 'CompanyService','$http', function($scope, CompanyService,$http) {
	console.log("\n\t stareted ");

	var self = this;
   self.company={id:null,name:'',panNumber:'',active:''};
   self.companys=[];
   self.company.active='true';
   self.submit = submit;
   self.edit = edit;
   self.remove = remove;
   self.reset = reset;
   
	console.log("\n\t *****");
//	$scope.users=[];
   /* $http({
            method: 'GET',
            url: 'http://localhost:8080/SpringMvcHibernateXML/countries'
           
        }).success(function (result) {
        	console.log("\n\t resule"+result);
        $scope.countryAccounts = result;
    });*/



    fetchCompanys();
    
    $scope.GetValue = function (fruit) {/*
        var fruitId = $scope.ddlFruits;
        var fruitName = $.grep($scope.Fruits, function (fruit) {
            return fruit.Id == fruitId;
        })[0].Name;
        $window.alert("Selected Value: " + fruitId + "\nSelected Text: " + fruitName);
    */}
    function fetchCompanys(){
    	console.log("\n\t fetchCompanys");
    	CompanyService.fetchCompanys()
            .then(
            function(d) {
            	console.log("\n\t fetchcompanys"+d.length);
             	self.companys = d;
            	$scope.companyList=angular.copy(d);
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
    
    
    function submit() {
        console.log('company C with id ', self.company.id+"\t name==>"+self.company.name);

        if(self.company.id===null){
            console.log('Saving New company', self.company);
            createCompany(self.company);
        }else{
        	updateCompany(self.company, self.company.id);
            console.log('company updated with id ', self.company.id);
        }
        reset();
    }

    function createCompany(company){
    	CompanyService.createCompany(company)
            .then(
            		fetchCompanys,
            function(errResponse){
                console.error('Error while creating company');
            }
        );
    }
    function reset(){
        self.company={id:null,name:'',panNumber:'',active:''};
        $scope.myForm.$setPristine(); //reset Form
    }
    
    
    function updateCompany(company, id){
    	CompanyService.updateCompany(company, id)
            .then(
            		fetchCompanys,
            function(errResponse){
                console.error('Error while updating company');
            }
        );
    }

    function deletecompany(id){
    	CompanyService.deleteCompany(id)
            .then(
            		fetchCompanys,
            function(errResponse){
                console.error('Error while deleting company');
            }
        );
    }

    
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.companys.length; i++){
        	console.log("active==="+self.companys[i].active);
            if(self.companys[i].id === id) {
                self.company = angular.copy(self.companys[i]);
                self.company.active=self.companys[i].active+'';
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.company.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteCompany(id);
    }
}]);
