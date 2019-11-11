(function () {
    'use strict';
    angular
        .module('app.services')
        .controller('navCtrl', navCtrl);

    function navCtrl() {
        var vm = this;
        vm.pageName = 'Employee Dashboard';
        activate();
        ////////////////

        function activate() {
        }
    }
})();
