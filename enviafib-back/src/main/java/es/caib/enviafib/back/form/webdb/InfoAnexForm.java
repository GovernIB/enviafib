package es.caib.enviafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.InfoAnexJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class InfoAnexForm extends EnviaFIBBaseForm {
  
  private InfoAnexJPA infoAnex;
  
  
  private CommonsMultipartFile anexID;
  private boolean anexIDDelete;
  
  public InfoAnexForm() {
  }
  
  public InfoAnexForm(InfoAnexForm __toClone) {
    super(__toClone);
      this.infoAnex = __toClone.infoAnex;
    this.listOfPeticioForPeticioID = __toClone.listOfPeticioForPeticioID;
  }
  
  public InfoAnexForm(InfoAnexJPA infoAnex, boolean nou) {
    super(nou);
    this.infoAnex = infoAnex;
  }
  
  public InfoAnexJPA getInfoAnex() {
    return infoAnex;
  }
  public void setInfoAnex(InfoAnexJPA infoAnex) {
    this.infoAnex = infoAnex;
  }
  
  
  public CommonsMultipartFile getAnexID() {
    return anexID;
  }
  
   public void setAnexID(CommonsMultipartFile anexID) {
    this.anexID = anexID;
  }
  public boolean isAnexIDDelete() {
    return anexIDDelete;
  }
  
  public void setAnexIDDelete(boolean anexIDDelete) {
    this.anexIDDelete = anexIDDelete;
   }
  private List<StringKeyValue> listOfPeticioForPeticioID;

  public List<StringKeyValue> getListOfPeticioForPeticioID() {
    return this.listOfPeticioForPeticioID;
  }

  public void setListOfPeticioForPeticioID(List<StringKeyValue> listOfPeticioForPeticioID) {
    this.listOfPeticioForPeticioID = listOfPeticioForPeticioID;
  }



  
} // Final de Classe 
