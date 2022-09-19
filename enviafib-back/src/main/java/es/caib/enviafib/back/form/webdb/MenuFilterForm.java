
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.MenuFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class MenuFilterForm extends EnviaFIBBaseFilterForm implements MenuFields {

  private java.lang.Long menuIDDesde;

  public java.lang.Long getMenuIDDesde() {
    return this.menuIDDesde;
  }

  public void setMenuIDDesde(java.lang.Long menuIDDesde) {
    this.menuIDDesde = menuIDDesde;
  }


  private java.lang.Long menuIDFins;

  public java.lang.Long getMenuIDFins() {
    return this.menuIDFins;
  }

  public void setMenuIDFins(java.lang.Long menuIDFins) {
    this.menuIDFins = menuIDFins;
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


  private java.lang.Long titolMenuIDDesde;

  public java.lang.Long getTitolMenuIDDesde() {
    return this.titolMenuIDDesde;
  }

  public void setTitolMenuIDDesde(java.lang.Long titolMenuIDDesde) {
    this.titolMenuIDDesde = titolMenuIDDesde;
  }


  private java.lang.Long titolMenuIDFins;

  public java.lang.Long getTitolMenuIDFins() {
    return this.titolMenuIDFins;
  }

  public void setTitolMenuIDFins(java.lang.Long titolMenuIDFins) {
    this.titolMenuIDFins = titolMenuIDFins;
  }


  private java.lang.Long ajudaMenuIDDesde;

  public java.lang.Long getAjudaMenuIDDesde() {
    return this.ajudaMenuIDDesde;
  }

  public void setAjudaMenuIDDesde(java.lang.Long ajudaMenuIDDesde) {
    this.ajudaMenuIDDesde = ajudaMenuIDDesde;
  }


  private java.lang.Long ajudaMenuIDFins;

  public java.lang.Long getAjudaMenuIDFins() {
    return this.ajudaMenuIDFins;
  }

  public void setAjudaMenuIDFins(java.lang.Long ajudaMenuIDFins) {
    this.ajudaMenuIDFins = ajudaMenuIDFins;
  }


  private java.lang.Integer ordreDesde;

  public java.lang.Integer getOrdreDesde() {
    return this.ordreDesde;
  }

  public void setOrdreDesde(java.lang.Integer ordreDesde) {
    this.ordreDesde = ordreDesde;
  }


  private java.lang.Integer ordreFins;

  public java.lang.Integer getOrdreFins() {
    return this.ordreFins;
  }

  public void setOrdreFins(java.lang.Integer ordreFins) {
    this.ordreFins = ordreFins;
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


  private java.lang.Long grupIDDesde;

  public java.lang.Long getGrupIDDesde() {
    return this.grupIDDesde;
  }

  public void setGrupIDDesde(java.lang.Long grupIDDesde) {
    this.grupIDDesde = grupIDDesde;
  }


  private java.lang.Long grupIDFins;

  public java.lang.Long getGrupIDFins() {
    return this.grupIDFins;
  }

  public void setGrupIDFins(java.lang.Long grupIDFins) {
    this.grupIDFins = grupIDFins;
  }


  private java.lang.String parametreCombo;

  public java.lang.String getParametreCombo() {
    return this.parametreCombo;
  }

  public void setParametreCombo(java.lang.String parametreCombo) {
    this.parametreCombo = parametreCombo;
  }


  private java.lang.String parametreText;

  public java.lang.String getParametreText() {
    return this.parametreText;
  }

  public void setParametreText(java.lang.String parametreText) {
    this.parametreText = parametreText;
  }


  public MenuFilterForm() {
  }
  
  public MenuFilterForm(MenuFilterForm __toClone) {
    super(__toClone);
    this.menuIDDesde = __toClone.menuIDDesde;
    this.menuIDFins = __toClone.menuIDFins;
    this.nom = __toClone.nom;
    this.descripcio = __toClone.descripcio;
    this.titolMenuIDDesde = __toClone.titolMenuIDDesde;
    this.titolMenuIDFins = __toClone.titolMenuIDFins;
    this.ajudaMenuIDDesde = __toClone.ajudaMenuIDDesde;
    this.ajudaMenuIDFins = __toClone.ajudaMenuIDFins;
    this.ordreDesde = __toClone.ordreDesde;
    this.ordreFins = __toClone.ordreFins;
    this.tipusDesde = __toClone.tipusDesde;
    this.tipusFins = __toClone.tipusFins;
    this.grupIDDesde = __toClone.grupIDDesde;
    this.grupIDFins = __toClone.grupIDFins;
    this.parametreCombo = __toClone.parametreCombo;
    this.parametreText = __toClone.parametreText;
    this.mapOfTraduccioForTitolMenuID = __toClone.mapOfTraduccioForTitolMenuID;
    this.mapOfTraduccioForAjudaMenuID = __toClone.mapOfTraduccioForAjudaMenuID;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
    this.mapOfGrupForGrupID = __toClone.mapOfGrupForGrupID;
    this.mapOfValuesForParametreCombo = __toClone.mapOfValuesForParametreCombo;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM ,DESCRIPCIO }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { GRUPID }));
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
  private Map<String, String> mapOfTraduccioForTitolMenuID;

  public Map<String, String> getMapOfTraduccioForTitolMenuID() {
    return this.mapOfTraduccioForTitolMenuID;
  }

  public void setMapOfTraduccioForTitolMenuID(Map<String, String> mapOfTraduccioForTitolMenuID) {
    this.mapOfTraduccioForTitolMenuID = mapOfTraduccioForTitolMenuID;
  }



  private Map<String, String> mapOfTraduccioForAjudaMenuID;

  public Map<String, String> getMapOfTraduccioForAjudaMenuID() {
    return this.mapOfTraduccioForAjudaMenuID;
  }

  public void setMapOfTraduccioForAjudaMenuID(Map<String, String> mapOfTraduccioForAjudaMenuID) {
    this.mapOfTraduccioForAjudaMenuID = mapOfTraduccioForAjudaMenuID;
  }



  private Map<String, String> mapOfValuesForTipus;

  public Map<String, String> getMapOfValuesForTipus() {
    return this.mapOfValuesForTipus;
  }

  public void setMapOfValuesForTipus(Map<String, String> mapOfValuesForTipus) {
    this.mapOfValuesForTipus = mapOfValuesForTipus;
  }



  private Map<String, String> mapOfGrupForGrupID;

  public Map<String, String> getMapOfGrupForGrupID() {
    return this.mapOfGrupForGrupID;
  }

  public void setMapOfGrupForGrupID(Map<String, String> mapOfGrupForGrupID) {
    this.mapOfGrupForGrupID = mapOfGrupForGrupID;
  }



  private Map<String, String> mapOfValuesForParametreCombo;

  public Map<String, String> getMapOfValuesForParametreCombo() {
    return this.mapOfValuesForParametreCombo;
  }

  public void setMapOfValuesForParametreCombo(Map<String, String> mapOfValuesForParametreCombo) {
    this.mapOfValuesForParametreCombo = mapOfValuesForParametreCombo;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
