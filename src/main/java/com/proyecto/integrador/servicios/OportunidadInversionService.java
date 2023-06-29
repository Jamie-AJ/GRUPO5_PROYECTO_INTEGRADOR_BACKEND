package com.proyecto.integrador.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.integrador.entidades.OportunidadInversion;

public interface OportunidadInversionService {
	public abstract OportunidadInversion insertaActualizaOportunidadInversion(OportunidadInversion obj);

	public abstract List<OportunidadInversion> listaOportunidadInversionActivas(String noActivo);
	
	public abstract List<OportunidadInversion> listaOportunidadInversionTodos();

	public Optional<OportunidadInversion> buscarxIdOportunidadInversion(int idOpoInv);
	
	public Page<OportunidadInversion> listarOportunidadInversionesActivasPage(String noActivo,Pageable pageable);
}
