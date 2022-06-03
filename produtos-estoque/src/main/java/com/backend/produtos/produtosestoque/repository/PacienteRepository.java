package com.backend.produtos.produtosestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.produtos.produtosestoque.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    @Modifying
	    @Transactional
	    @Query(value = "DELETE FROM paciente_listas_medicamentos where id_paciente = ?1; ", nativeQuery = true)
	    void deleteCopyByTradeId(Long id);
	  
	  	@Modifying
	    @Transactional
	    @Query(value = "DELETE FROM paciente_listas_medicamentos where id_paciente = ?1; ", nativeQuery = true)
	    void deleteById(Long id);

}
