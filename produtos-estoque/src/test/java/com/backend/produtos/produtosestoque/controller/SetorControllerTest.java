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
import java.util.Optional;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.backend.produtos.produtosestoque.model.Setor;
import com.backend.produtos.produtosestoque.model.Enfermeiro;
import com.backend.produtos.produtosestoque.repository.EnfermeiroRepository;
import com.backend.produtos.produtosestoque.repository.SetorRepository;
import com.backend.produtos.produtosestoque.repository.EnfermeiroRepository;
import com.backend.produtos.produtosestoque.repository.SetorRepository;

@SpringBootTest
class SetorControllerTest {
	
	@InjectMocks
	SetorController setorController;
	
	@Mock
	SetorRepository setorRepository;
	
	@Mock
	EnfermeiroRepository enfermeiroRepository;

	@DisplayName("Teste método para criar Setor. ")
	@Test
	void testCreateSetor(){
		EasyRandom generator = new EasyRandom();
		Setor mockSetor = generator.nextObject(Setor.class);
		
		ResponseEntity result = this.setorController.createSetor(mockSetor);
		
		assertNotNull(result, "Retorno não deveria ser null");
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		verify(setorRepository, times(1)).save(mockSetor);
	}
	 
	@DisplayName("Teste método para criar Setor com nome duplicado. ")
	 @Test
	    void createSetor_WithDuplicateEmail() {
	        Setor Setor = new Setor();
	        Setor.setNome("setor");

	        List<Setor> Setors = new ArrayList<>();
	        Setors.add(Setor);
	        when(setorRepository.findAll()).thenReturn(Setors);

	        ResponseEntity response = setorController.createSetor(Setor);

	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        assertEquals("Nome duplicado, digite um nome válido!", response.getBody());
	        verify(setorRepository, never()).save(Setor);
	    }
	
	@DisplayName("Teste método para retornar Setor. ")
	@Test
	void testList(){
		EasyRandom generator = new EasyRandom();
		List<Setor> listSetor =  generator.objects(Setor.class, 3).collect(Collectors.toList());
		when(setorRepository.findAll()).thenReturn(listSetor);
		
		List<Setor> result = this.setorController.list();
		
		assertEquals(listSetor.size(), result.size());
        assertEquals(listSetor.get(0), result.get(0));
        assertEquals(listSetor.get(1), result.get(1));
        verify(setorRepository, times(1)).findAll();
	}
	
	@DisplayName("Teste método para alocar Enfermeiros para Setor. ")
	@Test
	void testAlocarEnfermeirosParaSetor(){
		EasyRandom generator = new EasyRandom();
		Setor mockSetor = generator.nextObject(Setor.class);
		Enfermeiro mockEnfermeiro = generator.nextObject(Enfermeiro.class);
		
		when(setorRepository.findById(mockSetor.getId_setor())).thenReturn(Optional.of(mockSetor));
        when(enfermeiroRepository.findById(mockEnfermeiro.getId_enfermeiro())).thenReturn(Optional.of(mockEnfermeiro));
		
        ResponseEntity response = setorController.alocarPacientesParaMedico(mockSetor.getId_setor(), mockEnfermeiro.getId_enfermeiro());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSetor, response.getBody());
        verify(setorRepository, times(1)).save(mockSetor);
	}
	
	@DisplayName("Teste método para deletar Setor. ")
	@Test
	void testDeleteSetor() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Setor mockSetor = generator.nextObject(Setor.class);
		
		doNothing().when(setorRepository).deleteCopyByTradeId(mockSetor.getId_setor());
        doNothing().when(setorRepository).deleteById(mockSetor.getId_setor());

        ResponseEntity response = setorController.delete(mockSetor.getId_setor());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(setorRepository, times(1)).deleteCopyByTradeId(mockSetor.getId_setor());
        verify(setorRepository, times(1)).deleteById(mockSetor.getId_setor());
	}
	

}
