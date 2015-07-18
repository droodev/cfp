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
      .when('/admin', {
        templateUrl: 'views/admin/admin.html',
        controller: 'AdminController'
      })
      .when('/journals', {
        templateUrl: 'views/journals/journals.html',
        controller: 'JournalsController'
      })
      .otherwise({
        redirectTo: '/'
      });
    RestangularProvider.setBaseUrl('http://localhost:8080/');
  });
