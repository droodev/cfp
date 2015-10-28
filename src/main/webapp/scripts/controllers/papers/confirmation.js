'use strict';

angular.module('autorAppPapers')
.controller('ConfirmationController', function ($scope, $routeParams, Restangular) {

  $scope.downloadPDF = function(){
    window.open(Restangular.one('papers/pdf', $routeParams.id).getRequestedUrl())
  }

});
