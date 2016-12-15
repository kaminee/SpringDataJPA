'use strict';

angular.module('myUserApp').controller('BookingController', ['$scope', 'BookingService','$http', '$filter', 
                                                             function($scope, BookingService,$http ,$filter ) {
	console.log("\n\t stareted ");

	var self = this;
//    self.meeting={meetingId:null,subject:'',meetingDate:null,employees:[]};
    self.booking={bookingId:null,bookingType:'',clientId:'',fare:''};

    self.meetings=[];

//    self.submit = submit;
//    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


  
    function submit() {
    	
        if(self.booking.guiId===null){
            console.log('Saving New meeting', self.booking);
//            createMeeting(self.meeting);
        }else{
//        	updateMeeting(self.meeting, self.meeting.meetingId);
            console.log('meeting updated with id ', self.guiForm.guiId);
        }
        reset();
    }
    
    
    $scope.GetValue = function (btype) {
    	$scope.empsarrayele = [];
    	console.log("btype======"+btype)
        fetchGuiForm(btype);
    	 
    	}

    function remove(id){
        console.log('id to be deletedss', id);
        deleteMeeting(id);
    }


    function reset(){
        self.meeting={meetingId:null,subject:'',employees:[]};

//        self.meeting={meetingId:null,subject:'',meetingDate:null,employees:[]};
        $scope.myForm.$setPristine(); //reset Form
    }

    function fetchGuiForm(btype){
        console.log("d=btype==>"+btype);
    	BookingService.fetchGuiForm(btype)
            .then(
            function(d) {
            	
            console.log("d=fetchGuiForm==>"+d);
            	if(d==''){
            		console.log("empty");
            	}else{
            		self.meetings = d;

                	$scope.guiFieldsList=angular.copy(d);
                    console.log("d=guiFieldsList==>"+$scope.guiFieldsList);
                    $scope.filteredList = angular.copy(d);

                	$scope.temp = angular.fromJson(d);
                	
                	var len =d.length;
                	$scope.leng=len;
            	}
            	
            	
            },
            function(errResponse){
                console.error('Error while fetching booking');
            }
        );
    }
    // Calculate Total Number of Pages based on Search Result
  /*  $scope.pagination = function () {
        $scope.ItemsByPage = GuiFormService.paged($scope.filteredList, $scope.pageSize);
    };

    $scope.setPage = function () {
        $scope.currentPage = this.n;
    };

    $scope.firstPage = function () {
        $scope.currentPage = 0;
    };

    $scope.lastPage = function () {
        $scope.currentPage = $scope.ItemsByPage.length - 1;
    };*/

    
    
 

}]);
