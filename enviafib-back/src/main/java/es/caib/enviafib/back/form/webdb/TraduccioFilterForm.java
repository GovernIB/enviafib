
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.TraduccioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TraduccioFilterForm extends EnviaFIBBaseFilterForm implements TraduccioFields {

  private java.lang.Long traduccioIDDesde;

  public java.lang.Long getTraduccioIDDesde() {
    return this.traduccioIDDesde;
  }

  public void setTraduccioIDDesde(java.lang.Long traduccioIDDesde) {
    this.traduccioIDDesde = traduccioIDDesde;
  }


  private java.lang.Long traduccioIDFins;

  public java.lang.Long getTraduccioIDFins() {
    return this.traduccioIDFins;
  }

  public void setTraduccioIDFins(java.lang.Long traduccioIDFins) {
    this.traduccioIDFins = traduccioIDFins;
  }


  public TraduccioFilterForm() {
  }
  
  public TraduccioFilterForm(TraduccioFilterForm __toClone) {
    super(__toClone);
    this.traduccioIDDesde = __toClone.traduccioIDDesde;
    this.traduccioIDFins = __toClone.traduccioIDFins;
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
