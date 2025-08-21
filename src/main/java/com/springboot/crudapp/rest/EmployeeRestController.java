package com.springboot.crudapp.rest;

//import com.springboot.crudapp.dao.EmployeeDAO;
import com.springboot.crudapp.entity.Employee;
import com.springboot.crudapp.service.EmployeeService;
import jakarta.persistence.EntityManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    // quick and dirty solution : inject employee dao (use constructor injection)
/*
   private EmployeeDAO employeeDAO;

   public EmployeeRestController(EmployeeDAO theEmployeeDAO)
   {
        employeeDAO = theEmployeeDAO;
   }

    // expose "/employees" endpoint and return a list of employees

    @GetMapping("/employees")
    public List<Employee> findAll()
    {
        return employeeDAO.findAll();
    }
 */

    // Best solution - using Service layer

    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }

    // return a list of all employees
    @GetMapping("/employees")
    public List<Employee> findAll()
    {
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{empId}
    @GetMapping("/employees/{empId}")
    public Employee getEmployee(@PathVariable int empId)
    {
        Employee employee = employeeService.findById(empId);

        if(employee == null)
            throw new RuntimeException("Employee id not found - " + empId);

        return employee;
    }

    // add mapping for POST /employees - add new employee

    // @RequestBody is used to handle JSON data binding in our employee object
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee)
    {
        // Just in case, they pass an id in JSON -> set id to 0.
        // Coz this is to force a 'save' of new item instead of doing an update.
        employee.setId(0);

        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    // Add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee)
    {
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    // Add mapping for DELETE /employees/{empId} - Deleting that employe

    @DeleteMapping("/employees/{empId}")
    public String deleteEmployee(@PathVariable int empId)
    {
        Employee employee = employeeService.findById(empId);
        // throw exception if id is null
        if(employee == null)
            throw new RuntimeException("Employee id not found - " + empId);

        employeeService.deleteById(empId);
        return "Deleted employee Id - " + empId;
    }

}
