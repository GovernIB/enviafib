package es.caib.enviafib.back.form.webdb;

import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.GrupJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class GrupForm extends EnviaFIBBaseForm {
  
  private GrupJPA grup;
  
  public GrupForm() {
  }
  
  public GrupForm(GrupForm __toClone) {
    super(__toClone);
      this.grup = __toClone.grup;
  }
  
  public GrupForm(GrupJPA grup, boolean nou) {
    super(nou);
    this.grup = grup;
  }
  
  public GrupJPA getGrup() {
    return grup;
  }
  public void setGrup(GrupJPA grup) {
    this.grup = grup;
  }
  
  
  
} // Final de Classe 
