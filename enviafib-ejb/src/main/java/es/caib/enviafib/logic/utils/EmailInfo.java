package es.caib.enviafib.logic.utils;

import java.io.Serializable;

/**
 * Created by Fundacio Bit
 * 
 * @author anadal
 */

public class EmailInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long idObjectSent;

  private String email;
  private String subject;
  private String message;
  private boolean html;

  /**
   * 
   */
  public EmailInfo() {
    super();
  }



  /**
   * @param idObjectSent
   * @param email
   * @param subject
   * @param message
   * @param html
   */
  public EmailInfo(Long idObjectSent, String email, String subject, String message,
      boolean html) {
    super();
    this.idObjectSent = idObjectSent;
    this.email = email;
    this.subject = subject;
    this.message = message;
    this.html = html;
  }


  public Long getIdObjectSent() {
    return idObjectSent;
  }

  public void setIdObjectSent(Long idObjectSent) {
    this.idObjectSent = idObjectSent;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public void setMessage(String url) {
    this.message = url;
  }

  public boolean isHtml() {
    return html;
  }

  public void setHtml(boolean html) {
    this.html = html;
  }

}
