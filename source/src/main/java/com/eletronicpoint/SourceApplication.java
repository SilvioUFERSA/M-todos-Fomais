package com.eletronicpoint;

import com.eletronicpoint.dao.EmployeeDAO;
import com.eletronicpoint.dao.PointRegisterDAO;
import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.entities.PointRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class SourceApplication {

	public static void main(String[] args) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm");
		LocalDateTime dateTime = LocalDateTime.parse("11/03/2014 7:16", formatter);

		SpringApplication.run(SourceApplication.class, args);

		EmployeeDAO employeeDAO = new EmployeeDAO();
		Employee employee = new Employee("Xabcd1", "silvio martins santos", "costureiro");

		PointRegisterDAO pointRegisterDAO = new PointRegisterDAO();
		PointRegister pointRegister = new PointRegister(dateTime, "entrada", "atrasado por conta do transito");

		employeeDAO.save(employee);
		pointRegisterDAO.save(pointRegister);
	}

}
