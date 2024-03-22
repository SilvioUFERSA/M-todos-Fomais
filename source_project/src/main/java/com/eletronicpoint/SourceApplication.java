package com.eletronicpoint;

import com.eletronicpoint.dao.*;
import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.entities.Register;
import com.eletronicpoint.entities.Type;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class SourceApplication {

	public static void main(String[] args) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm");
		LocalDateTime dateTime = LocalDateTime.parse("11/03/2014 7:16", formatter);

		SpringApplication.run(SourceApplication.class, args);

		IEmployeeDAO employeeDAO = DaoFactory.createEmployeeDAO();
		IPointRegisterDAO pointRegisterDAO = DaoFactory.createRegisterDAO();

		Employee employee = new Employee("silvio martins santos", "1234", "costureiro");
		System.out.println(employee);

		employeeDAO.save(employee);

		Register register = new Register(employee, LocalDateTime.now(), Type.valueOf("ENTRY"), "atraso por transito");
		System.out.println(register);

		pointRegisterDAO.save(register);

		/*
		EmployeeDAO employeeDAO = new EmployeeDAO();
		PointRegisterDAO prDAO = new PointRegisterDAO();
		List<Register> listRegister = prDAO.findAll();
		List<Employee> listEmployee = employeeDAO.findAll();

		for (Employee e: listEmployee){
			System.out.println("id:" + e.getIdEmployee() + " name:" + e.getName() + " role:" + e.getRole());
		}

		for (Register pr: listRegister){
			System.out.println("id:" + pr.getIdPoint() + " date/hour:" + pr.getLocalDateTime() + " type:" + pr.getType() + " justfy:" + pr.getJustification());
		}


		/*
		Employee employee = new Employee("Xabcaaa", "Rodrigo Rodrigues da Silva", "vendedor");

		PointRegisterDAO pointRegisterDAO = new PointRegisterDAO();
		PointRegister pointRegister = new PointRegister(dateTime, "entrada");

		employeeDAO.save(employee);
		pointRegisterDAO.save(pointRegister);

		System.out.println("id employee:" + employee.getIdEmployee());
		System.out.println("id pointregister" + pointRegister.getIdPoint());
		*/



	}

}
