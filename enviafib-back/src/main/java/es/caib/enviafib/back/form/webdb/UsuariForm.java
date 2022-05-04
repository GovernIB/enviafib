package es.caib.enviafib.back.form.webdb;

import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.UsuariJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariForm extends EnviaFIBBaseForm {
  
  private UsuariJPA usuari;
  
  public UsuariForm() {
  }
  
  public UsuariForm(UsuariForm __toClone) {
    super(__toClone);
      this.usuari = __toClone.usuari;
  }
  
  public UsuariForm(UsuariJPA usuari, boolean nou) {
    super(nou);
    this.usuari = usuari;
  }
  
  public UsuariJPA getUsuari() {
    return usuari;
  }
  public void setUsuari(UsuariJPA usuari) {
    this.usuari = usuari;
  }
  
  
  
} // Final de Classe 
