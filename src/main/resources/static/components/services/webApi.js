(function () {
    'use strict';
    angular
        .module('app.services')
        .factory('webApi', webApi);

    function webApi($http) {
        var API_VERSION = 'v1';
        var ALL_EMP = '/api/' + API_VERSION + '/employee/all';
        var REG_EMP = '/api/' + API_VERSION + '/register/employee';
        var service = {
            getAllEmployees: getAllEmployees,
            registerEmployee: registerEmployee
        };

        return service;

        function getAllEmployees() {
            return $http.get(ALL_EMP);
        }

        function registerEmployee(params) {
            return $http.post(REG_EMP, params);
        }
    }

})();
