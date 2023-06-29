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
@Table(name = "OportunidadUsuario")
public class OportunidadUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOportunidadUsuario;
	
	@ManyToOne
	@JoinColumn(name="idOportunidad", insertable = false, updatable = false)
	private OportunidadInversion oportunidadInversion;
	private int idOportunidad;
	
	private double montoInvertido;
	
	private double ganancia;
	
	private String estado;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "America/Lima")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="idEmpresa", insertable = false, updatable = false)
	private Empresa empresa;
	private int idEmpresa;
	
	@ManyToOne
	@JoinColumn(name="usuarioId", insertable = false, updatable = false)
	private Usuario usuario;
	private long usuarioId;
	
	public OportunidadUsuario() {
		super();
	}

	public OportunidadUsuario(int idOportunidadUsuario, OportunidadInversion oportunidadInversion, int idOportunidad,
			double montoInvertido, double ganancia, String estado, Date fecha, Empresa empresa, int idEmpresa,
			Usuario usuario, long usuarioId) {
		super();
		this.idOportunidadUsuario = idOportunidadUsuario;
		this.oportunidadInversion = oportunidadInversion;
		this.idOportunidad = idOportunidad;
		this.montoInvertido = montoInvertido;
		this.ganancia = ganancia;
		this.estado = estado;
		this.fecha = fecha;
		this.empresa = empresa;
		this.idEmpresa = idEmpresa;
		this.usuario = usuario;
		this.usuarioId = usuarioId;
	}

	public int getIdOportunidadUsuario() {
		return idOportunidadUsuario;
	}

	public void setIdOportunidadUsuario(int idOportunidadUsuario) {
		this.idOportunidadUsuario = idOportunidadUsuario;
	}

	public OportunidadInversion getOportunidadInversion() {
		return oportunidadInversion;
	}

	public void setOportunidadInversion(OportunidadInversion oportunidadInversion) {
		this.oportunidadInversion = oportunidadInversion;
	}

	public int getIdOportunidad() {
		return idOportunidad;
	}

	public void setIdOportunidad(int idOportunidad) {
		this.idOportunidad = idOportunidad;
	}

	public double getMontoInvertido() {
		return montoInvertido;
	}

	public void setMontoInvertido(double montoInvertido) {
		this.montoInvertido = montoInvertido;
	}

	public double getGanancia() {
		return ganancia;
	}

	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
