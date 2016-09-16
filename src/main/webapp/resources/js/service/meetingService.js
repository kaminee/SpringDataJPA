'use strict';

angular.module('myUserApp').factory('MeetingService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/SpringMvcHibernateXML/meeting/';

    var factory = {
    		fetchAllMeetings: fetchAllMeetings,
//    		fetchAllEmployeeMeeting:fetchAllEmployeeMeeting,
    		createMeeting: createMeeting,
    		updateMeeting:updateMeeting
//        deleteGroup:deleteGroup
    };

    return factory;

    function fetchAllMeetings() {
//    	console.log("\n\t fetchAllGroups service 1");
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"fetch")
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching meeting');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllEmployeeMeeting() {
    	console.log("\n\t fetchAllEmployeeMeeting service 1");
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"usergroups/fetch")
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching meeting');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createMeeting(meeting) {
        console.log('REST_SERVICE_URI', REST_SERVICE_URI);
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"create", meeting)
            .then(
            function (response) {
            	   console.log('response.data', response.data);
                deferred.resolve(response.data);
            },
            function(errResponse){
            	 console.log('errResponse', errResponse);
                console.error('Error while creating meeting');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateMeeting(meeting, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, meeting)
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

    function deleteMeeting(id) {
    	
        var deferred = $q.defer();
        $http.del(REST_SERVICE_URI+id).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting meeting');
                deferred.reject(errResponse);
            }
        );
        
      
        return deferred.promise;
    }

}]);
