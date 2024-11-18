
package es.caib.enviafib.logic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleDocumentTypeInformation;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.jersey.ApiFirmaAsyncSimpleJersey;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.ejb.SerieDocumentalEJB;
import es.caib.enviafib.logic.utils.PortafibUtils;
import es.caib.enviafib.model.entity.SerieDocumental;
import es.caib.enviafib.model.fields.SerieDocumentalFields;
import es.caib.enviafib.persistence.SerieDocumentalJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class SerieDocumentalLogicaEJB extends SerieDocumentalEJB implements SerieDocumentalLogicaService {

	@Override
	@PermitAll
	public SerieDocumental createPublic(SerieDocumental instance) throws I18NException {
		return super.create(instance);
	}

	@Override
	@PermitAll
	public SerieDocumentalJPA findByPrimaryKeyPublic(Long _ID_) {
		return super.findByPrimaryKey(_ID_);
	}

	@Override
	@PermitAll
	public SerieDocumental update(SerieDocumental instance) throws I18NException {
		return super.update(instance);
	}

	@Override
	@PermitAll
	public void delete(SerieDocumental instance) {
		super.delete(instance);
	}

	@Override
	public List<StringKeyValue> getTipusDocumentals(String lang) throws I18NException {

		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

		List<FirmaAsyncSimpleDocumentTypeInformation> tipusDocsPFI = PortafibUtils.getTipusDocumentalsAll(lang);

		for (FirmaAsyncSimpleDocumentTypeInformation tipusDocPFI : tipusDocsPFI) {
			Long key = tipusDocPFI.getDocumentType();
			String name = tipusDocPFI.getName();
			Long base = tipusDocPFI.getDocumentTypeBase();

			log.info("Tipus documental: " + key + " - " + name + " - " + base);

			if (key == base) {
				StringKeyValue skv = new StringKeyValue(key.toString(), name);
				__tmp.add(skv);
			}

		}
		log.info("getTipusDocumentals()::Retornem " + __tmp.size() + " tipus documentals");
		return __tmp;
	}

}
