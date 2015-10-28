'use strict';

angular
  .module('autorAppPapers', [
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
      .when('/:id', {
        templateUrl: 'views/papers/new-paper.html',
        controller: 'NewPaperController'
      })
      .when('/confirmation/:id', {
        templateUrl: 'views/papers/confirmation.html',
        controller: 'ConfirmationController'
      })
    RestangularProvider.setBaseUrl('/rest');
  });
