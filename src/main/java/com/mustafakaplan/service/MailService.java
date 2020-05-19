package com.mustafakaplan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mustafakaplan.notalma.HomeController;

@Service
public class MailService 
{
	@Autowired
	private JavaMailSender mailSender;
	
	public void registerMail(String mail, String key)
	{
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("aracdeposu@gmail.com");
		email.setTo(mail);
		email.setSubject("�yeli�i Tamamla");
		email.setText("�yeli�i Tamamlamak ��in A�a��daki Linke T�klay�n�z\n\n" + HomeController.url + "/reg/" + key);
		
		mailSender.send(email);
	}
}
