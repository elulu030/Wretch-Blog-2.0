package myBeans;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class sendMail {
	final String username = "god801255@gmail.com";	//username@gmail.com
  	final String password = "encl cuml hfbe aikm";
  	private Session session;
  	
  	 public sendMail() {
         String host = "smtp.gmail.com";
         int port = 587;

         Properties props = new Properties();
         props.put("mail.smtp.host", host);
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.port", port);
         props.put("mail.smtp.ssl.protocols", "TLSv1.2");
         props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

         this.session = Session.getInstance(props, new Authenticator() {
             protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(username, password);
             }
         });
     }
  	
  	public boolean sendVerificationEmail(String recipient, int identifyCode) {
  		 try {
             MimeMessage msg = new MimeMessage(session);

             msg.setFrom(new InternetAddress(username));
             msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
             System.out.println("準備寄給: " + recipient);
             msg.setSubject("註冊驗證信");
             msg.setSentDate(new Date());

             MimeBodyPart htmlPart = new MimeBodyPart();
             String htmlContent = String.format(
                 "<a href='http://localhost:8080/jakartaee-hello-world/identify?identify_code=%d&&user_mail=%s'><b>hello %s，您的驗證碼是 %d，或點擊連結登入</b></a>",
                 identifyCode, recipient, recipient, identifyCode
             );
             htmlPart.setContent(htmlContent, "text/html;charset=UTF-8");

             Multipart multiPart = new MimeMultipart();
             multiPart.addBodyPart(htmlPart);
             msg.setContent(multiPart);

             Transport.send(msg);
             return true;

         } catch (MessagingException e) {
             e.printStackTrace();
             return false;
         }
     }
  	
  	public boolean sendPwdEmail(String recipient, String pwd) {
 		 try {
            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(username));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            System.out.println("準備寄給: " + recipient);
            msg.setSubject("取得密碼信");
            msg.setSentDate(new Date());

            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = String.format(
                "<a href='http://localhost:8080/jakartaee-hello-world/login'><b>hello %s，您的密碼是 %s，點擊連結登入</b></a>",
                	recipient, pwd
            );
            htmlPart.setContent(htmlContent, "text/html;charset=UTF-8");

            Multipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(htmlPart);
            msg.setContent(multiPart);

            Transport.send(msg);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
 }
