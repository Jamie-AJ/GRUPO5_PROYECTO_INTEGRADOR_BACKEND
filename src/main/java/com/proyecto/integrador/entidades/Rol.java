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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tipoUsuario")
@JsonIgnoreProperties({"tipo"})
public class Rol implements Serializable{

	@Id
	private Long idTipoUsu;
	private String tipo;
	
	
	public Rol() {
		super();
	}

	public Rol(Long idTipoUsu, String tipo) {
		super();
		this.idTipoUsu = idTipoUsu;
		this.tipo = tipo;
	
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

	
	
	
}
