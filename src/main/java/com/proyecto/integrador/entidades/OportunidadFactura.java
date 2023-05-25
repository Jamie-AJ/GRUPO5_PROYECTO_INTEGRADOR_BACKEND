package com.proyecto.integrador.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OportunidadFactura")
public class OportunidadFactura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOportunidadFactura;
	
	@ManyToOne
	@JoinColumn(name="idFactura", insertable = false, updatable = false)
	private Factura factura;
	private int idFactura;
	
	
	public int getIdOportunidadFactura() {
		return idOportunidadFactura;
	}
	public void setIdOportunidadFactura(int idOportunidadFactura) {
		this.idOportunidadFactura = idOportunidadFactura;
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
	public OportunidadFactura(int idOportunidadFactura, Factura factura, int idFactura) {
		super();
		this.idOportunidadFactura = idOportunidadFactura;
		this.factura = factura;
		this.idFactura = idFactura;
	}
	public OportunidadFactura() {
		super();
	}
	
	
	

}
