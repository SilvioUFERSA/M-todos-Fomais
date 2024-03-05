package com.eletronicpoint;

import com.eletronicpoint.dao.EmployeeDAO;
import com.eletronicpoint.entities.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class SourceApplication {

	public static void main(String[] args) {

		SpringApplication.run(SourceApplication.class, args);

		EmployeeDAO employeeDAO = new EmployeeDAO();

		Employee employee1 = new Employee(1, "1234", "silvio martins santos", "costureiro");
		Employee employee2 = new Employee("1234", "silvio martins santos", "costureiro");

		employeeDAO.save(employee1);
		employeeDAO.save(employee2);
	}

}
