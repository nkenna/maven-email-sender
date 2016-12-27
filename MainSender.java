/*
 * Maven Simple Email Sender Library, written by Ugwumsinachi Nnadi
 */
package com.emailer;

import com.libInterface.SenderInterface;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author STEINACOZ-PC
 */
public class MainSender implements SenderInterface {
    
    private String toAddres;
    private String fromAddres;
    private String username;
    private String password;
    private String host;
    private String emailSubject;
    private String emailMessage;
    private String status;

    @Override
    public String toAddress(String toAdd) {
        toAddres = toAdd;
        return toAddres;
    }

    @Override
    public String fromAddress(String fromAdd) {
        fromAddres = fromAdd;
        return fromAddres;
    }

    @Override
    public String emailUser(String username) {
        this.username = username;
        return this.username;
    }

    @Override
    public String emailPass(String password) {
        this.password = password;
        return this.password;
    }

    @Override
    public String emailHost(String host) {
      this.host = host;
        return this.host;
    }
    
    
/**
 * if you are using yahoo, I implemented method overloading so as to use 
 * yahoo as the default host.
 * if you prefer your own server apart from yahoo
 * use the other emailHost method.
 * 
 * @return 
 */
    @Override
    public String emailHost() {
        host = "smtp.mail.yahoo.com";
        return host;
    }

    @Override
    public String sendEmail(String subject, String mess) {
        
     
        emailSubject = subject;
        emailMessage = mess;
        
         Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        
        
        //get session
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

protected PasswordAuthentication getPasswordAuthentication(){
    return new PasswordAuthentication(username, password);
}
        });
        
        try {
// Create a default MimeMessage object.
Message message = new MimeMessage(session);

// Set From: header field of the header.
message.setFrom(new InternetAddress(fromAddres));

// Set To: header field of the header.
message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.toAddres));

// Set Subject: header field
message.setSubject(emailSubject);

// Now set the actual message
message.setText(emailMessage);

//Create Multipar message
Multipart multipart = new MimeMultipart();



//send complete message parts
message.setContent(multipart);

// Send message
Transport.send(message);

status = "Email Sent successfully";
System.out.println(status);
return status;
} catch (MessagingException e) {
throw new RuntimeException(e);

}

    }   
    
}
