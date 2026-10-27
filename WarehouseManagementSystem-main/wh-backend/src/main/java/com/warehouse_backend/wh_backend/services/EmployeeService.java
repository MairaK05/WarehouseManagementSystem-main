package com.warehouse_backend.wh_backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.warehouse_backend.wh_backend.entities.EmployeeEntity;
import com.warehouse_backend.wh_backend.repositories.EmployeeRepository;
import com.warehouse_backend.wh_backend.web.errors.NotFoundException;
import com.warehouse_backend.wh_backend.web.models.Employee;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository; //repository for employees and their attributes

    public EmployeeService(EmployeeRepository employeeRepository) { //Constructor for the employee service
        this.employeeRepository = employeeRepository;
    }

    private EmployeeEntity translateWebToDb(Employee employee) { //Translate "Employee" to database entry "EmployeeEntity"
        EmployeeEntity entity = new EmployeeEntity();
        entity.setEmployeeID(employee.getEmployeeID());
        entity.setFirstName(employee.getFirstName());
        entity.setLastName(employee.getLastName());
        entity.setEmail(employee.getEmailAddress());
        entity.setPhoneNumber(employee.getPhoneNumber());
        entity.setAddress(employee.getAddress());
        entity.setJobTitle(employee.getJobTitle());
        return entity;
    }

    public List<Employee> getAllEmployee(String filterEmail){
        List<Employee> employees = new ArrayList<>();
        if (StringUtils.hasLength(filterEmail)) {
            EmployeeEntity entity = this.employeeRepository.findByEmail(filterEmail);
            employees.add(this.translateDbToWeb(entity));
        } else {
            Iterable<EmployeeEntity> entities = this.employeeRepository.findAll();
            entities.forEach(entity-> {
                employees.add(this.translateDbToWeb(entity));
            });
        }
        return employees;
    }

    public Employee getEmployee(long id){
        Optional<EmployeeEntity> optional = this.employeeRepository.findById(id);
        if (optional.isEmpty()){
            throw new NotFoundException("NO EMPLOYEE WITH THIS ID");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Employee createOrUpdate(Employee employee){
        EmployeeEntity entity = this.translateWebToDb(employee);
        entity = this.employeeRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteEmployee(long id) {
        this.employeeRepository.deleteById(id);
    }
    
    private Employee translateDbToWeb(EmployeeEntity entity) { //Translate from Database Entry "EmployeeEntity" to "Employee"
        return new Employee(entity.getEmployeeID(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPhoneNumber(), entity.getAddress(), entity.getJobTitle());
    }
}