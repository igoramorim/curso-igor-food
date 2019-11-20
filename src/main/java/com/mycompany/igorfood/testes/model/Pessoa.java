package com.mycompany.igorfood.testes.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mycompany.igorfood.testes.util.BooleanSimNaoConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PESSOA")
public class Pessoa {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "ATIVO")
	@Convert(converter = BooleanSimNaoConverter.class)
	private Boolean ativo;

}
