package es.caib.enviafib.back.preparer;

import java.util.Map;

import javax.annotation.security.RunAs;

import org.apache.log4j.Logger;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Component;

import es.caib.enviafib.back.security.LoginInfo;
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
	    
	    Object pipella = attributeContext.getAttribute("pipella");
	    
	    //log.info("\n\n       ----  PASSA PER CAP PREPARER [pipella => " + pipella + " ]----   \n\n");
	    
	    super.execute(tilesRequest, attributeContext);
	    
        Map<String, Object> request = tilesRequest.getContext("request");
        request.put("url_sortida", Configuracio.getSortirURL());

	}
	
	@Override
    protected boolean getAdditionalCondition(String pipella) {
        return !LoginInfo.hasRole(Constants.ROLE_ADMIN);
    }
}
