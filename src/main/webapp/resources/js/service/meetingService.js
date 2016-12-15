'use strict';

angular.module('myUserApp').factory('MeetingService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/SpringMvcHibernateXML/meeting/';

    var factory = {
    		fetchAllMeetings: fetchAllMeetings,
//    		fetchAllEmployeeMeeting:fetchAllEmployeeMeeting,
    		createMeeting: createMeeting,
    		updateMeeting:updateMeeting,
//    		searched:searched,
    		paged:paged,
    		deleteMeeting:deleteMeeting
    		
    };

    return factory;


    /*this.searched = function (valLists, toSearch) {
        return _.filter(valLists,

        function (i) {
             Search Text in all 3 fields 
            return searchUtil(i, toSearch);
        });
    };*/

   function paged(valLists, pageSize) {
      var retVal = [];
      
      if(valLists!=null){
        for (var i = 0; i < valLists.length; i++) {
            if (i % pageSize === 0) {
                retVal[Math.floor(i / pageSize)] = [valLists[i]];
            } else {
                retVal[Math.floor(i / pageSize)].push(valLists[i]);
            }
        }
      }
        return retVal;
    }
   
   
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
    	console.log("\n\t delete id"+id)
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+"remove/"+id).then(
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
