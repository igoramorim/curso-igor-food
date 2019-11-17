package com.mycompany.igorfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mycompany.igorfood.IgorfoodApiApplication;
import com.mycompany.igorfood.domain.model.Cozinha;
import com.mycompany.igorfood.domain.repository.CozinhaRepository;

public class ExclusaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(IgorfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		Cozinha cozinha = Cozinha.builder().id(1L).build();
		
		cozinhaRepository.remover(cozinha.getId());
	}
	
}
