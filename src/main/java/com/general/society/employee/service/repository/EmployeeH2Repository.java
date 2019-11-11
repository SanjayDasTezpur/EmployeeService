package com.general.society.employee.service.repository;

import com.general.society.employee.service.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeH2Repository extends CrudRepository<EmployeeEntity, Integer> {
        List<EmployeeEntity> findAll();
}
