'use strict';

angular.module('autorApp')
  .controller('NewJournalController', function ($scope, $route, $location, Restangular) {
  //.controller('NewJournalController', function ($scope, $route, $location, FileUploader, Restangular) {
    $scope.newJournal = {};
    //$scope.uploader = new FileUploader();
    //$scope.fileUploaded=false;

    // the file input

    var $el = $('#input-1'), initPlugin = function() {
      $el.fileinput({
        //uploadUrl: '/file-upload-single/1',
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

    // initialize plugin
    initPlugin();

    //alert("Initialized")

    /*
    $scope.uploader.onCompleteItem = function(fileItem, response, status, headers) {
      $scope.fileUploaded=true;
      $scope.base64Logo = $scope.base64File.base64;
    };

    $scope.uploader.filters.push({
      name: 'oneFile',
      fn: function(item , options) {
        return this.queue.length < 1;
      }
    });

    $scope.uploader.onAfterAddingFile = function(fileItem) {
      return this.queue[0].upload()
    };
    */

    $scope.addJournal = function() {
      if ($scope.newjournalForm.$valid) {
        $scope.base64Logo = $scope.base64File.base64;
        Restangular.all('journals').customPOST("\"" + $scope.base64Logo + "\"", "", $scope.newJournal, {}).then(function (res) {
          $location.path('/journals');
          $route.reload();
        }, function (res) {
          alert(res.status)
        });
      }
    }

  });
