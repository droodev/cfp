'use strict';

angular.module('autorApp')
  .controller('NewPaperController', function ($scope, $location, $routeParams, $http, Restangular) {

    $scope.newPaper = {authors: [{}]};
    $scope.journalID = $routeParams.id;
    $scope.selection = 0;
    $scope.contributionSum = 0;

    Restangular.one('journals', $scope.journalID).get().then(
        function(ret){
          $scope.journal = ret;
        }, function error(reason){
          alert(reason.status)
        });

    $scope.updateContributionSum = function() {
      $scope.contributionSum = 0;
      var x;
      for (x in $scope.newPaper.authors) {
        $scope.contributionSum += $scope.newPaper.authors[x].contributionValue;
      }
      $scope.newpaperForm.$setValidity("totalSum", $scope.contributionSum == 100)
    }

    $scope.addAuthor = function() {
      $scope.newPaper.authors.push({});
    }

    $scope.deleteAuthor = function(index) {
      if ($scope.selection >= index){
        $scope.selection = $scope.selection-1
      }
      $scope.newPaper.authors.splice(index, 1);
      $scope.updateContributionSum();
    }

    $scope.addPaper = function() {
      if($scope.newpaperForm.$valid) {
        $scope.newPaper.authors.unshift($scope.newPaper.authors.splice($scope.selection, 1)[0]);
        $scope.newPaper.authors[0].correspondencyData = $scope.contactData;

        $scope.newPaper.signingDate = new Date().getTime()

        $http.get("https://api.ipify.org?format=json").then(function (response) {
          $scope.newPaper.IPAddress = response.data.ip
          Restangular.all('papers').customPOST($scope.newPaper, "", {journalID: $scope.journalID}, {})
            .then(function (res) {
            //That means, that 0 wes returned. It was a correct answer, 200 status OK
            if (typeof res == 'undefined') {
              res = 0
            }
            $location.path('/confirmation/' + res);
          }, function (res) {
            alert(res.status)
          });
        });
      }}})
