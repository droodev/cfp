angular.module('autorApp')
  .factory('authorizationService', function ($resource, $q, $rootScope, $location, ModalService) {
  return {
    isPermissionGranted: false
    ,

    permissionCheck: function () {
      var deferred = $q.defer();
      var parentPointer = this;

      var modal = function() {ModalService.showModal({
        templateUrl: "views/loginModal.html",
        controller: "ModalController"
      }).then(function(modal) {
        modal.element.modal();
        modal.close.then(function(result) {
          parentPointer.isPermissionGranted = result;
          if(result){
            deferred.resolve();
          } else{
            console.log("rerouting")
            window.location = "/#/permDenied"
            $rootScope.$on('$locationChangeSuccess', function (next, current) {
              deferred.resolve();
            });
          }
        });
      });}

      console.log("checking")
      if (this.isPermissionGranted) {
        deferred.resolve();
      } else {
        modal()
      }
      return deferred.promise;
    }
  };
});


/*
 .then(function(modal) {
 modal.element.modal();
 modal.close.then(function(result) {
 parentPointer.permissionModel.isPermissionLoaded = result;
 });
 parentPointer.getPermission(parentPointer.permissionModel, deferred);
 });
 }
 return deferred.promise;
 */
