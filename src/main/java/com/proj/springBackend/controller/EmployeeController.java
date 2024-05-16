package com.proj.springBackend.controller;

import com.proj.springBackend.exception.ResourceNotFoundException;
import com.proj.springBackend.model.Employee;
import com.proj.springBackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

/*    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(("The user is not for this id : "+ id)));

        return ResponseEntity.ok(employee);
    }*/
    //In below getMapping using ResponseEntity<Employee> seems recommended and better option but it works with Employee type also.
    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found by ID : " + id));
    }

    //In update method also it works with we use ResponseEntity<Employee> as type.

    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Employee employee1 = employeeRepository.findById(id).orElse(new Employee());
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setEmail(employee.getEmail());

        employeeRepository.save(employee1);
        return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found by ID : " + id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not found for this id : " + id));
        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //This is also correct.
/*    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeRepository.deleteById(id);
    }*/

    //I am not sure why we are using ResponseEntity as type instead of direct Employee.
}
