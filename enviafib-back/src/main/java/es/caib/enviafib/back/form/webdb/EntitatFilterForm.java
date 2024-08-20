
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.EntitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EntitatFilterForm extends EnviaFIBBaseFilterForm implements EntitatFields {

  private java.lang.String entitatid;

  public java.lang.String getEntitatid() {
    return this.entitatid;
  }

  public void setEntitatid(java.lang.String entitatid) {
    this.entitatid = entitatid;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.lang.String adrezahtml;

  public java.lang.String getAdrezahtml() {
    return this.adrezahtml;
  }

  public void setAdrezahtml(java.lang.String adrezahtml) {
    this.adrezahtml = adrezahtml;
  }


  private java.lang.String suporttelefon;

  public java.lang.String getSuporttelefon() {
    return this.suporttelefon;
  }

  public void setSuporttelefon(java.lang.String suporttelefon) {
    this.suporttelefon = suporttelefon;
  }


  private java.lang.String suportweb;

  public java.lang.String getSuportweb() {
    return this.suportweb;
  }

  public void setSuportweb(java.lang.String suportweb) {
    this.suportweb = suportweb;
  }


  private java.lang.String suportemail;

  public java.lang.String getSuportemail() {
    return this.suportemail;
  }

  public void setSuportemail(java.lang.String suportemail) {
    this.suportemail = suportemail;
  }


  private java.lang.String web;

  public java.lang.String getWeb() {
    return this.web;
  }

  public void setWeb(java.lang.String web) {
    this.web = web;
  }


  private java.lang.Long motiudelegacioIDDesde;

  public java.lang.Long getMotiudelegacioIDDesde() {
    return this.motiudelegacioIDDesde;
  }

  public void setMotiudelegacioIDDesde(java.lang.Long motiudelegacioIDDesde) {
    this.motiudelegacioIDDesde = motiudelegacioIDDesde;
  }


  private java.lang.Long motiudelegacioIDFins;

  public java.lang.Long getMotiudelegacioIDFins() {
    return this.motiudelegacioIDFins;
  }

  public void setMotiudelegacioIDFins(java.lang.Long motiudelegacioIDFins) {
    this.motiudelegacioIDFins = motiudelegacioIDFins;
  }


  private java.lang.Integer segelldetempsviawebDesde;

  public java.lang.Integer getSegelldetempsviawebDesde() {
    return this.segelldetempsviawebDesde;
  }

  public void setSegelldetempsviawebDesde(java.lang.Integer segelldetempsviawebDesde) {
    this.segelldetempsviawebDesde = segelldetempsviawebDesde;
  }


  private java.lang.Integer segelldetempsviawebFins;

  public java.lang.Integer getSegelldetempsviawebFins() {
    return this.segelldetempsviawebFins;
  }

  public void setSegelldetempsviawebFins(java.lang.Integer segelldetempsviawebFins) {
    this.segelldetempsviawebFins = segelldetempsviawebFins;
  }


  private java.lang.String propietatstaulafirmes;

  public java.lang.String getPropietatstaulafirmes() {
    return this.propietatstaulafirmes;
  }

  public void setPropietatstaulafirmes(java.lang.String propietatstaulafirmes) {
    this.propietatstaulafirmes = propietatstaulafirmes;
  }


  public EntitatFilterForm() {
  }
  
  public EntitatFilterForm(EntitatFilterForm __toClone) {
    super(__toClone);
    this.entitatid = __toClone.entitatid;
    this.nom = __toClone.nom;
    this.descripcio = __toClone.descripcio;
    this.adrezahtml = __toClone.adrezahtml;
    this.suporttelefon = __toClone.suporttelefon;
    this.suportweb = __toClone.suportweb;
    this.suportemail = __toClone.suportemail;
    this.web = __toClone.web;
    this.motiudelegacioIDDesde = __toClone.motiudelegacioIDDesde;
    this.motiudelegacioIDFins = __toClone.motiudelegacioIDFins;
    this.segelldetempsviawebDesde = __toClone.segelldetempsviawebDesde;
    this.segelldetempsviawebFins = __toClone.segelldetempsviawebFins;
    this.propietatstaulafirmes = __toClone.propietatstaulafirmes;
    this.mapOfTraduccioForMotiudelegacioID = __toClone.mapOfTraduccioForMotiudelegacioID;
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
  private Map<String, String> mapOfTraduccioForMotiudelegacioID;

  public Map<String, String> getMapOfTraduccioForMotiudelegacioID() {
    return this.mapOfTraduccioForMotiudelegacioID;
  }

  public void setMapOfTraduccioForMotiudelegacioID(Map<String, String> mapOfTraduccioForMotiudelegacioID) {
    this.mapOfTraduccioForMotiudelegacioID = mapOfTraduccioForMotiudelegacioID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
