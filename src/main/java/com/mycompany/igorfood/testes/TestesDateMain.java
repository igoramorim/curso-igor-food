package com.mycompany.igorfood.testes;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class TestesDateMain {

	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		LocalDate birthDate = LocalDate.of(1993, Month.JULY, 14);
		
		System.out.printf("Today: %s\n", today.toString());
		System.out.printf("BirthDate: %s\n", birthDate.toString());
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.printf("Today formatted: %s\n", today.format(format));
		
		Integer age = Period.between(birthDate, today).getYears();
		System.out.printf("Age: %d\n", age);
		
		LocalDate yesterday = today.minusDays(1);
		System.out.printf("Yesterday: %s\n", yesterday.toString());
		
		long weeks = 5;
		LocalDate afterWeeks = today.plusWeeks(weeks);
		System.out.printf("Today plus %d weeks: %s\n", weeks, afterWeeks.toString());
		
		Boolean x = today.isAfter(birthDate);
		System.out.printf("%s is after %s : %s\n", today.toString(), birthDate.toString(), x.toString());
		
		LocalDateTime dateHourNow = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String dateHourNowFormated = dateHourNow.format(formatter);
		System.out.printf("LocalDateTime now: %s\nLocalDateTime Formatter: %s\n", dateHourNow.toString(), dateHourNowFormated);
		
		Long totalDays = Duration.between(LocalDateTime.of(birthDate, LocalTime.now()), LocalDateTime.of(today, LocalTime.now())).toDays();
		System.out.printf("Total days from %s : %s\n", birthDate.toString(), totalDays.toString());
	}

}
