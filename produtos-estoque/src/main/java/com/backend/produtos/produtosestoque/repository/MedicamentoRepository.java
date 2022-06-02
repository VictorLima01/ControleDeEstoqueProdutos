package com.backend.produtos.produtosestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.produtos.produtosestoque.model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{

}
