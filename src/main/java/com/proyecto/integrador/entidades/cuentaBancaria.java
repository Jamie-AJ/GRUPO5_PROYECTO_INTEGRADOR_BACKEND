package com.proyecto.integrador.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cuentasbancarias")
public class cuentaBancaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCuentaBancaria;
	private String nroCuenta;
	private String nroCuentaCci;
	private int idBanco;
	private int idTipoMoneda;
	
	@ManyToOne
	@JoinColumn(name="idBanco")
	private Bancos bancos;
	
	@ManyToOne
	@JoinColumn(name="idTipoMoneda")
	private monedas monedas;

	public int getIdCuentaBancaria() {
		return idCuentaBancaria;
	}

	public void setIdCuentaBancaria(int idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getNroCuentaCci() {
		return nroCuentaCci;
	}

	public void setNroCuentaCci(String nroCuentaCci) {
		this.nroCuentaCci = nroCuentaCci;
	}

	public int getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}

	public int getIdTipoMoneda() {
		return idTipoMoneda;
	}

	public void setIdTipoMoneda(int idTipoMoneda) {
		this.idTipoMoneda = idTipoMoneda;
	}

	public Bancos getBancos() {
		return bancos;
	}

	public void setBancos(Bancos bancos) {
		this.bancos = bancos;
	}

	public monedas getMonedas() {
		return monedas;
	}

	public void setMonedas(monedas monedas) {
		this.monedas = monedas;
	}

	public cuentaBancaria(int idCuentaBancaria, String nroCuenta, String nroCuentaCci, int idBanco, int idTipoMoneda,
			Bancos bancos, com.proyecto.integrador.entidades.monedas monedas) {
		super();
		this.idCuentaBancaria = idCuentaBancaria;
		this.nroCuenta = nroCuenta;
		this.nroCuentaCci = nroCuentaCci;
		this.idBanco = idBanco;
		this.idTipoMoneda = idTipoMoneda;
		this.bancos = bancos;
		this.monedas = monedas;
	}

	public cuentaBancaria() {
		super();
	}
	
	
	
	

}
