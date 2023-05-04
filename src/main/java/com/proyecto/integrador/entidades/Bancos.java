package com.proyecto.integrador.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bancos")
public class Bancos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idBanco")
	private int idBancos;
	@Column(name="nomBanco")
	private String nomBancos;
	public int getIdBancos() {
		return idBancos;
	}
	public void setIdBancos(int idBancos) {
		this.idBancos = idBancos;
	}
	public String getNomBancos() {
		return nomBancos;
	}
	public void setNomBancos(String nomBancos) {
		this.nomBancos = nomBancos;
	}
	public Bancos(int idBancos, String nomBancos) {
		super();
		this.idBancos = idBancos;
		this.nomBancos = nomBancos;
	}
	public Bancos() {
		super();
	}
	
	
	
	


}
