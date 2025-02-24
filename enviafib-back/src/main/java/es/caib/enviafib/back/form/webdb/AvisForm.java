package es.caib.enviafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.AvisJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class AvisForm extends EnviaFIBBaseForm {
  
  private AvisJPA avis;
  
  public AvisForm() {
  }
  
  public AvisForm(AvisForm __toClone) {
    super(__toClone);
      this.avis = __toClone.avis;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
  }
  
  public AvisForm(AvisJPA avis, boolean nou) {
    super(nou);
    this.avis = avis;
  }
  
  public AvisJPA getAvis() {
    return avis;
  }
  public void setAvis(AvisJPA avis) {
    this.avis = avis;
  }
  
  
  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  
} // Final de Classe 
