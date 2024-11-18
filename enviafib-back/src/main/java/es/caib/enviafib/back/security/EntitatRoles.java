package es.caib.enviafib.back.security;

import java.util.List;

import es.caib.enviafib.model.entity.Entitat;

public class EntitatRoles {
	private Entitat entitat;
	private List<String> roles;

	public EntitatRoles(Entitat entitat) {
		this.entitat = entitat;
		this.roles = new java.util.ArrayList<String>();
	}

	public Entitat getEntitat() {
		return entitat;
	}

	public List<String> getRoles() {
		return this.roles;
	}
	
	public void addRole(String role) {
		this.roles.add(role);
	}
}
