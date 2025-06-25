package es.caib.enviafib.logic;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateList;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleKeyValue;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Values;

import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.ejb.UsuariEJB;
import es.caib.enviafib.ejb.UsuariEntitatService;
import es.caib.enviafib.logic.utils.PortafibUtils;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.entity.UsuariEntitat;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariEntitatFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.UsuariJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class UsuariLogicaEJB extends UsuariEJB implements UsuariLogicaService {
	
	@EJB(mappedName = PeticioLogicaService.JNDI_NAME)
	protected PeticioLogicaService peticioLogicaEjb;

	
	@EJB(mappedName = UsuariEntitatLogicaService.JNDI_NAME)
	protected UsuariEntitatService usuariEntitatLogicaEjb;
	
	@Override
	@PermitAll
	public Usuari createPublic(Usuari instance) throws I18NException {
		return super.create(instance);
	}

	@Override
	@PermitAll
	public UsuariJPA findByPrimaryKeyPublic(Long _ID_) {
		return super.findByPrimaryKey(_ID_);
	}

	@Override
	@PermitAll
	public Usuari update(Usuari instance) throws I18NException {
		// TODO Auto-generated method stub
		return super.update(instance);
	}
	
	@Override
	@PermitAll
	public Usuari getUserByUsername(String username) {

		List<Usuari> listUsuariPersona;
		try {
			// Cerca de l'usuari que es conecta
			listUsuariPersona = this.select(UsuariFields.USERNAME.equal(username));
			log.info("Llista d'usuaris amb usuariEjb: " + listUsuariPersona.size());

		} catch (I18NException e1) {
			listUsuariPersona = null;
			log.error("Error llegint usuari " + username + " : " + e1.getMessage(), e1);
		}

		UsuariJPA usuariPersona = null;

		if (listUsuariPersona != null && !listUsuariPersona.isEmpty()) {
			usuariPersona = (UsuariJPA) listUsuariPersona.get(0);

			//Hibernate.initialize(usuariPersona.getUsuariEntitats());
		}

		return usuariPersona;
	}
	
	
	@Override
	public void delete(Usuari instance) {
		// Cuando se borra un usuario, borrar todas sus peticiones y sus registros en la
		// tabla usuari_entitat
		Long usuariID = instance.getUsuariID();
		try {
			List<UsuariEntitat> usuariEntitats = usuariEntitatLogicaEjb
					.select(UsuariEntitatFields.USUARIID.equal(usuariID));

			for (UsuariEntitat usuariEntitat : usuariEntitats) {
				usuariEntitatLogicaEjb.delete(usuariEntitat);
			}

		} catch (I18NException e) {
			log.error("Error al borrar en la tabla usuariEntitat" + usuariID + " : " + e.getMessage(), e);
		}

		try {
			List<Peticio> peticions = peticioLogicaEjb.select(PeticioFields.SOLICITANTID.equal(usuariID));

			for (Peticio peticio : peticions) {
				peticioLogicaEjb.delete(peticio);
			}

		} catch (I18NException e) {
			log.error("Error al borrar en la tabla peticio" + usuariID + " : " + e.getMessage(), e);
		}

		super.delete(instance);
	}

	@Override
	public List<String> actualitzarUsernamesPlantillesFlux() {
		
		log.info("Inicio del proceso de actualización de usernames de plantilles de flux");

		List<String> script = new ArrayList<String>();
		try {
			List<Select2Values<String, Long>> list = obtenerUsuariosOrdenados();
			if (list.isEmpty()) {
				log.info("No hay usuarios por procesar. Salimos del proceso.");
				return script;
			}

			ApiFlowTemplateSimple api = PortafibUtils.getApiFlowTemplateSimple();
			
			for(Select2Values<String, Long> usuari : list) {
				procesarUsuario(api, usuari, script);
			}
			log.info("Finalización del proceso de actualización de usernames de plantilles de flux");
		} catch (Exception e) {
			log.error("Error al actualizar usernames de plantilles de flux: " + e.getMessage(), e);
		}
		return script;
}

	private void procesarUsuario(ApiFlowTemplateSimple api, Select2Values<String, Long> select2Values, List<String> script)
			throws AbstractApisIBException {
		String username = select2Values.getValue1();
		Long usuariID = select2Values.getValue2();

		log.info("Procesando usuario: " + username + " con ID: " + usuariID);
		List<FlowTemplateSimpleKeyValue> plantilles = getPlantillesByUsername(api, username);
		
//		String comentari = "-- Usuari " + username + " ";
//		if (plantilles.size() == 0) {
//			log.info("Usuari sense plantilles: " + username);
//			comentari += "sense";
//		}else {
//			comentari += "amb " + plantilles.size() ;
//		}
//		
//		comentari += " plantilles.\n";
		
		String comentari = "-- Usuari " + username + " "
				+ (plantilles.size() == 0 ? "sense" : "amb " + plantilles.size()) + " plantilles.";
		
		script.add(comentari);
		
		String updateStr = "UPDATE pfi_plantillafluxdefirmes SET " + "descripcio = REPLACE(descripcio, '{owner="
				+ username + "}','{owner=" + usuariID + "}') " + "WHERE usuariaplicacioid='"
				+ Configuracio.getPortaFIBApiFlowUsername() + "' " + "AND descripcio LIKE '%{owner=" + username + "}%'" + ";\n";
		script.add(updateStr);
		
//		UPDATE pfi_plantillafluxdefirmes SET descripcio = REPLACE(descripcio, '{owner=ptrias}','{owner=1}') WHERE usuariaplicacioid='enviafib2' AND descripcio LIKE '%{owner=ptrias}%'
		log.info("Usuario procesado: " + username);
	}

	private List<Select2Values<String, Long>> obtenerUsuariosOrdenados() throws I18NException {
		Select2Columns<String, Long> s2c = new Select2Columns<>(UsuariFields.USERNAME.select,
				UsuariFields.USUARIID.select);
		List<Select2Values<String, Long>> list = this.executeQuery(s2c, new OrderBy(UsuariFields.USERNAME));
		if (list == null || list.isEmpty()) {
			log.warn("No hay usuarios para procesar.");
			return Collections.emptyList();
		}
		log.info("Usuarios obtenidos: " + list.size());
		return list;
	}

	private List<FlowTemplateSimpleKeyValue> getPlantillesByUsername(ApiFlowTemplateSimple api, String username)
			throws AbstractApisIBException {
		// Simulación de consulta a base de datos o servicio
		log.info("Obteniendo plantilles para usuario: " + username);

		FlowTemplateSimpleFilterGetAllByFilter filter = getFilterPlantillaFluxFirma(username);

		FlowTemplateSimpleFlowTemplateList flowTemplates = api.getAllFlowTemplatesByFilter(filter);
		List<FlowTemplateSimpleKeyValue> plantilles = flowTemplates.getList();
		
		log.info("Usuari " + username + " amb " + plantilles.size() + " plantilles");
		return plantilles;
	}

	
	public FlowTemplateSimpleFilterGetAllByFilter getFilterPlantillaFluxFirma(String username) {
		FlowTemplateSimpleFilterGetAllByFilter filter = new FlowTemplateSimpleFilterGetAllByFilter();
		filter.setLanguageUI("ca");

		final String usrapp = Configuracio.getPortaFIBApiFlowUsername();

		filter.setDescriptionFilter("{usrapp=" + usrapp + "}" + (username == null ? "" : "{owner=" + username + "}"));
		return filter;
	}

}
