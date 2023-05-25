package com.proyecto.integrador.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto.integrador.entidades.OportunidadInversion;

public interface OportunidadInversionService {
	public abstract OportunidadInversion insertaOActualizaOportunidadInversion(OportunidadInversion obj);
	
	public abstract List<OportunidadInversion> listaOportunidadInversionTodos();
	
	public abstract Optional<OportunidadInversion> listaOportunidadInversionxId(int idOportunidaInversion);
	
	public Optional<OportunidadInversion> buscarxIdOportunidadInversion(int idUsu);
	public abstract List<OportunidadInversion> listaOportunidadInversionxIdUsuAct(long idUsuario);
	public abstract void eliminarOportunidadInversion(Long usuarioId);
	
	
	public abstract Optional<OportunidadInversion> listaEmpresaxId(int idUsu);
	public abstract Optional<OportunidadInversion> listaFacturaxId(int idUsu);

	

	
	
}
