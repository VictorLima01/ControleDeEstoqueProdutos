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

import com.backend.produtos.produtosestoque.model.Medicamento;
import com.backend.produtos.produtosestoque.model.Medico;
import com.backend.produtos.produtosestoque.model.Paciente;
import com.backend.produtos.produtosestoque.model.Pratileira;
import com.backend.produtos.produtosestoque.model.Paciente;
import com.backend.produtos.produtosestoque.repository.MedicamentoRepository;
import com.backend.produtos.produtosestoque.repository.PacienteRepository;
import com.backend.produtos.produtosestoque.repository.PacienteRepository;

@SpringBootTest
class PacienteControllerTest {
	
	@InjectMocks
	PacienteController pacienteController;
	
	@Mock
	MedicamentoRepository medicamentoRepository;
	
	@Mock
	PacienteRepository pacienteRepository;

	@DisplayName("Teste método para criar Paciente. ")
	@Test
	void testCreatePaciente(){
		EasyRandom generator = new EasyRandom();
		Paciente mockPaciente = generator.nextObject(Paciente.class);
		
		ResponseEntity result = this.pacienteController.createPaciente(mockPaciente);
		
		assertNotNull(result, "Retorno não deveria ser null");
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		verify(pacienteRepository, times(1)).save(mockPaciente);
	}
	 
	@DisplayName("Teste método para criar Paciente com email duplicado. ")
	 @Test
	    void createPaciente_WithDuplicateEmail() {
	        Paciente Paciente = new Paciente();
	        Paciente.setEmail("Paciente@example.com");

	        List<Paciente> Pacientes = new ArrayList<>();
	        Pacientes.add(Paciente);
	        when(pacienteRepository.findAll()).thenReturn(Pacientes);

	        ResponseEntity response = pacienteController.createPaciente(Paciente);

	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        assertEquals("Email duplicado, digite um email válido!", response.getBody());
	        verify(pacienteRepository, never()).save(Paciente);
	    }
	
	@DisplayName("Teste método para retornar Paciente. ")
	@Test
	void testList(){
		EasyRandom generator = new EasyRandom();
		List<Paciente> listPaciente =  generator.objects(Paciente.class, 3).collect(Collectors.toList());
		when(pacienteRepository.findAll()).thenReturn(listPaciente);
		
		List<Paciente> result = this.pacienteController.list();
		
		assertEquals(listPaciente.size(), result.size());
        assertEquals(listPaciente.get(0), result.get(0));
        assertEquals(listPaciente.get(1), result.get(1));
        verify(pacienteRepository, times(1)).findAll();
	}
	
	@DisplayName("Teste método para deletar Paciente. ")
	@Test
	void testDeletePaciente() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Paciente mockPaciente = generator.nextObject(Paciente.class);
		
		doNothing().when(pacienteRepository).deleteCopyByTradeId(mockPaciente.getId_paciente());
        doNothing().when(pacienteRepository).deleteById(mockPaciente.getId_paciente());

        ResponseEntity response = pacienteController.delete(mockPaciente.getId_paciente());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(pacienteRepository, times(1)).deleteCopyByTradeId(mockPaciente.getId_paciente());
        verify(pacienteRepository, times(1)).deleteById(mockPaciente.getId_paciente());
	}
	
	@DisplayName("Teste método para editar Paciente. ")
	@Test
	void testUpdatePaciente() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Paciente mockPaciente = generator.nextObject(Paciente.class);
		
		when(pacienteRepository.findById(mockPaciente.getId_paciente())).thenReturn(Optional.of(mockPaciente));
        when(pacienteRepository.save(mockPaciente)).thenReturn(mockPaciente);

        ResponseEntity response = pacienteController.update(mockPaciente.getId_paciente(), mockPaciente);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPaciente, response.getBody());
        verify(pacienteRepository, times(1)).save(mockPaciente);
	}
	
	@DisplayName("Teste método para alocar medicamentos para Paciente. ")
	@Test
	void testAlocarPacientesParaMedicamento(){
		EasyRandom generator = new EasyRandom();
		Medicamento mockMedicamento = generator.nextObject(Medicamento.class);
		Paciente mockPaciente = generator.nextObject(Paciente.class);
		
		when(medicamentoRepository.findById(mockMedicamento.getId_medicamento())).thenReturn(Optional.of(mockMedicamento));
        when(pacienteRepository.findById(mockPaciente.getId_paciente())).thenReturn(Optional.of(mockPaciente));
		
        ResponseEntity response = this.pacienteController.alocarMedicamentosParaPacientes(mockPaciente.getId_paciente(), mockMedicamento.getId_medicamento());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPaciente, response.getBody());
        verify(pacienteRepository, times(1)).save(mockPaciente);
	}
	
	@DisplayName("Teste método para sem alocar medicamentos para Paciente. ")
	@Test
	void testAlocarPacientesNoMedicamento(){
		EasyRandom generator = new EasyRandom();
		Medicamento mockMedicamento = generator.nextObject(Medicamento.class);
		Paciente mockPaciente = generator.nextObject(Paciente.class);
		
		Pratileira pratileira =  generator.nextObject(Pratileira.class);
		mockMedicamento.setListasPratileiras(pratileira);
		
		when(medicamentoRepository.findById(mockMedicamento.getId_medicamento())).thenReturn(Optional.of(mockMedicamento));
        when(pacienteRepository.findById(mockPaciente.getId_paciente())).thenReturn(Optional.of(mockPaciente));
		
        ResponseEntity response = this.pacienteController.alocarMedicamentosParaPacientes(mockPaciente.getId_paciente(), mockMedicamento.getId_medicamento());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPaciente, response.getBody());
        verify(pacienteRepository, times(1)).save(mockPaciente);
	}

}
