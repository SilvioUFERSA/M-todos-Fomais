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


//@ public invariant sc != null;
//@ public invariant employeeDAO != null;
//@ public invariant pointRegisterDAO != null;

@SpringBootApplication
public class SourceApplication {

	private static Scanner sc = new Scanner(System.in);
	private static final IEmployeeDAO employeeDAO = DaoFactory.createEmployeeDAO();
	private static final IPointRegisterDAO pointRegisterDAO = DaoFactory.createRegisterDAO();

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(SourceApplication.class, args);
		System.out.println("Bem vindo ao sistema de registro de horários");

		boolean exitRequested = false;

		while (!exitRequested) {
			System.out.println("Digite seu ID:");
			Long id = sc.nextLong();
			sc.nextLine();

			System.out.println("Digite sua senha:");
			String password = sc.nextLine();

			if (employeeDAO.verfyCredentials(id, password)) {
				Employee employee = employeeDAO.findById(id);
				manageUserSession(employee);
			} else {
				exitRequested = shouldExit();
			}
		}
	}

	private static boolean shouldExit() {
		System.out.println("Credenciais inválidas. Deseja sair? (Digite 's' para sair OU 'n' para continuar)");
		return sc.next().equalsIgnoreCase("s");
	}

	private static void manageUserSession(Employee employee) throws FileNotFoundException {
		if (employee.getRole().equalsIgnoreCase("gerente comercial")) {
			if (askForReport(employee)) {
				generateAndPrintReport();
				return;
			}
		}
		registerTime(employee);
	}

	//[RF005] Gerente pode gerar relatório
	//@ requires employee != null && employee.getRole().equalsIgnoreCase("gerente comercial");
	//@ ensures \result == true ==> askForReport() == true;
	private static boolean askForReport(Employee employee) {
		System.out.println("Olá Gerente. Deseja imprimir um relatório?");
		System.out.println("Digite 1 para SIM ou 2 para NÃO");
		return sc.nextInt() == 1;
	}

	private static void generateAndPrintReport() {
		try {
			List<Register> registers = pointRegisterDAO.findAll();
			PDFgenerator pdFgenerator = new PDFgenerator();
			pdFgenerator.generatePDF(registers, "C:\\Cli\\pdfRegister.pdf");
		} catch (FileNotFoundException e) {
			System.err.println("Erro ao gerar o relatório: arquivo não encontrado.");
		}
	}

	//[RF003] Registro de DATA E HORA automática e [RF004] Justificativa do Funcionário
	//@ requires employee != null;
	//@ requires type == Type.ENTRY || type == Type.EXIT;
	//@ ensures justification == null || pointRegisterDAO.save(new Register(employee, LocalDateTime.now(), type, justification));
	private static void registerTime(Employee employee) {
		System.out.println("Digite 1 - ENTRADA do expediente");
		System.out.println("Digite 2 - SAÍDA do expediente");
		Type type = sc.nextInt() == 1 ? Type.ENTRY : Type.EXIT;

		System.out.println("Deseja adicionar alguma justificativa?");
		System.out.println("Digite 1 - se SIM");
		System.out.println("Digite 2 - se NÃO");
		boolean hasJustification = sc.nextInt() == 1;
		sc.nextLine();

		String justification = null;
		if (hasJustification) {
			System.out.println("Digite sua justificativa: ");
			justification = sc.nextLine();
		}

		Register register = new Register(employee, LocalDateTime.now(), type, justification);
		pointRegisterDAO.save(register);
		System.out.println("Ponto registrado com sucesso!");
	}
}

