package es.caib.enviafib.back.security;

import org.springframework.security.core.authority.mapping.MappableAttributesRetriever;

import java.util.HashSet;
import java.util.Set;

/**
 * Obtiene los roles posibles 
 * 
 */
public class RolesBasedMappableAttributesRetriever implements MappableAttributesRetriever {

	private Set<String> defaultMappableAttributes;

	private Set<String> mappableAttributes = new HashSet<String>();

  @Override
	public Set<String> getMappableAttributes() {
		refrescarMappableAttributes();
		return mappableAttributes;
	}

	public void setDefaultMappableAttributes(Set<String> defaultMappableAttributes) {
		this.defaultMappableAttributes = defaultMappableAttributes;
	}

	private void refrescarMappableAttributes() {
		mappableAttributes.clear();
		if (defaultMappableAttributes != null)
			mappableAttributes.addAll(defaultMappableAttributes);
	}

}
