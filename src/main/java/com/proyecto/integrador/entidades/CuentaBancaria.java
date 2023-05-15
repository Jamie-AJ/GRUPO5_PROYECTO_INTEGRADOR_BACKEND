package com.proyecto.integrador.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cuentasbancarias")
public class CuentaBancaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCuentaBancaria;
	private String nroCuenta;
	private String nroCuentaCci;
	private String cvv;
	private Date mes;
	private Date year;
	private Double saldo;
	
	@ManyToOne
	@JoinColumn(name="idBancos")
	private Bancos bancos;
	
	@ManyToOne
	@JoinColumn(name="idMonedas")
	private monedas monedas;
	
	@ManyToOne
	@JoinColumn
	private Usuario usuario;
	
	

	public CuentaBancaria() {
		super();
		
	}

	public CuentaBancaria(int idCuentaBancaria, String nroCuenta, String nroCuentaCci, String cvv,Date mes,Date year,Double saldo, Bancos bancos,
			com.proyecto.integrador.entidades.monedas monedas, Usuario usuario) {
		super();
		this.idCuentaBancaria = idCuentaBancaria;
		this.nroCuenta = nroCuenta;
		this.nroCuentaCci = nroCuentaCci;
		this.cvv = cvv;
		this.mes = mes;
		this.year = year;
		this.saldo = saldo;
		this.bancos = bancos;
		this.monedas = monedas;
		this.usuario = usuario;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

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

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	
	public Date getMes() {
		return mes;
	}

	public void setMes(Date mes) {
		this.mes = mes;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	

}
