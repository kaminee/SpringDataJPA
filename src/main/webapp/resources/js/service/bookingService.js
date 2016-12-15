'use strict';

angular.module('myUserApp').factory('BookingService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/SpringMvcHibernateXML/guiform/';

    var factory = {
    		fetchGuiForm:fetchGuiForm
    };

    return factory;


    
    function fetchGuiForm(btype) {
    	
    	console.log("\n\t book service -->"+btype)
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"byformtype/"+btype)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating booking');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
