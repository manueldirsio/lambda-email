package com.aztlan.lambda.email.util;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.naming.NamingException;

public class EmaillUtil{

	private String hostName = System.getenv("email_hostName");
	private String port = System.getenv("email_port");
	private String sender =System.getenv("email_sender");
	private String smtpUser=System.getenv("email_smtp_user");
	private String smtpPass=System.getenv("email_smtp_pass");
	private String to;
	private String subject;
	private String message;
	private String cc=System.getenv("email_cc");
	private String fileName;
	private Session mailSession;
	private Properties props;
	private StringBuilder file;
	private File fileAux;

	public EmaillUtil() {}

	public EmaillUtil(String hostName, String port){
		this.hostName = hostName;
		this.port = port;
	}

	public String getCc() {
		return cc;
	}

	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public void setCc(String cc) {
		this.cc = cc;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostName() {
		return hostName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setFile(StringBuilder file){
		this.file = file;
	}
	public void setFile(File file){
		this.fileAux = file;
	}
	
	
	
	public String getSmtpUser() {
		return smtpUser;
	}

	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}

	public String getSmtpPass() {
		return smtpPass;
	}

	public void setSmtpPass(String smtpPass) {
		this.smtpPass = smtpPass;
	}

	private void init() throws NamingException{
		try{
			props = new Properties();
		
		
		    // Create a Properties object to contain connection configuration information.
			props.put("mail.transport.protocol", "smtp");
	    	props.put("mail.smtp.port", port); 
	    	props.put("mail.smtp.starttls.enable", true);
	    	props.put("mail.smtp.auth", true);
	    	props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	  
	        // Create a Session object to represent a mail session with the specified properties. 
	    	mailSession = Session.getDefaultInstance(props);
		}catch (Exception e){
			System.out.println("Ocurrio un error al enviar el correo por aws..."+e.getMessage());

		}
	}

	public boolean sendEmail() {
		boolean sendEmail=true;
		MimeMessage messagePetition = null;
		Multipart mp = null;
		MimeBodyPart partBody = null;
		MimeBodyPart partFile = null;
		FileDataSource fds = null;
		Transport transport=null;
		try {
		init();
        System.out.println("Datos de configuracion :hostname:"+hostName+"port:"+port+"sender:"+sender+"smtpUser:"+smtpUser+"smtpPass:"+smtpPass);
		messagePetition = new MimeMessage(mailSession);
		messagePetition.setFrom(new InternetAddress(sender));
		messagePetition.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		messagePetition.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
		messagePetition.setSubject(subject);
		
		mp = new MimeMultipart();
		
		partBody = new MimeBodyPart();
	    partBody.setText(message, "utf-8", "html");
	    mp.addBodyPart(partBody);

	    if(file != null){
		    partFile = new MimeBodyPart();
		    partFile.setDataHandler(new DataHandler(new ByteArrayDataSource(file.toString().getBytes(), "application/vnd.oasis.opendocument.text")));
		    partFile.setFileName(fileName);
		    mp.addBodyPart(partFile);
	    } if(fileAux != null){
	    	fds = new FileDataSource(fileAux);
	    	
	    	partFile = new MimeBodyPart();
		    partFile.setDataHandler(new DataHandler(fds));
		    partFile.setFileName(fileName);
		    mp.addBodyPart(partFile);
		    }
	    
		
	       transport = mailSession.getTransport();

	       messagePetition.setContent(mp);

		
			System.out.println("Enviando correo...");
          
            transport.connect(hostName, smtpUser, smtpPass);
            transport.sendMessage(messagePetition,messagePetition.getAllRecipients());
			System.out.println("Correo Enviado...");

		}catch(Exception e) {
			sendEmail=false;
			System.out.println("Ocurio un error al enviar correo..."+e.getMessage()+":"+e.getLocalizedMessage());

		} finally {
				try {
				   if(transport!=null)
					   transport.close();
				} catch (MessagingException e) {
					System.out.println("Ocurio un error al cerrar el transporte del correo...");
				}
		}
		return sendEmail;
	}
	/*
	public static void main(String[] args) {
		  EmaillUtil email=new EmaillUtil();
		  email.setHostName("smtp.gmail.com");
		  email.setPort("587");
		  email.setSender("AZTLAN-NOTIFICACIONES <tecnologias.aztlan@gmail.com>");
		  email.setSmtpUser("tecnologias.aztlan@gmail.com");
		  email.setSmtpPass("nuxnjdjsfncvbjji");
		  email.setCc("manu.dirsio@gmail.com");
			email.setTo("manu.dirsio@gmail.com");
			email.setSubject("Solicitud de Requerimiento para");
			email.setMessage("Hola mentos un ejecutivo se comunicara contigo");
			email.sendEmail();
	}*/
}