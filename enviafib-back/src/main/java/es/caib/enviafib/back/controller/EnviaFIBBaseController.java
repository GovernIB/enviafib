package es.caib.enviafib.back.controller;

import org.fundaciobit.genapp.common.IGenAppEntity;
import org.fundaciobit.genapp.common.web.controller.CommonBaseController;
import org.springframework.web.servlet.ModelAndView;

/**
 * POT SOBRESCRIURE AQUESTA CLASSE
 * @author anadal
 *
 */
public abstract class EnviaFIBBaseController<I extends IGenAppEntity, PK extends Object> 
  extends CommonBaseController<I, PK> {

    /**
     * 
     * @param mav
     * @param pagina
     * @param itemsPerPagina
     * @param total
     */
    @Override
    public void omplirDadesPaginacio(ModelAndView mav, Integer pagina, Integer itemsPerPagina,
        Long total) {
      
        super.omplirDadesPaginacio(mav, pagina, itemsPerPagina, total);

        mav.addObject("__totalItems", total);
        mav.addObject("__itemsPerPagina", itemsPerPagina);
        mav.addObject("__pagina", pagina);
    }

}

