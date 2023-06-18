package com.backend.produtos.produtosestoque.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.stream.Collectors;

@SpringBootTest
class EnfermeiroTest {
	
	@InjectMocks
	Enfermeiro enfermeiro;
	
	@InjectMocks
	Pratileira pratileira;
	
	@DisplayName("Teste método para recuperar o id do enfermeiro. ")
	@Test
	void getIdEnfermeiro() {
		EasyRandom generator = new EasyRandom();
		Enfermeiro mockEnfermeiro = generator.nextObject(Enfermeiro.class);
		
		this.enfermeiro.setId_enfermeiro(mockEnfermeiro.getId_enfermeiro());
		 assertEquals(mockEnfermeiro.getId_enfermeiro(), this.enfermeiro.getId_enfermeiro());
	}

	@DisplayName("Teste método para recuperar o nome do enfermeiro. ")
	@Test
	void getNome() {
		EasyRandom generator = new EasyRandom();
		Enfermeiro mockEnfermeiro = generator.nextObject(Enfermeiro.class);
		
		this.enfermeiro.setNome(mockEnfermeiro.getNome());
		 assertEquals(mockEnfermeiro.getNome(), this.enfermeiro.getNome());
	}
	
	@DisplayName("Teste método para recuperar o email do enfermeiro. ")
	@Test
	void getEmail() {
		EasyRandom generator = new EasyRandom();
		Enfermeiro mockEnfermeiro = generator.nextObject(Enfermeiro.class);
		
		this.enfermeiro.setEmail(mockEnfermeiro.getEmail());
		assertEquals(mockEnfermeiro.getEmail(), this.enfermeiro.getEmail());
	}
	
	@DisplayName("Teste método para recuperar o telefone do enfermeiro. ")
	@Test
	void getTelefone() {
		EasyRandom generator = new EasyRandom();
		Enfermeiro mockEnfermeiro = generator.nextObject(Enfermeiro.class);
		
		this.enfermeiro.setTelefone(mockEnfermeiro.getTelefone());
		assertEquals(mockEnfermeiro.getTelefone(), this.enfermeiro.getTelefone());
	}
	
	@DisplayName("Teste método para recuperar o endereco do enfermeiro. ")
	@Test
	void getEndereco() {
		EasyRandom generator = new EasyRandom();
		Enfermeiro mockEnfermeiro = generator.nextObject(Enfermeiro.class);
		
		this.enfermeiro.setEndereco(mockEnfermeiro.getEndereco());
		assertEquals(mockEnfermeiro.getEndereco(), this.enfermeiro.getEndereco());
	}
	
	@DisplayName("Teste método para recuperar o setor do enfermeiro. ")
	@Test
	void getSetor() {
		EasyRandom generator = new EasyRandom();
		Enfermeiro mockEnfermeiro = generator.nextObject(Enfermeiro.class);
		
		this.enfermeiro.setSetor(mockEnfermeiro.getSetor());
		assertEquals(mockEnfermeiro.getSetor(), this.enfermeiro.getSetor());
	}
	
	@DisplayName("Teste método para recuperar a pratileira do enfermeiro. ")
	@Test
	void getPratileiraSobResponsabilidade() {
		EasyRandom generator = new EasyRandom();
		List<Pratileira> listPatrileira =  generator.objects(Pratileira.class, 3).collect(Collectors.toList());
		
		for(Pratileira ptr : listPatrileira) {
			this.enfermeiro.setPratileiraSobResponsabilidade(ptr);
		}
		
		assertEquals(listPatrileira.size(), this.enfermeiro.getPratileiraSobResponsabilidade().size());
	}

}
