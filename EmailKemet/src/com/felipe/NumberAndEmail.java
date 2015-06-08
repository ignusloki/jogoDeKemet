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
	static String[] pessoas;
	static int[] numeros;
	static String csvKemetDI = "D:\\Kemet\\DIs.csv";
	static String csvKemetPessoas = "D:\\Kemet\\Pessoas.csv";

	public static void main(String args[]) throws AddressException,
			MessagingException, IOException {

		// Carrega o csv na memoria
		OperarCSV csv = new OperarCSV();
		pessoas = csv.carregarCSV(pessoas, csvKemetPessoas);
		historicoCSV = csv.carregarCSV(historicoCSV, csvKemetDI);

		// Conta quantas DIs devem gerar
		int numeroDIsParaGerar = Integer.parseInt(pessoas[1])
				+ Integer.parseInt(pessoas[3]) + Integer.parseInt(pessoas[5])
				+ Integer.parseInt(pessoas[7]);

		// Gera as DIs
		RandomDI numbers = new RandomDI();
		numeros = RandomDI.validarDI(historicoCSV, numeroDIsParaGerar);

		/*
		 * System.out.println(pessoas[0] + " para " + numeros[0]);
		 * System.out.println(pessoas[2] + " para " + numeros[1]);
		 * System.out.println(pessoas[4] + " para " + numeros[2]);
		 * System.out.println(pessoas[6] + " para " + numeros[3]);
		 */

		generateAndSendEmail(pessoas[0], numeros[0]);
		generateAndSendEmail(pessoas[2], numeros[1]);
		generateAndSendEmail(pessoas[4], numeros[2]);
		generateAndSendEmail(pessoas[6], numeros[3]);

		System.out
				.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");

		csv.escreverNumCSV(numeros, csvKemetDI);
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
