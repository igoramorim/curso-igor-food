package com.mycompany.igorfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.igorfood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	// jpa query keywords -> https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	
	// 				  findByNomeContaining
	Optional<Cozinha> findByNome(String nome);
	
	Optional<Cozinha> findFirstCozinhaContaining(String nome);
	
	List<Cozinha> findTop2CozinhaContaining(String nome);
	
	boolean existsByNome(String nome);
	
	int countByNomeContaining(String nome);
	 
	
}
