package com.example.em_project;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRespository employeeRespository;

     List<Employee>  employees = new ArrayList<>();

    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRespository.save(employeeEntity);
        
        return "Saved Successfully";
    }

    @Override
    public Employee getEmployee(Long id) {
        EmployeeEntity emp = employeeRespository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(emp, employee);
        //shrey
        return employee;
        
    }
   

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity>   employeesList =  employeeRespository.findAll();
        List<Employee>  employees = new ArrayList<>();
        for(EmployeeEntity employeeEntity : employeesList){
            Employee emp = new Employee();
            emp.setName(employeeEntity.getName());
            emp.setEmail(employeeEntity.getEmail());
            emp.setPhone(employeeEntity.getPhone());
            emp.setId(employeeEntity.getId());
            employees.add(emp);
        }
        
        return employees;
    }

    
    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity emp = employeeRespository.findById(id).get();
        employeeRespository.delete(emp);
        return true;
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmployeeEntity existingemp = employeeRespository.findById(id).get();

        existingemp.setEmail(employee.getEmail());
        existingemp.setName(employee.getName());
        existingemp.setPhone(employee.getPhone());

        
        employeeRespository.save(existingemp);
        return "Updated Successfully";
    }



    

}
