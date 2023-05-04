package com.example.employee.services;

import com.example.employee.entity.EmployeeEntity;
import com.example.employee.model.Employee;
import com.example.employee.repasitory.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = merge(employee);
        employeeRepository.save(employeeEntity);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employees = employeeEntities.stream().map(x -> new Employee(x.getId(), x.getFirstName(), x.getLastName(), x.getEmailId()))
                .collect(Collectors.toList());
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get(); // TODO: add exceptions
        employeeRepository.delete(employeeEntity);
        return true;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setEmailId(employee.getEmailId());
        employeeRepository.save(employeeEntity);
        return employee;
    }

    @Override
    public EmployeeEntity merge(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setEmailId(employee.getEmailId());

        return employeeEntity;
    }
}
