package com.backend.produtos.produtosestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.produtos.produtosestoque.model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{
    @Modifying
	    @Transactional
	    @Query(value = "DELETE FROM medicamento_listas_pratileiras where id_medicamento = ?1; ", nativeQuery = true)
	    void deleteCopyByTradeId(Long id);
	  
	  	@Modifying
	    @Transactional
	    @Query(value = "DELETE FROM medicamento_listas_pratileiras where id_medicamento = ?1; ", nativeQuery = true)
	    void deleteById(Long id);

}
