package com.mycompany.igorfood.testes.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.mycompany.igorfood.testes.model.Pessoa;

@Component
public class PessoaRepositoryImpl {
	
	@PersistenceContext
	private EntityManager manager;
	
	public Pessoa buscar(Long id) {
		return manager.find(Pessoa.class, id);
	}

}
