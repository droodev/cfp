'use strict';

angular.module('autorApp')
  .controller('NewPaperController', function ($scope, $location, $routeParams, Restangular) {

    $scope.newPaper = {authors: [{}]};
    $scope.journalID = $routeParams.journals;

    Restangular.one('journals', $routeParams.id).get().then(
        function(ret){
          $scope.journal = ret;
        }, function error(reason){
          alert(reason.status)
        });

    $scope.addAuthor = function() {
      $scope.newPaper.authors.push({});
    }

    $scope.deleteAuthor = function(index) {
      $scope.newPaper.authors.splice(index, 1);
    }

    $scope.addPaper = function() {
      Restangular.all('papers').customPOST($scope.newPaper,"",$scope.journalID,{}).then(function(res){
        $location.path('/confirmation');
      }, function(res){
        alert(res.status)
      });

    }

  });
