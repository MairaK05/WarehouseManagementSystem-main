package com.warehouse_backend.wh_backend.web.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse_backend.wh_backend.services.EmployeeService;
import com.warehouse_backend.wh_backend.web.errors.BadRequestException;
import com.warehouse_backend.wh_backend.web.models.Employee;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll(@RequestParam(name="email", required= false)String email){
        return this.employeeService.getAllEmployee(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee){
        return this.employeeService.createOrUpdate(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id")long id){
        return this.employeeService.getEmployee(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id")long id, @RequestBody Employee employee){
        if(id != employee.getEmployeeID()){
            throw new BadRequestException("MISMATCHED IDs");
        }
        return this.employeeService.createOrUpdate(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id")long id){
        this.employeeService.deleteEmployee(id);
    }
}
