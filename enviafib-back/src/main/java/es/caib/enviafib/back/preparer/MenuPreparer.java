package es.caib.enviafib.back.preparer;

import javax.annotation.security.RunAs;


import org.apache.log4j.Logger;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Component;
import es.caib.enviafib.commons.utils.Constants;


/**
 * @author anadal
 *
 */
@RunAs(Constants.EFI_USER)
@Component
public class MenuPreparer implements ViewPreparer {

  protected final Logger log = Logger.getLogger(getClass());


	@Override
	public void execute(Request tilesRequest, AttributeContext attributeContext) throws PreparerException {
	  
	}
}
