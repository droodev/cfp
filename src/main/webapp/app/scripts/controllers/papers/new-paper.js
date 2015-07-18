'use strict';

angular.module('autorApp')
  .controller('NewPaperController', function ($scope, $location, Restangular) {

    $scope.newPaper = {authors: [{}]};

    $scope.addAuthor = function() {
      $scope.authors.push({});
    }

    $scope.deleteAuthor = function(index) {
      $scope.authors.splice(index, 1);
    }

    $scope.addPaper = function() {
      Restangular.all('papers').customPOST({},"",$scope.newPaper,{}).then(function(res){
        $location.path('/confirmation');
      }, function(res){
        alert(res.status)
      });

    }

  });
