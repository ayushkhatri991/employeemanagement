package com.EmployeeManagement.Management.services;

import com.EmployeeManagement.Management.models.Employee;
import com.EmployeeManagement.Management.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServices {

    @Autowired
    EmployeeRepo repo;


    //get all employees
    public List<Employee> getEmployees() {
        return repo.findAll();
    }

    //get employees by id
    public Employee getEmployeeById(long id) {
        return repo.findById(id).orElse(null);
    }

    //create employee
    public Employee postEmployee(Employee employee) {
        return repo.save(employee);
    }

    //update employee
    public Employee updateEmployee(long id, Employee employee) {
        if (!repo.existsById(id)) {
            return null;
        }

        employee.setId(id);
        return repo.save(employee);
    }

    //delete employee
    public void deleteEmployee(long id) {
  repo.deleteById(id);
    }

    //search employee
    public List<Employee> searchEmployees(String keyword) {
        return repo.searchEmployees(keyword);
    }

}

