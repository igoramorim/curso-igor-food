package com.mycompany.igorfood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mycompany.igorfood.IgorfoodApiApplication;
import com.mycompany.igorfood.domain.model.FormaPagamento;
import com.mycompany.igorfood.domain.repository.FormaPagamentoRepository;

public class ConsultaFormaPagamentoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(IgorfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);
		
		List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.listar();
		
		for (FormaPagamento formaPagamento : todasFormasPagamentos) {
			System.out.println(formaPagamento.getDescricao());
		}
	}
	
}
