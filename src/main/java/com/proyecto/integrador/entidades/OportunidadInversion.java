package com.proyecto.integrador.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "OportunidadInversion")
public class OportunidadInversion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOportunidad;
	private String rendimiento;
	private Double monto;
	private String enable;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
	private Date fechaPago;
	
	@ManyToOne
	@JoinColumn(name="idEmpresa", insertable = false, updatable = false)
	private Empresa empresa;
	private int idEmpresa;
	
	@ManyToOne
	@JoinColumn(name="idFactura", insertable = false, updatable = false)
	private Factura factura;
	private int idFactura;
	
	
	@ManyToOne
	@JoinColumn(name="usuarioId", insertable = false, updatable = false)
	private Usuario usuario;
	private long usuarioId;
	
	public int getIdOportunidad() {
		return idOportunidad;
	}
	public void setIdOportunidad(int idOportunidad) {
		this.idOportunidad = idOportunidad;
	}
	public String getRendimiento() {
		return rendimiento;
	}
	public void setRendimiento(String rendimiento) {
		this.rendimiento = rendimiento;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
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
	public OportunidadInversion(int idOportunidad, String rendimiento, Double monto, String enable, Date fechaPago,
			Empresa empresa, int idEmpresa, Factura factura, int idFactura, Usuario usuario, long usuarioId) {
		super();
		this.idOportunidad = idOportunidad;
		this.rendimiento = rendimiento;
		this.monto = monto;
		this.enable = enable;
		this.fechaPago = fechaPago;
		this.empresa = empresa;
		this.idEmpresa = idEmpresa;
		this.factura = factura;
		this.idFactura = idFactura;
		this.usuario = usuario;
		this.usuarioId = usuarioId;
	}
	public OportunidadInversion() {
		super();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
