'use strict';

angular.module('autorApp')
  .controller('AdminController', function ($scope, Restangular) {
    $scope.newJournal = {};
    $scope.newJournal.logo = "HJJSDJJ";

    $scope.addJournal = function() {
      Restangular.all('journals').post($scope.newJournal);
    }

  });
