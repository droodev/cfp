'use strict';

angular.module('autorApp')
  .controller('NewPaperController', function ($scope) {

    $scope.authors = [{}];

    $scope.addAuthor = function() {
      $scope.authors.push({});
    }

    $scope.deleteAuthor = function(index) {
      $scope.authors.splice(index, 1);
    }

    $scope.addPaper = function() {
      console.log("Adding paper");
    }

  });
