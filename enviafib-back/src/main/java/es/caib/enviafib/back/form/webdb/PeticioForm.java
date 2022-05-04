package es.caib.enviafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PeticioForm extends EnviaFIBBaseForm {
  
  private PeticioJPA peticio;
  
  
  private CommonsMultipartFile fitxerID;
  private boolean fitxerIDDelete;
  
  public PeticioForm() {
  }
  
  public PeticioForm(PeticioForm __toClone) {
    super(__toClone);
      this.peticio = __toClone.peticio;
    this.listOfTraduccioForTitolID = __toClone.listOfTraduccioForTitolID;
    this.listOfUsuariForSolicitantID = __toClone.listOfUsuariForSolicitantID;
    this.listOfIdiomaForIdiomaID = __toClone.listOfIdiomaForIdiomaID;
  }
  
  public PeticioForm(PeticioJPA peticio, boolean nou) {
    super(nou);
    this.peticio = peticio;
  }
  
  public PeticioJPA getPeticio() {
    return peticio;
  }
  public void setPeticio(PeticioJPA peticio) {
    this.peticio = peticio;
  }
  
  java.util.List<es.caib.enviafib.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.enviafib.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.enviafib.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  public CommonsMultipartFile getFitxerID() {
    return fitxerID;
  }
  
   public void setFitxerID(CommonsMultipartFile fitxerID) {
    this.fitxerID = fitxerID;
  }
  public boolean isFitxerIDDelete() {
    return fitxerIDDelete;
  }
  
  public void setFitxerIDDelete(boolean fitxerIDDelete) {
    this.fitxerIDDelete = fitxerIDDelete;
   }
  private List<StringKeyValue> listOfTraduccioForTitolID;

  public List<StringKeyValue> getListOfTraduccioForTitolID() {
    return this.listOfTraduccioForTitolID;
  }

  public void setListOfTraduccioForTitolID(List<StringKeyValue> listOfTraduccioForTitolID) {
    this.listOfTraduccioForTitolID = listOfTraduccioForTitolID;
  }



  private List<StringKeyValue> listOfUsuariForSolicitantID;

  public List<StringKeyValue> getListOfUsuariForSolicitantID() {
    return this.listOfUsuariForSolicitantID;
  }

  public void setListOfUsuariForSolicitantID(List<StringKeyValue> listOfUsuariForSolicitantID) {
    this.listOfUsuariForSolicitantID = listOfUsuariForSolicitantID;
  }



  private List<StringKeyValue> listOfIdiomaForIdiomaID;

  public List<StringKeyValue> getListOfIdiomaForIdiomaID() {
    return this.listOfIdiomaForIdiomaID;
  }

  public void setListOfIdiomaForIdiomaID(List<StringKeyValue> listOfIdiomaForIdiomaID) {
    this.listOfIdiomaForIdiomaID = listOfIdiomaForIdiomaID;
  }



  
} // Final de Classe 
