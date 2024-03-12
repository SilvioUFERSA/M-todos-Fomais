package com.eletronicpoint.dao;

import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.entities.PointRegister;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IPointRegisterDAO {

    PointRegister save(PointRegister pointRegister);
    PointRegister update( PointRegister pointRegister);
    void delete(Integer id);
    List<PointRegister> findAll();
    Optional<PointRegister> findByDate(LocalDateTime localDateTime);
    List<PointRegister> findByEmployee(Employee employee);

}
