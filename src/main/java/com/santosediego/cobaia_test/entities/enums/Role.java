package com.santosediego.cobaia_test.entities.enums;

import com.santosediego.cobaia_test.services.exceptions.DomainValidationException;

public enum Role {

	ADMIN("ADM", "Administrador"),
	USER("USR", "Usuário Comum");

	private final String id;
	private final String descricao;

	Role(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Role fromId(String id) {
		if (id == null)
			return null;
		for (Role role : Role.values()) {
			if (role.id.equals(id)) {
				return role;
			}
		}
		throw new DomainValidationException("Role inválida: " + id);
	}
}
