package com.mycompany.igorfood.testes;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mycompany.igorfood.IgorfoodApiApplication;
import com.mycompany.igorfood.testes.model.Pessoa;
import com.mycompany.igorfood.testes.repository.PessoaRepositoryImpl;

public class TestesBuscaPessoaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(IgorfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PessoaRepositoryImpl pessoaRepository = applicationContext.getBean(PessoaRepositoryImpl.class);
		
		Pessoa pessoa = pessoaRepository.buscar(4L);
		
		System.out.println(pessoa.toString());

	}

}
