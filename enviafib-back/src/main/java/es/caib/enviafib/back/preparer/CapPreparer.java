package es.caib.enviafib.back.preparer;

import java.util.Map;

import javax.annotation.security.RunAs;

import org.apache.log4j.Logger;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Component;

import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;

/**
 * @author anadal
 *
 */
@RunAs(Constants.EFI_USER)
@Component
public class CapPreparer extends MenuPreparer {

	protected final Logger log = Logger.getLogger(getClass());

	@Override
	public void execute(Request tilesRequest, AttributeContext attributeContext) throws PreparerException {
	    
        Map<String, Object> request = tilesRequest.getContext("request");

        Object pipella = attributeContext.getAttribute("pipella");

//        super.execute(tilesRequest, attributeContext);
	    
        request.put("url_sortida", Configuracio.getSortirURL());

	}
}
