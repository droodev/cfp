'use strict';

angular.module('autorApp')
  .controller('NewPaperController', function ($scope, $location, $routeParams, Restangular) {

    $scope.newPaper = {authors: [{}]};
    $scope.journalID = $routeParams.journals;
    $scope.newPaper.authors[0].corresponding = true;

    Restangular.one('journals', $scope.journalID).get().then(
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
      if ($scope.newpaperForm.$valid) {
        var auths = $scope.newPaper.authors
        for (var ind in auths) {
          if (auths[ind].corresponding === "true") {
            auths[ind].correspondencyData = $scope.contactData;
          }
          delete auths[ind].corresponding
        }
        Restangular.all('papers').customPOST($scope.newPaper, "", {journalID: $scope.journalID}, {}).then(function (res) {
          $location.path('/confirmation');
        }, function (res) {
          alert(res.status)
        });
      }
    }

  });
