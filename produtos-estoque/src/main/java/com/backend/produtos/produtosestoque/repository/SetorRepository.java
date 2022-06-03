package com.backend.produtos.produtosestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.produtos.produtosestoque.model.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long>{
     @Modifying
	    @Transactional
	    @Query(value = "DELETE FROM setor_lista_enfermeiro where id_setor = ?1; ", nativeQuery = true)
	    void deleteCopyByTradeId(Long id);
	  
	  	
}
