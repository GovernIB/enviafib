package es.caib.enviafib.hibernate;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.caib.enviafib.commons.utils.Configuracio;
/**
 * ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!!
 * @author jagarcia
 */
public class CustomHibernatePersistenceProvider extends HibernatePersistenceProvider {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, @SuppressWarnings("rawtypes") Map properties) {
		
		Map<String,String> projecteProperties = new HashMap<String,String>();
		
		if(!properties.isEmpty()) {
			projecteProperties.putAll(properties);
		}
		
		Properties fitxerProperties = Configuracio.getSystemAndFileProperties();
		if(fitxerProperties != null) {
			fitxerProperties.forEach((k,v) -> {
               String kStr = k.toString();
	        	if (kStr.startsWith("es.caib.enviafib.hibernate")) {
	        		projecteProperties.put(kStr.replace("es.caib.enviafib.",""), v.toString());
	        	} else if(kStr.startsWith("hibernate.")) {
	        		//if (!projecteProperties.containsKey(kStr)) {
	        			projecteProperties.put(kStr, v.toString());
	        		//}
	        	}
	        });
		}
		if (Configuracio.isDesenvolupament()) {
			projecteProperties.forEach((k,v) -> {
	        	log.info("HibernateProperties: " + k + ", Value : " + v);
	        });
		}
		return super.createContainerEntityManagerFactory(info, projecteProperties);
	}
}