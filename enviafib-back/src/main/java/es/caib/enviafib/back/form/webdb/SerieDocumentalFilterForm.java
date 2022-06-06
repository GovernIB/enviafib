
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.SerieDocumentalFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class SerieDocumentalFilterForm extends EnviaFIBBaseFilterForm implements SerieDocumentalFields {

  private java.lang.Long seriedocuidDesde;

  public java.lang.Long getSeriedocuidDesde() {
    return this.seriedocuidDesde;
  }

  public void setSeriedocuidDesde(java.lang.Long seriedocuidDesde) {
    this.seriedocuidDesde = seriedocuidDesde;
  }


  private java.lang.Long seriedocuidFins;

  public java.lang.Long getSeriedocuidFins() {
    return this.seriedocuidFins;
  }

  public void setSeriedocuidFins(java.lang.Long seriedocuidFins) {
    this.seriedocuidFins = seriedocuidFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String tipusdocu;

  public java.lang.String getTipusdocu() {
    return this.tipusdocu;
  }

  public void setTipusdocu(java.lang.String tipusdocu) {
    this.tipusdocu = tipusdocu;
  }


  public SerieDocumentalFilterForm() {
  }
  
  public SerieDocumentalFilterForm(SerieDocumentalFilterForm __toClone) {
    super(__toClone);
    this.seriedocuidDesde = __toClone.seriedocuidDesde;
    this.seriedocuidFins = __toClone.seriedocuidFins;
    this.nom = __toClone.nom;
    this.tipusdocu = __toClone.tipusdocu;
    this.mapOfValuesForTipusdocu = __toClone.mapOfValuesForTipusdocu;
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
  private Map<String, String> mapOfValuesForTipusdocu;

  public Map<String, String> getMapOfValuesForTipusdocu() {
    return this.mapOfValuesForTipusdocu;
  }

  public void setMapOfValuesForTipusdocu(Map<String, String> mapOfValuesForTipusdocu) {
    this.mapOfValuesForTipusdocu = mapOfValuesForTipusdocu;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
