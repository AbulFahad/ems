package com.proj.springBackend.controller;

import com.proj.springBackend.model.Employee;
import com.proj.springBackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
       return employeeRepository.save(employee);
    }

    @GetMapping({"/id"})
    public Optional<Employee> getEmployeeById(@PathVariable String id){
        return Optional.of(employeeRepository.findById(Long.valueOf(id)).orElse(new Employee(0L, "UNKNOWN", "UNKNOWN", "UNKNOWN")));
    }
}
