package es.caib.enviafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.enviafib.back.form.EnviaFIBBaseForm;
import es.caib.enviafib.persistence.PluginJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PluginForm extends EnviaFIBBaseForm {
  
  private PluginJPA plugin;
  
  public PluginForm() {
  }
  
  public PluginForm(PluginForm __toClone) {
    super(__toClone);
      this.plugin = __toClone.plugin;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
  }
  
  public PluginForm(PluginJPA plugin, boolean nou) {
    super(nou);
    this.plugin = plugin;
  }
  
  public PluginJPA getPlugin() {
    return plugin;
  }
  public void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
  }
  
  
  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  
} // Final de Classe 
