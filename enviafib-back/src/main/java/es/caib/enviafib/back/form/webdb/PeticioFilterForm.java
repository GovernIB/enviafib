
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

  private java.lang.Long titolIDDesde;

  public java.lang.Long getTitolIDDesde() {
    return this.titolIDDesde;
  }

  public void setTitolIDDesde(java.lang.Long titolIDDesde) {
    this.titolIDDesde = titolIDDesde;
  }


  private java.lang.Long titolIDFins;

  public java.lang.Long getTitolIDFins() {
    return this.titolIDFins;
  }

  public void setTitolIDFins(java.lang.Long titolIDFins) {
    this.titolIDFins = titolIDFins;
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


  private java.sql.Timestamp datacreacioDesde;

  public java.sql.Timestamp getDatacreacioDesde() {
    return this.datacreacioDesde;
  }

  public void setDatacreacioDesde(java.sql.Timestamp datacreacioDesde) {
    this.datacreacioDesde = datacreacioDesde;
  }


  private java.sql.Timestamp datacreacioFins;

  public java.sql.Timestamp getDatacreacioFins() {
    return this.datacreacioFins;
  }

  public void setDatacreacioFins(java.sql.Timestamp datacreacioFins) {
    this.datacreacioFins = datacreacioFins;
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


  private java.lang.String destinatarinif;

  public java.lang.String getDestinatarinif() {
    return this.destinatarinif;
  }

  public void setDestinatarinif(java.lang.String destinatarinif) {
    this.destinatarinif = destinatarinif;
  }


  private java.lang.Long estatDesde;

  public java.lang.Long getEstatDesde() {
    return this.estatDesde;
  }

  public void setEstatDesde(java.lang.Long estatDesde) {
    this.estatDesde = estatDesde;
  }


  private java.lang.Long estatFins;

  public java.lang.Long getEstatFins() {
    return this.estatFins;
  }

  public void setEstatFins(java.lang.Long estatFins) {
    this.estatFins = estatFins;
  }


  private java.lang.Long peticioPortafibDesde;

  public java.lang.Long getPeticioPortafibDesde() {
    return this.peticioPortafibDesde;
  }

  public void setPeticioPortafibDesde(java.lang.Long peticioPortafibDesde) {
    this.peticioPortafibDesde = peticioPortafibDesde;
  }


  private java.lang.Long peticioPortafibFins;

  public java.lang.Long getPeticioPortafibFins() {
    return this.peticioPortafibFins;
  }

  public void setPeticioPortafibFins(java.lang.Long peticioPortafibFins) {
    this.peticioPortafibFins = peticioPortafibFins;
  }


  private java.lang.String tipusdocumental;

  public java.lang.String getTipusdocumental() {
    return this.tipusdocumental;
  }

  public void setTipusdocumental(java.lang.String tipusdocumental) {
    this.tipusdocumental = tipusdocumental;
  }


  private java.lang.String idiomadoc;

  public java.lang.String getIdiomadoc() {
    return this.idiomadoc;
  }

  public void setIdiomadoc(java.lang.String idiomadoc) {
    this.idiomadoc = idiomadoc;
  }


  public PeticioFilterForm() {
  }
  
  public PeticioFilterForm(PeticioFilterForm __toClone) {
    super(__toClone);
    this.titolIDDesde = __toClone.titolIDDesde;
    this.titolIDFins = __toClone.titolIDFins;
    this.peticioIDDesde = __toClone.peticioIDDesde;
    this.peticioIDFins = __toClone.peticioIDFins;
    this.datacreacioDesde = __toClone.datacreacioDesde;
    this.datacreacioFins = __toClone.datacreacioFins;
    this.solicitantIDDesde = __toClone.solicitantIDDesde;
    this.solicitantIDFins = __toClone.solicitantIDFins;
    this.idiomaID = __toClone.idiomaID;
    this.destinatarinif = __toClone.destinatarinif;
    this.estatDesde = __toClone.estatDesde;
    this.estatFins = __toClone.estatFins;
    this.peticioPortafibDesde = __toClone.peticioPortafibDesde;
    this.peticioPortafibFins = __toClone.peticioPortafibFins;
    this.tipusdocumental = __toClone.tipusdocumental;
    this.idiomadoc = __toClone.idiomadoc;
    this.mapOfTraduccioForTitolID = __toClone.mapOfTraduccioForTitolID;
    this.mapOfUsuariForSolicitantID = __toClone.mapOfUsuariForSolicitantID;
    this.mapOfIdiomaForIdiomaID = __toClone.mapOfIdiomaForIdiomaID;
    this.mapOfValuesForEstat = __toClone.mapOfValuesForEstat;
    this.mapOfValuesForTipusdocumental = __toClone.mapOfValuesForTipusdocumental;
    this.mapOfValuesForIdiomadoc = __toClone.mapOfValuesForIdiomadoc;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
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
  private Map<String, String> mapOfTraduccioForTitolID;

  public Map<String, String> getMapOfTraduccioForTitolID() {
    return this.mapOfTraduccioForTitolID;
  }

  public void setMapOfTraduccioForTitolID(Map<String, String> mapOfTraduccioForTitolID) {
    this.mapOfTraduccioForTitolID = mapOfTraduccioForTitolID;
  }



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



  private Map<String, String> mapOfValuesForTipusdocumental;

  public Map<String, String> getMapOfValuesForTipusdocumental() {
    return this.mapOfValuesForTipusdocumental;
  }

  public void setMapOfValuesForTipusdocumental(Map<String, String> mapOfValuesForTipusdocumental) {
    this.mapOfValuesForTipusdocumental = mapOfValuesForTipusdocumental;
  }



  private Map<String, String> mapOfValuesForIdiomadoc;

  public Map<String, String> getMapOfValuesForIdiomadoc() {
    return this.mapOfValuesForIdiomadoc;
  }

  public void setMapOfValuesForIdiomadoc(Map<String, String> mapOfValuesForIdiomadoc) {
    this.mapOfValuesForIdiomadoc = mapOfValuesForIdiomadoc;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
