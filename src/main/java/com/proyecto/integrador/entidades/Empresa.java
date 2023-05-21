package com.proyecto.integrador.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empresas")
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEmpresa;
	private String nomEmpresa;
	private int ruc;
	private int razonSocial;
	private Date fechaDeInicioActv;
	private String direccion;
	private String telefono;
	private String correo;
	private String nroCuentaBancaria;
	private String sector;
	private Date fechRegistro;
	private String enable;
	public Empresa() {
		super();
	}
	public Empresa(int idEmpresa, String nomEmpresa, int ruc, int razonSocial, Date fechaDeInicioActv, String direccion,
			String telefono, String correo, String nroCuentaBancaria, String sector, Date fechRegistro, String enable) {
		super();
		this.idEmpresa = idEmpresa;
		this.nomEmpresa = nomEmpresa;
		this.ruc = ruc;
		this.razonSocial = razonSocial;
		this.fechaDeInicioActv = fechaDeInicioActv;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		this.nroCuentaBancaria = nroCuentaBancaria;
		this.sector = sector;
		this.fechRegistro = fechRegistro;
		this.enable = enable;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getNomEmpresa() {
		return nomEmpresa;
	}
	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}
	public int getRuc() {
		return ruc;
	}
	public void setRuc(int ruc) {
		this.ruc = ruc;
	}
	public int getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(int razonSocial) {
		this.razonSocial = razonSocial;
	}
	public Date getFechaDeInicioActv() {
		return fechaDeInicioActv;
	}
	public void setFechaDeInicioActv(Date fechaDeInicioActv) {
		this.fechaDeInicioActv = fechaDeInicioActv;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNroCuentaBancaria() {
		return nroCuentaBancaria;
	}
	public void setNroCuentaBancaria(String nroCuentaBancaria) {
		this.nroCuentaBancaria = nroCuentaBancaria;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public Date getFechRegistro() {
		return fechRegistro;
	}
	public void setFechRegistro(Date fechRegistro) {
		this.fechRegistro = fechRegistro;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	
}