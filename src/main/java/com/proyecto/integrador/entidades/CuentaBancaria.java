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
public class CuentaBancaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCuentaBancaria;
	private String nroCuenta;
	private String nroCuentaCci;
	private String cvv;
	private String mes;
	private String year;
	private Double saldo;
	private String enable;
	@ManyToOne
	@JoinColumn(name="idBancos", insertable = false, updatable = false)
	private Bancos bancos;
	private int idBancos;
	
	@ManyToOne
	@JoinColumn(name="idMonedas", insertable = false, updatable = false)
	private monedas monedas;
	private int idMonedas;
	@ManyToOne
	@JoinColumn(name="usuarioId", insertable = false, updatable = false)
	private Usuario usuario;
	private long usuarioId;
	
	public CuentaBancaria() {
		super();
	}

	public CuentaBancaria(int idCuentaBancaria, String nroCuenta, String nroCuentaCci, String cvv, String mes,
			String year, Double saldo, String enable, Bancos bancos, int idBancos,
			com.proyecto.integrador.entidades.monedas monedas, int idMonedas, Usuario usuario, long usuarioId) {
		super();
		this.idCuentaBancaria = idCuentaBancaria;
		this.nroCuenta = nroCuenta;
		this.nroCuentaCci = nroCuentaCci;
		this.cvv = cvv;
		this.mes = mes;
		this.year = year;
		this.saldo = saldo;
		this.enable = enable;
		this.bancos = bancos;
		this.idBancos = idBancos;
		this.monedas = monedas;
		this.idMonedas = idMonedas;
		this.usuario = usuario;
		this.usuarioId = usuarioId;
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

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String isEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public Bancos getBancos() {
		return bancos;
	}

	public void setBancos(Bancos bancos) {
		this.bancos = bancos;
	}

	public int getIdBancos() {
		return idBancos;
	}

	public void setIdBancos(int idBancos) {
		this.idBancos = idBancos;
	}

	public monedas getMonedas() {
		return monedas;
	}

	public void setMonedas(monedas monedas) {
		this.monedas = monedas;
	}

	public int getIdMonedas() {
		return idMonedas;
	}

	public void setIdMonedas(int idMonedas) {
		this.idMonedas = idMonedas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}
}
