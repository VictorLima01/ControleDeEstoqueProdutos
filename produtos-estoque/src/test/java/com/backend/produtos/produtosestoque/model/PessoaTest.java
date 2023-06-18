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
class PessoaTest {
	
	@InjectMocks
	Pessoa pessoa;
	
	@InjectMocks
	Produto produto;

	@DisplayName("Teste método para recuperar o id do Pessoa. ")
	@Test
	void getIdPessoa() {
		EasyRandom generator = new EasyRandom();
		Pessoa mockPessoa = generator.nextObject(Pessoa.class);
		
		this.pessoa.setId(mockPessoa.getId());
		 assertEquals(mockPessoa.getId(), this.pessoa.getId());
	}
	
	@DisplayName("Teste método para recuperar o nome do Pessoa. ")
	@Test
	void getNome() {
		EasyRandom generator = new EasyRandom();
		Pessoa mockPessoa = generator.nextObject(Pessoa.class);
		
		this.pessoa.setNome(mockPessoa.getNome());
		 assertEquals(mockPessoa.getNome(), this.pessoa.getNome());
	}
	
	@DisplayName("Teste método para recuperar o email do Pessoa. ")
	@Test
	void getEmail() {
		EasyRandom generator = new EasyRandom();
		Pessoa mockPessoa = generator.nextObject(Pessoa.class);
		
		this.pessoa.setEmail(mockPessoa.getEmail());
		assertEquals(mockPessoa.getEmail(), this.pessoa.getEmail());
	}
	
	@DisplayName("Teste método para recuperar o telefone do Pessoa. ")
	@Test
	void getTelefone() {
		EasyRandom generator = new EasyRandom();
		Pessoa mockPessoa = generator.nextObject(Pessoa.class);
		
		this.pessoa.setTelefone(mockPessoa.getTelefone());
		assertEquals(mockPessoa.getTelefone(), this.pessoa.getTelefone());
	}
	
	@DisplayName("Teste método para recuperar o password do Pessoa. ")
	@Test
	void getPassword() {
		EasyRandom generator = new EasyRandom();
		Pessoa mockPessoa = generator.nextObject(Pessoa.class);
		
		this.pessoa.setPassword(mockPessoa.getPassword());
		assertEquals(mockPessoa.getPassword(), this.pessoa.getPassword());
	}
	
	@DisplayName("Teste método para alocar os produtos da Pessoa. ")
	@Test
	void getListasProduto() {
		EasyRandom generator = new EasyRandom();
		List<Produto> listPaciente =  generator.objects(Produto.class, 3).collect(Collectors.toList());
		
		for(Produto ptr : listPaciente) {
			this.pessoa.setListasProdutos(ptr);
		}
		
		assertEquals(listPaciente.size(), this.pessoa.getListasProdutos().size());
	}
}
