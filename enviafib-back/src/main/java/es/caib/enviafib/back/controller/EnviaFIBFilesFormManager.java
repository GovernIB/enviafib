package es.caib.enviafib.back.controller;

import es.caib.enviafib.persistence.FitxerJPA;
import es.caib.enviafib.model.entity.Fitxer;

import org.fundaciobit.genapp.common.filesystem.IFileManager;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;


/**
 * Gestiona Multiples Fitxers d'un Form
 * 
 * @author anadal
 * 
 */
public class EnviaFIBFilesFormManager extends FilesFormManager<Fitxer> {

  public EnviaFIBFilesFormManager(IFileManager<Fitxer> fitxerEjb) {
    super(fitxerEjb);
  }

  @Override
  public FitxerJPA createEmptyFile() {    
    return new FitxerJPA();
  }

}
