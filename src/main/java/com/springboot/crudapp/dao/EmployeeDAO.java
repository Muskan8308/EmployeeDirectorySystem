package com.springboot.crudapp.dao;

import com.springboot.crudapp.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
