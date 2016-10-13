// MODULE
var angularApp = angular.module('angularApp', []);

// CONTROLLERS
angularApp.controller('mainController', ['$scope', '$http',function ($scope, $http) {
    
    $scope.place = '';
    
    $scope.alertClick = function() {
        $http.get('http://localhost:8080/api/venues/nearby/'+$scope.place).success(function(result) {
        	$scope.venues = result.data.venues;
        });
    };
}]);



