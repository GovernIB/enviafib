package es.caib.enviafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.SerieDocumentalJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class SerieDocumentalForm extends EnviaFIBBaseForm {
  
  private SerieDocumentalJPA serieDocumental;
  
  public SerieDocumentalForm() {
  }
  
  public SerieDocumentalForm(SerieDocumentalForm __toClone) {
    super(__toClone);
      this.serieDocumental = __toClone.serieDocumental;
    this.listOfValuesForTipusdocu = __toClone.listOfValuesForTipusdocu;
  }
  
  public SerieDocumentalForm(SerieDocumentalJPA serieDocumental, boolean nou) {
    super(nou);
    this.serieDocumental = serieDocumental;
  }
  
  public SerieDocumentalJPA getSerieDocumental() {
    return serieDocumental;
  }
  public void setSerieDocumental(SerieDocumentalJPA serieDocumental) {
    this.serieDocumental = serieDocumental;
  }
  
  
  private List<StringKeyValue> listOfValuesForTipusdocu;

  public List<StringKeyValue> getListOfValuesForTipusdocu() {
    return this.listOfValuesForTipusdocu;
  }

  public void setListOfValuesForTipusdocu(List<StringKeyValue> listOfValuesForTipusdocu) {
    this.listOfValuesForTipusdocu = listOfValuesForTipusdocu;
  }



  
} // Final de Classe 
