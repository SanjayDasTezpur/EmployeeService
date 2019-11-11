package com.general.society.employee.service.controller;

import com.general.society.employee.service.dtos.Employee;
import com.general.society.employee.service.dtos.Employees;
import com.general.society.employee.service.entities.EmployeeEntity;
import com.general.society.employee.service.exceptions.BadParamsException;
import com.general.society.employee.service.ifaces.IEmployeeController;
import com.general.society.employee.service.mappers.IMapper;
import com.general.society.employee.service.services.EmployeeManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@Slf4j
public class EmployeeController implements IEmployeeController {

    private final EmployeeManagementService employeeManagementService;
    private final IMapper mapper;

    @Override
    public Employee registerEmployee(@Valid Employee employee, HttpServletResponse response) {
        EmployeeEntity employeeEntity = employeeManagementService.registerEmployee(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getGender(),
                employee.getDob(),
                employee.getOrganisation());
        if (null == employeeEntity) {
            throw new BadParamsException("Missing fields");
        }
        return mapper.toEMployee(employeeEntity);
    }

    @Override
    public Employees getAllEmployees() {
        return mapper.toEmployees(employeeManagementService.getAllEmployees());
    }

    @Override
    public void explorer(HttpServletResponse res) {
        try {
            res.sendRedirect("/swagger-ui.html");
        } catch (IOException e) {
            log.error("Caught I/O Exception Could not redirect to swagger ui " + e);
        } catch (Exception e) {
            log.error("Caught Exception Could not redirect to swagger ui " + e);
        }
    }
}
