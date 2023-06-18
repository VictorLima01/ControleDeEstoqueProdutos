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

import com.backend.produtos.produtosestoque.model.Pessoa;
import com.backend.produtos.produtosestoque.model.Paciente;
import com.backend.produtos.produtosestoque.model.Pessoa;
import com.backend.produtos.produtosestoque.model.Pessoa;
import com.backend.produtos.produtosestoque.model.Produto;
import com.backend.produtos.produtosestoque.model.Pessoa;
import com.backend.produtos.produtosestoque.repository.PessoaRepository;
import com.backend.produtos.produtosestoque.repository.ProdutoRepository;
import com.backend.produtos.produtosestoque.repository.PessoaRepository;
import com.backend.produtos.produtosestoque.repository.ProdutoRepository;

@SpringBootTest
class PessoaControllerTest {
	
	@InjectMocks
	PessoaController pessoaController;
	
	@Mock
	ProdutoRepository produtoRepository;
	
	@Mock
	PessoaRepository pessoaRepository;

	@DisplayName("Teste método para criar Pessoa. ")
	@Test
	void testCreatePessoa(){
		EasyRandom generator = new EasyRandom();
		Pessoa mockPessoa = generator.nextObject(Pessoa.class);
		
		ResponseEntity result = this.pessoaController.createPessoa(mockPessoa);
		
		assertNotNull(result, "Retorno não deveria ser null");
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		verify(pessoaRepository, times(1)).save(mockPessoa);
	}
	 
	@DisplayName("Teste método para criar Pessoa com email duplicado. ")
	 @Test
	    void createPessoa_WithDuplicateEmail() {
	        Pessoa Pessoa = new Pessoa();
	        Pessoa.setEmail("Pessoa@example.com");

	        List<Pessoa> Pessoas = new ArrayList<>();
	        Pessoas.add(Pessoa);
	        when(pessoaRepository.findAll()).thenReturn(Pessoas);

	        ResponseEntity response = pessoaController.createPessoa(Pessoa);

	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        assertEquals("Email duplicado, digite um email válido!", response.getBody());
	        verify(pessoaRepository, never()).save(Pessoa);
	    }
	@DisplayName("Teste método para criar Pessoa com senha duplicado. ")
	 @Test
	    void createPessoa_WithDuplicateSenha() {
		EasyRandom generator = new EasyRandom();
		Pessoa pessoa = generator.nextObject(Pessoa.class);
		Pessoa pessoa1 = generator.nextObject(Pessoa.class);
		
	        List<Pessoa> Pessoas = new ArrayList<>();
	        Pessoas.add(pessoa1);
	        pessoa1.setPassword(pessoa.getPassword());
	        when(pessoaRepository.findAll()).thenReturn(Pessoas);

	        ResponseEntity response = pessoaController.createPessoa(pessoa);

	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        assertEquals("Senha duplicada, digite uma senha válida!", response.getBody());
	        verify(pessoaRepository, never()).save(pessoa);
	    }
	
	@DisplayName("Teste método para retornar Pessoa. ")
	@Test
	void testList(){
		EasyRandom generator = new EasyRandom();
		List<Pessoa> listPessoa =  generator.objects(Pessoa.class, 3).collect(Collectors.toList());
		when(pessoaRepository.findAll()).thenReturn(listPessoa);
		
		List<Pessoa> result = this.pessoaController.list();
		
		assertEquals(listPessoa.size(), result.size());
        assertEquals(listPessoa.get(0), result.get(0));
        assertEquals(listPessoa.get(1), result.get(1));
        verify(pessoaRepository, times(1)).findAll();
	}
	
	@DisplayName("Teste método para alocar produto para Pessoa. ")
	@Test
	void testAlocarProdutosParaPessoa(){
		EasyRandom generator = new EasyRandom();
		Pessoa mockPessoa = generator.nextObject(Pessoa.class);
		Produto mockProduto = generator.nextObject(Produto.class);
		
		when(pessoaRepository.findById(mockPessoa.getId())).thenReturn(Optional.of(mockPessoa));
        when(produtoRepository.findById(mockProduto.getId())).thenReturn(Optional.of(mockProduto));
		
        ResponseEntity response = pessoaController.alocaProduto(mockProduto.getId(), mockPessoa.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPessoa, response.getBody());
        verify(pessoaRepository, times(1)).save(mockPessoa);
	}
	
	@Test
    void login_WithInvalidCredentials_ReturnsBadRequestResponse() {
        String email = "test@example.com";
        String password = "password";

        List<Pessoa> pessoas = new ArrayList<>();

        when(pessoaRepository.listByLoginCredential(email, password)).thenReturn(pessoas);

        ResponseEntity response = pessoaController.login(email, password);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Acesso não liberado!", response.getBody());
    }

    @Test
    void login_WithValidCredentials_ReturnsOkResponse() {
        String email = "test@example.com";
        String password = "password";

        Pessoa pessoa = new Pessoa();
        pessoa.setEmail(email);
        pessoa.setPassword(password);

        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(pessoa);

        when(pessoaRepository.listByLoginCredential(email, password)).thenReturn(pessoas);

        ResponseEntity response = pessoaController.login(email, password);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoas, response.getBody());
    }
    
    @DisplayName("Teste método para deletar pessoa. ")
	@Test
	void testDeletePessoa() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Pessoa mockPessoa = generator.nextObject(Pessoa.class);
		
		//doNothing().when(pessoaRepository).deleteCopyByTradeId(mockPessoa.getId());
        doNothing().when(pessoaRepository).deleteById(mockPessoa.getId());

        ResponseEntity response = pessoaController.delete(mockPessoa.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
        //verify(pessoaRepository, times(1)).deleteCopyByTradeId(mockPessoa.getId());
        verify(pessoaRepository, times(1)).deleteById(mockPessoa.getId());
	}
	

}
