package com.backend.produtos.produtosestoque.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PacienteTest {
	
	@InjectMocks
	Paciente paciente;
	
	@InjectMocks
	Medicamento medicamento;

	@DisplayName("Teste método para recuperar o id do Paciente. ")
	@Test
	void getIdPaciente() {
		EasyRandom generator = new EasyRandom();
		Paciente mockPaciente = generator.nextObject(Paciente.class);
		
		this.paciente.setId_paciente(mockPaciente.getId_paciente());
		 assertEquals(mockPaciente.getId_paciente(), this.paciente.getId_paciente());
	}
	
	@DisplayName("Teste método para recuperar o nome do Paciente. ")
	@Test
	void getNome() {
		EasyRandom generator = new EasyRandom();
		Paciente mockPaciente = generator.nextObject(Paciente.class);
		
		this.paciente.setNome(mockPaciente.getNome());
		 assertEquals(mockPaciente.getNome(), this.paciente.getNome());
	}
	
	@DisplayName("Teste método para recuperar o email do Paciente. ")
	@Test
	void getEmail() {
		EasyRandom generator = new EasyRandom();
		Paciente mockPaciente = generator.nextObject(Paciente.class);
		
		this.paciente.setEmail(mockPaciente.getEmail());
		assertEquals(mockPaciente.getEmail(), this.paciente.getEmail());
	}
	
	@DisplayName("Teste método para recuperar o convenio do Paciente. ")
	@Test
	void getConvenio() {
		EasyRandom generator = new EasyRandom();
		Paciente mockPaciente = generator.nextObject(Paciente.class);
		
		this.paciente.setConvenio(mockPaciente.getConvenio());
		assertEquals(mockPaciente.getConvenio(), this.paciente.getConvenio());
	}
	
	@DisplayName("Teste método para alocar os medicamentos do Paciente. ")
	@Test
	void getListasMedicamentos() {
		EasyRandom generator = new EasyRandom();
		List<Medicamento> listMedicamento =  generator.objects(Medicamento.class, 3).collect(Collectors.toList());
		
		for(Medicamento ptr : listMedicamento) {
			this.paciente.setListasMedicamentos(ptr);
		}
		
		assertEquals(listMedicamento.size(), this.paciente.getListasMedicamentos().size());
	}

}
