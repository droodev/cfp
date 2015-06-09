'use strict';

angular.module('autorApp')
  .service('JournalService', function(Restangular) {

    function create(journal) {
      Restangular.all('journals').post(journal);
    }

  });
