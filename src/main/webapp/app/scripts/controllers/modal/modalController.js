angular.module('autorApp').controller('ModalController', function($scope, close, $element) {

  $scope.closeOK = function(){
    $element.modal('hide');
    close($scope.pass == "admin" && $scope.login=="admin",1000);
  }

  $scope.closeCancel = function(){
    $element.modal('hide');
    close(false,1000);
  }

});
