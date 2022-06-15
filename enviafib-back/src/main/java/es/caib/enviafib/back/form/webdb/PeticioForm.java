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
  
  
  private CommonsMultipartFile fitxerFirmatID;
  private boolean fitxerFirmatIDDelete;
  
  public PeticioForm() {
  }
  
  public PeticioForm(PeticioForm __toClone) {
    super(__toClone);
      this.peticio = __toClone.peticio;
    this.listOfTraduccioForTitolID = __toClone.listOfTraduccioForTitolID;
    this.listOfUsuariForSolicitantID = __toClone.listOfUsuariForSolicitantID;
    this.listOfIdiomaForIdiomaID = __toClone.listOfIdiomaForIdiomaID;
    this.listOfValuesForEstat = __toClone.listOfValuesForEstat;
    this.listOfValuesForTipusdocumental = __toClone.listOfValuesForTipusdocumental;
    this.listOfValuesForIdiomadoc = __toClone.listOfValuesForIdiomadoc;
    this.listOfInfoSignaturaForInfosignaturaid = __toClone.listOfInfoSignaturaForInfosignaturaid;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
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
  public CommonsMultipartFile getFitxerFirmatID() {
    return fitxerFirmatID;
  }
  
   public void setFitxerFirmatID(CommonsMultipartFile fitxerFirmatID) {
    this.fitxerFirmatID = fitxerFirmatID;
  }
  public boolean isFitxerFirmatIDDelete() {
    return fitxerFirmatIDDelete;
  }
  
  public void setFitxerFirmatIDDelete(boolean fitxerFirmatIDDelete) {
    this.fitxerFirmatIDDelete = fitxerFirmatIDDelete;
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



  private List<StringKeyValue> listOfValuesForEstat;

  public List<StringKeyValue> getListOfValuesForEstat() {
    return this.listOfValuesForEstat;
  }

  public void setListOfValuesForEstat(List<StringKeyValue> listOfValuesForEstat) {
    this.listOfValuesForEstat = listOfValuesForEstat;
  }



  private List<StringKeyValue> listOfValuesForTipusdocumental;

  public List<StringKeyValue> getListOfValuesForTipusdocumental() {
    return this.listOfValuesForTipusdocumental;
  }

  public void setListOfValuesForTipusdocumental(List<StringKeyValue> listOfValuesForTipusdocumental) {
    this.listOfValuesForTipusdocumental = listOfValuesForTipusdocumental;
  }



  private List<StringKeyValue> listOfValuesForIdiomadoc;

  public List<StringKeyValue> getListOfValuesForIdiomadoc() {
    return this.listOfValuesForIdiomadoc;
  }

  public void setListOfValuesForIdiomadoc(List<StringKeyValue> listOfValuesForIdiomadoc) {
    this.listOfValuesForIdiomadoc = listOfValuesForIdiomadoc;
  }



  private List<StringKeyValue> listOfInfoSignaturaForInfosignaturaid;

  public List<StringKeyValue> getListOfInfoSignaturaForInfosignaturaid() {
    return this.listOfInfoSignaturaForInfosignaturaid;
  }

  public void setListOfInfoSignaturaForInfosignaturaid(List<StringKeyValue> listOfInfoSignaturaForInfosignaturaid) {
    this.listOfInfoSignaturaForInfosignaturaid = listOfInfoSignaturaForInfosignaturaid;
  }



  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  
} // Final de Classe 
