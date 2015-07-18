'use strict';

angular.module('autorApp')
  .controller('JournalDetailsController', function ($scope, $location, $routeParams, Restangular) {
    Restangular.one('journals', $routeParams.id).get().then(
      function(ret){
        $scope.journal = ret;
      }, function error(reason){
        alert(reason.status)
      });

    Restangular.one('journals', $routeParams.id).all('papers').getList().then(
      function(ret){
        $scope.papers = ret;
      }, function error(reason){
        alert(reason.status)
      });

});
