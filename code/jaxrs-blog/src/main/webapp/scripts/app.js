'use strict';

angular.module('jaxrs-blog', [ "ng" ])
// Routing configuration
.config(function($routeProvider, $httpProvider) {
  $routeProvider.when('/', {
    controller : 'ListCtrl',
    templateUrl : 'views/list.html'
  }).when('/edit/:postId', {
    controller : 'EditCtrl',
    templateUrl : 'views/editdetail.html'
  }).when('/view/:postId', {
    controller : 'EditCtrl',
    templateUrl : 'views/viewdetail.html'
  }).when('/new', {
    controller : 'CreateCtrl',
    templateUrl : 'views/editdetail.html'
  }).otherwise({
    redirectTo : '/'
  });

  //By default we communicate in JSON
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
          $location.path("/view/" + $scope.post.id);
        });
      };

      $scope.edit = function() {
        $location.path("/edit/" + $scope.post.id);
      }

      $scope.destroy = function() {
        $http.delete(postUri).then( function() {
          $location.path("/");
        });
      }


      $scope.refreshComments = function() {
        $http.get(postUri + "/comments").then(function(response){
         $scope.comments = response.data.comment;
        });
      }
      $scope.refreshComments();



      $scope.addComment = function() {
       $http.post(postUri + "/comments",{'comment':$scope.comment}).then(function(){
          $scope.refreshComments();
       })
      }
 })
 .controller("CreateCtrl",
    function($scope, $http, $location) {
    $scope.post = {};
      $scope.save = function() {
        $http.post("/jaxrs-blog/api/post", {'post':$scope.post}).then(function(response) {
          $location.path("/view/" + response.data.post.id);
        });
      }
 });