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
class MedicoTest {
	
	@InjectMocks
	Medico Medico;
	
	@InjectMocks
	Paciente paciente;

	@DisplayName("Teste método para recuperar o id do Medico. ")
	@Test
	void getIdMedico() {
		EasyRandom generator = new EasyRandom();
		Medico mockMedico = generator.nextObject(Medico.class);
		
		this.Medico.setId_medico(mockMedico.getId_medico());
		 assertEquals(mockMedico.getId_medico(), this.Medico.getId_medico());
	}
	
	@DisplayName("Teste método para recuperar o nome do Medico. ")
	@Test
	void getNome() {
		EasyRandom generator = new EasyRandom();
		Medico mockMedico = generator.nextObject(Medico.class);
		
		this.Medico.setNome(mockMedico.getNome());
		 assertEquals(mockMedico.getNome(), this.Medico.getNome());
	}
	
	@DisplayName("Teste método para recuperar o email do Medico. ")
	@Test
	void getEmail() {
		EasyRandom generator = new EasyRandom();
		Medico mockMedico = generator.nextObject(Medico.class);
		
		this.Medico.setEmail(mockMedico.getEmail());
		assertEquals(mockMedico.getEmail(), this.Medico.getEmail());
	}
	
	@DisplayName("Teste método para recuperar o telefone do Medico. ")
	@Test
	void getTelefone() {
		EasyRandom generator = new EasyRandom();
		Medico mockMedico = generator.nextObject(Medico.class);
		
		this.Medico.setTelefone(mockMedico.getTelefone());
		assertEquals(mockMedico.getTelefone(), this.Medico.getTelefone());
	}
	
	@DisplayName("Teste método para recuperar o endereco do Medico. ")
	@Test
	void getEndereco() {
		EasyRandom generator = new EasyRandom();
		Medico mockMedico = generator.nextObject(Medico.class);
		
		this.Medico.setEndereco(mockMedico.getEndereco());
		assertEquals(mockMedico.getEndereco(), this.Medico.getEndereco());
	}
	
	@DisplayName("Teste método para recuperar o setor do Medico. ")
	@Test
	void getSetor() {
		EasyRandom generator = new EasyRandom();
		Medico mockMedico = generator.nextObject(Medico.class);
		
		this.Medico.setEspecialidade(mockMedico.getEspecialidade());
		assertEquals(mockMedico.getEspecialidade(), this.Medico.getEspecialidade());
	}
	
	@DisplayName("Teste método para alocar os pacientes do Medico. ")
	@Test
	void getListasPacientes() {
		EasyRandom generator = new EasyRandom();
		List<Paciente> listPaciente =  generator.objects(Paciente.class, 3).collect(Collectors.toList());
		
		for(Paciente ptr : listPaciente) {
			this.Medico.setListasPacientes(ptr);
		}
		
		assertEquals(listPaciente.size(), this.Medico.getListasPacientes().size());
	}

}
