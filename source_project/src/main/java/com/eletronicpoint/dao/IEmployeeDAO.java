package com.eletronicpoint.dao;

import com.eletronicpoint.entities.Employee;

import java.util.List;
import java.util.Optional;

//todas asoperações que serão realizadas sobre o funcionarios

public interface IEmployeeDAO {

    Employee save(Employee employee);
    Employee update(Employee employee);
    Employee delete(Employee employee);
    List<Employee> findAll();
    Employee findById(Long id); // optional pq posso buscar por um id que não existe
    List<Employee> findByRole(Employee employee);

    boolean verfyCredentials(Long employeeID, String password);
}
