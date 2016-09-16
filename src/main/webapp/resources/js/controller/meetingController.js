'use strict';

angular.module('myUserApp').controller('MeetingController', ['$scope', 'MeetingService','$http', function($scope, MeetingService,$http) {
	console.log("\n\t stareted ");

	var self = this;
//    self.meeting={meetingId:null,subject:'',meetingDate:null,employees:[]};
    self.meeting={meetingId:null,subject:'',employees:[]};

    self.meetings=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
	console.log("\n\t *****");
//	$scope.users=[];
	$scope.countemps=[];
    $scope.meetingArray = [];

    $scope.empsarrayele = [];
 /*   $http({
            method: 'GET',
            url: 'http://localhost:8080/SpringMvcHibernateXML/meeting/fetch'
           
        }).success(function (result) {
        $scope.meetingArray = result;
    });
*/
    
    $scope.emplArray = [];
    $http({
        method: 'GET',
        url: 'http://localhost:8080/SpringMvcHibernateXML/employee/emp'
       
    }).success(function (result) {
//    	console.log("\n\t resule"+result);
    $scope.emplArray = result;
});
    
    
    fetchAllMeetings();
//    fetchAllEmployeeMeeting();
    
    $scope.GetValue = function (count) {
    	console.log("count======"+count)
    	console.log("count======"+count.id)
//    	 $scope.countusers = count.id;
    	$scope.empsarrayele.push(count);
    	 $scope.countemps=count;
    		console.log("$scope.empsarrayele======"+$scope.empsarrayele)
//    	 console.log("scope.name = "+count.name);
    	 
//    	 console.log("\n\t $scope.id "+$scope.id+"\t ==="+$scope.name);
    	 
    	}
    function fetchAllMeetings(){
    	MeetingService.fetchAllMeetings()
            .then(
            function(d) {
            	
            console.log("d===>"+d);
//            	$scope.userN=d[0].username;
            	if(d==''){
            		console.log("empty");
            	}else{
            		self.meetings = d;
                	$scope.meetingList=angular.copy(d);
//                	console.log("\n\t self.users-->"+self.group.length+"\t angular.copy---->"+angular.copy(d));
                	
                	$scope.temp = angular.fromJson(d);
                	
//                	console.log("\n\t $scope.temp-->"+$scope.temp.country);
                	var len =d.length;
                	$scope.leng=len;
            	}
            	
            	
            },
            function(errResponse){
                console.error('Error while fetching Emps');
            }
        );
    }

    
    function fetchAllEmployeeMeeting(){
    	MeetingService.fetchAllEmployeeMeeting()
            .then(
            function(d) {
            	
            
//            	console.log("\n\t fetchAllUsersGroups"+d.length);
//            	$scope.userN=d[0].username;
            	
            	self.meetings = d;
            	$scope.meetingList=angular.copy(d);
//            	console.log("\n\t self.usersgroups-->"+self.group.length+"\t angular.copy---->"+angular.copy(d));
            	
            	$scope.temp = angular.fromJson(d);
            	
//            	console.log("\n\t $scope.temp-->"+$scope.temp.country);
            	var len =d.length;
            	$scope.leng=len;
            },
            function(errResponse){
                console.error('Error while fetching Emps');
            }
        );
    }

    function createMeeting(meeting){
    	console.log("Selected Value: " +$scope.countemps + "\nSelected Text: "+$scope.empsarrayele );

    	meeting.employees=$scope.empsarrayele;

    	MeetingService.createMeeting(meeting)
            .then(
            		fetchAllMeetings,
            function(errResponse){
                console.error('Error while creating meeting');
            }
        );
    }

    function updateMeeting(meeting, id){
    	meeting.employees=$scope.empsarrayele;

//    	group.users=[{id:group.usersarr}];
    	console.log("\n\t\t ---meeting-->"+meeting+"\t\t meeting.employees-->"+meeting.employees)
    	MeetingService.updateMeeting(meeting, id)
            .then(
            		fetchAllMeetings,
            function(errResponse){
                console.error('Error while updating meeting');
            }
        );
    }

    function deleteMeeting(id){
       /* UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );*/
    	  $http({
              method: 'DELETE',
              url: 'http://localhost:8080/SpringMVCHibernate/meeting/' + id,
            
              headers: {
                  'Content-type': 'application/json;charset=utf-8'
              }
          })
          .then(function(response) {
              console.log(response.data);
          }, function(rejection) {
              console.log(rejection.data);
          });
    }

    function submit() {
        if(self.meeting.meetingId===null){
            console.log('Saving New meeting', self.meeting);
            createMeeting(self.meeting);
        }else{
        	updateMeeting(self.meeting, self.meeting.meetingId);
            console.log('meeting updated with id ', self.group.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        console.log('id to be self.meetings', self.meetings);

        for(var i = 0; i < self.meetings.length; i++){
            if(self.meetings[i].id === id) {
                self.selEmployee = angular.copy(self.meetings[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.meeting.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteMeeting(id);
    }


    function reset(){
        self.meeting={meetingId:null,subject:'',employees:[]};

//        self.meeting={meetingId:null,subject:'',meetingDate:null,employees:[]};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
