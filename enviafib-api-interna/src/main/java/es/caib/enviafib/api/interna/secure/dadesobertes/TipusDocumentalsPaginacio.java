package es.caib.enviafib.api.interna.secure.dadesobertes;

import java.util.List;

import org.fundaciobit.pluginsib.utils.rest.RestPagination;


/**
 * 
 * @author anadal
 *
 */
public class TipusDocumentalsPaginacio extends RestPagination<String> {
    
    
    public TipusDocumentalsPaginacio() {
        super();
    }
    
    public TipusDocumentalsPaginacio(int pagesize, int page, int totalpages, int totalcount, List<String> data) {
        super(pagesize, page, totalpages, totalcount, data);
    }

}
