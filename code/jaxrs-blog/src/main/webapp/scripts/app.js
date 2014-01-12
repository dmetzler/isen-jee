'use strict';

angular.module('jaxrs-blog', [ "ng" ])
// Routing configuration
.config(function($routeProvider, $httpProvider) {
  $routeProvider.when('/', {
    controller : 'ListCtrl',
    templateUrl : 'views/list.html'
  }).when('/edit/:postId', {
    controller : 'EditCtrl',
    templateUrl : 'views/detail.html'
  }).when('/new', {
    controller : 'CreateCtrl',
    templateUrl : 'views/detail.html'
  }).otherwise({
    redirectTo : '/'
  });

  $httpProvider.defaults.headers.common.Accept = "application/json";
  $httpProvider.defaults.headers.common['Content-Type'] = "application/json";


})

.controller('ListCtrl', function($scope, $http) {
  $scope.posts = $http.get("/jaxrs-blog/api/post");
})

.controller(
    "EditCtrl",
    function($scope, $http, $routeParams, $location) {
      var postUri = "/jaxrs-blog/api/post/" + $routeParams['postId']
      $http.get(postUri).then(function (response){
        $scope.post = response.data.post;
      });

      $scope.save = function() {
        $http.put(postUri, {'post':$scope.post}).then(function() {
          $location.path("/");
        });
      };

      $scope.destroy = function() {
        $http.delete(postUri).then( function() {
          $location.path("/");
        });
      }
 })
 .controller("CreateCtrl",
    function($scope, $http, $location) {
    $scope.post = {};
      $scope.save = function() {
        $http.post("/jaxrs-blog/api/post", {'post':$scope.post}).then(function() {
          $location.path("/");
        });
      }
 });