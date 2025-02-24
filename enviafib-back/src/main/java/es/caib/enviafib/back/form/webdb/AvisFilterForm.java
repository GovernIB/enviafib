
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.AvisFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AvisFilterForm extends EnviaFIBBaseFilterForm implements AvisFields {

  private java.lang.Long avisIDDesde;

  public java.lang.Long getAvisIDDesde() {
    return this.avisIDDesde;
  }

  public void setAvisIDDesde(java.lang.Long avisIDDesde) {
    this.avisIDDesde = avisIDDesde;
  }


  private java.lang.Long avisIDFins;

  public java.lang.Long getAvisIDFins() {
    return this.avisIDFins;
  }

  public void setAvisIDFins(java.lang.Long avisIDFins) {
    this.avisIDFins = avisIDFins;
  }


  private java.lang.String missatge;

  public java.lang.String getMissatge() {
    return this.missatge;
  }

  public void setMissatge(java.lang.String missatge) {
    this.missatge = missatge;
  }


  private java.sql.Timestamp datainiciDesde;

  public java.sql.Timestamp getDatainiciDesde() {
    return this.datainiciDesde;
  }

  public void setDatainiciDesde(java.sql.Timestamp datainiciDesde) {
    this.datainiciDesde = datainiciDesde;
  }


  private java.sql.Timestamp datainiciFins;

  public java.sql.Timestamp getDatainiciFins() {
    return this.datainiciFins;
  }

  public void setDatainiciFins(java.sql.Timestamp datainiciFins) {
    this.datainiciFins = datainiciFins;
  }


  private java.sql.Timestamp datafiDesde;

  public java.sql.Timestamp getDatafiDesde() {
    return this.datafiDesde;
  }

  public void setDatafiDesde(java.sql.Timestamp datafiDesde) {
    this.datafiDesde = datafiDesde;
  }


  private java.sql.Timestamp datafiFins;

  public java.sql.Timestamp getDatafiFins() {
    return this.datafiFins;
  }

  public void setDatafiFins(java.sql.Timestamp datafiFins) {
    this.datafiFins = datafiFins;
  }


  private java.lang.String tipus;

  public java.lang.String getTipus() {
    return this.tipus;
  }

  public void setTipus(java.lang.String tipus) {
    this.tipus = tipus;
  }


  public AvisFilterForm() {
  }
  
  public AvisFilterForm(AvisFilterForm __toClone) {
    super(__toClone);
    this.avisIDDesde = __toClone.avisIDDesde;
    this.avisIDFins = __toClone.avisIDFins;
    this.missatge = __toClone.missatge;
    this.datainiciDesde = __toClone.datainiciDesde;
    this.datainiciFins = __toClone.datainiciFins;
    this.datafiDesde = __toClone.datafiDesde;
    this.datafiFins = __toClone.datafiFins;
    this.tipus = __toClone.tipus;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
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


  protected OrderBy[] defaultOrderBy = null;


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
