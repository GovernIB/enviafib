package es.caib.enviafib.api.interna.secure.dadesobertes;

import java.util.List;

import es.caib.enviafib.api.interna.common.DadesObertesPaginacio;

/**
 * 
 * @author anadal
 *
 */
public class TipusDocumentalsPaginacio extends DadesObertesPaginacio<String> {
    
    
    public TipusDocumentalsPaginacio() {
        super();
    }
    
    public TipusDocumentalsPaginacio(int pagesize, int page, int totalpages, int totalcount, List<String> data) {
        super(pagesize, page, totalpages, totalcount, data);
    }

}
