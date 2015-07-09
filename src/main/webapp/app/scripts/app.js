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
        templateUrl: 'views/new_paper/new_paper.html',
        controller: 'NewPaperController'
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
