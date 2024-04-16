package com.eletronicpoint;

import com.eletronicpoint.dao.*;
import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.entities.Register;
import com.eletronicpoint.entities.Type;
import org.hibernate.validator.internal.metadata.aggregated.PotentiallyContainerCascadingMetaData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;


@SpringBootApplication
public class SourceApplication {

	public static void main(String[] args) {


		SpringApplication.run(SourceApplication.class, args);

		IEmployeeDAO employeeDAO = DaoFactory.createEmployeeDAO();
		IPointRegisterDAO pointRegisterDAO = DaoFactory.createRegisterDAO();

		Scanner sc = new Scanner(System.in);

		Employee employee = new Employee("SILVIO MARTINS SANTOS", "xxmyghm", "costureiro");
		employee.setId(1L);


		Register register1 = new Register(employee,LocalDateTime.now(), Type.valueOf("EXIT"), "medico" );
		pointRegisterDAO.save(register1);


	}

}
