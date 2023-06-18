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

import com.backend.produtos.produtosestoque.model.Produto;
import com.backend.produtos.produtosestoque.repository.ProdutoRepository;
import com.backend.produtos.produtosestoque.repository.ProdutoRepository;

@SpringBootTest
class ProdutoControllerTest {
	@InjectMocks
	ProdutoController produtoController;
	
	@Mock
	ProdutoRepository produtoRepository;

	@DisplayName("Teste método para criar Produto. ")
	@Test
	void testCreateProduto(){
		EasyRandom generator = new EasyRandom();
		Produto mockProduto = generator.nextObject(Produto.class);
		
		ResponseEntity result = this.produtoController.createProdutos(mockProduto);
		
		assertNotNull(result, "Retorno não deveria ser null");
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		verify(produtoRepository, times(1)).save(mockProduto);
	}
	 
	@DisplayName("Teste método para criar Produto com nome duplicado. ")
	 @Test
	    void createProduto_WithDuplicateNome() {
	        Produto Produto = new Produto();
	        Produto.setNumeroLote(5L);

	        List<Produto> Produtos = new ArrayList<>();
	        Produtos.add(Produto);
	        when(produtoRepository.findAll()).thenReturn(Produtos);

	        ResponseEntity response = produtoController.createProdutos(Produto);

	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        assertEquals("Número de lote inválido!", response.getBody());
	        verify(produtoRepository, never()).save(Produto);
	    }
	
	@DisplayName("Teste método para retornar Produto. ")
	@Test
	void testList(){
		EasyRandom generator = new EasyRandom();
		List<Produto> listProduto =  generator.objects(Produto.class, 3).collect(Collectors.toList());
		when(produtoRepository.findAll()).thenReturn(listProduto);
		
		List<Produto> result = this.produtoController.list();
		
		assertEquals(listProduto.size(), result.size());
        assertEquals(listProduto.get(0), result.get(0));
        assertEquals(listProduto.get(1), result.get(1));
        verify(produtoRepository, times(1)).findAll();
	}
	
	@DisplayName("Teste método para deletar Produto. ")
	@Test
	void testDeleteProduto() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Produto mockProduto = generator.nextObject(Produto.class);
		
		doNothing().when(produtoRepository).deleteCopyByTradeId(mockProduto.getId());
        doNothing().when(produtoRepository).deleteById(mockProduto.getId());

        ResponseEntity response = produtoController.delete(mockProduto.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(produtoRepository, times(1)).deleteCopyByTradeId(mockProduto.getId());
        verify(produtoRepository, times(1)).deleteById(mockProduto.getId());
	}
	
	@DisplayName("Teste método para editar Produto. ")
	@Test
	void testUpdateProduto() throws SQLException, ClassNotFoundException{
		EasyRandom generator = new EasyRandom();
		Produto mockProduto = generator.nextObject(Produto.class);
		
		when(produtoRepository.findById(mockProduto.getId())).thenReturn(Optional.of(mockProduto));
        when(produtoRepository.save(mockProduto)).thenReturn(mockProduto);

        ResponseEntity response = produtoController.update(mockProduto.getId(), mockProduto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProduto, response.getBody());
        verify(produtoRepository, times(1)).save(mockProduto);
	}

}
