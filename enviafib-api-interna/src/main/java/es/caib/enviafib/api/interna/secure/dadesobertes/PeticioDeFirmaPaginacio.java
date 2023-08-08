package es.caib.enviafib.api.interna.secure.dadesobertes;

import java.util.List;

import es.caib.enviafib.api.interna.common.DadesObertesPaginacio;

/**
 * 
 * @author anadal
 *
 */
public class PeticioDeFirmaPaginacio extends DadesObertesPaginacio<PeticioDeFirma> {
    
    
    public PeticioDeFirmaPaginacio() {
        super();
    }
    
    public PeticioDeFirmaPaginacio(int pagesize, int page, int totalpages, int totalcount, List<PeticioDeFirma> data) {
        super(pagesize, page, totalpages, totalcount, data);
    }

}
