package com.eletronicpoint;

import com.eletronicpoint.entities.Employee;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SourceApplicationTests {
	/*
	@Test
	void Employees() {
		Employee employee = new Employee(1, "123", "silvio martins santos", "gerente");
		Employee employee1 = new Employee(1, "123", "silvio martins santos", "gerente");
		assertEquals(employee, employee1);
	}
	@Test
	public void testCreateEmployee() {
		Integer idEsperado = 1;
		String senhaEsperada = "senha123";
		String nomeEsperado = "João";
		String funcaoEsperada = "Vendedor";

		Employee employee = new Employee(idEsperado, senhaEsperada, nomeEsperado, funcaoEsperada);

		assertEquals(idEsperado, employee.getIdEmployee());
		assertEquals(senhaEsperada, employee.getPassword());
		assertEquals(nomeEsperado, employee.getName());
		assertEquals(funcaoEsperada, employee.getRole());
	}

	@Test
	public void testForToString() {
		Integer id = 1;
		String nome = "João";
		String funcao = "Vendedor";

		String resultadoEsperado = "id: 1, nome: João, funcao: Vendedor";

		Employee employee = new Employee(id, "senha123", nome, funcao);

		assertEquals(resultadoEsperado, employee.toString());
	}


	@Test
	public void testCreatePointRegister() {

		LocalDateTime dataHoraEsperada = LocalDateTime.of(2024, 3, 1, 8, 0); // 01/03/2024 08:00
		String tipoEsperado = "Entrada";
		String justificativaEsperada = "Atraso de 5 minutos";


		PointRegister pointRegister = new PointRegister(1, dataHoraEsperada, tipoEsperado, justificativaEsperada);


		assertEquals(1, pointRegister.getIdPoint().intValue());
		assertEquals(dataHoraEsperada, pointRegister.getLocalDateTime());
		assertEquals(tipoEsperado, pointRegister.getType());
		assertEquals(justificativaEsperada, pointRegister.getJustification());
	}

	@Test
	public void testToString() {

		LocalDateTime dataHora = LocalDateTime.of(2024, 3, 1, 8, 0); // 01/03/2024 08:00
		String tipo = "Entrada";
		String justificativa = "Atraso de 5 minutos";
		String resultadoEsperado = "id:1\n data/Hora:01/03/2024 08:00\n tipo:Entrada\n justificativa:Atraso de 5 minutos";

		PointRegister pointRegister = new PointRegister(1, dataHora, tipo, justificativa);
		assertEquals(resultadoEsperado, pointRegister.toString());
	}

*/
}
