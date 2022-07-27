
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.InfoArxiuFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class InfoArxiuFilterForm extends EnviaFIBBaseFilterForm implements InfoArxiuFields {

  private java.lang.Long infoArxiuIDDesde;

  public java.lang.Long getInfoArxiuIDDesde() {
    return this.infoArxiuIDDesde;
  }

  public void setInfoArxiuIDDesde(java.lang.Long infoArxiuIDDesde) {
    this.infoArxiuIDDesde = infoArxiuIDDesde;
  }


  private java.lang.Long infoArxiuIDFins;

  public java.lang.Long getInfoArxiuIDFins() {
    return this.infoArxiuIDFins;
  }

  public void setInfoArxiuIDFins(java.lang.Long infoArxiuIDFins) {
    this.infoArxiuIDFins = infoArxiuIDFins;
  }


  private java.lang.String originalfileurl;

  public java.lang.String getOriginalfileurl() {
    return this.originalfileurl;
  }

  public void setOriginalfileurl(java.lang.String originalfileurl) {
    this.originalfileurl = originalfileurl;
  }


  private java.lang.String csv;

  public java.lang.String getCsv() {
    return this.csv;
  }

  public void setCsv(java.lang.String csv) {
    this.csv = csv;
  }


  private java.lang.String csvgenerationdefinition;

  public java.lang.String getCsvgenerationdefinition() {
    return this.csvgenerationdefinition;
  }

  public void setCsvgenerationdefinition(java.lang.String csvgenerationdefinition) {
    this.csvgenerationdefinition = csvgenerationdefinition;
  }


  private java.lang.String csvvalidationweb;

  public java.lang.String getCsvvalidationweb() {
    return this.csvvalidationweb;
  }

  public void setCsvvalidationweb(java.lang.String csvvalidationweb) {
    this.csvvalidationweb = csvvalidationweb;
  }


  private java.lang.String arxiuexpedientid;

  public java.lang.String getArxiuexpedientid() {
    return this.arxiuexpedientid;
  }

  public void setArxiuexpedientid(java.lang.String arxiuexpedientid) {
    this.arxiuexpedientid = arxiuexpedientid;
  }


  private java.lang.String arxiudocumentid;

  public java.lang.String getArxiudocumentid() {
    return this.arxiudocumentid;
  }

  public void setArxiudocumentid(java.lang.String arxiudocumentid) {
    this.arxiudocumentid = arxiudocumentid;
  }


  private java.lang.String printableurl;

  public java.lang.String getPrintableurl() {
    return this.printableurl;
  }

  public void setPrintableurl(java.lang.String printableurl) {
    this.printableurl = printableurl;
  }


  private java.lang.String enifileurl;

  public java.lang.String getEnifileurl() {
    return this.enifileurl;
  }

  public void setEnifileurl(java.lang.String enifileurl) {
    this.enifileurl = enifileurl;
  }


  private java.lang.String validationfileurl;

  public java.lang.String getValidationfileurl() {
    return this.validationfileurl;
  }

  public void setValidationfileurl(java.lang.String validationfileurl) {
    this.validationfileurl = validationfileurl;
  }


  public InfoArxiuFilterForm() {
  }
  
  public InfoArxiuFilterForm(InfoArxiuFilterForm __toClone) {
    super(__toClone);
    this.infoArxiuIDDesde = __toClone.infoArxiuIDDesde;
    this.infoArxiuIDFins = __toClone.infoArxiuIDFins;
    this.originalfileurl = __toClone.originalfileurl;
    this.csv = __toClone.csv;
    this.csvgenerationdefinition = __toClone.csvgenerationdefinition;
    this.csvvalidationweb = __toClone.csvvalidationweb;
    this.arxiuexpedientid = __toClone.arxiuexpedientid;
    this.arxiudocumentid = __toClone.arxiudocumentid;
    this.printableurl = __toClone.printableurl;
    this.enifileurl = __toClone.enifileurl;
    this.validationfileurl = __toClone.validationfileurl;
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
