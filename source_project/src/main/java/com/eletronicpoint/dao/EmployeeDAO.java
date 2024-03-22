package com.eletronicpoint.dao;

import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.infrastructure.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements IEmployeeDAO {


    @Override
    public Employee save(Employee employee) {
        String sql = "INSERT INTO employee (name, passwordhash, role) VALUES (?,?,?)";

        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPasswordHash());
            preparedStatement.setString(3, employee.getRole());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Long generatedID = resultSet.getLong("id");

            employee.setId(generatedID);

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
