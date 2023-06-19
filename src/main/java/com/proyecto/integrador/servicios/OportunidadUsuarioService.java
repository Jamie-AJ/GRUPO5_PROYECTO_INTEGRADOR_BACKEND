package com.proyecto.integrador.servicios;

import java.util.List;

import com.proyecto.integrador.entidades.OportunidadUsuario;



public interface OportunidadUsuarioService {
	public List<OportunidadUsuario> listaOportunidadUsuario();
	
	public OportunidadUsuario RegistrarActualizarOportunidad(OportunidadUsuario opusu );

}
