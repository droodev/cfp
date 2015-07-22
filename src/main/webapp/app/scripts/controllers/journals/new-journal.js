'use strict';

angular.module('autorApp')
  .controller('NewJournalController', function ($scope, $location, FileUploader, Restangular) {
    $scope.newJournal = {};
    $scope.uploader = new FileUploader();
    $scope.fileUploaded=false;

    $scope.uploader.onCompleteItem = function(fileItem, response, status, headers) {
      $scope.fileUploaded=true;
      $scope.base64Logo = $scope.base64File.base64;
    };

    $scope.addJournal = function() {
      Restangular.all('journals').customPOST("\""+$scope.base64Logo+"\"","",$scope.newJournal,{}).then(function(res){
        $location.path('/journals');
        $route.reload();
      }, function(res){
        alert(res.status)
      });

    }

  });
