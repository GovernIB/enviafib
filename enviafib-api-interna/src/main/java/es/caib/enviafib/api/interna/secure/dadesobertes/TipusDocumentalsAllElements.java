package es.caib.enviafib.api.interna.secure.dadesobertes;

import java.util.List;
import org.fundaciobit.pluginsib.utils.rest.ReuseDataAllElements;


/**
 * 
 * @author anadal
 *
 */
public class TipusDocumentalsAllElements extends ReuseDataAllElements<TipusDocumental> {
    
    
    public TipusDocumentalsAllElements() {
        super();
    }

    public TipusDocumentalsAllElements(List<TipusDocumental> data, int totalcount, String dateDownload, String name) {
        super(data, totalcount, dateDownload, name);
    }

}
