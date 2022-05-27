package com.backend.produtos.produtosestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.produtos.produtosestoque.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
