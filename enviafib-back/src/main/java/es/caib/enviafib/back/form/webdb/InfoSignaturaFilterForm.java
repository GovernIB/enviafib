
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


  private java.lang.Integer signOperationDesde;

  public java.lang.Integer getSignOperationDesde() {
    return this.signOperationDesde;
  }

  public void setSignOperationDesde(java.lang.Integer signOperationDesde) {
    this.signOperationDesde = signOperationDesde;
  }


  private java.lang.Integer signOperationFins;

  public java.lang.Integer getSignOperationFins() {
    return this.signOperationFins;
  }

  public void setSignOperationFins(java.lang.Integer signOperationFins) {
    this.signOperationFins = signOperationFins;
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


  private java.lang.Integer signModeDesde;

  public java.lang.Integer getSignModeDesde() {
    return this.signModeDesde;
  }

  public void setSignModeDesde(java.lang.Integer signModeDesde) {
    this.signModeDesde = signModeDesde;
  }


  private java.lang.Integer signModeFins;

  public java.lang.Integer getSignModeFins() {
    return this.signModeFins;
  }

  public void setSignModeFins(java.lang.Integer signModeFins) {
    this.signModeFins = signModeFins;
  }


  private java.lang.Integer signaturesTableLocationDesde;

  public java.lang.Integer getSignaturesTableLocationDesde() {
    return this.signaturesTableLocationDesde;
  }

  public void setSignaturesTableLocationDesde(java.lang.Integer signaturesTableLocationDesde) {
    this.signaturesTableLocationDesde = signaturesTableLocationDesde;
  }


  private java.lang.Integer signaturesTableLocationFins;

  public java.lang.Integer getSignaturesTableLocationFins() {
    return this.signaturesTableLocationFins;
  }

  public void setSignaturesTableLocationFins(java.lang.Integer signaturesTableLocationFins) {
    this.signaturesTableLocationFins = signaturesTableLocationFins;
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
    this.signOperationDesde = __toClone.signOperationDesde;
    this.signOperationFins = __toClone.signOperationFins;
    this.signType = __toClone.signType;
    this.signAlgorithm = __toClone.signAlgorithm;
    this.signModeDesde = __toClone.signModeDesde;
    this.signModeFins = __toClone.signModeFins;
    this.signaturesTableLocationDesde = __toClone.signaturesTableLocationDesde;
    this.signaturesTableLocationFins = __toClone.signaturesTableLocationFins;
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
