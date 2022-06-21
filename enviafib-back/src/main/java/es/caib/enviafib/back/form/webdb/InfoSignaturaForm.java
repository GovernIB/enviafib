package es.caib.enviafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.InfoSignaturaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class InfoSignaturaForm extends EnviaFIBBaseForm {
  
  private InfoSignaturaJPA infoSignatura;
  
  public InfoSignaturaForm() {
  }
  
  public InfoSignaturaForm(InfoSignaturaForm __toClone) {
    super(__toClone);
      this.infoSignatura = __toClone.infoSignatura;
    this.listOfValuesForSignoperation = __toClone.listOfValuesForSignoperation;
    this.listOfValuesForSignmode = __toClone.listOfValuesForSignmode;
    this.listOfValuesForSignaturestablelocation = __toClone.listOfValuesForSignaturestablelocation;
  }
  
  public InfoSignaturaForm(InfoSignaturaJPA infoSignatura, boolean nou) {
    super(nou);
    this.infoSignatura = infoSignatura;
  }
  
  public InfoSignaturaJPA getInfoSignatura() {
    return infoSignatura;
  }
  public void setInfoSignatura(InfoSignaturaJPA infoSignatura) {
    this.infoSignatura = infoSignatura;
  }
  
  
  private List<StringKeyValue> listOfValuesForSignoperation;

  public List<StringKeyValue> getListOfValuesForSignoperation() {
    return this.listOfValuesForSignoperation;
  }

  public void setListOfValuesForSignoperation(List<StringKeyValue> listOfValuesForSignoperation) {
    this.listOfValuesForSignoperation = listOfValuesForSignoperation;
  }



  private List<StringKeyValue> listOfValuesForSignmode;

  public List<StringKeyValue> getListOfValuesForSignmode() {
    return this.listOfValuesForSignmode;
  }

  public void setListOfValuesForSignmode(List<StringKeyValue> listOfValuesForSignmode) {
    this.listOfValuesForSignmode = listOfValuesForSignmode;
  }



  private List<StringKeyValue> listOfValuesForSignaturestablelocation;

  public List<StringKeyValue> getListOfValuesForSignaturestablelocation() {
    return this.listOfValuesForSignaturestablelocation;
  }

  public void setListOfValuesForSignaturestablelocation(List<StringKeyValue> listOfValuesForSignaturestablelocation) {
    this.listOfValuesForSignaturestablelocation = listOfValuesForSignaturestablelocation;
  }



  
} // Final de Classe 
