package com.eletronicpoint.dao;

import com.eletronicpoint.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeDao {

    Employee save(Employee employee);
    Employee update(Employee employee);
    Employee delete(Employee employee);
    List<Employee> findAll();
    Optional<Employee> findById(Integer id); // optional pq posso buscar por um id que não existe
    List<Employee> findByRole(Employee employee);
}
