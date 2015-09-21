'use strict';

angular.module('autorApp')
  .controller('NewJournalController', function ($scope, $route, $location, Restangular) {
    $scope.newJournal = {};
    var $el = $('#input-1'), initPlugin = function() {
      $el.fileinput({
        uploadExtraData: {kvId: '10'},
        dropZoneEnabled: false,
        autoReplace: true,
        allowedFileTypes: ['image'],
        maxFileSize: 48,
        uploadLabel: 'Create journal'
      }).off('filepreupload').on('filepreupload', function() {
        alert("Initial pre-upload message!");
      });
    };
    initPlugin();
    $scope.addJournal = function() {
      if ($scope.newjournalForm.$valid) {
        if($scope.base64File){
          $scope.base64Logo = $scope.base64File.base64;
        }
        Restangular.all('journals').customPOST("\"" + $scope.base64Logo + "\"", "", $scope.newJournal, {}).then(function (res) {
          $location.path('/journals');
          $route.reload();
        }, function (res) {
          alert(res.status)
        });
      }
    }

  });
