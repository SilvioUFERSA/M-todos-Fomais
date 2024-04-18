package com.eletronicpoint.entities;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class Employee implements Serializable {
    private Long id;
    private String name;
    private String passwordHash;
    private String role;

    // um funcionário possui uma lista com todas suas entraas e saídas;
    private List<Register> registerList = new ArrayList<>();

    public Employee (){

    }

    public Employee(Long id ,String name, String role){
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Employee(Long id ,String name, String password, String role){
        this.id = id;
        this.name = name;
        this.passwordHash = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public void addRegister(Register register) {
        this.registerList.add(register);
    }
    public void removeRegister(Register register) {
        this.registerList.remove(register);
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(getId(), employee.getId()) && Objects.equals(getPasswordHash(), employee.getPasswordHash());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPasswordHash());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}