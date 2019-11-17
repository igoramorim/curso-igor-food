package com.mycompany.igorfood.testes.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Async
	public void enviarEmail() {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			message.setTo("igor340@gmail.com");
			message.setFrom("igor340@gmail.com");
			message.setSubject("Assunto teste");
			message.setText("Conteudo teste", "<p>Conteudo teste</p>");
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
