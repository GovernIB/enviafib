package es.caib.enviafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.MenuJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class MenuForm extends EnviaFIBBaseForm {
  
  private MenuJPA menu;
  
  public MenuForm() {
  }
  
  public MenuForm(MenuForm __toClone) {
    super(__toClone);
      this.menu = __toClone.menu;
    this.listOfTraduccioForTitolMenuID = __toClone.listOfTraduccioForTitolMenuID;
    this.listOfTraduccioForAjudaMenuID = __toClone.listOfTraduccioForAjudaMenuID;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
    this.listOfGrupForGrupID = __toClone.listOfGrupForGrupID;
    this.listOfValuesForParametreCombo = __toClone.listOfValuesForParametreCombo;
  }
  
  public MenuForm(MenuJPA menu, boolean nou) {
    super(nou);
    this.menu = menu;
  }
  
  public MenuJPA getMenu() {
    return menu;
  }
  public void setMenu(MenuJPA menu) {
    this.menu = menu;
  }
  
  java.util.List<es.caib.enviafib.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.enviafib.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.enviafib.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  private List<StringKeyValue> listOfTraduccioForTitolMenuID;

  public List<StringKeyValue> getListOfTraduccioForTitolMenuID() {
    return this.listOfTraduccioForTitolMenuID;
  }

  public void setListOfTraduccioForTitolMenuID(List<StringKeyValue> listOfTraduccioForTitolMenuID) {
    this.listOfTraduccioForTitolMenuID = listOfTraduccioForTitolMenuID;
  }



  private List<StringKeyValue> listOfTraduccioForAjudaMenuID;

  public List<StringKeyValue> getListOfTraduccioForAjudaMenuID() {
    return this.listOfTraduccioForAjudaMenuID;
  }

  public void setListOfTraduccioForAjudaMenuID(List<StringKeyValue> listOfTraduccioForAjudaMenuID) {
    this.listOfTraduccioForAjudaMenuID = listOfTraduccioForAjudaMenuID;
  }



  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  private List<StringKeyValue> listOfGrupForGrupID;

  public List<StringKeyValue> getListOfGrupForGrupID() {
    return this.listOfGrupForGrupID;
  }

  public void setListOfGrupForGrupID(List<StringKeyValue> listOfGrupForGrupID) {
    this.listOfGrupForGrupID = listOfGrupForGrupID;
  }



  private List<StringKeyValue> listOfValuesForParametreCombo;

  public List<StringKeyValue> getListOfValuesForParametreCombo() {
    return this.listOfValuesForParametreCombo;
  }

  public void setListOfValuesForParametreCombo(List<StringKeyValue> listOfValuesForParametreCombo) {
    this.listOfValuesForParametreCombo = listOfValuesForParametreCombo;
  }



  
} // Final de Classe 
