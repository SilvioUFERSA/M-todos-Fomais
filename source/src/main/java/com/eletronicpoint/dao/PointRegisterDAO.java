package com.eletronicpoint.dao;

import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.entities.PointRegister;
import com.eletronicpoint.infrastructure.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, timesTamp);
            preparedStatement.setString(2, pointRegister.getType());
            preparedStatement.setString(3, pointRegister.getJustification());

            preparedStatement.executeUpdate();
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
        return null;
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
