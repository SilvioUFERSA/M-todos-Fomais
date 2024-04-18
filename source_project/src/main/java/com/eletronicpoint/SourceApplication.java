package com.eletronicpoint;

import com.eletronicpoint.dao.*;
import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.entities.Register;
import com.eletronicpoint.entities.Type;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.*;


@SpringBootApplication
public class SourceApplication {

	public static void main(String[] args) {


		SpringApplication.run(SourceApplication.class, args);

		IEmployeeDAO employeeDAO = DaoFactory.createEmployeeDAO();
		IPointRegisterDAO pointRegisterDAO = DaoFactory.createRegisterDAO();


		Employee employee = new Employee(1000L,"SILVIO MARTINS SANTOS", "xxmyghm", "costureiro");
		Employee employee1 = new Employee(1001L, "JOICY PEIXOTO DIAS", "abbccc", "gerente comercial");
		Employee employee2 = new Employee(1002L, "RODRIGO RODRIGUES DA SILVA", "xesqdl123", "aux. administrativo");

		List<Register> registers = new ArrayList<>();
		registers = pointRegisterDAO.findAll();

		for(Register r : registers){
			System.out.println(r);
		}


	}

}
