package com.general.society.employee.service.ifaces;

import com.general.society.employee.service.dtos.Employee;
import com.general.society.employee.service.dtos.Employees;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface IEmployeeController {

    @ApiOperation(value = "Register an employee")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = API.REGISTER)
    Employee registerEmployee(@RequestBody @Valid Employee employee, HttpServletResponse response);

    @ApiOperation(value = "Get All Employees",
            response = Employees.class)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = API.EMPLOYEES)
    Employees getAllEmployees();

    @RequestMapping(value = "/explorer", method = RequestMethod.GET)
    void explorer(HttpServletResponse res);

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    class API {
        static final String EMPLOYEES= "/api/v1/employee/all";
        static final String REGISTER = "/api/v1/register/employee";
    }
}
