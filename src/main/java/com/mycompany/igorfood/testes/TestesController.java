package com.mycompany.igorfood.testes;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.igorfood.testes.service.MailService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/testes")
public class TestesController {
	
	@Autowired
	private MailService mailService;
	
	@GetMapping("/max-min-param")
	public String validateMaxMinParam(@RequestParam @Min(value = 5, message = "Muito baixo!") @Max(value = 10, message = "Muito alto!") Integer number) {
		log.debug("Request /max-min-param: {}", number);
		return String.format("Request param: %d", number);
	}
	
	@GetMapping("/max-min-path/{number}")
	public String validateMaxMinPath(@PathVariable @Min(value = 5, message = "Muito baixo!") @Max(value = 10, message = "Muito alto!") Integer number) {
		log.debug("Request /max-min-path: {}", number);
		return String.format("Request path variable: %d", number);
	}
	
	@PostMapping("/mail")
	public void sendMail() {
		mailService.enviarEmail();
	}
	
	@GetMapping("/size-param/{param}")
	public String validateSize(@PathVariable @Size(min = 2, max = 5, message = "Size min(3) max(5)") String param) {
		return String.format("Request size param: %s", param);
	}
	
	@GetMapping("/email")
	public String validateEmail(@Email(message = "Digite um email valido") @RequestParam String email) {
		return String.format("Email: %s", email);
	}
	
	@GetMapping("/date")
	public String validateDate(@DateTimeFormat(iso = ISO.DATE) @RequestParam LocalDate date) {
		return String.format("Date: %s", date.toString());
	}
	
	@PostMapping("/file")
	public String saveFile(@RequestParam MultipartFile file) {
		return String.format("Save file %s", file.getOriginalFilename());
	}

}
