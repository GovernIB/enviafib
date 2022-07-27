package es.caib.enviafib.back.form.webdb;

import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.InfoArxiuJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class InfoArxiuForm extends EnviaFIBBaseForm {
  
  private InfoArxiuJPA infoArxiu;
  
  public InfoArxiuForm() {
  }
  
  public InfoArxiuForm(InfoArxiuForm __toClone) {
    super(__toClone);
      this.infoArxiu = __toClone.infoArxiu;
  }
  
  public InfoArxiuForm(InfoArxiuJPA infoArxiu, boolean nou) {
    super(nou);
    this.infoArxiu = infoArxiu;
  }
  
  public InfoArxiuJPA getInfoArxiu() {
    return infoArxiu;
  }
  public void setInfoArxiu(InfoArxiuJPA infoArxiu) {
    this.infoArxiu = infoArxiu;
  }
  
  
  
} // Final de Classe 
