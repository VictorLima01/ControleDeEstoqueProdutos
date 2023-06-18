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

import com.backend.produtos.produtosestoque.model.Pratileira;
import com.backend.produtos.produtosestoque.repository.MedicamentoRepository;
import com.backend.produtos.produtosestoque.repository.PratileiraRepository;

@SpringBootTest
class PratileiraControllerTest {
	@InjectMocks
	PratileiraController pratileiraController;
	
	@Mock
	PratileiraRepository pratileiraRepository;

	@DisplayName("Teste método para criar Pratileira. ")
	@Test
	void testCreatePratileira(){
		EasyRandom generator = new EasyRandom();
		Pratileira mockPratileira = generator.nextObject(Pratileira.class);
		
		ResponseEntity result = this.pratileiraController.createPratileira(mockPratileira);
		
		assertNotNull(result, "Retorno não deveria ser null");
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		verify(pratileiraRepository, times(1)).save(mockPratileira);
	}
	 
	@DisplayName("Teste método para criar Pratileira com nome duplicado. ")
	 @Test
	    void createPratileira_WithDuplicateNome() {
	        Pratileira Pratileira = new Pratileira();
	        Pratileira.setNome_pratileira("hjh");

	        List<Pratileira> Pratileiras = new ArrayList<>();
	        Pratileiras.add(Pratileira);
	        when(pratileiraRepository.findAll()).thenReturn(Pratileiras);

	        ResponseEntity response = pratileiraController.createPratileira(Pratileira);

	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        assertEquals("Nome duplicado, digite um nome válido!", response.getBody());
	        verify(pratileiraRepository, never()).save(Pratileira);
	    }
	
	@DisplayName("Teste método para retornar Pratileira. ")
	@Test
	void testList(){
		EasyRandom generator = new EasyRandom();
		List<Pratileira> listPratileira =  generator.objects(Pratileira.class, 3).collect(Collectors.toList());
		when(pratileiraRepository.findAll()).thenReturn(listPratileira);
		
		List<Pratileira> result = this.pratileiraController.list();
		
		assertEquals(listPratileira.size(), result.size());
        assertEquals(listPratileira.get(0), result.get(0));
        assertEquals(listPratileira.get(1), result.get(1));
        verify(pratileiraRepository, times(1)).findAll();
	}
	
	@DisplayName("Teste método para deletar Pratileira. ")
	@Test
	void testDeletePratileira() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Pratileira mockPratileira = generator.nextObject(Pratileira.class);
		
		//doNothing().when(pratileiraRepository).deleteCopyByTradeId(mockPratileira.getId_pratileira());
        doNothing().when(pratileiraRepository).deleteById(mockPratileira.getId_pratileira());

        ResponseEntity response = pratileiraController.delete(mockPratileira.getId_pratileira());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
        //verify(pratileiraRepository, times(1)).deleteCopyByTradeId(mockPratileira.getId_pratileira());
        verify(pratileiraRepository, times(1)).deleteById(mockPratileira.getId_pratileira());
	}
	
	@DisplayName("Teste método para editar Pratileira. ")
	@Test
	void testUpdatePratileira() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Pratileira mockPratileira = generator.nextObject(Pratileira.class);
		
		when(pratileiraRepository.findById(mockPratileira.getId_pratileira())).thenReturn(Optional.of(mockPratileira));
        when(pratileiraRepository.save(mockPratileira)).thenReturn(mockPratileira);

        ResponseEntity response = pratileiraController.update(mockPratileira.getId_pratileira(), mockPratileira);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPratileira, response.getBody());
        verify(pratileiraRepository, times(1)).save(mockPratileira);
	}

}
