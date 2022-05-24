package es.caib.enviafib.logic;


/*
import es.caib.enviafib.ejb.AnnexEJB;
import es.caib.enviafib.ejb.FitxerService;
import es.caib.enviafib.persistence.AnnexJPA;
import es.caib.enviafib.model.entity.AnnexFirmat;
import es.caib.enviafib.model.fields.AnnexFields;
import es.caib.enviafib.model.fields.AnnexFirmatFields;

import org.fundaciobit.genapp.common.i18n.I18NException;

*/

import javax.ejb.Stateless;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "SampleLogicaEJB")
public class SampleLogicaEJB implements SampleLogicaService {


}
 
 /**
@Stateless(name = "AnnexLogicaEJB")
public class AnnexLogicaEJB extends AnnexEJB implements AnnexLogicaService,
    AnnexFields {


  @EJB(mappedName = FitxerService.JNDI_NAME)
  private FitxerService fitxerEjb;

  @EJB(mappedName = es.caib.enviafib.ejb.AnnexFirmatService.JNDI_NAME)
  protected es.caib.enviafib.ejb.AnnexFirmatServiceannexFirmatEjb;
  
  
  @Override
  public AnnexJPA createFull(AnnexJPA annex) throws I18NException {
    // TODO Validar !!!
    
    return (AnnexJPA)create(annex);
  }
  


  @Override
  public Set<Long> deleteFull(AnnexJPA annex) throws I18NException {
    
    Set<Long> files = new HashSet<Long>();
    
    if (annex == null) {
      return files;
    }
    
    // Annex
    delete(annex);
    
    return files;
  }

}
*/
