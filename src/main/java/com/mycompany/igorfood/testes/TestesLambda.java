package com.mycompany.igorfood.testes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mycompany.igorfood.domain.model.Cozinha;
import com.mycompany.igorfood.domain.model.Restaurante;

public class TestesLambda {

	public static void main(String[] args) {
		
		List<Restaurante> restaurantes =  getRestaurantes();
		restaurantes.forEach(Restaurante::testeReferenciaMetodo);
		
		
		System.out.printf("\nStream: taxa frete < 10.00\n");
		restaurantes.stream()
			.filter(r -> r.getTaxaFrete().compareTo(new BigDecimal("10.00")) == -1)
			.forEach(System.out::println);
		
		
		System.out.printf("\nStream: media de todas taxas frete\n");
		double average = restaurantes.stream()
			.mapToInt(r -> r.getTaxaFrete().intValue())
			.average()
			.getAsDouble();
		System.out.printf("Media: %.2f\n", average);
		
		
		System.out.printf("\nStream: outra forma de calcular a media de todas taxas frete\n");
		double average2 = restaurantes.stream()
				.collect(Collectors.averagingInt(r -> r.getTaxaFrete().intValue()));
		System.out.printf("Media: %.2f\n", average2);
		
		
		System.out.printf("\nStream: usando map para dobrar o valor de um atributo\n");
		restaurantes.stream()
			.map(r -> r.getId()* 2)
			.forEach(System.out::println);
		
		
		System.out.printf("\nStream: ordem alfabetica\n");
		// Altera a lista
//		Collections.sort(restaurantes, new Comparator<Restaurante>() {
//			@Override
//			public int compare(Restaurante r1, Restaurante r2) {
//				return r1.getNome().compareTo(r2.getNome());
//			}
//		});
//		restaurantes.forEach(System.out::println);
		
		// Nao altera a lista
		restaurantes.stream()
			.sorted((r1, r2) -> r1.getNome().compareTo(r2.getNome()))
			.forEach(System.out::println);
		
		
		System.out.printf("\nStream: IntSummaryStatistics\n");
		DoubleSummaryStatistics statistics = restaurantes.stream()
				.collect(Collectors.summarizingDouble(r -> r.getTaxaFrete().doubleValue()));
		System.out.printf("\nStatistics: %s\n", statistics);
		
		
		System.out.printf("\nStream: agrupando por taxa de frete\n");
		Map<Integer, List<Restaurante>> collectorMapFrete = restaurantes.stream()
				.collect(Collectors.groupingBy(r -> r.getTaxaFrete().intValue()));
		collectorMapFrete.forEach((k, v) -> { System.out.println("taxa: " + k + " - " + v); });
		
		
		System.out.printf("\nStream: agrupando por tipo de cozinha\n");
		Map<Object, List<Restaurante>> collectorMapCozinha = restaurantes.stream()
				.collect(Collectors.groupingBy(r -> r.getCozinha().getNome()));
		collectorMapCozinha.forEach((k, v) -> { System.out.println("cozinha: " + k + " - " + v); });

	}

	private static List<Restaurante> getRestaurantes() {
		Cozinha c1 = Cozinha.builder().id(1L).nome("Italiana").build();
		Cozinha c2 = Cozinha.builder().id(2L).nome("Japonesa").build();
		Cozinha c3 = Cozinha.builder().id(2L).nome("Francesa").build();
		Restaurante r1 = Restaurante.builder().id(1L).nome("Joao e Maria").taxaFrete(new BigDecimal("1.50")).cozinha(c1).build();
		Restaurante r2 = Restaurante.builder().id(2L).nome("Grillarica").taxaFrete(new BigDecimal("20.00")).cozinha(c1).build();
		Restaurante r3 = Restaurante.builder().id(3L).nome("Giorgio").taxaFrete(new BigDecimal("9.00")).cozinha(c2).build();
		Restaurante r4 = Restaurante.builder().id(4L).nome("Dona Lena").taxaFrete(new BigDecimal("1.50")).cozinha(c3).build();
		Restaurante r5 = Restaurante.builder().id(5L).nome("Sujinho").taxaFrete(new BigDecimal("15.50")).cozinha(c2).build();
		
		List<Restaurante> restaurantes = new ArrayList<>();
		restaurantes.add(r1);
		restaurantes.add(r2);
		restaurantes.add(r3);
		restaurantes.add(r4);
		restaurantes.add(r5);
		
		return restaurantes;
	}

}
