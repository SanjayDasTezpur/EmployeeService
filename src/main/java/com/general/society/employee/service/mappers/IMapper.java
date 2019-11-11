package com.general.society.employee.service.mappers;

import com.general.society.employee.service.dtos.Employee;
import com.general.society.employee.service.dtos.Employees;
import com.general.society.employee.service.entities.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IMapper {
    Employee toEMployee(EmployeeEntity employeeEntity);

    default Employees toEmployees(List<EmployeeEntity> list) {
        List<Employee> employees = list
                .stream()
                .map(this::toEMployee)
                .collect(Collectors.toList());
        return new Employees(employees);
    }
}
