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
    'picardy.fontawesome'
  ])
  .config(function ($routeProvider, RestangularProvider) {
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
      .when('/journals/:id/new_paper', {
        templateUrl: 'views/papers/new-paper.html',
        controller: 'NewPaperController'
      })
      .otherwise({
        redirectTo: '/'
      });
    RestangularProvider.setBaseUrl('http://localhost:8080/');
  });
