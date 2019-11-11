(function () {
    'use strict';
    angular
        .module('app.home')
        .controller('homeCtrl', homeCtrl);

    function homeCtrl(webApi) {
        var vm = this;
        vm.gridApi = undefined;
        vm.pageName = 'Employee Dashboard';
        vm.register = register;
        vm.employeeList = [];
        vm.register = register;
        vm.dataLength = 0;
        var colDef = [
            { headerName: 'First Name', field: 'firstName', width: 100, filter: 'agTextColumnFilter' },
            { headerName: 'Last Name', field: 'lastName', width: 100, filter: 'agTextColumnFilter' },
            { headerName: 'Gender', field: 'gender', width: 100, filter: 'agTextColumnFilter' },
            { headerName: 'DOB', field: 'dob', width: 100, filter: 'agTextColumnFilter' },
            { headerName: 'Department', field: 'organisation', filter: 'agTextColumnFilter' }
        ];

        activate();
        ////////////////

        function activate() {
            createGrid();
            _setupMainView();
        }

        function _setupMainView() {
            getAllEmployees();
        }

        function  resetFields(){
            vm.fName = "";
            vm.lName = "";
            vm.gender = "";
            vm.dob = "";
            vm.dept = "";
        }

        function register(){
            var params = {
                'firstName' : vm.fName,
                'lastName': vm.lName,
                'gender': vm.gender,
                'dob': vm.dob,
                'organisation': vm.dept
            };
            webApi.registerEmployee(params).then(function (response)
            {
                if (response) {
                    vm.employee = response.data;
                    resetFields();
                    if(vm.gridApi){
                        vm.employeeList.push(params);
                        sortByfirstName(vm.employeeList);
                        vm.gridApi.api.setRowData(vm.employeeList);
                    }
                    getAllEmployees();
                }
            }).catch(function () {
                window.alert("Something went wrong ! Could not Register Employee");
            });
        }
        function getAllEmployees(){
            webApi.getAllEmployees().then(function (response)
            {
                if (response) {
                    vm.employeeList = response.data.employees;
                    sortByfirstName(vm.employeeList);
                    vm.gridOptions.data = vm.employeeList;
                    vm.dataLength = vm.gridOptions.data.length;
                    createGrid();
                }
            });
        }

        function sortByfirstName(arr){
            arr.sort(function(emp1, emp2)
            {
                return emp1.firstName.toLowerCase() < emp2.firstName.toLowerCase() ? -1 : 1;
            });

        }
        function createGrid(){
            vm.gridOptions = {
                defaultColDef: {
                    resizable: true,
                    sortable: true,
                    filter: true,
                },
                floatingFilter: true,
                onGridReady: getGrid,
                debug: true,
                columnDefs: colDef,
                rowData: vm.employeeList
            };
        }

        function getGrid(gridApi) {
            vm.gridApi = gridApi;
            gridApi.api.sizeColumnsToFit();
        }
    }
})();
