package com.mycompany.igorfood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mycompany.igorfood.domain.model.Restaurante;

@Repository
public interface EstadoRepository extends JpaRepository<Restaurante, Long>{

	@Query("from Restaurante where nome like %:nome% and cozinha.id = :cozinha")
	List<Restaurante> consultarPorNomeECozinha(String nome, Long cozinha);
	
}
