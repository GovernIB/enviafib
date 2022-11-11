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
    this.listOfUsuariForSolicitantID = __toClone.listOfUsuariForSolicitantID;
    this.listOfIdiomaForIdiomaID = __toClone.listOfIdiomaForIdiomaID;
    this.listOfValuesForEstat = __toClone.listOfValuesForEstat;
    this.listOfValuesForTipusDocumental = __toClone.listOfValuesForTipusDocumental;
    this.listOfValuesForIdiomaDoc = __toClone.listOfValuesForIdiomaDoc;
    this.listOfInfoSignaturaForInfoSignaturaID = __toClone.listOfInfoSignaturaForInfoSignaturaID;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
    this.listOfValuesForArxiuReqParamDocEstatElabora = __toClone.listOfValuesForArxiuReqParamDocEstatElabora;
    this.listOfValuesForArxiuReqParamOrigen = __toClone.listOfValuesForArxiuReqParamOrigen;
    this.listOfInfoArxiuForInfoArxiuID = __toClone.listOfInfoArxiuForInfoArxiuID;
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



  private List<StringKeyValue> listOfValuesForTipusDocumental;

  public List<StringKeyValue> getListOfValuesForTipusDocumental() {
    return this.listOfValuesForTipusDocumental;
  }

  public void setListOfValuesForTipusDocumental(List<StringKeyValue> listOfValuesForTipusDocumental) {
    this.listOfValuesForTipusDocumental = listOfValuesForTipusDocumental;
  }



  private List<StringKeyValue> listOfValuesForIdiomaDoc;

  public List<StringKeyValue> getListOfValuesForIdiomaDoc() {
    return this.listOfValuesForIdiomaDoc;
  }

  public void setListOfValuesForIdiomaDoc(List<StringKeyValue> listOfValuesForIdiomaDoc) {
    this.listOfValuesForIdiomaDoc = listOfValuesForIdiomaDoc;
  }



  private List<StringKeyValue> listOfInfoSignaturaForInfoSignaturaID;

  public List<StringKeyValue> getListOfInfoSignaturaForInfoSignaturaID() {
    return this.listOfInfoSignaturaForInfoSignaturaID;
  }

  public void setListOfInfoSignaturaForInfoSignaturaID(List<StringKeyValue> listOfInfoSignaturaForInfoSignaturaID) {
    this.listOfInfoSignaturaForInfoSignaturaID = listOfInfoSignaturaForInfoSignaturaID;
  }



  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  private List<StringKeyValue> listOfValuesForArxiuReqParamDocEstatElabora;

  public List<StringKeyValue> getListOfValuesForArxiuReqParamDocEstatElabora() {
    return this.listOfValuesForArxiuReqParamDocEstatElabora;
  }

  public void setListOfValuesForArxiuReqParamDocEstatElabora(List<StringKeyValue> listOfValuesForArxiuReqParamDocEstatElabora) {
    this.listOfValuesForArxiuReqParamDocEstatElabora = listOfValuesForArxiuReqParamDocEstatElabora;
  }



  private List<StringKeyValue> listOfValuesForArxiuReqParamOrigen;

  public List<StringKeyValue> getListOfValuesForArxiuReqParamOrigen() {
    return this.listOfValuesForArxiuReqParamOrigen;
  }

  public void setListOfValuesForArxiuReqParamOrigen(List<StringKeyValue> listOfValuesForArxiuReqParamOrigen) {
    this.listOfValuesForArxiuReqParamOrigen = listOfValuesForArxiuReqParamOrigen;
  }



  private List<StringKeyValue> listOfInfoArxiuForInfoArxiuID;

  public List<StringKeyValue> getListOfInfoArxiuForInfoArxiuID() {
    return this.listOfInfoArxiuForInfoArxiuID;
  }

  public void setListOfInfoArxiuForInfoArxiuID(List<StringKeyValue> listOfInfoArxiuForInfoArxiuID) {
    this.listOfInfoArxiuForInfoArxiuID = listOfInfoArxiuForInfoArxiuID;
  }



  
} // Final de Classe 
