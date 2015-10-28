'use strict';

angular
  .module('autorApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'restangular',
    'ngTouch',
    'picardy.fontawesome',
    'naif.base64',
    'angularModalService'
  ])
  .config(function ($routeProvider, RestangularProvider,  $locationProvider) {
    $routeProvider
      .when('/', {
        redirectTo: '/journals'
      })
      .when('/new_journal', {
        templateUrl: 'views/journals/new-journal.html',
        controller: 'NewJournalController'
      })
      .when('/journals', {
        templateUrl: 'views/journals/journals.html',
        controller: 'JournalsController'
      })
      .when('/journal_details/:id', {
        templateUrl: 'views/journals/journal-details.html',
        controller: 'JournalDetailsController'
      })
      .otherwise({
        redirectTo: '/journals'
      });
    RestangularProvider.setBaseUrl('/rest');
  });
