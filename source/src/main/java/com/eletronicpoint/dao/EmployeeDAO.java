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
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "INSERT INTO employee (password, name, role) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getPassword());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getRole());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

           Long generatedId =  resultSet.getLong("id");

           employee.setIdEmployee(generatedId);

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

        String sql = "SELECT id, name, role FROM employee";

        List<Employee> employees = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String role = resultSet.getString("role");

                Employee employee = new Employee(id, name, role);
                employees.add(employee);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return employees;
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
