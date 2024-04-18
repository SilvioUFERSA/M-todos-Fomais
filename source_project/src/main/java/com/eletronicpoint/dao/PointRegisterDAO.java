package com.eletronicpoint.dao;

import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.entities.Register;
import com.eletronicpoint.entities.Type;
import com.eletronicpoint.infrastructure.ConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PointRegisterDAO implements IPointRegisterDAO {

    @Override
    public Register save(Register pointRegister) {
        String sql = "INSERT INTO register(employee_id, datehour, type, justification) VALUES (?,?,?,?)";

        LocalDateTime ldt = pointRegister.getDateEndTime();
        Timestamp timesTamp = Timestamp.valueOf(ldt);

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, pointRegister.getEmployee().getId());
            preparedStatement.setTimestamp(2, timesTamp);
            preparedStatement.setString(3, pointRegister.getType().toString());
            preparedStatement.setString(4, pointRegister.getJustification());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Long generatedID = resultSet.getLong("id");
            pointRegister.setId(generatedID);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pointRegister;
    }

    @Override
    public Register update(Register register) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Register> findAll() {

        String sql = "SELECT r.*, e.role, e.name AS employee_name " +
                     "FROM register r " +
                     "INNER JOIN employee e ON r.employee_id = e.id;";

        List<Register> allRegisters = new ArrayList<>();

        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("employee_id"));
                employee.setName(resultSet.getString("employee_name"));
                employee.setRole(resultSet.getString("role"));

                Register register = new Register();
                register.setId(resultSet.getLong("id"));
                register.setDateEndTime(resultSet.getTimestamp("datehour").toLocalDateTime());
                register.setType(Type.valueOf(resultSet.getString("type")));
                register.setJustification(resultSet.getString("justification"));
                register.setEmployee(employee);

                allRegisters.add(register);
            }

        }
        catch(SQLException e ){
            throw new RuntimeException(e);
        }

        return allRegisters;
    }

    @Override
    public Optional<Register> findByDate(LocalDateTime localDateTime) {
        return Optional.empty();
    }

    @Override
    public List<Register> findByEmployeeID(Long id) {
        List<Register> registers = new ArrayList<>();

        String sql = "SELECT r.*, e.name, e.role AS role " +
                "FROM register r " +
                "INNER JOIN employee e ON r.employee_id = e.id " +
                "WHERE e.id = ?;";

        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setRole(resultSet.getString("role"));

                Register register = new Register();
                register.setId(resultSet.getLong("id"));
                register.setDateEndTime(resultSet.getTimestamp("datehour").toLocalDateTime());
                register.setType(Type.valueOf(resultSet.getString("type")));
                register.setJustification(resultSet.getString("justification"));
                register.setEmployee(employee);

                registers.add(register);

            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return registers;

    }
}