'use strict';

angular.module('autorApp')
  .controller('JournalDetailsController', function ($scope, $location, $routeParams, Restangular) {

    Restangular.one('journals', $routeParams.id).get().then(
      function(ret){
        $scope.journal = ret;
        $scope.addingLink = getAddingLink();
        $scope.notSecuredAddingLink = getNotSecuredAddingLink();
      }, function error(reason){
        alert(reason.status)
      });

    Restangular.one('journals', $routeParams.id).all('papers').getList().then(
      function(ret){
        $scope.papers = ret;
      }, function error(reason){
        alert(reason.status)
      });

    $scope.openLinkModal = function() {
      $("#linkModal").modal('show');
    }

    $scope.openDeleteConfirmModal = function(paper) {
      $scope.paper = paper;
      $("#deletePaperConfirmModal").modal('show');
    }

    $scope.deletePaper = function(id){
      Restangular.one('papers', id).remove().then(function(res){
        for(var i = $scope.papers.length - 1; i >= 0; i--) {
          if($scope.papers[i].id === id) {
            $scope.papers.splice(i, 1)
          }
        }
      }, function(res){
        alert(res.status)
      });
      $("#deletePaperConfirmModal").modal('hide');
    }

    $scope.openPDF = function(id){
      window.open(Restangular.one('papers/pdf', id).getRequestedUrl())
    }

    var getAddingLink = function(){
      return $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/adding.html#/" +
          $scope.journal.id
    }

    var getNotSecuredAddingLink = function(){
      return "http://" + $location.host() + "/adding.html#/" +
        $scope.journal.id
    }
});l
