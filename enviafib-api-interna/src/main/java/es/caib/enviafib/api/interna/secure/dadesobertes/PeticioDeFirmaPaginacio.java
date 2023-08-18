package es.caib.enviafib.api.interna.secure.dadesobertes;

import java.util.List;

import org.fundaciobit.pluginsib.utils.rest.RestPagination;

/**
 * 
 * @author anadal
 *
 */
public class PeticioDeFirmaPaginacio extends RestPagination<PeticioDeFirma> {

    public PeticioDeFirmaPaginacio() {
        super();
    }

    public PeticioDeFirmaPaginacio(int pagesize, int page, int totalpages, int totalcount, List<PeticioDeFirma> data) {
        super(pagesize, page, totalpages, totalcount, data);
    }

}
