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
class SetorTest {
	
	@InjectMocks
	Setor setor;
	
	@InjectMocks
	Enfermeiro enfermeiro;
	
	@DisplayName("Teste método para recuperar o id do Setor. ")
	@Test
	void getIdSetor() {
		EasyRandom generator = new EasyRandom();
		Setor mockSetor = generator.nextObject(Setor.class);
		
		this.setor.setId_setor(mockSetor.getId_setor());
		 assertEquals(mockSetor.getId_setor(), this.setor.getId_setor());
	}
	
	@DisplayName("Teste método para recuperar o nome do Setor. ")
	@Test
	void getNome() {
		EasyRandom generator = new EasyRandom();
		Setor mockSetor = generator.nextObject(Setor.class);
		
		this.setor.setNome(mockSetor.getNome());
		 assertEquals(mockSetor.getNome(), this.setor.getNome());
	}

	@DisplayName("Teste método para alocar os enfermeiros do Setor. ")
	@Test
	void getListaEnfermeiro() {
		EasyRandom generator = new EasyRandom();
		List<Enfermeiro> listEnfermeiro =  generator.objects(Enfermeiro.class, 3).collect(Collectors.toList());
		
		for(Enfermeiro ptr : listEnfermeiro) {
			this.setor.setListaEnfermeiro(ptr);
		}
		
		assertEquals(listEnfermeiro.size(), this.setor.getListaEnfermeiro().size());
	}

}
