
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.PluginFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PluginFilterForm extends EnviaFIBBaseFilterForm implements PluginFields {

  private java.lang.Long pluginIDDesde;

  public java.lang.Long getPluginIDDesde() {
    return this.pluginIDDesde;
  }

  public void setPluginIDDesde(java.lang.Long pluginIDDesde) {
    this.pluginIDDesde = pluginIDDesde;
  }


  private java.lang.Long pluginIDFins;

  public java.lang.Long getPluginIDFins() {
    return this.pluginIDFins;
  }

  public void setPluginIDFins(java.lang.Long pluginIDFins) {
    this.pluginIDFins = pluginIDFins;
  }


  private java.lang.Long nomidDesde;

  public java.lang.Long getNomidDesde() {
    return this.nomidDesde;
  }

  public void setNomidDesde(java.lang.Long nomidDesde) {
    this.nomidDesde = nomidDesde;
  }


  private java.lang.Long nomidFins;

  public java.lang.Long getNomidFins() {
    return this.nomidFins;
  }

  public void setNomidFins(java.lang.Long nomidFins) {
    this.nomidFins = nomidFins;
  }


  private java.lang.Long descripciocurtaidDesde;

  public java.lang.Long getDescripciocurtaidDesde() {
    return this.descripciocurtaidDesde;
  }

  public void setDescripciocurtaidDesde(java.lang.Long descripciocurtaidDesde) {
    this.descripciocurtaidDesde = descripciocurtaidDesde;
  }


  private java.lang.Long descripciocurtaidFins;

  public java.lang.Long getDescripciocurtaidFins() {
    return this.descripciocurtaidFins;
  }

  public void setDescripciocurtaidFins(java.lang.Long descripciocurtaidFins) {
    this.descripciocurtaidFins = descripciocurtaidFins;
  }


  private java.lang.String classe;

  public java.lang.String getClasse() {
    return this.classe;
  }

  public void setClasse(java.lang.String classe) {
    this.classe = classe;
  }


  private java.lang.String properties;

  public java.lang.String getProperties() {
    return this.properties;
  }

  public void setProperties(java.lang.String properties) {
    this.properties = properties;
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


  public PluginFilterForm() {
  }
  
  public PluginFilterForm(PluginFilterForm __toClone) {
    super(__toClone);
    this.pluginIDDesde = __toClone.pluginIDDesde;
    this.pluginIDFins = __toClone.pluginIDFins;
    this.nomidDesde = __toClone.nomidDesde;
    this.nomidFins = __toClone.nomidFins;
    this.descripciocurtaidDesde = __toClone.descripciocurtaidDesde;
    this.descripciocurtaidFins = __toClone.descripciocurtaidFins;
    this.classe = __toClone.classe;
    this.properties = __toClone.properties;
    this.tipusDesde = __toClone.tipusDesde;
    this.tipusFins = __toClone.tipusFins;
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
