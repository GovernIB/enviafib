package es.caib.enviafib.api.interna.secure.dadesobertes;

import java.util.List;
import org.fundaciobit.pluginsib.utils.rest.ReuseDataPagination;

/**
 * 
 * @author anadal
 *
 */
public class PeticioDeFirmaPaginacio extends ReuseDataPagination<PeticioDeFirma> {

    public PeticioDeFirmaPaginacio() {
        super();
    }

    public PeticioDeFirmaPaginacio(int pagesize, int page, int totalpages, int totalcount, List<PeticioDeFirma> data,
            String nextUrl, String dateDownload, String name) {
        super(data, page, pagesize, totalpages, totalcount, nextUrl, dateDownload, name);
    }

}
