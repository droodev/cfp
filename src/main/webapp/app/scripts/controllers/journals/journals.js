'use strict';

angular.module('autorApp')
  .controller('JournalsController', function ($scope, Restangular) {
    Restangular.all('journals').getList().then(
      function(ret){
      $scope.journals = ret;
    }, function error(reason){
      alert(reason.status)
    });

    $scope.remove = function(){
      alert("Removed")
    }
    //alert($scope.journals)

    /*
    $scope.addJournal = function() {
      Restangular.all('journals').customPOST({},"",$scope.newJournal,{});
    }
    */

  });
