
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


  private java.lang.String originalFileUrl;

  public java.lang.String getOriginalFileUrl() {
    return this.originalFileUrl;
  }

  public void setOriginalFileUrl(java.lang.String originalFileUrl) {
    this.originalFileUrl = originalFileUrl;
  }


  private java.lang.String csv;

  public java.lang.String getCsv() {
    return this.csv;
  }

  public void setCsv(java.lang.String csv) {
    this.csv = csv;
  }


  private java.lang.String csvGenerationDefinition;

  public java.lang.String getCsvGenerationDefinition() {
    return this.csvGenerationDefinition;
  }

  public void setCsvGenerationDefinition(java.lang.String csvGenerationDefinition) {
    this.csvGenerationDefinition = csvGenerationDefinition;
  }


  private java.lang.String csvValidationWeb;

  public java.lang.String getCsvValidationWeb() {
    return this.csvValidationWeb;
  }

  public void setCsvValidationWeb(java.lang.String csvValidationWeb) {
    this.csvValidationWeb = csvValidationWeb;
  }


  private java.lang.String arxiuExpedientID;

  public java.lang.String getArxiuExpedientID() {
    return this.arxiuExpedientID;
  }

  public void setArxiuExpedientID(java.lang.String arxiuExpedientID) {
    this.arxiuExpedientID = arxiuExpedientID;
  }


  private java.lang.String arxiuDocumentID;

  public java.lang.String getArxiuDocumentID() {
    return this.arxiuDocumentID;
  }

  public void setArxiuDocumentID(java.lang.String arxiuDocumentID) {
    this.arxiuDocumentID = arxiuDocumentID;
  }


  private java.lang.String printableUrl;

  public java.lang.String getPrintableUrl() {
    return this.printableUrl;
  }

  public void setPrintableUrl(java.lang.String printableUrl) {
    this.printableUrl = printableUrl;
  }


  private java.lang.String eniFileUrl;

  public java.lang.String getEniFileUrl() {
    return this.eniFileUrl;
  }

  public void setEniFileUrl(java.lang.String eniFileUrl) {
    this.eniFileUrl = eniFileUrl;
  }


  private java.lang.String validationFileUrl;

  public java.lang.String getValidationFileUrl() {
    return this.validationFileUrl;
  }

  public void setValidationFileUrl(java.lang.String validationFileUrl) {
    this.validationFileUrl = validationFileUrl;
  }


  public InfoArxiuFilterForm() {
  }
  
  public InfoArxiuFilterForm(InfoArxiuFilterForm __toClone) {
    super(__toClone);
    this.infoArxiuIDDesde = __toClone.infoArxiuIDDesde;
    this.infoArxiuIDFins = __toClone.infoArxiuIDFins;
    this.originalFileUrl = __toClone.originalFileUrl;
    this.csv = __toClone.csv;
    this.csvGenerationDefinition = __toClone.csvGenerationDefinition;
    this.csvValidationWeb = __toClone.csvValidationWeb;
    this.arxiuExpedientID = __toClone.arxiuExpedientID;
    this.arxiuDocumentID = __toClone.arxiuDocumentID;
    this.printableUrl = __toClone.printableUrl;
    this.eniFileUrl = __toClone.eniFileUrl;
    this.validationFileUrl = __toClone.validationFileUrl;
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
