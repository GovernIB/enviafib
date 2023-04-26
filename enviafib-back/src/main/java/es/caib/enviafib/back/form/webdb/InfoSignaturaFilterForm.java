
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

  private java.lang.Long infoSignaturaIDDesde;

  public java.lang.Long getInfoSignaturaIDDesde() {
    return this.infoSignaturaIDDesde;
  }

  public void setInfoSignaturaIDDesde(java.lang.Long infoSignaturaIDDesde) {
    this.infoSignaturaIDDesde = infoSignaturaIDDesde;
  }


  private java.lang.Long infoSignaturaIDFins;

  public java.lang.Long getInfoSignaturaIDFins() {
    return this.infoSignaturaIDFins;
  }

  public void setInfoSignaturaIDFins(java.lang.Long infoSignaturaIDFins) {
    this.infoSignaturaIDFins = infoSignaturaIDFins;
  }


  private java.util.List<java.lang.Integer> signOperationSelect;

  public java.util.List<java.lang.Integer> getSignOperationSelect() {
    return this.signOperationSelect;
  }

  public void setSignOperationSelect(java.util.List<java.lang.Integer> signOperationSelect) {
    this.signOperationSelect = signOperationSelect;
  }


  private java.lang.String signType;

  public java.lang.String getSignType() {
    return this.signType;
  }

  public void setSignType(java.lang.String signType) {
    this.signType = signType;
  }


  private java.lang.String signAlgorithm;

  public java.lang.String getSignAlgorithm() {
    return this.signAlgorithm;
  }

  public void setSignAlgorithm(java.lang.String signAlgorithm) {
    this.signAlgorithm = signAlgorithm;
  }


  private java.util.List<java.lang.Integer> signModeSelect;

  public java.util.List<java.lang.Integer> getSignModeSelect() {
    return this.signModeSelect;
  }

  public void setSignModeSelect(java.util.List<java.lang.Integer> signModeSelect) {
    this.signModeSelect = signModeSelect;
  }


  private java.util.List<java.lang.Integer> signaturesTableLocationSelect;

  public java.util.List<java.lang.Integer> getSignaturesTableLocationSelect() {
    return this.signaturesTableLocationSelect;
  }

  public void setSignaturesTableLocationSelect(java.util.List<java.lang.Integer> signaturesTableLocationSelect) {
    this.signaturesTableLocationSelect = signaturesTableLocationSelect;
  }


  private java.lang.String eniTipoFirma;

  public java.lang.String getEniTipoFirma() {
    return this.eniTipoFirma;
  }

  public void setEniTipoFirma(java.lang.String eniTipoFirma) {
    this.eniTipoFirma = eniTipoFirma;
  }


  private java.lang.String eniPerfilFirma;

  public java.lang.String getEniPerfilFirma() {
    return this.eniPerfilFirma;
  }

  public void setEniPerfilFirma(java.lang.String eniPerfilFirma) {
    this.eniPerfilFirma = eniPerfilFirma;
  }


  private java.lang.String eniRolFirma;

  public java.lang.String getEniRolFirma() {
    return this.eniRolFirma;
  }

  public void setEniRolFirma(java.lang.String eniRolFirma) {
    this.eniRolFirma = eniRolFirma;
  }


  private java.lang.String eniSignerName;

  public java.lang.String getEniSignerName() {
    return this.eniSignerName;
  }

  public void setEniSignerName(java.lang.String eniSignerName) {
    this.eniSignerName = eniSignerName;
  }


  private java.lang.String eniSignerAdministrationId;

  public java.lang.String getEniSignerAdministrationId() {
    return this.eniSignerAdministrationId;
  }

  public void setEniSignerAdministrationId(java.lang.String eniSignerAdministrationId) {
    this.eniSignerAdministrationId = eniSignerAdministrationId;
  }


  private java.lang.String eniSignLevel;

  public java.lang.String getEniSignLevel() {
    return this.eniSignLevel;
  }

  public void setEniSignLevel(java.lang.String eniSignLevel) {
    this.eniSignLevel = eniSignLevel;
  }


  public InfoSignaturaFilterForm() {
  }
  
  public InfoSignaturaFilterForm(InfoSignaturaFilterForm __toClone) {
    super(__toClone);
    this.infoSignaturaIDDesde = __toClone.infoSignaturaIDDesde;
    this.infoSignaturaIDFins = __toClone.infoSignaturaIDFins;
    this.signOperationSelect = __toClone.signOperationSelect;
    this.signType = __toClone.signType;
    this.signAlgorithm = __toClone.signAlgorithm;
    this.signModeSelect = __toClone.signModeSelect;
    this.signaturesTableLocationSelect = __toClone.signaturesTableLocationSelect;
    this.eniTipoFirma = __toClone.eniTipoFirma;
    this.eniPerfilFirma = __toClone.eniPerfilFirma;
    this.eniRolFirma = __toClone.eniRolFirma;
    this.eniSignerName = __toClone.eniSignerName;
    this.eniSignerAdministrationId = __toClone.eniSignerAdministrationId;
    this.eniSignLevel = __toClone.eniSignLevel;
    this.mapOfValuesForSignOperation = __toClone.mapOfValuesForSignOperation;
    this.mapOfValuesForSignMode = __toClone.mapOfValuesForSignMode;
    this.mapOfValuesForSignaturesTableLocation = __toClone.mapOfValuesForSignaturesTableLocation;
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
  private Map<String, String> mapOfValuesForSignOperation;

  public Map<String, String> getMapOfValuesForSignOperation() {
    return this.mapOfValuesForSignOperation;
  }

  public void setMapOfValuesForSignOperation(Map<String, String> mapOfValuesForSignOperation) {
    this.mapOfValuesForSignOperation = mapOfValuesForSignOperation;
  }



  private Map<String, String> mapOfValuesForSignMode;

  public Map<String, String> getMapOfValuesForSignMode() {
    return this.mapOfValuesForSignMode;
  }

  public void setMapOfValuesForSignMode(Map<String, String> mapOfValuesForSignMode) {
    this.mapOfValuesForSignMode = mapOfValuesForSignMode;
  }



  private Map<String, String> mapOfValuesForSignaturesTableLocation;

  public Map<String, String> getMapOfValuesForSignaturesTableLocation() {
    return this.mapOfValuesForSignaturesTableLocation;
  }

  public void setMapOfValuesForSignaturesTableLocation(Map<String, String> mapOfValuesForSignaturesTableLocation) {
    this.mapOfValuesForSignaturesTableLocation = mapOfValuesForSignaturesTableLocation;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
