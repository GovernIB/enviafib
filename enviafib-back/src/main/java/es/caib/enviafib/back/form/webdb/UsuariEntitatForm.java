package es.caib.enviafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.UsuariEntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariEntitatForm extends EnviaFIBBaseForm {
  
  private UsuariEntitatJPA usuariEntitat;
  
  public UsuariEntitatForm() {
  }
  
  public UsuariEntitatForm(UsuariEntitatForm __toClone) {
    super(__toClone);
      this.usuariEntitat = __toClone.usuariEntitat;
    this.listOfUsuariForUsuariid = __toClone.listOfUsuariForUsuariid;
    this.listOfEntitatForEntitatid = __toClone.listOfEntitatForEntitatid;
  }
  
  public UsuariEntitatForm(UsuariEntitatJPA usuariEntitat, boolean nou) {
    super(nou);
    this.usuariEntitat = usuariEntitat;
  }
  
  public UsuariEntitatJPA getUsuariEntitat() {
    return usuariEntitat;
  }
  public void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }
  
  
  private List<StringKeyValue> listOfUsuariForUsuariid;

  public List<StringKeyValue> getListOfUsuariForUsuariid() {
    return this.listOfUsuariForUsuariid;
  }

  public void setListOfUsuariForUsuariid(List<StringKeyValue> listOfUsuariForUsuariid) {
    this.listOfUsuariForUsuariid = listOfUsuariForUsuariid;
  }



  private List<StringKeyValue> listOfEntitatForEntitatid;

  public List<StringKeyValue> getListOfEntitatForEntitatid() {
    return this.listOfEntitatForEntitatid;
  }

  public void setListOfEntitatForEntitatid(List<StringKeyValue> listOfEntitatForEntitatid) {
    this.listOfEntitatForEntitatid = listOfEntitatForEntitatid;
  }



  
} // Final de Classe 
