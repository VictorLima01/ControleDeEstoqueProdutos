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

import com.backend.produtos.produtosestoque.model.Enfermeiro;
import com.backend.produtos.produtosestoque.model.Medicamento;
import com.backend.produtos.produtosestoque.model.Medicamento;
import com.backend.produtos.produtosestoque.model.Medicamento;
import com.backend.produtos.produtosestoque.model.Medicamento;
import com.backend.produtos.produtosestoque.model.Pratileira;
import com.backend.produtos.produtosestoque.repository.MedicamentoRepository;
import com.backend.produtos.produtosestoque.repository.MedicamentoRepository;
import com.backend.produtos.produtosestoque.repository.PratileiraRepository;

@SpringBootTest
class MedicamentosControllerTest {
	
	@InjectMocks
	MedicamentosController medicamentosController;
	
	@Mock
	MedicamentoRepository medicamentoRepository;
	
	@Mock 
	PratileiraRepository pratileiraRepository;

	@DisplayName("Teste método para retornar medicamentos. ")
	@Test
	void testList(){
		EasyRandom generator = new EasyRandom();
		List<Medicamento> listMedicamentos =  generator.objects(Medicamento.class, 3).collect(Collectors.toList());
		when(medicamentoRepository.findAll()).thenReturn(listMedicamentos);
		
		List<Medicamento> result = this.medicamentosController.list();
		
		assertEquals(listMedicamentos.size(), result.size());
        assertEquals(listMedicamentos.get(0), result.get(0));
        assertEquals(listMedicamentos.get(1), result.get(1));
        verify(medicamentoRepository, times(1)).findAll();
	}
	
	@DisplayName("Teste método para criar medicamentos. ")
	@Test
	void testCreateMedicamentos(){
		EasyRandom generator = new EasyRandom();
		Medicamento mockMedicamento = generator.nextObject(Medicamento.class);
		
		ResponseEntity result = this.medicamentosController.createMedicamento(mockMedicamento);
		
		assertNotNull(result, "Retorno não deveria ser null");
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		verify(medicamentoRepository, times(1)).save(mockMedicamento);
	}
	
	@DisplayName("Teste método para criar medicamentos, que nome é duplicado.")
    @Test
    void createMedicamento_WithDuplicateNome_ReturnsBadRequestResponse() {
        Medicamento medicamento = new Medicamento();
        medicamento.setNome("Medicamento A");

        Medicamento existingMedicamento = new Medicamento();
        existingMedicamento.setNome("Medicamento A");

        List<Medicamento> medicamentos = new ArrayList<>();
        medicamentos.add(existingMedicamento);

        List<Pratileira> pratileiras = new ArrayList<>();

        when(medicamentoRepository.findAll()).thenReturn(medicamentos);
        when(pratileiraRepository.findAll()).thenReturn(pratileiras);

        ResponseEntity response = medicamentosController.createMedicamento(medicamento);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Nome duplicado, digite um nome válido!", response.getBody());
        verify(medicamentoRepository, never()).save(medicamento);
    }
	
	@DisplayName("Teste método para criar medicamentos, que não tem pratileiras alocadas. ")
    @Test
    void createMedicamento_WithNoPratileiras_ReturnsBadRequestResponse() {
        Medicamento medicamento = new Medicamento();
        medicamento.setNome("Medicamento A");
        
        EasyRandom generator = new EasyRandom();
		List<Medicamento> medicamentos = generator.objects(Medicamento.class, 3).collect(Collectors.toList());
		List<Pratileira> pratileiras = new ArrayList<Pratileira>();

        when(medicamentoRepository.findAll()).thenReturn(medicamentos);
        when(pratileiraRepository.findAll()).thenReturn(pratileiras);

        ResponseEntity response = medicamentosController.createMedicamento(medicamento);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Você precisa guardar esse remédio em uma pratileira, por favor cadastre uma!", response.getBody());
        verify(medicamentoRepository, never()).save(medicamento);
    }
	
	@DisplayName("Teste método para alocar medicamentos para pratileiras. ")
	@Test
	void testAlocarMedicamentoParaPratileira(){
		EasyRandom generator = new EasyRandom();
		Medicamento mockMedicamento = generator.nextObject(Medicamento.class);
		Pratileira mockpratileira = generator.nextObject(Pratileira.class);
		
		when(medicamentoRepository.findById(mockMedicamento.getId_medicamento())).thenReturn(Optional.of(mockMedicamento));
        when(pratileiraRepository.findById(mockpratileira.getId_pratileira())).thenReturn(Optional.of(mockpratileira));
		
        ResponseEntity response = medicamentosController.alocarMedicamentosParaPacientes(mockMedicamento.getId_medicamento(), mockpratileira.getId_pratileira());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockMedicamento, response.getBody());
        verify(medicamentoRepository, times(1)).save(mockMedicamento);
	}
	
	@DisplayName("Teste método para deletar medicamentos. ")
	@Test
	void testDeleteMedicamento() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Medicamento mockMedicamento = generator.nextObject(Medicamento.class);
		
		doNothing().when(medicamentoRepository).deleteCopyByTradeId(mockMedicamento.getId_medicamento());
        doNothing().when(medicamentoRepository).deleteById(mockMedicamento.getId_medicamento());

        ResponseEntity response = medicamentosController.delete(mockMedicamento.getId_medicamento());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(medicamentoRepository, times(1)).deleteCopyByTradeId(mockMedicamento.getId_medicamento());
        verify(medicamentoRepository, times(1)).deleteById(mockMedicamento.getId_medicamento());
	}
	
	@DisplayName("Teste método para editar medicamentos. ")
	@Test
	void testUpdateMedicamento() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Medicamento mockMedicamento = generator.nextObject(Medicamento.class);
		
		when(medicamentoRepository.findById(mockMedicamento.getId_medicamento())).thenReturn(Optional.of(mockMedicamento));
        when(medicamentoRepository.save(mockMedicamento)).thenReturn(mockMedicamento);

        ResponseEntity response = medicamentosController.update(mockMedicamento.getId_medicamento(), mockMedicamento);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockMedicamento, response.getBody());
        verify(medicamentoRepository, times(1)).save(mockMedicamento);
	}

}
