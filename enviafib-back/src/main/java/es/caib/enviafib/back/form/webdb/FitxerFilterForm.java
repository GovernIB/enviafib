
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.FitxerFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class FitxerFilterForm extends EnviaFIBBaseFilterForm implements FitxerFields {

  private java.lang.Long fitxerIDDesde;

  public java.lang.Long getFitxerIDDesde() {
    return this.fitxerIDDesde;
  }

  public void setFitxerIDDesde(java.lang.Long fitxerIDDesde) {
    this.fitxerIDDesde = fitxerIDDesde;
  }


  private java.lang.Long fitxerIDFins;

  public java.lang.Long getFitxerIDFins() {
    return this.fitxerIDFins;
  }

  public void setFitxerIDFins(java.lang.Long fitxerIDFins) {
    this.fitxerIDFins = fitxerIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String mime;

  public java.lang.String getMime() {
    return this.mime;
  }

  public void setMime(java.lang.String mime) {
    this.mime = mime;
  }


  private java.lang.Long tamanyDesde;

  public java.lang.Long getTamanyDesde() {
    return this.tamanyDesde;
  }

  public void setTamanyDesde(java.lang.Long tamanyDesde) {
    this.tamanyDesde = tamanyDesde;
  }


  private java.lang.Long tamanyFins;

  public java.lang.Long getTamanyFins() {
    return this.tamanyFins;
  }

  public void setTamanyFins(java.lang.Long tamanyFins) {
    this.tamanyFins = tamanyFins;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  public FitxerFilterForm() {
  }
  
  public FitxerFilterForm(FitxerFilterForm __toClone) {
    super(__toClone);
    this.fitxerIDDesde = __toClone.fitxerIDDesde;
    this.fitxerIDFins = __toClone.fitxerIDFins;
    this.nom = __toClone.nom;
    this.mime = __toClone.mime;
    this.tamanyDesde = __toClone.tamanyDesde;
    this.tamanyFins = __toClone.tamanyFins;
    this.descripcio = __toClone.descripcio;
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

   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
