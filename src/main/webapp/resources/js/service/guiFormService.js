'use strict';

angular.module('myUserApp').factory('GuiFormService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/SpringMvcHibernateXML/guiform/';

    var factory = {
    		fetchGuiForm:fetchGuiForm
    };

    return factory;


    
    function fetchGuiForm(meeting, id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"byformtype/login", meeting)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating meeting');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
