package com.backend.produtos.produtosestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.produtos.produtosestoque.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
}
