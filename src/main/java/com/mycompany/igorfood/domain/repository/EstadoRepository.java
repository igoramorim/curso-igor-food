package com.mycompany.igorfood.domain.repository;

import java.util.List;

import com.mycompany.igorfood.domain.model.Estado;

public interface EstadoRepository {

	List<Estado> listar();
	Estado buscar(Long id);
	Estado salvar(Estado estado);
	void remover(Estado estado);
	
}
