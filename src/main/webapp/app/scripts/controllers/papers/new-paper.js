'use strict';

angular.module('autorApp')
  .controller('NewPaperController', function ($scope, $location, $routeParams, $http, Restangular) {

    $scope.newPaper = {authors: [{}]};
    $scope.journalID = $routeParams.id;
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
            auths.unshift(auths.splice(ind, 1)[0]);
            break;
          }
        }
        for (var ind in auths) {
          delete auths[ind].corresponding
        }

        $scope.newPaper.signingDate = new Date().getTime()
        $http.get("https://api.ipify.org?format=json").then(function (response) {
          $scope.newPaper.IPAddress = response.data.ip
          alert($scope.newPaper.IPAddress)
          Restangular.all('papers').customPOST($scope.newPaper, "", {journalID: $scope.journalID}, {}).then(function (res) {
            $location.path('/confirmation');
          }, function (res) {
            alert(res.status)
          });
        });
      }}})
