package es.caib.enviafib.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleDocumentTypeInformation;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

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
	public List<StringKeyValue> getAllTipusDocumentals(String lang) throws I18NException {
		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

		List<FirmaAsyncSimpleDocumentTypeInformation> tipusDocsPFI = PortafibUtils.getTipusDocumentalsBase(lang);

		for (FirmaAsyncSimpleDocumentTypeInformation tipusDocPFI : tipusDocsPFI) {
			Long key = tipusDocPFI.getDocumentType();
			String name = tipusDocPFI.getName();

			StringKeyValue skv = new StringKeyValue(key.toString(), name);
			__tmp.add(skv);

		}
		log.info("getAllTipusDocumentals()::Retornem " + __tmp.size() + " tipus documentals");
		return __tmp;

	}
	
	
	
	@Override
	public List<StringKeyValue> getTipusDocumentalsBase(String lang) throws I18NException {

		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

		List<FirmaAsyncSimpleDocumentTypeInformation> tipusDocsPFI = PortafibUtils.getTipusDocumentalsBase(lang);

		for (FirmaAsyncSimpleDocumentTypeInformation tipusDocPFI : tipusDocsPFI) {
			Long key = tipusDocPFI.getDocumentType();
			String name = tipusDocPFI.getName();
			Long base = tipusDocPFI.getDocumentTypeBase();

			log.info("Tipus documental: " + key + " - " + name + " - " + base);

			StringKeyValue skv = new StringKeyValue(key.toString(), name);
			__tmp.add(skv);

		}
		log.info("getTipusDocumentalsBase()::Retornem " + __tmp.size() + " tipus documentals");
		return __tmp;
	}
	
	
	@Override
	public SerieDocumental getSerieDocFromTipusDoc(String lang, String tipusDocumental, String entitatID) throws I18NException {
		//Cercar el tipusDocumental, obtenir el seu base, i cercar a la lista de series de l'entitat, la serie documental amb td la base
		
		
		//Obtenir el base del tipusDocumental
		List<FirmaAsyncSimpleDocumentTypeInformation> tipusDocsPFI = PortafibUtils.getTipusDocumentalsAll(lang);
		
		Long tipusDoc = Long.parseLong(tipusDocumental);
		Long base = null;
		for (FirmaAsyncSimpleDocumentTypeInformation tipusDocPFI : tipusDocsPFI) {
			if (tipusDocPFI.getDocumentType() == tipusDoc) {
				base = tipusDocPFI.getDocumentTypeBase();
				break;
			}
		}

		if (base == null) {
			throw new I18NException("error.portafib.tipusdocumental",
					"No s'ha trobat el tipus documental base de " + tipusDocumental);
		}
		
		SerieDocumental serieDocumental = null;
		
		Where wEntitat = SerieDocumentalFields.ENTITATID.equal(entitatID);
		Where wTipusDoc = SerieDocumentalFields.TIPUSDOCUMENTAL.equal(String.valueOf(base));
		
		List<SerieDocumental> series = this.select(Where.AND(wEntitat, wTipusDoc));
		if (series.size() == 1) {
			serieDocumental = series.get(0);
		}

		return serieDocumental;
	}

}
