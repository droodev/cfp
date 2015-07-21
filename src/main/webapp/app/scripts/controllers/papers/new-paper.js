'use strict';

angular.module('autorApp')
  .controller('NewPaperController', function ($scope, $location, $routeParams, Restangular) {

    $scope.newPaper = {authors: [{}]};
    $scope.journalID = $routeParams.journals

    $scope.addAuthor = function() {
      $scope.authors.push({});
    }

    $scope.deleteAuthor = function(index) {
      $scope.authors.splice(index, 1);
    }

    $scope.addPaper = function() {
      Restangular.all('papers').customPOST($scope.newPaper,"",$scope.journalID,{}).then(function(res){
        $location.path('/confirmation');
      }, function(res){
        alert(res.status)
      });

    }

  });
