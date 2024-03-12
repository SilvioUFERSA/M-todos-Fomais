package com.eletronicpoint.dao;

import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.infrastructure.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements IEmployeeDAO {
    @Override
    public Employee save(Employee employee) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "INSERT INTO employee (password, name, role) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getPassword());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getRole());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        return null;
    }

    @Override
    public Employee delete(Employee employee) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Employee> findByRole(Employee employee) {
        return null;
    }
}
