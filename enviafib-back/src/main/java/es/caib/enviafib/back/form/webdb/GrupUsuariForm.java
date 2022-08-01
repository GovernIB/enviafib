package es.caib.enviafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.GrupUsuariJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class GrupUsuariForm extends EnviaFIBBaseForm {
  
  private GrupUsuariJPA grupUsuari;
  
  public GrupUsuariForm() {
  }
  
  public GrupUsuariForm(GrupUsuariForm __toClone) {
    super(__toClone);
      this.grupUsuari = __toClone.grupUsuari;
    this.listOfGrupForGrupID = __toClone.listOfGrupForGrupID;
    this.listOfUsuariForUsuariID = __toClone.listOfUsuariForUsuariID;
  }
  
  public GrupUsuariForm(GrupUsuariJPA grupUsuari, boolean nou) {
    super(nou);
    this.grupUsuari = grupUsuari;
  }
  
  public GrupUsuariJPA getGrupUsuari() {
    return grupUsuari;
  }
  public void setGrupUsuari(GrupUsuariJPA grupUsuari) {
    this.grupUsuari = grupUsuari;
  }
  
  
  private List<StringKeyValue> listOfGrupForGrupID;

  public List<StringKeyValue> getListOfGrupForGrupID() {
    return this.listOfGrupForGrupID;
  }

  public void setListOfGrupForGrupID(List<StringKeyValue> listOfGrupForGrupID) {
    this.listOfGrupForGrupID = listOfGrupForGrupID;
  }



  private List<StringKeyValue> listOfUsuariForUsuariID;

  public List<StringKeyValue> getListOfUsuariForUsuariID() {
    return this.listOfUsuariForUsuariID;
  }

  public void setListOfUsuariForUsuariID(List<StringKeyValue> listOfUsuariForUsuariID) {
    this.listOfUsuariForUsuariID = listOfUsuariForUsuariID;
  }



  
} // Final de Classe 
