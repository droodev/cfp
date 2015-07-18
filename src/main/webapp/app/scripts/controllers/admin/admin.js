'use strict';

angular.module('autorApp')
  .controller('AdminController', function ($scope, $location, Restangular) {
    $scope.newJournal = {};
    $scope.newJournal.logo = "HJJSDJJ";

    $scope.addJournal = function() {
      Restangular.all('journals').customPOST({},"",$scope.newJournal,{}).then(function(res){
        $location.path('/journals');
        $route.reload();
      }, function(res){
        alert(res.status)
      });

    }

  });
