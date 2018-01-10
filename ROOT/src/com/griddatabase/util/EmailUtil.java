package com.griddatabase.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class EmailUtil {
	private static Logger logger = Logger.getLogger(EmailUtil.class);
	private Session session;
	private Multipart multipart = new MimeMultipart();
	private String subject;
	private Properties properties = new Properties();
	
	public EmailUtil() { 
		properties.put("mail.store.protocol", "pop3");
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.host", ResourceUtil.get().getString("email.server"));
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}
	
	public void addAttachement(final DataSource dataSource, final String attachmentName, final String mimeType) throws MessagingException {
		if (dataSource == null || mimeType == null) return;
		BodyPart result = new MimeBodyPart();
	    result.setDisposition(javax.mail.Part.ATTACHMENT);
	    result.setDataHandler(new DataHandler(dataSource));
	    result.setFileName(attachmentName);
	    result.setHeader("Content-Type", mimeType);	    
	    multipart.addBodyPart(result);
	}
	
	public void send(final String recipient) throws MessagingException, InterruptedException {
		if (recipient == null) return;
		logger.info("EmailUtil sending to " + recipient);
		session = Session.getInstance(properties);
	    MimeMessage message = new MimeMessage(session);
		if (multipart.getCount() > 0) 
			message.setContent(multipart);
		else
			message.setContent(" ", "text/plain");
	    message.setSubject(subject);
	    message.setFrom(new InternetAddress(ResourceUtil.get().getString("email.account")));
	    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
	    Transport transport = session.getTransport();
	    for (int j = 0 ; j < 5 ; j++) {
	       transport.connect(ResourceUtil.get().getString("email.account"), ResourceUtil.get().getString("email.password"));
	       if (transport.isConnected()) break;
	       else wait(5000);
	    }
	    if (!transport.isConnected()) throw new MessagingException();
	    transport.sendMessage(message, message.getAllRecipients());
	    transport.close();
	}
}