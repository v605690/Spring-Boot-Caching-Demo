package com.crus.cache.controller;

import com.crus.cache.model.Employee;
import com.crus.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employee = employeeService.saveEmployee(employee);
        return ResponseEntity.created(URI.create("/api/employees" + employee.getId()))
                .body(employee);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("employees/{employeeId}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable(value = "employeeId") Integer employeeId) {
        return ResponseEntity.ok(employeeService.getEmployee(employeeId));
    }

    @PutMapping("employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable(value = "employeeId") Integer employeeId,
            @RequestBody Employee employeeDetails) {

        Employee employee = employeeService.getEmployee(employeeId);
        employee.setName(employeeDetails.getName());
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok().build();
    }
}
