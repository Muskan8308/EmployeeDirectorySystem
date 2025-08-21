package com.springboot.crudapp.dao;

import com.springboot.crudapp.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOJpaImp implements EmployeeDAO {

    // define fields for entity manager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOJpaImp(EntityManager theEntityManager)
    {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll()
    {

        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }

    // get employee of that particular id
    @Override
    public Employee findById(int id) {

        Employee theEmployee = entityManager.find(Employee.class, id);
        return theEmployee;
    }

    // merge() - if id == 0 then it will create a new entry (insert/save), else it will update the existing one.
    @Override
    public Employee save(Employee employee) {

        Employee dbEmployee = entityManager.merge(employee);
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
        // find employee by id
        Employee employee = entityManager.find(Employee.class, id);
        // delete that entry
        entityManager.remove(employee);
    }

}
