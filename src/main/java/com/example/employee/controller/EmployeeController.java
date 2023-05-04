package com.example.employee.controller;

import com.example.employee.Utils.Url;
import com.example.employee.model.Employee;
import com.example.employee.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "https://localhost:3000")
@RestController
@RequestMapping("/employee-system")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(Url.createUser)
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @GetMapping(Url.employeeList)
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @DeleteMapping(Url.deleteEmployee)
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        boolean deleted = false;
        deleted = employeeService.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping(Url.employeeById)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = null;
        employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping(Url.updateEmployeeById)
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        employee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(employee);
    }
}
