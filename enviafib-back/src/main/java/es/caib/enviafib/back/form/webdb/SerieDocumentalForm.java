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
    this.listOfValuesForTipusDocumental = __toClone.listOfValuesForTipusDocumental;
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
  
  
  private List<StringKeyValue> listOfValuesForTipusDocumental;

  public List<StringKeyValue> getListOfValuesForTipusDocumental() {
    return this.listOfValuesForTipusDocumental;
  }

  public void setListOfValuesForTipusDocumental(List<StringKeyValue> listOfValuesForTipusDocumental) {
    this.listOfValuesForTipusDocumental = listOfValuesForTipusDocumental;
  }



  
} // Final de Classe 
