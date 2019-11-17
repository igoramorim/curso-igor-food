package com.mycompany.igorfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.igorfood.domain.model.Restaurante;
import com.mycompany.igorfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<Restaurante> listar() {
		return restauranteRepository.listar();
	}
	
	public Restaurante buscar(Long restauranteId) {
		return restauranteRepository.buscar(restauranteId);
	}

}
