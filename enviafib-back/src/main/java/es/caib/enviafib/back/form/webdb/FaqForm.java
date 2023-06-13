package es.caib.enviafib.back.form.webdb;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.FaqJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class FaqForm extends EnviaFIBBaseForm {
  
  private FaqJPA faq;
  
  
  private CommonsMultipartFile fitxer1ID;
  private boolean fitxer1IDDelete;
  
  
  private CommonsMultipartFile fitxer2ID;
  private boolean fitxer2IDDelete;
  
  
  private CommonsMultipartFile fitxer3ID;
  private boolean fitxer3IDDelete;
  
  public FaqForm() {
  }
  
  public FaqForm(FaqForm __toClone) {
    super(__toClone);
      this.faq = __toClone.faq;
  }
  
  public FaqForm(FaqJPA faq, boolean nou) {
    super(nou);
    this.faq = faq;
  }
  
  public FaqJPA getFaq() {
    return faq;
  }
  public void setFaq(FaqJPA faq) {
    this.faq = faq;
  }
  
  
  public CommonsMultipartFile getFitxer1ID() {
    return fitxer1ID;
  }
  
   public void setFitxer1ID(CommonsMultipartFile fitxer1ID) {
    this.fitxer1ID = fitxer1ID;
  }
  public boolean isFitxer1IDDelete() {
    return fitxer1IDDelete;
  }
  
  public void setFitxer1IDDelete(boolean fitxer1IDDelete) {
    this.fitxer1IDDelete = fitxer1IDDelete;
   }
  public CommonsMultipartFile getFitxer2ID() {
    return fitxer2ID;
  }
  
   public void setFitxer2ID(CommonsMultipartFile fitxer2ID) {
    this.fitxer2ID = fitxer2ID;
  }
  public boolean isFitxer2IDDelete() {
    return fitxer2IDDelete;
  }
  
  public void setFitxer2IDDelete(boolean fitxer2IDDelete) {
    this.fitxer2IDDelete = fitxer2IDDelete;
   }
  public CommonsMultipartFile getFitxer3ID() {
    return fitxer3ID;
  }
  
   public void setFitxer3ID(CommonsMultipartFile fitxer3ID) {
    this.fitxer3ID = fitxer3ID;
  }
  public boolean isFitxer3IDDelete() {
    return fitxer3IDDelete;
  }
  
  public void setFitxer3IDDelete(boolean fitxer3IDDelete) {
    this.fitxer3IDDelete = fitxer3IDDelete;
   }
  
} // Final de Classe 
