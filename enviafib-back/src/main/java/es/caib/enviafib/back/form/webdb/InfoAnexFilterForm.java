
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.InfoAnexFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class InfoAnexFilterForm extends EnviaFIBBaseFilterForm implements InfoAnexFields {

  private java.lang.Long infoanexidDesde;

  public java.lang.Long getInfoanexidDesde() {
    return this.infoanexidDesde;
  }

  public void setInfoanexidDesde(java.lang.Long infoanexidDesde) {
    this.infoanexidDesde = infoanexidDesde;
  }


  private java.lang.Long infoanexidFins;

  public java.lang.Long getInfoanexidFins() {
    return this.infoanexidFins;
  }

  public void setInfoanexidFins(java.lang.Long infoanexidFins) {
    this.infoanexidFins = infoanexidFins;
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


  public InfoAnexFilterForm() {
  }
  
  public InfoAnexFilterForm(InfoAnexFilterForm __toClone) {
    super(__toClone);
    this.infoanexidDesde = __toClone.infoanexidDesde;
    this.infoanexidFins = __toClone.infoanexidFins;
    this.peticioIDDesde = __toClone.peticioIDDesde;
    this.peticioIDFins = __toClone.peticioIDFins;
    this.mapOfPeticioForPeticioID = __toClone.mapOfPeticioForPeticioID;
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
  private Map<String, String> mapOfPeticioForPeticioID;

  public Map<String, String> getMapOfPeticioForPeticioID() {
    return this.mapOfPeticioForPeticioID;
  }

  public void setMapOfPeticioForPeticioID(Map<String, String> mapOfPeticioForPeticioID) {
    this.mapOfPeticioForPeticioID = mapOfPeticioForPeticioID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
