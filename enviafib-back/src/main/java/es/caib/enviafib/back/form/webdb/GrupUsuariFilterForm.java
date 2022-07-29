
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.GrupUsuariFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class GrupUsuariFilterForm extends EnviaFIBBaseFilterForm implements GrupUsuariFields {

  private java.lang.Long grupUsuariIDDesde;

  public java.lang.Long getGrupUsuariIDDesde() {
    return this.grupUsuariIDDesde;
  }

  public void setGrupUsuariIDDesde(java.lang.Long grupUsuariIDDesde) {
    this.grupUsuariIDDesde = grupUsuariIDDesde;
  }


  private java.lang.Long grupUsuariIDFins;

  public java.lang.Long getGrupUsuariIDFins() {
    return this.grupUsuariIDFins;
  }

  public void setGrupUsuariIDFins(java.lang.Long grupUsuariIDFins) {
    this.grupUsuariIDFins = grupUsuariIDFins;
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


  private java.lang.Long usuariIDDesde;

  public java.lang.Long getUsuariIDDesde() {
    return this.usuariIDDesde;
  }

  public void setUsuariIDDesde(java.lang.Long usuariIDDesde) {
    this.usuariIDDesde = usuariIDDesde;
  }


  private java.lang.Long usuariIDFins;

  public java.lang.Long getUsuariIDFins() {
    return this.usuariIDFins;
  }

  public void setUsuariIDFins(java.lang.Long usuariIDFins) {
    this.usuariIDFins = usuariIDFins;
  }


  public GrupUsuariFilterForm() {
  }
  
  public GrupUsuariFilterForm(GrupUsuariFilterForm __toClone) {
    super(__toClone);
    this.grupUsuariIDDesde = __toClone.grupUsuariIDDesde;
    this.grupUsuariIDFins = __toClone.grupUsuariIDFins;
    this.grupIDDesde = __toClone.grupIDDesde;
    this.grupIDFins = __toClone.grupIDFins;
    this.usuariIDDesde = __toClone.usuariIDDesde;
    this.usuariIDFins = __toClone.usuariIDFins;
    this.mapOfGrupForGrupID = __toClone.mapOfGrupForGrupID;
    this.mapOfUsuariForUsuariID = __toClone.mapOfUsuariForUsuariID;
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
  private Map<String, String> mapOfGrupForGrupID;

  public Map<String, String> getMapOfGrupForGrupID() {
    return this.mapOfGrupForGrupID;
  }

  public void setMapOfGrupForGrupID(Map<String, String> mapOfGrupForGrupID) {
    this.mapOfGrupForGrupID = mapOfGrupForGrupID;
  }



  private Map<String, String> mapOfUsuariForUsuariID;

  public Map<String, String> getMapOfUsuariForUsuariID() {
    return this.mapOfUsuariForUsuariID;
  }

  public void setMapOfUsuariForUsuariID(Map<String, String> mapOfUsuariForUsuariID) {
    this.mapOfUsuariForUsuariID = mapOfUsuariForUsuariID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
