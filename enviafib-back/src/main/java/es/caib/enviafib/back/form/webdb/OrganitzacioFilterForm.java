
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.OrganitzacioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class OrganitzacioFilterForm extends EnviaFIBBaseFilterForm implements OrganitzacioFields {

  private java.lang.Long organitzacioIDDesde;

  public java.lang.Long getOrganitzacioIDDesde() {
    return this.organitzacioIDDesde;
  }

  public void setOrganitzacioIDDesde(java.lang.Long organitzacioIDDesde) {
    this.organitzacioIDDesde = organitzacioIDDesde;
  }


  private java.lang.Long organitzacioIDFins;

  public java.lang.Long getOrganitzacioIDFins() {
    return this.organitzacioIDFins;
  }

  public void setOrganitzacioIDFins(java.lang.Long organitzacioIDFins) {
    this.organitzacioIDFins = organitzacioIDFins;
  }


  private java.lang.String codiConselleria;

  public java.lang.String getCodiConselleria() {
    return this.codiConselleria;
  }

  public void setCodiConselleria(java.lang.String codiConselleria) {
    this.codiConselleria = codiConselleria;
  }


  private java.lang.String codiDireccioGeneral;

  public java.lang.String getCodiDireccioGeneral() {
    return this.codiDireccioGeneral;
  }

  public void setCodiDireccioGeneral(java.lang.String codiDireccioGeneral) {
    this.codiDireccioGeneral = codiDireccioGeneral;
  }


  private java.lang.String tipus;

  public java.lang.String getTipus() {
    return this.tipus;
  }

  public void setTipus(java.lang.String tipus) {
    this.tipus = tipus;
  }


  private java.lang.String valor;

  public java.lang.String getValor() {
    return this.valor;
  }

  public void setValor(java.lang.String valor) {
    this.valor = valor;
  }


  public OrganitzacioFilterForm() {
  }
  
  public OrganitzacioFilterForm(OrganitzacioFilterForm __toClone) {
    super(__toClone);
    this.organitzacioIDDesde = __toClone.organitzacioIDDesde;
    this.organitzacioIDFins = __toClone.organitzacioIDFins;
    this.codiConselleria = __toClone.codiConselleria;
    this.codiDireccioGeneral = __toClone.codiDireccioGeneral;
    this.tipus = __toClone.tipus;
    this.valor = __toClone.valor;
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
