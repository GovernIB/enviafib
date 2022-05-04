package es.caib.enviafib.back.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.Attributes2GrantedAuthoritiesMapper;

import java.util.*;


/**
 * Defineix el mapeig entre rols J2EE i els rols interns de l'aplicació
 * @author anadal
 */

@SuppressWarnings("rawtypes")
public class RolesBasedAttributes2GrantedAuthoritiesMapper implements Attributes2GrantedAuthoritiesMapper {
 
  protected final Logger log = Logger.getLogger(getClass());
  
	private Map baseRoleMapping = new HashMap();

	public void setBaseRoleMapping(Map baseRoleMapping) {
		this.baseRoleMapping = baseRoleMapping;
	}

	public Collection<GrantedAuthority> getGrantedAuthorities(Collection<String> attributes) {
		List<GrantedAuthority> gaList = new ArrayList<GrantedAuthority>();
		boolean isDebug = log.isDebugEnabled();
		if (isDebug) {  log.info("------------  MAPPING -----------------"); };
		for (String attribute: attributes) {
		  log.info("  + Attribute: " + attribute);
			Object mapping = baseRoleMapping.get(attribute);
			if (mapping != null) {
				if (mapping instanceof Collection) {
					for (Object obj: (Collection)mapping) {
						if (obj != null)
						  if (isDebug) { log.info("      - CAS 1 = " + obj.toString()); };
							gaList.add(new SimpleGrantedAuthority(obj.toString()));
					}
				} else {
					gaList.add(new SimpleGrantedAuthority(mapping.toString()));
					if (isDebug) {  log.info("      - CAS 2 = " + mapping.toString()); }
				}
			} else {
			  
				gaList.add(new SimpleGrantedAuthority(attribute));
				if (isDebug) {  log.info("      - CAS 3 = " + attribute); }
			}
		}
		if (isDebug) { log.info("---------------------------------------"); }
		return gaList;
	}

}
