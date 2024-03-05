package com.eletronicpoint.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee implements Serializable {
    private Integer idEmployee;     //identificador unico
    private String password;        //senha
    private String name;            //nome do funcionário
    private String role;            // função (ex: constureiro, vendedor, gerente)

    private List<PointRegister> registers = new ArrayList<>();

    public Employee(String password, String name, String role) {
        this.password = password;
        this.name = name;
        this.role = role;
    }
    public Employee(Integer idEmployee, String password, String name, String role) {
        this.idEmployee = idEmployee;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    // não queremos métodos 'sets' para: password e id;

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordCrypted = encoder.encode(password);
        this.password = passwordCrypted;
    }

    public boolean verifyPassowrd(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, this.password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addRegister(PointRegister pointRegister) {
        this.registers.add(pointRegister);
    }
    public void removeRegister(PointRegister pointRegister) {
        this.registers.remove(pointRegister);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(idEmployee, employee.idEmployee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployee);
    }

    @Override
    public java.lang.String toString() {
        return "id: " + this.idEmployee + ", nome: " + this.name + ", funcao: " + this.role ;
    }
}
