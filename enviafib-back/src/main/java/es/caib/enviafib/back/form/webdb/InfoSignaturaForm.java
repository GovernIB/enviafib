package es.caib.enviafib.back.form.webdb;

import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.InfoSignaturaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class InfoSignaturaForm extends EnviaFIBBaseForm {
  
  private InfoSignaturaJPA infoSignatura;
  
  public InfoSignaturaForm() {
  }
  
  public InfoSignaturaForm(InfoSignaturaForm __toClone) {
    super(__toClone);
      this.infoSignatura = __toClone.infoSignatura;
  }
  
  public InfoSignaturaForm(InfoSignaturaJPA infoSignatura, boolean nou) {
    super(nou);
    this.infoSignatura = infoSignatura;
  }
  
  public InfoSignaturaJPA getInfoSignatura() {
    return infoSignatura;
  }
  public void setInfoSignatura(InfoSignaturaJPA infoSignatura) {
    this.infoSignatura = infoSignatura;
  }
  
  
  
} // Final de Classe 
