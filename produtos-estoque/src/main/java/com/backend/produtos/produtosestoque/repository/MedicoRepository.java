package com.backend.produtos.produtosestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.produtos.produtosestoque.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
     @Modifying
	    @Transactional
	    @Query(value = "DELETE FROM medico_listas_pacientes where id_medico = ?1; ", nativeQuery = true)
	    void deleteCopyByTradeId(Long id);
	  
	  	@Modifying
	    @Transactional
	    @Query(value = "DELETE FROM medico_listas_pacientes where id_medico = ?1; ", nativeQuery = true)
	    void deleteById(Long id);

}
