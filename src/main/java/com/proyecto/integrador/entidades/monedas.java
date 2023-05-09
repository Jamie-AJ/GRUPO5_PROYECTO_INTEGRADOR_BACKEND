package com.proyecto.integrador.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "monedas")
public class monedas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMonedas;

	private String nomMonedas;

	private String valorMoneda;
	
	public int getIdMonedas() {
		return idMonedas;
	}
	public void setIdMonedas(int idMonedas) {
		this.idMonedas = idMonedas;
	}
	public String getNomMonedas() {
		return nomMonedas;
	}
	public void setNomMonedas(String nomMonedas) {
		this.nomMonedas = nomMonedas;
	}
	public String getValorMoneda() {
		return valorMoneda;
	}
	public void setValorMoneda(String valorMoneda) {
		this.valorMoneda = valorMoneda;
	}
	public monedas(int idMonedas, String nomMonedas, String valorMoneda) {
		super();
		this.idMonedas = idMonedas;
		this.nomMonedas = nomMonedas;
		this.valorMoneda = valorMoneda;
	}
	public monedas() {
		super();
	}
	
	
	
	

}
