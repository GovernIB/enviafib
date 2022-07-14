
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.PeticioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PeticioFilterForm extends EnviaFIBBaseFilterForm implements PeticioFields {

  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.Long peticioIDDesde;

  public java.lang.Long getPeticioIDDesde() {
    return this.peticioIDDesde;
  }

  public void setPeticioIDDesde(java.lang.Long peticioIDDesde) {
    this.peticioIDDesde = peticioIDDesde;
  }


  private java.lang.Long peticioIDFins;

  public java.lang.Long getPeticioIDFins() {
    return this.peticioIDFins;
  }

  public void setPeticioIDFins(java.lang.Long peticioIDFins) {
    this.peticioIDFins = peticioIDFins;
  }


  private java.sql.Timestamp dataCreacioDesde;

  public java.sql.Timestamp getDataCreacioDesde() {
    return this.dataCreacioDesde;
  }

  public void setDataCreacioDesde(java.sql.Timestamp dataCreacioDesde) {
    this.dataCreacioDesde = dataCreacioDesde;
  }


  private java.sql.Timestamp dataCreacioFins;

  public java.sql.Timestamp getDataCreacioFins() {
    return this.dataCreacioFins;
  }

  public void setDataCreacioFins(java.sql.Timestamp dataCreacioFins) {
    this.dataCreacioFins = dataCreacioFins;
  }


  private java.sql.Timestamp dataFinalDesde;

  public java.sql.Timestamp getDataFinalDesde() {
    return this.dataFinalDesde;
  }

  public void setDataFinalDesde(java.sql.Timestamp dataFinalDesde) {
    this.dataFinalDesde = dataFinalDesde;
  }


  private java.sql.Timestamp dataFinalFins;

  public java.sql.Timestamp getDataFinalFins() {
    return this.dataFinalFins;
  }

  public void setDataFinalFins(java.sql.Timestamp dataFinalFins) {
    this.dataFinalFins = dataFinalFins;
  }


  private java.lang.Long solicitantIDDesde;

  public java.lang.Long getSolicitantIDDesde() {
    return this.solicitantIDDesde;
  }

  public void setSolicitantIDDesde(java.lang.Long solicitantIDDesde) {
    this.solicitantIDDesde = solicitantIDDesde;
  }


  private java.lang.Long solicitantIDFins;

  public java.lang.Long getSolicitantIDFins() {
    return this.solicitantIDFins;
  }

  public void setSolicitantIDFins(java.lang.Long solicitantIDFins) {
    this.solicitantIDFins = solicitantIDFins;
  }


  private java.lang.String idiomaID;

  public java.lang.String getIdiomaID() {
    return this.idiomaID;
  }

  public void setIdiomaID(java.lang.String idiomaID) {
    this.idiomaID = idiomaID;
  }


  private java.lang.String destinatariNif;

  public java.lang.String getDestinatariNif() {
    return this.destinatariNif;
  }

  public void setDestinatariNif(java.lang.String destinatariNif) {
    this.destinatariNif = destinatariNif;
  }


  private java.lang.Integer estatDesde;

  public java.lang.Integer getEstatDesde() {
    return this.estatDesde;
  }

  public void setEstatDesde(java.lang.Integer estatDesde) {
    this.estatDesde = estatDesde;
  }


  private java.lang.Integer estatFins;

  public java.lang.Integer getEstatFins() {
    return this.estatFins;
  }

  public void setEstatFins(java.lang.Integer estatFins) {
    this.estatFins = estatFins;
  }


  private java.lang.String tipusDocumental;

  public java.lang.String getTipusDocumental() {
    return this.tipusDocumental;
  }

  public void setTipusDocumental(java.lang.String tipusDocumental) {
    this.tipusDocumental = tipusDocumental;
  }


  private java.lang.String idiomaDoc;

  public java.lang.String getIdiomaDoc() {
    return this.idiomaDoc;
  }

  public void setIdiomaDoc(java.lang.String idiomaDoc) {
    this.idiomaDoc = idiomaDoc;
  }


  private java.lang.Long infoSignaturaIDDesde;

  public java.lang.Long getInfoSignaturaIDDesde() {
    return this.infoSignaturaIDDesde;
  }

  public void setInfoSignaturaIDDesde(java.lang.Long infoSignaturaIDDesde) {
    this.infoSignaturaIDDesde = infoSignaturaIDDesde;
  }


  private java.lang.Long infoSignaturaIDFins;

  public java.lang.Long getInfoSignaturaIDFins() {
    return this.infoSignaturaIDFins;
  }

  public void setInfoSignaturaIDFins(java.lang.Long infoSignaturaIDFins) {
    this.infoSignaturaIDFins = infoSignaturaIDFins;
  }


  private java.lang.Integer tipusDesde;

  public java.lang.Integer getTipusDesde() {
    return this.tipusDesde;
  }

  public void setTipusDesde(java.lang.Integer tipusDesde) {
    this.tipusDesde = tipusDesde;
  }


  private java.lang.Integer tipusFins;

  public java.lang.Integer getTipusFins() {
    return this.tipusFins;
  }

  public void setTipusFins(java.lang.Integer tipusFins) {
    this.tipusFins = tipusFins;
  }


  private java.lang.String errorMsg;

  public java.lang.String getErrorMsg() {
    return this.errorMsg;
  }

  public void setErrorMsg(java.lang.String errorMsg) {
    this.errorMsg = errorMsg;
  }


  private java.lang.String errorException;

  public java.lang.String getErrorException() {
    return this.errorException;
  }

  public void setErrorException(java.lang.String errorException) {
    this.errorException = errorException;
  }


  private java.lang.String peticioPortafirmes;

  public java.lang.String getPeticioPortafirmes() {
    return this.peticioPortafirmes;
  }

  public void setPeticioPortafirmes(java.lang.String peticioPortafirmes) {
    this.peticioPortafirmes = peticioPortafirmes;
  }


  private java.lang.String reason;

  public java.lang.String getReason() {
    return this.reason;
  }

  public void setReason(java.lang.String reason) {
    this.reason = reason;
  }


  public PeticioFilterForm() {
  }
  
  public PeticioFilterForm(PeticioFilterForm __toClone) {
    super(__toClone);
    this.nom = __toClone.nom;
    this.peticioIDDesde = __toClone.peticioIDDesde;
    this.peticioIDFins = __toClone.peticioIDFins;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.dataFinalDesde = __toClone.dataFinalDesde;
    this.dataFinalFins = __toClone.dataFinalFins;
    this.solicitantIDDesde = __toClone.solicitantIDDesde;
    this.solicitantIDFins = __toClone.solicitantIDFins;
    this.idiomaID = __toClone.idiomaID;
    this.destinatariNif = __toClone.destinatariNif;
    this.estatDesde = __toClone.estatDesde;
    this.estatFins = __toClone.estatFins;
    this.tipusDocumental = __toClone.tipusDocumental;
    this.idiomaDoc = __toClone.idiomaDoc;
    this.infoSignaturaIDDesde = __toClone.infoSignaturaIDDesde;
    this.infoSignaturaIDFins = __toClone.infoSignaturaIDFins;
    this.tipusDesde = __toClone.tipusDesde;
    this.tipusFins = __toClone.tipusFins;
    this.errorMsg = __toClone.errorMsg;
    this.errorException = __toClone.errorException;
    this.peticioPortafirmes = __toClone.peticioPortafirmes;
    this.reason = __toClone.reason;
    this.mapOfUsuariForSolicitantID = __toClone.mapOfUsuariForSolicitantID;
    this.mapOfIdiomaForIdiomaID = __toClone.mapOfIdiomaForIdiomaID;
    this.mapOfValuesForEstat = __toClone.mapOfValuesForEstat;
    this.mapOfValuesForTipusDocumental = __toClone.mapOfValuesForTipusDocumental;
    this.mapOfValuesForIdiomaDoc = __toClone.mapOfValuesForIdiomaDoc;
    this.mapOfInfoSignaturaForInfoSignaturaID = __toClone.mapOfInfoSignaturaForInfoSignaturaID;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { ERRORMSG ,ERROREXCEPTION }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TIPUS }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(ESTAT )};


  public OrderBy[] getDefaultOrderBy() {
    return this.defaultOrderBy;
  }

  public void setDefaultOrderBy(OrderBy[] defOrderBy) {
    this.defaultOrderBy = defOrderBy;
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

   // -----------------------
   // Maps de referencies.
   // -----------------------
  private Map<String, String> mapOfUsuariForSolicitantID;

  public Map<String, String> getMapOfUsuariForSolicitantID() {
    return this.mapOfUsuariForSolicitantID;
  }

  public void setMapOfUsuariForSolicitantID(Map<String, String> mapOfUsuariForSolicitantID) {
    this.mapOfUsuariForSolicitantID = mapOfUsuariForSolicitantID;
  }



  private Map<String, String> mapOfIdiomaForIdiomaID;

  public Map<String, String> getMapOfIdiomaForIdiomaID() {
    return this.mapOfIdiomaForIdiomaID;
  }

  public void setMapOfIdiomaForIdiomaID(Map<String, String> mapOfIdiomaForIdiomaID) {
    this.mapOfIdiomaForIdiomaID = mapOfIdiomaForIdiomaID;
  }



  private Map<String, String> mapOfValuesForEstat;

  public Map<String, String> getMapOfValuesForEstat() {
    return this.mapOfValuesForEstat;
  }

  public void setMapOfValuesForEstat(Map<String, String> mapOfValuesForEstat) {
    this.mapOfValuesForEstat = mapOfValuesForEstat;
  }



  private Map<String, String> mapOfValuesForTipusDocumental;

  public Map<String, String> getMapOfValuesForTipusDocumental() {
    return this.mapOfValuesForTipusDocumental;
  }

  public void setMapOfValuesForTipusDocumental(Map<String, String> mapOfValuesForTipusDocumental) {
    this.mapOfValuesForTipusDocumental = mapOfValuesForTipusDocumental;
  }



  private Map<String, String> mapOfValuesForIdiomaDoc;

  public Map<String, String> getMapOfValuesForIdiomaDoc() {
    return this.mapOfValuesForIdiomaDoc;
  }

  public void setMapOfValuesForIdiomaDoc(Map<String, String> mapOfValuesForIdiomaDoc) {
    this.mapOfValuesForIdiomaDoc = mapOfValuesForIdiomaDoc;
  }



  private Map<String, String> mapOfInfoSignaturaForInfoSignaturaID;

  public Map<String, String> getMapOfInfoSignaturaForInfoSignaturaID() {
    return this.mapOfInfoSignaturaForInfoSignaturaID;
  }

  public void setMapOfInfoSignaturaForInfoSignaturaID(Map<String, String> mapOfInfoSignaturaForInfoSignaturaID) {
    this.mapOfInfoSignaturaForInfoSignaturaID = mapOfInfoSignaturaForInfoSignaturaID;
  }



  private Map<String, String> mapOfValuesForTipus;

  public Map<String, String> getMapOfValuesForTipus() {
    return this.mapOfValuesForTipus;
  }

  public void setMapOfValuesForTipus(Map<String, String> mapOfValuesForTipus) {
    this.mapOfValuesForTipus = mapOfValuesForTipus;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
