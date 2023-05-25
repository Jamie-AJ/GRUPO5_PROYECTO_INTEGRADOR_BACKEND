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
@Table(name = "factura")
public class Factura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFactura;
	private double monto;
	private Date fechaEmision;
	private Date fechaPago;
	private String enable;
	
	
	@ManyToOne
	@JoinColumn(name="idEmpresa", insertable = false, updatable = false)
	private Empresa empresa;
	private int idEmpresa;
	
	
	public Factura(int idFactura, double monto, Date fechaEmision, Date fechaPago, String enable,
			Empresa empresa, int idEmpresa) {
		super();
		this.idFactura = idFactura;
		this.monto = monto;
		this.fechaEmision = fechaEmision;
		this.fechaPago = fechaPago;
		this.enable = enable;
		this.empresa = empresa;
		this.idEmpresa = idEmpresa;
			
	}
			
			
			
	public Factura() {
		super();
	}
	

	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public Empresa getEmpresas() {
		return empresa;
	}
	public void setEmpresas(Empresa empresas) {
		this.empresa = empresas;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	

}
