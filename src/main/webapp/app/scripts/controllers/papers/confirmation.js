'use strict';

angular.module('autorApp')
.controller('ConfirmationController', function ($scope, $routeParams, Restangular) {

  $scope.downloadPDF = function(){
    window.open(Restangular.one('papers/pdf', $routeParams.id).getRequestedUrl())
  }
});
