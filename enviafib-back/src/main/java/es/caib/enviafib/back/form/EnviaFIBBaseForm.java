package es.caib.enviafib.back.form;

import org.fundaciobit.genapp.common.web.form.BaseForm;

/**
 * 
 * @author anadal
 *
 */
public abstract class EnviaFIBBaseForm extends BaseForm {

  public EnviaFIBBaseForm() {
  }
  
  public EnviaFIBBaseForm(boolean nou) {
    super(nou);
  }
  
  public EnviaFIBBaseForm(EnviaFIBBaseForm __toClone) {
    super(__toClone);
  }
  
}
