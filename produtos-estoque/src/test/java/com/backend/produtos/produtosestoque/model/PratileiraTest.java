package com.backend.produtos.produtosestoque.model;

import static org.junit.jupiter.api.Assertions.*;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PratileiraTest {
	
	@InjectMocks
	Pratileira pratileira;

	@DisplayName("Teste método para recuperar o id do Pratileira. ")
	@Test
	void getIdPratileira() {
		EasyRandom generator = new EasyRandom();
		Pratileira mockPratileira = generator.nextObject(Pratileira.class);
		
		this.pratileira.setId_pratileira(mockPratileira.getId_pratileira());
		 assertEquals(mockPratileira.getId_pratileira(), this.pratileira.getId_pratileira());
	}
	
	@DisplayName("Teste método para recuperar o nome do Pratileira. ")
	@Test
	void getNome() {
		EasyRandom generator = new EasyRandom();
		Pratileira mockPratileira = generator.nextObject(Pratileira.class);
		
		this.pratileira.setNome_pratileira(mockPratileira.getNome_pratileira());
		 assertEquals(mockPratileira.getNome_pratileira(), this.pratileira.getNome_pratileira());
	}
	
	@DisplayName("Teste método para recuperar o setor do Pratileira. ")
	@Test
	void getSetor() {
		EasyRandom generator = new EasyRandom();
		Pratileira mockPratileira = generator.nextObject(Pratileira.class);
		
		this.pratileira.setSetor(mockPratileira.getSetor());
		assertEquals(mockPratileira.getSetor(), this.pratileira.getSetor());
	}
}
