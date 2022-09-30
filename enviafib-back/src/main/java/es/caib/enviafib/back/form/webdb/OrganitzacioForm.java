package es.caib.enviafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.OrganitzacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class OrganitzacioForm extends EnviaFIBBaseForm {
  
  private OrganitzacioJPA organitzacio;
  
  public OrganitzacioForm() {
  }
  
  public OrganitzacioForm(OrganitzacioForm __toClone) {
    super(__toClone);
      this.organitzacio = __toClone.organitzacio;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
  }
  
  public OrganitzacioForm(OrganitzacioJPA organitzacio, boolean nou) {
    super(nou);
    this.organitzacio = organitzacio;
  }
  
  public OrganitzacioJPA getOrganitzacio() {
    return organitzacio;
  }
  public void setOrganitzacio(OrganitzacioJPA organitzacio) {
    this.organitzacio = organitzacio;
  }
  
  
  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  
} // Final de Classe 
