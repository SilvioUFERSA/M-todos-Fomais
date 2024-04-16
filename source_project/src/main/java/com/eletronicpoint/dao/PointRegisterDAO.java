package com.eletronicpoint.dao;

import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.entities.Register;
import com.eletronicpoint.infrastructure.ConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PointRegisterDAO implements IPointRegisterDAO{

    @Override
    public Register save(Register pointRegister) {
        String sql = "INSERT INTO register(employee_id, datehour, type, justification) VALUES (?,?,?,?)";

        LocalDateTime ldt = pointRegister.getDateEndTime();
        Timestamp timesTamp = Timestamp.valueOf(ldt);

        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, pointRegister.getEmployee().getId());
            preparedStatement.setTimestamp(2, timesTamp);
            preparedStatement.setString(3,pointRegister.getType().toString());
            preparedStatement.setString(4, pointRegister.getJustification());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Long generatedID = resultSet.getLong("id");
            pointRegister.setId(generatedID);



        }catch (SQLException e) {
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
        return null;
    }

    @Override
    public Optional<Register> findByDate(LocalDateTime localDateTime) {
        return Optional.empty();
    }

    @Override
    public List<Register> findByEmployee(Employee employee) {
        return null;
    }
}