
package es.caib.enviafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.enviafib.back.form.EnviaFIBBaseFilterForm;

import es.caib.enviafib.model.fields.InfoSignaturaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class InfoSignaturaFilterForm extends EnviaFIBBaseFilterForm implements InfoSignaturaFields {

  private java.lang.Long infosignaturaidDesde;

  public java.lang.Long getInfosignaturaidDesde() {
    return this.infosignaturaidDesde;
  }

  public void setInfosignaturaidDesde(java.lang.Long infosignaturaidDesde) {
    this.infosignaturaidDesde = infosignaturaidDesde;
  }


  private java.lang.Long infosignaturaidFins;

  public java.lang.Long getInfosignaturaidFins() {
    return this.infosignaturaidFins;
  }

  public void setInfosignaturaidFins(java.lang.Long infosignaturaidFins) {
    this.infosignaturaidFins = infosignaturaidFins;
  }


  private java.lang.Integer signoperationDesde;

  public java.lang.Integer getSignoperationDesde() {
    return this.signoperationDesde;
  }

  public void setSignoperationDesde(java.lang.Integer signoperationDesde) {
    this.signoperationDesde = signoperationDesde;
  }


  private java.lang.Integer signoperationFins;

  public java.lang.Integer getSignoperationFins() {
    return this.signoperationFins;
  }

  public void setSignoperationFins(java.lang.Integer signoperationFins) {
    this.signoperationFins = signoperationFins;
  }


  private java.lang.String signtype;

  public java.lang.String getSigntype() {
    return this.signtype;
  }

  public void setSigntype(java.lang.String signtype) {
    this.signtype = signtype;
  }


  private java.lang.String signalgorithm;

  public java.lang.String getSignalgorithm() {
    return this.signalgorithm;
  }

  public void setSignalgorithm(java.lang.String signalgorithm) {
    this.signalgorithm = signalgorithm;
  }


  private java.lang.Integer signmodeDesde;

  public java.lang.Integer getSignmodeDesde() {
    return this.signmodeDesde;
  }

  public void setSignmodeDesde(java.lang.Integer signmodeDesde) {
    this.signmodeDesde = signmodeDesde;
  }


  private java.lang.Integer signmodeFins;

  public java.lang.Integer getSignmodeFins() {
    return this.signmodeFins;
  }

  public void setSignmodeFins(java.lang.Integer signmodeFins) {
    this.signmodeFins = signmodeFins;
  }


  private java.lang.Integer signaturestablelocationDesde;

  public java.lang.Integer getSignaturestablelocationDesde() {
    return this.signaturestablelocationDesde;
  }

  public void setSignaturestablelocationDesde(java.lang.Integer signaturestablelocationDesde) {
    this.signaturestablelocationDesde = signaturestablelocationDesde;
  }


  private java.lang.Integer signaturestablelocationFins;

  public java.lang.Integer getSignaturestablelocationFins() {
    return this.signaturestablelocationFins;
  }

  public void setSignaturestablelocationFins(java.lang.Integer signaturestablelocationFins) {
    this.signaturestablelocationFins = signaturestablelocationFins;
  }


  private java.lang.String enitipofirma;

  public java.lang.String getEnitipofirma() {
    return this.enitipofirma;
  }

  public void setEnitipofirma(java.lang.String enitipofirma) {
    this.enitipofirma = enitipofirma;
  }


  private java.lang.String eniperfilfirma;

  public java.lang.String getEniperfilfirma() {
    return this.eniperfilfirma;
  }

  public void setEniperfilfirma(java.lang.String eniperfilfirma) {
    this.eniperfilfirma = eniperfilfirma;
  }


  private java.lang.String enirolfirma;

  public java.lang.String getEnirolfirma() {
    return this.enirolfirma;
  }

  public void setEnirolfirma(java.lang.String enirolfirma) {
    this.enirolfirma = enirolfirma;
  }


  private java.lang.String enisignername;

  public java.lang.String getEnisignername() {
    return this.enisignername;
  }

  public void setEnisignername(java.lang.String enisignername) {
    this.enisignername = enisignername;
  }


  private java.lang.String enisigneradministrationid;

  public java.lang.String getEnisigneradministrationid() {
    return this.enisigneradministrationid;
  }

  public void setEnisigneradministrationid(java.lang.String enisigneradministrationid) {
    this.enisigneradministrationid = enisigneradministrationid;
  }


  private java.lang.String enisignlevel;

  public java.lang.String getEnisignlevel() {
    return this.enisignlevel;
  }

  public void setEnisignlevel(java.lang.String enisignlevel) {
    this.enisignlevel = enisignlevel;
  }


  public InfoSignaturaFilterForm() {
  }
  
  public InfoSignaturaFilterForm(InfoSignaturaFilterForm __toClone) {
    super(__toClone);
    this.infosignaturaidDesde = __toClone.infosignaturaidDesde;
    this.infosignaturaidFins = __toClone.infosignaturaidFins;
    this.signoperationDesde = __toClone.signoperationDesde;
    this.signoperationFins = __toClone.signoperationFins;
    this.signtype = __toClone.signtype;
    this.signalgorithm = __toClone.signalgorithm;
    this.signmodeDesde = __toClone.signmodeDesde;
    this.signmodeFins = __toClone.signmodeFins;
    this.signaturestablelocationDesde = __toClone.signaturestablelocationDesde;
    this.signaturestablelocationFins = __toClone.signaturestablelocationFins;
    this.enitipofirma = __toClone.enitipofirma;
    this.eniperfilfirma = __toClone.eniperfilfirma;
    this.enirolfirma = __toClone.enirolfirma;
    this.enisignername = __toClone.enisignername;
    this.enisigneradministrationid = __toClone.enisigneradministrationid;
    this.enisignlevel = __toClone.enisignlevel;
    this.mapOfValuesForSignoperation = __toClone.mapOfValuesForSignoperation;
    this.mapOfValuesForSignmode = __toClone.mapOfValuesForSignmode;
    this.mapOfValuesForSignaturestablelocation = __toClone.mapOfValuesForSignaturestablelocation;
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
  private Map<String, String> mapOfValuesForSignoperation;

  public Map<String, String> getMapOfValuesForSignoperation() {
    return this.mapOfValuesForSignoperation;
  }

  public void setMapOfValuesForSignoperation(Map<String, String> mapOfValuesForSignoperation) {
    this.mapOfValuesForSignoperation = mapOfValuesForSignoperation;
  }



  private Map<String, String> mapOfValuesForSignmode;

  public Map<String, String> getMapOfValuesForSignmode() {
    return this.mapOfValuesForSignmode;
  }

  public void setMapOfValuesForSignmode(Map<String, String> mapOfValuesForSignmode) {
    this.mapOfValuesForSignmode = mapOfValuesForSignmode;
  }



  private Map<String, String> mapOfValuesForSignaturestablelocation;

  public Map<String, String> getMapOfValuesForSignaturestablelocation() {
    return this.mapOfValuesForSignaturestablelocation;
  }

  public void setMapOfValuesForSignaturestablelocation(Map<String, String> mapOfValuesForSignaturestablelocation) {
    this.mapOfValuesForSignaturestablelocation = mapOfValuesForSignaturestablelocation;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
