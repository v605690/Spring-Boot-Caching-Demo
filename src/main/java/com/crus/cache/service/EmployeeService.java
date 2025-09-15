package com.crus.cache.service;

import com.crus.cache.exceptions.ResourceNotFoundException;
import com.crus.cache.model.Employee;
import com.crus.cache.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getEmployee(Integer employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found: " + employeeId));
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
