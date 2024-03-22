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
    private byte[] salt;
    private String role;

    // um funcionário possui uma lista com todas suas entraas e saídas;
    private List<Register> registerList = new ArrayList<>();

    public Employee (){

    }
    public Employee(String name, String password, String role){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // seção para tratamento de senhas
                        public boolean verifyPassword(String password) {
                            String passwordHashIn = hashPassword(password, this.salt);
                            return passwordHashIn.equals(this.passwordHash);
                        }

                        private byte[] generateSalt() {
                            byte[] salt = new byte[16];
                            SecureRandom random = new SecureRandom();
                            random.nextBytes(salt);
                            return salt;
                        }

                        private String hashPassword(String password, byte[] salt) {
                            try {
                                PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
                                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                                byte[] hash = factory.generateSecret(spec).getEncoded();
                                return Base64.getEncoder().encodeToString(hash);
                            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                        public String getPasswordHash() {
                            byte [] salt = generateSalt();
                            return hashPassword(this.passwordHash,salt);
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