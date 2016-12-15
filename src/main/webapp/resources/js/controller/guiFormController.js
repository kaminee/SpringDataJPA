'use strict';

angular.module('myUserApp').controller('GuiFormController', ['$scope', 'GuiFormService','$http', '$filter', 
                                                             function($scope, GuiFormService,$http ,$filter ) {
	console.log("\n\t stareted ");

	var self = this;
//    self.meeting={meetingId:null,subject:'',meetingDate:null,employees:[]};
    self.guiForm={guiId:null,subject:'',employees:[]};

    self.meetings=[];

//    self.submit = submit;
//    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchGuiForm();
  
    function submit() {
        if(self.guiForm.guiId===null){
            console.log('Saving New meeting', self.guiForm);
//            createMeeting(self.meeting);
        }else{
//        	updateMeeting(self.meeting, self.meeting.meetingId);
            console.log('meeting updated with id ', self.guiForm.guiId);
        }
        reset();
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

    function fetchGuiForm(){
    	GuiFormService.fetchGuiForm()
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
                console.error('Error while fetching Emps');
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
