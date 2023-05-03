package com.proyecto.integrador.dto;

import java.util.Date;

import javax.validation.constraints.Pattern;

import com.proyecto.integrador.utils.Validaciones;

public class UsuarioDTO {
	
private long id;
	
	/*@Pattern (regexp = Validaciones.TEXT, message = "el nombre es invalido")*/
	private String nombre;
	
	/*@Pattern (regexp = Validaciones.TEXT, message = "el apellido es invalido")*/
	private String apellidoPa;
	/*
	@Pattern (regexp = Validaciones.TEXT, message = "el apellido es invalido")*/
	private String apellidoMa;
	
	/*@Pattern (regexp = Validaciones.PHONE, message = "el telefono no es correcto")*/
	private String telefono;
	
	/*@Pattern (regexp = Validaciones.EMAIL, message = "el correo es invalido")*/
	private String correo;
	
	/*@Pattern (regexp = Validaciones.TEXT, message = "el username es invalido")*/
	private String username;
	
	/*@Pattern (regexp = Validaciones.PASSWORD, message = "la contraseña debe tener minimo 8 caracteres, contar con al menos un caracter especial y al menos un numero")*/
	private String password;
	
	private String foto;
	
	private Long idTipoUsu;
	
	
	private String fecha;
	
	/*@Pattern (regexp = Validaciones.DNI, message = "el dni no es correcto")*/
	private String dni;
	
	
	private String enable;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPa() {
		return apellidoPa;
	}
	public void setApellidoPa(String apellidoPa) {
		this.apellidoPa = apellidoPa;
	}
	public String getApellidoMa() {
		return apellidoMa;
	}
	public void setApellidoMa(String apellidoMa) {
		this.apellidoMa = apellidoMa;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Long getIdTipoUsu() {
		return idTipoUsu;
	}
	public void setIdTipoUsu(Long idTipoUsu) {
		this.idTipoUsu = idTipoUsu;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String isEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	

}

