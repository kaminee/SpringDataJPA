'use strict';

angular.module('myUserApp').controller('UserController', ['$scope', 'UserService','$http', function($scope, UserService,$http) {
	console.log("\n\t stareted ");

	var self = this;
    self.user={id:null,username:'',address:'',email:''};
    self.users=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
	console.log("\n\t *****");
//	$scope.users=[];
    $scope.countryAccounts = [];
    $http({
            method: 'GET',
            url: 'http://localhost:8080/SpringMvcHibernateXML/countries'
           
        }).success(function (result) {
        	console.log("\n\t resule"+result);
        $scope.countryAccounts = result;
    });



    fetchAllUsers();
    
    $scope.GetValue = function (fruit) {/*
        var fruitId = $scope.ddlFruits;
        var fruitName = $.grep($scope.Fruits, function (fruit) {
            return fruit.Id == fruitId;
        })[0].Name;
        $window.alert("Selected Value: " + fruitId + "\nSelected Text: " + fruitName);
    */}
    function fetchAllUsers(){
    	console.log("\n\t fetchAllUsers");
        UserService.fetchAllUsers()
            .then(
            function(d) {
            	
            
            	console.log("\n\t fetchAllUsers"+d.length);
            	$scope.userN=d[0].username;
            	
            	self.users = d;
            	$scope.usersList=angular.copy(d);
            	console.log("\n\t self.users-->"+self.users.length+"\t ---->"+self.users);
            	var len =d.length;
            	$scope.leng=len;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

    function createUser(user){
        UserService.createUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }

    function updateUser(user, id){
        UserService.updateUser(user, id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating User');
            }
        );
    }

    function deleteUser(id){
        UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }

    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }


    function reset(){
        self.user={id:null,username:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
