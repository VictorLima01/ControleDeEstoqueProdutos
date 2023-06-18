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
class MedicamentoTest {

	@InjectMocks
	Medicamento medicamento;
	
	@InjectMocks
	Pratileira pratileira;
	
	@DisplayName("Teste método para recuperar o id do medicamento. ")
	@Test
	void getIdMedicamento() {
		EasyRandom generator = new EasyRandom();
		Medicamento mockMedicamento = generator.nextObject(Medicamento.class);
		
		this.medicamento.setId_medicamento(mockMedicamento.getId_medicamento());
		 assertEquals(mockMedicamento.getId_medicamento(), this.medicamento.getId_medicamento());
	}

	@DisplayName("Teste método para recuperar o nome do Medicamento. ")
	@Test
	void getNome() {
		EasyRandom generator = new EasyRandom();
		Medicamento mockMedicamento = generator.nextObject(Medicamento.class);
		
		this.medicamento.setNome(mockMedicamento.getNome());
		 assertEquals(mockMedicamento.getNome(), this.medicamento.getNome());
	}
	
	@DisplayName("Teste método para recuperar a quantidade do Medicamento. ")
	@Test
	void getEmail() {
		EasyRandom generator = new EasyRandom();
		Medicamento mockMedicamento = generator.nextObject(Medicamento.class);
		
		this.medicamento.setQtd(mockMedicamento.getQtd());
		assertEquals(mockMedicamento.getQtd(), this.medicamento.getQtd());
	}
	
	@DisplayName("Teste método para recuperar os efeitos do Medicamento. ")
	@Test
	void getTelefone() {
		EasyRandom generator = new EasyRandom();
		Medicamento mockMedicamento = generator.nextObject(Medicamento.class);
		
		this.medicamento.setEfeitos(mockMedicamento.getEfeitos());
		assertEquals(mockMedicamento.getEfeitos(), this.medicamento.getEfeitos());
	}
	
	@DisplayName("Teste método para recuperar a pratileira do Medicamento. ")
	@Test
	void getPratileiraSobResponsabilidade() {
		EasyRandom generator = new EasyRandom();
		List<Pratileira> listPatrileira =  generator.objects(Pratileira.class, 3).collect(Collectors.toList());
		
		for(Pratileira ptr : listPatrileira) {
			this.medicamento.setListasPratileiras(ptr);
		}
		
		assertEquals(listPatrileira.size(), this.medicamento.getListasPratileiras().size());
	}
	
	@DisplayName("Teste método para alterar medicamentos que estão sendo consumidos. ")
	@Test
	void alterarFaltaDeRemedios() {
		EasyRandom generator = new EasyRandom();
		Medicamento mockMedicamento = generator.nextObject(Medicamento.class);
		
		mockMedicamento.setQtd(0);
		this.medicamento.aleterarQtd(mockMedicamento.getQtd());
		
		assertEquals(mockMedicamento.getQtd(), this.medicamento.getQtd());
	}

}
