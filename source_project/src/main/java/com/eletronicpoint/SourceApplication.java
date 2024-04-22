package com.eletronicpoint;

import com.eletronicpoint.dao.*;
import com.eletronicpoint.entities.Employee;
import com.eletronicpoint.entities.Register;
import com.eletronicpoint.entities.Type;
import com.eletronicpoint.services.PDFgenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;


@SpringBootApplication
public class SourceApplication {

	public static void main(String[] args) throws FileNotFoundException {


		SpringApplication.run(SourceApplication.class, args);

		IEmployeeDAO employeeDAO = DaoFactory.createEmployeeDAO();
		IPointRegisterDAO pointRegisterDAO = DaoFactory.createRegisterDAO();

		Scanner sc = new Scanner(System.in);

		//Employee employee = new Employee(1000L,"SILVIO MARTINS SANTOS", "xxmyghm", "costureiro");
		//Employee employee1 = new Employee(1001L, "JOICY PEIXOTO DIAS", "abbccc", "gerente comercial");
		//Employee employee2 = new Employee(1002L, "RODRIGO RODRIGUES DA SILVA", "xesqdl123", "aux. administrativo");

		System.out.println("Bem vindo ao sistema de registro de horarios");

		while (true) {
			System.out.println("Digite seu ID:");
			Long id = sc.nextLong();

			System.out.println("Digite sua senha:");
			String password = sc.next();

			if (employeeDAO.verfyCredentials(id, password) == true) {

				Employee employee = employeeDAO.findById(id);

				if (employee.getRole().equalsIgnoreCase("gerente comercial")) {
					System.out.println("Você é um gerente. Deseja imprimir um relatório?");
					System.out.println("Digite 1 para SIM ou 2 para NÃO");
					int printReport = sc.nextInt();
					if (printReport == 1) {
						List<Register> registers = new ArrayList<>();
						registers = pointRegisterDAO.findAll();
						PDFgenerator pdFgenerator = new PDFgenerator();
						pdFgenerator.generatePDF(registers, "C:\\Cli\\pdfRegister.pdf");
						continue;
					}
				}

				System.out.println("Digite 1 - ENTRADA do expediente");
				System.out.println("Digite 2 - SAÍDA do expediente");
				int option = sc.nextInt();

				System.out.println("Deseja adicionar alguma justificativa?");
				System.out.println("Digite 1 - se SIM");
				System.out.println("Digite 2 - se NÃO");
				int justfy = sc.nextInt();

				if (justfy == 1) {
					System.out.println("Digite sua justificativa: ");
					String justification = sc.next();
					if (option == 1) {
						Register register = new Register(employee, LocalDateTime.now(), Type.valueOf("ENTRY"), justification);
						pointRegisterDAO.save(register);
						System.out.println("ponto registrado com sucesso!");

					}
					if (option == 2) {
						Register register = new Register(employee, LocalDateTime.now(), Type.valueOf("EXIT"), justification);
						pointRegisterDAO.save(register);
						System.out.println("ponto registrado com sucesso!");

					}

				}
				if (justfy == 2) {
					if (option == 1) {
						Register register = new Register(employee, LocalDateTime.now(), Type.valueOf("ENTRY"));
						pointRegisterDAO.save(register);
						System.out.println("ponto registrado com sucesso!");

					}
					if (option == 2) {
						Register register = new Register(employee, LocalDateTime.now(), Type.valueOf("EXIT"));
						pointRegisterDAO.save(register);
						System.out.println("ponto registrado com sucesso!");

					}
				}

			}

			else {
				System.out.println("Credenciais inválidas. Deseja sair? (Digite 's' para sair)");
				String sair = sc.next();
				if (sair.equalsIgnoreCase("s")) {
					break; // Sai do loop
				}
			}
		}

	}

}
