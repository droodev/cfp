'use strict';

angular.module('autorApp')
  .controller('AdminController', function ($scope, Restangular) {
    $scope.newJournal = {};

    $scope.addJournal = function() {
      Restangular.all('journals').post($scope.newJournal);
    }

  });
