package es.caib.enviafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.EntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EntitatForm extends EnviaFIBBaseForm {
  
  private EntitatJPA entitat;
  
  
  private CommonsMultipartFile faviconID;
  private boolean faviconIDDelete;
  
  
  private CommonsMultipartFile logowebID;
  private boolean logowebIDDelete;
  
  
  private CommonsMultipartFile logowebpeuID;
  private boolean logowebpeuIDDelete;
  
  
  private CommonsMultipartFile logosegellID;
  private boolean logosegellIDDelete;
  
  public EntitatForm() {
  }
  
  public EntitatForm(EntitatForm __toClone) {
    super(__toClone);
      this.entitat = __toClone.entitat;
    this.listOfTraduccioForMotiudelegacioID = __toClone.listOfTraduccioForMotiudelegacioID;
  }
  
  public EntitatForm(EntitatJPA entitat, boolean nou) {
    super(nou);
    this.entitat = entitat;
  }
  
  public EntitatJPA getEntitat() {
    return entitat;
  }
  public void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }
  
  java.util.List<es.caib.enviafib.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.enviafib.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.enviafib.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  public CommonsMultipartFile getFaviconID() {
    return faviconID;
  }
  
   public void setFaviconID(CommonsMultipartFile faviconID) {
    this.faviconID = faviconID;
  }
  public boolean isFaviconIDDelete() {
    return faviconIDDelete;
  }
  
  public void setFaviconIDDelete(boolean faviconIDDelete) {
    this.faviconIDDelete = faviconIDDelete;
   }
  public CommonsMultipartFile getLogowebID() {
    return logowebID;
  }
  
   public void setLogowebID(CommonsMultipartFile logowebID) {
    this.logowebID = logowebID;
  }
  public boolean isLogowebIDDelete() {
    return logowebIDDelete;
  }
  
  public void setLogowebIDDelete(boolean logowebIDDelete) {
    this.logowebIDDelete = logowebIDDelete;
   }
  public CommonsMultipartFile getLogowebpeuID() {
    return logowebpeuID;
  }
  
   public void setLogowebpeuID(CommonsMultipartFile logowebpeuID) {
    this.logowebpeuID = logowebpeuID;
  }
  public boolean isLogowebpeuIDDelete() {
    return logowebpeuIDDelete;
  }
  
  public void setLogowebpeuIDDelete(boolean logowebpeuIDDelete) {
    this.logowebpeuIDDelete = logowebpeuIDDelete;
   }
  public CommonsMultipartFile getLogosegellID() {
    return logosegellID;
  }
  
   public void setLogosegellID(CommonsMultipartFile logosegellID) {
    this.logosegellID = logosegellID;
  }
  public boolean isLogosegellIDDelete() {
    return logosegellIDDelete;
  }
  
  public void setLogosegellIDDelete(boolean logosegellIDDelete) {
    this.logosegellIDDelete = logosegellIDDelete;
   }
  private List<StringKeyValue> listOfTraduccioForMotiudelegacioID;

  public List<StringKeyValue> getListOfTraduccioForMotiudelegacioID() {
    return this.listOfTraduccioForMotiudelegacioID;
  }

  public void setListOfTraduccioForMotiudelegacioID(List<StringKeyValue> listOfTraduccioForMotiudelegacioID) {
    this.listOfTraduccioForMotiudelegacioID = listOfTraduccioForMotiudelegacioID;
  }



  
} // Final de Classe 
