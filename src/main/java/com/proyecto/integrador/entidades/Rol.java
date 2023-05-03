package com.proyecto.integrador.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipoUsuario")
public class Rol implements Serializable{

	@Id
	private Long idTipoUsu;
	private String tipo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
	private Set<UsuarioRol> usuarioRoles = new HashSet<>();

	public Rol() {
		super();
	}

	public Rol(Long idTipoUsu, String tipo, Set<UsuarioRol> usuarioRoles) {
		super();
		this.idTipoUsu = idTipoUsu;
		this.tipo = tipo;
		this.usuarioRoles = usuarioRoles;
	}

	public Long getIdTipoUsu() {
		return idTipoUsu;
	}

	public void setIdTipoUsu(Long idTipoUsu) {
		this.idTipoUsu = idTipoUsu;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Set<UsuarioRol> getUsuarioRoles() {
		return usuarioRoles;
	}

	public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}
	
	
}
