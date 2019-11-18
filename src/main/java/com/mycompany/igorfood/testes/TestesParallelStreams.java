package com.mycompany.igorfood.testes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestesParallelStreams {
	
	public static void main(String[] args) {
		LocalDateTime start = LocalDateTime.now();
		
		long count = Stream.iterate(0, n -> n + 1)
				.limit(1_000_00)
//				.parallel()
				.filter(TestesParallelStreams::isPrime)
				.peek(x -> System.out.format("%s\t", x))
				.count();
		
		System.out.println("\nTotal: " + count);
		
		LocalDateTime end = LocalDateTime.now();
		
		System.out.println(Duration.between(start, end).toMillis());
		
	}
	
	public static boolean isPrime(int number) {
        if (number <= 1) return false;
        return !IntStream.rangeClosed(2, number / 2).anyMatch(i -> number % i == 0);
    }

}
