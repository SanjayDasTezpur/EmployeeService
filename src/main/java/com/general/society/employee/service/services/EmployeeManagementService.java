package com.general.society.employee.service.services;

import com.general.society.employee.service.entities.EmployeeEntity;
import com.general.society.employee.service.repository.EmployeeH2Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeManagementService {

    private final EmployeeH2Repository employeeH2Repository;

    public List<EmployeeEntity> getAllEmployees() {
        log.info("Fetching all employess from database...");
        return Optional.ofNullable(employeeH2Repository.findAll()).orElse(new ArrayList<>());
    }

    public EmployeeEntity registerEmployee(String fistName, String lastName, String gender, String dob, String org) {

        try {
            log.info("Registering employee with details {}", fistName);
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setFirstName(fistName);
            employeeEntity.setLastName(lastName);
            employeeEntity.setGender(gender);
            employeeEntity.setDob(dob);
            employeeEntity.setOrganisation(org);
            return (employeeH2Repository.save(employeeEntity));
        } catch (Exception e) {
            log.error("Error: while registering employee with details {}", fistName);
        }
        return null;
    }
}
