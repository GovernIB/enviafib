
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

  private java.lang.Long serieDocumentalIDDesde;

  public java.lang.Long getSerieDocumentalIDDesde() {
    return this.serieDocumentalIDDesde;
  }

  public void setSerieDocumentalIDDesde(java.lang.Long serieDocumentalIDDesde) {
    this.serieDocumentalIDDesde = serieDocumentalIDDesde;
  }


  private java.lang.Long serieDocumentalIDFins;

  public java.lang.Long getSerieDocumentalIDFins() {
    return this.serieDocumentalIDFins;
  }

  public void setSerieDocumentalIDFins(java.lang.Long serieDocumentalIDFins) {
    this.serieDocumentalIDFins = serieDocumentalIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String tipusDocumental;

  public java.lang.String getTipusDocumental() {
    return this.tipusDocumental;
  }

  public void setTipusDocumental(java.lang.String tipusDocumental) {
    this.tipusDocumental = tipusDocumental;
  }


  public SerieDocumentalFilterForm() {
  }
  
  public SerieDocumentalFilterForm(SerieDocumentalFilterForm __toClone) {
    super(__toClone);
    this.serieDocumentalIDDesde = __toClone.serieDocumentalIDDesde;
    this.serieDocumentalIDFins = __toClone.serieDocumentalIDFins;
    this.nom = __toClone.nom;
    this.tipusDocumental = __toClone.tipusDocumental;
    this.mapOfValuesForTipusDocumental = __toClone.mapOfValuesForTipusDocumental;
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
  private Map<String, String> mapOfValuesForTipusDocumental;

  public Map<String, String> getMapOfValuesForTipusDocumental() {
    return this.mapOfValuesForTipusDocumental;
  }

  public void setMapOfValuesForTipusDocumental(Map<String, String> mapOfValuesForTipusDocumental) {
    this.mapOfValuesForTipusDocumental = mapOfValuesForTipusDocumental;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
