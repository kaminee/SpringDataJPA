'use strict';

angular.module('myUserApp').factory('CompanyService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/SpringMvcHibernateXML/company/';

    var factory = {
    		fetchCompanys: fetchCompanys,
    		createCompany: createCompany,
    		updateCompany:updateCompany
//        deleteUser:deleteUser
    };

    return factory;

    function fetchCompanys() {
    	console.log("\n\t fetchCompanys service 1");
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"list")
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
    
    function createCompany(company) {
        console.log('REST_SERVICE_URI', REST_SERVICE_URI);
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"create", company)
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

    
    function updateCompany(company, id) {
        var deferred = $q.defer();
        console.log("\n\t url-->"+(REST_SERVICE_URI+id));
        $http.put(REST_SERVICE_URI+id, company)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating company');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);
