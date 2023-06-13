
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.FaqFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class FaqFilterForm extends EnviaFIBBaseFilterForm implements FaqFields {

  private java.lang.Long faqIDDesde;

  public java.lang.Long getFaqIDDesde() {
    return this.faqIDDesde;
  }

  public void setFaqIDDesde(java.lang.Long faqIDDesde) {
    this.faqIDDesde = faqIDDesde;
  }


  private java.lang.Long faqIDFins;

  public java.lang.Long getFaqIDFins() {
    return this.faqIDFins;
  }

  public void setFaqIDFins(java.lang.Long faqIDFins) {
    this.faqIDFins = faqIDFins;
  }


  private java.lang.String enunciat_es;

  public java.lang.String getEnunciat_es() {
    return this.enunciat_es;
  }

  public void setEnunciat_es(java.lang.String enunciat_es) {
    this.enunciat_es = enunciat_es;
  }


  private java.lang.String enunciat_ca;

  public java.lang.String getEnunciat_ca() {
    return this.enunciat_ca;
  }

  public void setEnunciat_ca(java.lang.String enunciat_ca) {
    this.enunciat_ca = enunciat_ca;
  }


  private java.lang.String resposta_es;

  public java.lang.String getResposta_es() {
    return this.resposta_es;
  }

  public void setResposta_es(java.lang.String resposta_es) {
    this.resposta_es = resposta_es;
  }


  private java.lang.String resposta_ca;

  public java.lang.String getResposta_ca() {
    return this.resposta_ca;
  }

  public void setResposta_ca(java.lang.String resposta_ca) {
    this.resposta_ca = resposta_ca;
  }


  private java.lang.Long ordreDesde;

  public java.lang.Long getOrdreDesde() {
    return this.ordreDesde;
  }

  public void setOrdreDesde(java.lang.Long ordreDesde) {
    this.ordreDesde = ordreDesde;
  }


  private java.lang.Long ordreFins;

  public java.lang.Long getOrdreFins() {
    return this.ordreFins;
  }

  public void setOrdreFins(java.lang.Long ordreFins) {
    this.ordreFins = ordreFins;
  }


  public FaqFilterForm() {
  }
  
  public FaqFilterForm(FaqFilterForm __toClone) {
    super(__toClone);
    this.faqIDDesde = __toClone.faqIDDesde;
    this.faqIDFins = __toClone.faqIDFins;
    this.enunciat_es = __toClone.enunciat_es;
    this.enunciat_ca = __toClone.enunciat_ca;
    this.resposta_es = __toClone.resposta_es;
    this.resposta_ca = __toClone.resposta_ca;
    this.ordreDesde = __toClone.ordreDesde;
    this.ordreFins = __toClone.ordreFins;
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
