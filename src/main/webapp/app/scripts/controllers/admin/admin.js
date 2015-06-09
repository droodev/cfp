'use strict';

angular.module('autorApp')
  .controller('AdminController', function ($scope, JournalService) {
    $scope.journal = {};

    function addJournal() {
      JournalService.create($scope.journal);
    }

  });
