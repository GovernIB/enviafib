package es.caib.enviafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.InfoCustodyJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class InfoCustodyForm extends EnviaFIBBaseForm {
  
  private InfoCustodyJPA infoCustody;
  
  public InfoCustodyForm() {
  }
  
  public InfoCustodyForm(InfoCustodyForm __toClone) {
    super(__toClone);
      this.infoCustody = __toClone.infoCustody;
    this.listOfPeticioForPeticioid = __toClone.listOfPeticioForPeticioid;
  }
  
  public InfoCustodyForm(InfoCustodyJPA infoCustody, boolean nou) {
    super(nou);
    this.infoCustody = infoCustody;
  }
  
  public InfoCustodyJPA getInfoCustody() {
    return infoCustody;
  }
  public void setInfoCustody(InfoCustodyJPA infoCustody) {
    this.infoCustody = infoCustody;
  }
  
  
  private List<StringKeyValue> listOfPeticioForPeticioid;

  public List<StringKeyValue> getListOfPeticioForPeticioid() {
    return this.listOfPeticioForPeticioid;
  }

  public void setListOfPeticioForPeticioid(List<StringKeyValue> listOfPeticioForPeticioid) {
    this.listOfPeticioForPeticioid = listOfPeticioForPeticioid;
  }



  
} // Final de Classe 
