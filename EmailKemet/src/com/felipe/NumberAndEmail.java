package com.felipe;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NumberAndEmail {

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	static String[] historicoCSV;
	static int[] numeros;

	public static void main(String args[]) throws AddressException,
			MessagingException, IOException {

		String[] pessoas = new String[3];
		pessoas[0] = "lvillardi@gmail.com";
		pessoas[1] = "fpontes@gmail.com";
		pessoas[2] = "ignusloki@hotmail.com";

		// Carrega o csv na memoria
		OperarCSV csv = new OperarCSV();
		csv.carregarCSV(historicoCSV);

		// Gera as DIs
		RandomDI numbers = new RandomDI();

		numeros = new int[3];
		numeros = RandomDI.validarDI();

		generateAndSendEmail(pessoas[0], numeros[0]);
		generateAndSendEmail(pessoas[1], numeros[1]);
		generateAndSendEmail(pessoas[2], numeros[2]);

		System.out
				.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");

		OperarCSV file = new OperarCSV();
		csv.escreverNumCSV(numeros);
	}

	public static void generateAndSendEmail(String pessoa, int numero)
			throws AddressException, MessagingException {

		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out
				.println("Mail Server Properties have been setup successfully..");

		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO,
				new InternetAddress(pessoa));
		generateMailMessage.setSubject("DI da Fase Noturna");
		String emailBody = "Sua DI é a de numero: " + numero
				+ "<br><br> Cordialmente, <br> Kemet";
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");

		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");

		// Enter your correct gmail UserID and Password (XXXApp Shah@gmail.com)
		transport.connect("smtp.gmail.com", "jogarkemet@gmail.com", "1234muda");
		transport.sendMessage(generateMailMessage,
				generateMailMessage.getAllRecipients());
		transport.close();
	}

}
