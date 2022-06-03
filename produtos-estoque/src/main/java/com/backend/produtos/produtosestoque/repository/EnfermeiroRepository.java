package com.backend.produtos.produtosestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.produtos.produtosestoque.model.Enfermeiro;

public interface EnfermeiroRepository extends JpaRepository<Enfermeiro, Long>{
    @Modifying
	    @Transactional
	    @Query(value = "DELETE FROM enfermeiro_pratileira_sob_responsabilidade where id_enfermeiro = ?1; ", nativeQuery = true)
	    void deleteCopyByTradeId(Long id);
	  
	  	@Modifying
	    @Transactional
	    @Query(value = "DELETE FROM enfermeiro_pratileira_sob_responsabilidade where id_enfermeiro = ?1; ", nativeQuery = true)
	    void deleteById(Long id);

}
