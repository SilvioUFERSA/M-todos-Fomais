package com.eletronicpoint.dao;

import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.entities.Register;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IPointRegisterDAO {

    Register save(Register pointRegister);
    Register update(Register register);
    void delete(Integer id);
    List<Register> findAll();
    Optional<Register> findByDate(LocalDateTime localDateTime);
    List<Register> findByEmployeeID(Long id);

}
