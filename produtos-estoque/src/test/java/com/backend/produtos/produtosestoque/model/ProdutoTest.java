package com.backend.produtos.produtosestoque.model;

import static org.junit.jupiter.api.Assertions.*;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoTest {
	
	@InjectMocks
	Produto produto;

	@DisplayName("Teste método para recuperar o id do Produto. ")
	@Test
	void getIdProduto() {
		EasyRandom generator = new EasyRandom();
		Produto mockProduto = generator.nextObject(Produto.class);
		
		this.produto.setId(mockProduto.getId());
		 assertEquals(mockProduto.getId(), this.produto.getId());
	}
	
	@DisplayName("Teste método para recuperar o nome do Produto. ")
	@Test
	void getNome() {
		EasyRandom generator = new EasyRandom();
		Produto mockProduto = generator.nextObject(Produto.class);
		
		this.produto.setNome(mockProduto.getNome());
		 assertEquals(mockProduto.getNome(), this.produto.getNome());
	}
	
	@DisplayName("Teste método para recuperar o função do Produto. ")
	@Test
	void getFuncao() {
		EasyRandom generator = new EasyRandom();
		Produto mockProduto = generator.nextObject(Produto.class);
		
		this.produto.setFuncao(mockProduto.getFuncao());
		assertEquals(mockProduto.getFuncao(), this.produto.getFuncao());
	}
	
	@DisplayName("Teste método para recuperar o numero lote do Produto. ")
	@Test
	void getLote() {
		EasyRandom generator = new EasyRandom();
		Produto mockProduto = generator.nextObject(Produto.class);
		
		this.produto.setNumeroLote(mockProduto.getNumeroLote());
		assertEquals(mockProduto.getNumeroLote(), this.produto.getNumeroLote());
	}
	
	@DisplayName("Teste método para recuperar se o produto está alocado para alguém. ")
	@Test
	void getAlocado() {
		EasyRandom generator = new EasyRandom();
		Produto mockProduto = generator.nextObject(Produto.class);
		
		this.produto.setAlocado(mockProduto.isAlocado());
		assertEquals(mockProduto.isAlocado(), this.produto.isAlocado());
	}

}
