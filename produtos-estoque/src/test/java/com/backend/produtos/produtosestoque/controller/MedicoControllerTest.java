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

import com.backend.produtos.produtosestoque.model.Medico;
import com.backend.produtos.produtosestoque.model.Paciente;
import com.backend.produtos.produtosestoque.repository.MedicoRepository;
import com.backend.produtos.produtosestoque.repository.PacienteRepository;
import com.backend.produtos.produtosestoque.repository.PacienteRepository;

@SpringBootTest
class MedicoControllerTest {
	
	@InjectMocks
	MedicoController medicoController;
	
	@Mock
	MedicoRepository medicoRepository;
	
	@Mock
	PacienteRepository pacienteRepository;

	@DisplayName("Teste método para criar Medico. ")
	@Test
	void testCreateMedico(){
		EasyRandom generator = new EasyRandom();
		Medico mockMedico = generator.nextObject(Medico.class);
		
		ResponseEntity result = this.medicoController.createPessoa(mockMedico);
		
		assertNotNull(result, "Retorno não deveria ser null");
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		verify(medicoRepository, times(1)).save(mockMedico);
	}
	 
	@DisplayName("Teste método para criar Medico com email duplicado. ")
	 @Test
	    void createMedico_WithDuplicateEmail() {
	        Medico medico = new Medico();
	        medico.setEmail("Medico@example.com");

	        List<Medico> medicos = new ArrayList<>();
	        medicos.add(medico);
	        when(medicoRepository.findAll()).thenReturn(medicos);

	        ResponseEntity response = medicoController.createPessoa(medico);

	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        assertEquals("Email duplicado, digite um email válido!", response.getBody());
	        verify(medicoRepository, never()).save(medico);
	    }
	
	@DisplayName("Teste método para retornar Medico. ")
	@Test
	void testList(){
		EasyRandom generator = new EasyRandom();
		List<Medico> listMedico =  generator.objects(Medico.class, 3).collect(Collectors.toList());
		when(medicoRepository.findAll()).thenReturn(listMedico);
		
		List<Medico> result = this.medicoController.list();
		
		assertEquals(listMedico.size(), result.size());
        assertEquals(listMedico.get(0), result.get(0));
        assertEquals(listMedico.get(1), result.get(1));
        verify(medicoRepository, times(1)).findAll();
	}
	
	@DisplayName("Teste método para alocar pacientes para Medico. ")
	@Test
	void testAlocarPacientesParaMedico(){
		EasyRandom generator = new EasyRandom();
		Medico mockMedico = generator.nextObject(Medico.class);
		Paciente mockPaciente = generator.nextObject(Paciente.class);
		
		when(medicoRepository.findById(mockMedico.getId_medico())).thenReturn(Optional.of(mockMedico));
        when(pacienteRepository.findById(mockPaciente.getId_paciente())).thenReturn(Optional.of(mockPaciente));
		
        ResponseEntity response = medicoController.alocarPacientesParaMedico(mockMedico.getId_medico(), mockPaciente.getId_paciente());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockMedico, response.getBody());
        verify(medicoRepository, times(1)).save(mockMedico);
	}
	
	@DisplayName("Teste método para deletar Medico. ")
	@Test
	void testDeleteMedico() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Medico mockMedico = generator.nextObject(Medico.class);
		
		doNothing().when(medicoRepository).deleteCopyByTradeId(mockMedico.getId_medico());
        doNothing().when(medicoRepository).deleteById(mockMedico.getId_medico());

        ResponseEntity response = medicoController.delete(mockMedico.getId_medico());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(medicoRepository, times(1)).deleteCopyByTradeId(mockMedico.getId_medico());
        verify(medicoRepository, times(1)).deleteById(mockMedico.getId_medico());
	}
	
	@DisplayName("Teste método para editar Medico. ")
	@Test
	void testUpdateMedico() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Medico mockMedico = generator.nextObject(Medico.class);
		
		when(medicoRepository.findById(mockMedico.getId_medico())).thenReturn(Optional.of(mockMedico));
        when(medicoRepository.save(mockMedico)).thenReturn(mockMedico);

        ResponseEntity response = medicoController.update(mockMedico.getId_medico(), mockMedico);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockMedico, response.getBody());
        verify(medicoRepository, times(1)).save(mockMedico);
	}

}
