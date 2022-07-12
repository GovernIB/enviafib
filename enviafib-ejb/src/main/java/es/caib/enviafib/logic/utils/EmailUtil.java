package es.caib.enviafib.logic.utils;

import java.util.Date;
import java.util.List;


import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import es.caib.enviafib.commons.utils.Constants;

/**
 * 
 * @author anadal
 * 
 */
public class EmailUtil {

 /**
   * Envia un email a un {@link List} de emails
   * 
   * @param subject
   *          Asunto del Mensaje
   * @param message
   *          Contenido a enviar
   * @param from
   *          Indica la procedencia del mensaje
   * @param type
   *          Indica el con que de que tipo es el destinatario, Copia, Copia
   *          Oculta, etc
   * @param isHtml
   *          Decide si el contenido del mensaje a de ser visualizado en html o
   *          no
   * @param recipients
   *          Conjunto de emails para los que va dirigido el mensaje
   * @throws Exception
   */
  public static void postMail(String subject, String message, boolean isHtml,
      String from, String ... recipients) throws Exception {
    
    

    Context ctx = new InitialContext();
    Session session = (javax.mail.Session) ctx.lookup(Constants.MAIL_SERVICE);

    // Creamos el mensaje
    MimeMessage msg = new MimeMessage(session);

    InternetAddress addressFrom = new InternetAddress(from);
    System.out.println("XYZ AdressFrom = "+addressFrom);
    msg.setFrom(addressFrom);

    // Indicamos los destinatarios
    InternetAddress[] addressTo = new InternetAddress[recipients.length];
    for (int i = 0; i < recipients.length; i++) {
      System.out.println("XYZ AdressTo = "+recipients[i]);
      addressTo[i] = new InternetAddress(recipients[i]);
    }
    
    final RecipientType type = RecipientType.TO;

    msg.setRecipients(type, addressTo);

    // Configuramos el asunto
    msg.setSubject(subject, "UTF-8");
    msg.setSentDate(new Date());

    // Configuramos el contenido
    if (isHtml) {
      msg.setHeader("Content-Type", "text/html;charset=utf-8");
      /*
      URL urlToAdd = new URL(url);
      msg.setDataHandler(new DataHandler(urlToAdd));
      */
      msg.setContent(message, "text/html;charset=utf-8");
    } else {
      msg.setContent(message, "text/plain" /*; charset=UTF-8"*/);
    }

    // Mandamos el mail
    Transport.send(msg);

  }

}
