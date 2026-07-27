package es.caib.enviafib.logic.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.EntitatService;
import es.caib.enviafib.ejb.IdiomaService;
import es.caib.enviafib.ejb.UsuariEntitatService;
import es.caib.enviafib.ejb.UsuariService;
import es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService;
import es.caib.enviafib.logic.UsuariLogicaService;

/**
 * 
 * @author anadal
 * 
 */
public final class EjbManager {

	protected static final Logger log = Logger.getLogger(EjbManager.class);

	protected static UsuariLogicaService usuariLogicaEjb;

	private static void throwNewI18NException(Throwable e, String name) throws I18NException {
		throw new I18NException(e, "error.unknown",
				new I18NArgumentString("No puc instanciar " + name + ": " + e.getMessage()));
	}

	public static UsuariLogicaService getUsuariEJB() throws I18NException {

		if (usuariLogicaEjb == null) {
			try {
				usuariLogicaEjb = (UsuariLogicaService) new InitialContext().lookup(UsuariLogicaService.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "UsuariLogicaService");
			}
		}
		return usuariLogicaEjb;
	}
	
	
	
	protected static IdiomaService idiomaEjb;

    public static IdiomaService getIdiomaEJB() throws I18NException {

        if (idiomaEjb == null) {
            try {
                idiomaEjb = (IdiomaService) new InitialContext().lookup(IdiomaService.JNDI_NAME);
            } catch (Throwable e) {
                throwNewI18NException(e, "IdiomaService");
            }
        }
        return idiomaEjb;
    }


	protected static EntitatService entitatEjb;

    public static EntitatService getEntitatEJB() throws I18NException {

        if (entitatEjb == null) {
            try {
            	entitatEjb = (EntitatService) new InitialContext().lookup(EntitatService.JNDI_NAME);
            } catch (Throwable e) {
                throwNewI18NException(e, "EntitatService");
            }
        }
        return entitatEjb;
    }
    
    
	protected static UsuariEntitatService usuariEntitatEjb;

    public static UsuariEntitatService getUsuariEntitatEJB() throws I18NException {

        if (usuariEntitatEjb == null) {
            try {
            	usuariEntitatEjb = (UsuariEntitatService) new InitialContext().lookup(UsuariEntitatService.JNDI_NAME);
            } catch (Throwable e) {
                throwNewI18NException(e, "UsuariEntitatService");
            }
        }
        return usuariEntitatEjb;
    }
    
	protected static PluginEstructuraOrganitzativaLogicaService pluginEstructuraOrganitzativaLogicaService;

	public static PluginEstructuraOrganitzativaLogicaService getPluginEstructuraOrganitzativa() {

		if (pluginEstructuraOrganitzativaLogicaService == null) {
			try {
				pluginEstructuraOrganitzativaLogicaService = (PluginEstructuraOrganitzativaLogicaService) new InitialContext()
						.lookup(PluginEstructuraOrganitzativaLogicaService.JNDI_NAME);
			} catch (Throwable e) {
				log.error(e.getMessage(), e);
			}
		}
		return pluginEstructuraOrganitzativaLogicaService;
	}

	protected static UsuariService usuariService;

	public static UsuariService getUsuariService() throws I18NException {

		if (usuariService == null) {
			try {
				usuariService = (UsuariService) new InitialContext().lookup(UsuariService.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "UsuariService");
			}
		}
		return usuariService;

	}
    
    
    public static Map<Class<?>, Object> servicesCache = new HashMap<Class<?>, Object>();
    
    public static <E> E getEJB(Class<E> classe) throws I18NException {

        E ejb = (E)servicesCache.get(classe); 
        
        if (ejb == null) {
            try {
                
                Field field = classe.getDeclaredField("JNDI_NAME");
                if (field == null) {
                    throw new Exception("No es troba la constant JNDI_NAME en la classe " + classe.getName());
                }
                
                idiomaEjb = (IdiomaService) new InitialContext().lookup((String)field.get(null));
                
            } catch (Throwable e) {
                throwNewI18NException(e, classe.getName());
            }
        }
        return ejb;
    }
}