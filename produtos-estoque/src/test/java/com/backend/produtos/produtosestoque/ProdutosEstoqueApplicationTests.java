package com.backend.produtos.produtosestoque;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutosEstoqueApplicationTests {
	
	@InjectMocks
	private ProdutosEstoqueApplication produtosEstoqueApplication;
	
	@DisplayName("Teste main da aplicação.")
	@Test
	void testarMain() {
		String[] args = {};
		produtosEstoqueApplication.main(args);
	}

}
