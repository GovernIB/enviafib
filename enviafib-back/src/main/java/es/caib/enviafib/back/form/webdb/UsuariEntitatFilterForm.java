
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.UsuariEntitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UsuariEntitatFilterForm extends EnviaFIBBaseFilterForm implements UsuariEntitatFields {

  private java.lang.Long usuarientitatidDesde;

  public java.lang.Long getUsuarientitatidDesde() {
    return this.usuarientitatidDesde;
  }

  public void setUsuarientitatidDesde(java.lang.Long usuarientitatidDesde) {
    this.usuarientitatidDesde = usuarientitatidDesde;
  }


  private java.lang.Long usuarientitatidFins;

  public java.lang.Long getUsuarientitatidFins() {
    return this.usuarientitatidFins;
  }

  public void setUsuarientitatidFins(java.lang.Long usuarientitatidFins) {
    this.usuarientitatidFins = usuarientitatidFins;
  }


  private java.lang.Long usuariidDesde;

  public java.lang.Long getUsuariidDesde() {
    return this.usuariidDesde;
  }

  public void setUsuariidDesde(java.lang.Long usuariidDesde) {
    this.usuariidDesde = usuariidDesde;
  }


  private java.lang.Long usuariidFins;

  public java.lang.Long getUsuariidFins() {
    return this.usuariidFins;
  }

  public void setUsuariidFins(java.lang.Long usuariidFins) {
    this.usuariidFins = usuariidFins;
  }


  private java.lang.String entitatid;

  public java.lang.String getEntitatid() {
    return this.entitatid;
  }

  public void setEntitatid(java.lang.String entitatid) {
    this.entitatid = entitatid;
  }


  public UsuariEntitatFilterForm() {
  }
  
  public UsuariEntitatFilterForm(UsuariEntitatFilterForm __toClone) {
    super(__toClone);
    this.usuarientitatidDesde = __toClone.usuarientitatidDesde;
    this.usuarientitatidFins = __toClone.usuarientitatidFins;
    this.usuariidDesde = __toClone.usuariidDesde;
    this.usuariidFins = __toClone.usuariidFins;
    this.entitatid = __toClone.entitatid;
    this.mapOfUsuariForUsuariid = __toClone.mapOfUsuariForUsuariid;
    this.mapOfEntitatForEntitatid = __toClone.mapOfEntitatForEntitatid;
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
  private Map<String, String> mapOfUsuariForUsuariid;

  public Map<String, String> getMapOfUsuariForUsuariid() {
    return this.mapOfUsuariForUsuariid;
  }

  public void setMapOfUsuariForUsuariid(Map<String, String> mapOfUsuariForUsuariid) {
    this.mapOfUsuariForUsuariid = mapOfUsuariForUsuariid;
  }



  private Map<String, String> mapOfEntitatForEntitatid;

  public Map<String, String> getMapOfEntitatForEntitatid() {
    return this.mapOfEntitatForEntitatid;
  }

  public void setMapOfEntitatForEntitatid(Map<String, String> mapOfEntitatForEntitatid) {
    this.mapOfEntitatForEntitatid = mapOfEntitatForEntitatid;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
