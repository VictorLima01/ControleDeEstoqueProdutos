package com.backend.produtos.produtosestoque.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.backend.produtos.produtosestoque.model.Enfermeiro;
import com.backend.produtos.produtosestoque.model.Pratileira;
import com.backend.produtos.produtosestoque.repository.EnfermeiroRepository;
import com.backend.produtos.produtosestoque.repository.PratileiraRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class EnfermeiroControllerTest {
	
	
	@InjectMocks
	EnfermeiroController enfermeiroController;
	
	@Mock
	EnfermeiroRepository enfermeiroRepository;
	
	@Mock 
	PratileiraRepository pratileiraRepository;
	
	@DisplayName("Teste método para criar enfermeiros. ")
	@Test
	void testCreateEnfermeiros(){
		EasyRandom generator = new EasyRandom();
		Enfermeiro mockEnfermeiro = generator.nextObject(Enfermeiro.class);
		
		ResponseEntity result = this.enfermeiroController.createEnfermeiros(mockEnfermeiro);
		
		assertNotNull(result, "Retorno não deveria ser null");
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		verify(enfermeiroRepository, times(1)).save(mockEnfermeiro);
	}
	 
	@DisplayName("Teste método para criar enfermeiros com email duplicado. ")
	 @Test
	    void createEnfermeiros_WithDuplicateEmail() {
	        Enfermeiro enfermeiro = new Enfermeiro();
	        enfermeiro.setEmail("enfermeiro@example.com");

	        List<Enfermeiro> enfermeiros = new ArrayList<>();
	        enfermeiros.add(enfermeiro);
	        when(enfermeiroRepository.findAll()).thenReturn(enfermeiros);

	        ResponseEntity response = enfermeiroController.createEnfermeiros(enfermeiro);

	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        assertEquals("Email duplicado, digite um Email válido!", response.getBody());
	        verify(enfermeiroRepository, never()).save(enfermeiro);
	    }
	
	@DisplayName("Teste método para retornar enfermeiros. ")
	@Test
	void testList(){
		EasyRandom generator = new EasyRandom();
		List<Enfermeiro> listEnfermeiros =  generator.objects(Enfermeiro.class, 3).collect(Collectors.toList());
		when(enfermeiroRepository.findAll()).thenReturn(listEnfermeiros);
		
		List<Enfermeiro> result = this.enfermeiroController.list();
		
		assertEquals(listEnfermeiros.size(), result.size());
        assertEquals(listEnfermeiros.get(0), result.get(0));
        assertEquals(listEnfermeiros.get(1), result.get(1));
        verify(enfermeiroRepository, times(1)).findAll();
	}
	
	@DisplayName("Teste método para alocar pratileira para enfermeiros. ")
	@Test
	void testAlocarPratileiraParaEnfermeiro(){
		EasyRandom generator = new EasyRandom();
		Enfermeiro mockEnfermeiro = generator.nextObject(Enfermeiro.class);
		Pratileira mockpratileira = generator.nextObject(Pratileira.class);
		
		mockpratileira.setSetor(mockEnfermeiro.getSetor());
		
		when(enfermeiroRepository.findById(mockEnfermeiro.getId_enfermeiro())).thenReturn(Optional.of(mockEnfermeiro));
        when(pratileiraRepository.findById(mockpratileira.getId_pratileira())).thenReturn(Optional.of(mockpratileira));
		
        ResponseEntity response = enfermeiroController.alocarPratileiraParaEnfermeiro(mockEnfermeiro.getId_enfermeiro(), mockpratileira.getId_pratileira());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockEnfermeiro, response.getBody());
        verify(enfermeiroRepository, times(1)).save(mockEnfermeiro);
	}
	
	@DisplayName("Teste método para deletar enfermeiros. ")
	@Test
	void testDeleteEnfermeiro() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Enfermeiro mockEnfermeiro = generator.nextObject(Enfermeiro.class);
		
		doNothing().when(enfermeiroRepository).deleteCopyByTradeId(mockEnfermeiro.getId_enfermeiro());
        doNothing().when(enfermeiroRepository).deleteById(mockEnfermeiro.getId_enfermeiro());

        ResponseEntity response = enfermeiroController.delete(mockEnfermeiro.getId_enfermeiro());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(enfermeiroRepository, times(1)).deleteCopyByTradeId(mockEnfermeiro.getId_enfermeiro());
        verify(enfermeiroRepository, times(1)).deleteById(mockEnfermeiro.getId_enfermeiro());
	}
	
	@DisplayName("Teste método para editar enfermeiros. ")
	@Test
	void testUpdateEnfermeiro() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Enfermeiro mockEnfermeiro = generator.nextObject(Enfermeiro.class);
		
		when(enfermeiroRepository.findById(mockEnfermeiro.getId_enfermeiro())).thenReturn(Optional.of(mockEnfermeiro));
        when(enfermeiroRepository.save(mockEnfermeiro)).thenReturn(mockEnfermeiro);

        ResponseEntity response = enfermeiroController.update(mockEnfermeiro.getId_enfermeiro(), mockEnfermeiro);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockEnfermeiro, response.getBody());
        verify(enfermeiroRepository, times(1)).save(mockEnfermeiro);
	}
	
}
