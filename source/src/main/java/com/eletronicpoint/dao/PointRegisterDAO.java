package com.eletronicpoint.dao;

import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.entities.PointRegister;
import com.eletronicpoint.infrastructure.ConnectionFactory;

import java.net.ConnectException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class PointRegisterDAO implements IPointRegisterDAO{

    @Override
    public PointRegister save(PointRegister pointRegister) {
        try(Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO pointregister(datehour, type, justfy) VALUES (? , ? , ?)";

                LocalDateTime ldt = pointRegister.getLocalDateTime();
                Timestamp timesTamp = Timestamp.valueOf(ldt);

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, timesTamp);
            preparedStatement.setString(2, pointRegister.getType());
            preparedStatement.setString(3, pointRegister.getJustification());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Long generatedId = resultSet.getLong("id");
            pointRegister.setIdPoint(generatedId);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return pointRegister;
    }

    @Override
    public PointRegister update(PointRegister pointRegister) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<PointRegister> findAll() {
        String slq = "SELECT id, datehour, type, justfy FROM pointregister";

        List<PointRegister> registers = new ArrayList<>();
        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(slq);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                Timestamp timestamp = resultSet.getTimestamp("datehour");
                LocalDateTime datehour = timestamp.toLocalDateTime();
                String type = resultSet.getString("type");
                String justify = resultSet.getString("justfy");

                PointRegister pr = new PointRegister(id, datehour,type,justify);
                registers.add(pr);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return  registers;
    }

    @Override
    public Optional<PointRegister> findByDate(LocalDateTime localDateTime) {
        return Optional.empty();
    }

    @Override
    public List<PointRegister> findByEmployee(Employee employee) {
        return null;
    }
}
