'use strict';

angular.module('autorApp')
  .controller('JournalsController', function ($scope, Restangular) {
    Restangular.all('journals').getList().then(
      function(ret){
      $scope.journals = ret;
    }, function error(reason){
      alert(reason.status)
    });

    $scope.remove = function(par) {
      Restangular.one('journals', par).remove().then(function(res){
        for(var i = $scope.journals.length - 1; i >= 0; i--) {
          if($scope.journals[i].id === par) {
            $scope.journals.splice(i, 1)
          }
        }
      }, function(res){
        alert(res.status)
      });
    }
  });
