package com.eletronicpoint.dao;

import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.entities.Register;
import com.eletronicpoint.entities.Type;
import com.eletronicpoint.infrastructure.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements IEmployeeDAO {


    @Override
    public Employee save(Employee employee) {
        String sql = "INSERT INTO employee (id, name, passwordhash, role) VALUES (?,?,?,?)";

        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getPasswordHash());
            preparedStatement.setString(4, employee.getRole());
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
    public Optional<Employee> findById(Long id) {
        String sql = "SELECT * FROM employee WHERE id = ?;";

        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setPasswordHash(resultSet.getString("passwordhash"));
                employee.setRole(resultSet.getString("role"));

                return Optional.ofNullable(employee);
            }
            return null;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> findByRole(Employee employee) {
        return null;
    }
}
