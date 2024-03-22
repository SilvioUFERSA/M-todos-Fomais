package com.eletronicpoint.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Register implements Serializable {
        private Long id;
        private LocalDateTime dateEndTime;
        private Type type;
        private String justification;

        // cada registro pertence a somente 1 funcionário
        private Employee employee;
        public Register(){

        }

        public Register(Employee employee,LocalDateTime dateEndTime, Type type) {
                this.id = id;
                this.dateEndTime = dateEndTime;
                this.type = type;
                this.employee = employee;
        }

        public Register(Employee employee, LocalDateTime dateEndTime, Type type, String justification) {
                this.id = id;
                this.employee = employee;
                this.dateEndTime = dateEndTime;
                this.type = type;
                this.justification = justification;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public LocalDateTime getDateEndTime() {
                return dateEndTime;
        }

        public void setDateEndTime(LocalDateTime dateEndTime) {
                this.dateEndTime = dateEndTime;
        }

        public Type getType() {
                return type;
        }

        public void setType(Type type) {
                this.type = type;
        }

        public String getJustification() {
                return justification;
        }

        public void setJustification(String justification) {
                this.justification = justification;
        }

        public Employee getEmployee() {
                return employee;
        }

        public void setEmployee(Employee employee) {
                this.employee = employee;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Register register = (Register) o;
                return Objects.equals(id, register.id);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id);
        }

        @Override
        public String toString() {
                return "Register{" +
                        "id=" + id +
                        ", employee=" + employee.getName() +
                        ", role=" + employee.getRole()+
                        ", dateEndTime=" + dateEndTime +
                        ", type=" + type +
                        ", justification='" + justification + '\'' +
                        '}';
        }
}
