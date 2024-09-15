package com.example.em_project;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class EmpController {

    @Autowired
    EmployeeService employeeService = new EmployeeServiceImpl();
        

    @GetMapping("employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("employees/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return employeeService.getEmployee(id);
    }

  
    
    @PostMapping("employees")
    public String createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
        
    }


    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable Long id){
       if (employeeService.deleteEmployee(id))
                return "Deleted Successfully";
        return "Not Found";
    }

    @PutMapping("employees/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }   

    
}
