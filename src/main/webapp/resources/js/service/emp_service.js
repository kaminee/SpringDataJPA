'use strict';

angular.module('myUserApp').factory('EmployeeService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/SpringMvcHibernateXML/employee/';

    var factory = {
    		fetchEmployees: fetchEmployees,
    		createEmployee: createEmployee,
    		updateEmployee:updateEmployee
//        deleteUser:deleteUser
    };

    return factory;

    function fetchEmployees() {
    	console.log("\n\t fetchEmployees service 1");
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"emp")
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching company');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function createEmployee(emp) {
        console.log('REST_SERVICE_URI', REST_SERVICE_URI);
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"create", emp)
            .then(
            function (response) {
            	   console.log('response.data', response.data);
                deferred.resolve(response.data);
            },
            function(errResponse){
            	 console.log('errResponse', errResponse);
                console.error('Error while creating company');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    
    function updateEmployee(emp, id) {
        var deferred = $q.defer();
        console.log("\n\t url-->"+(REST_SERVICE_URI+id));
        $http.put(REST_SERVICE_URI+id, emp)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating emp');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);
