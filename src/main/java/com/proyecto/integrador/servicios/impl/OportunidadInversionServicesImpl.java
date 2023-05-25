package com.proyecto.integrador.servicios.impl;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.OportunidadInversion;
import com.proyecto.integrador.repositorio.OportunidadInversionRepository;
import com.proyecto.integrador.servicios.OportunidadInversionService;


@Service
public class OportunidadInversionServicesImpl implements OportunidadInversionService{
	
	@Autowired
	private OportunidadInversionRepository repositorio;
	
	@Override
	public OportunidadInversion insertaOActualizaOportunidadInversion(OportunidadInversion obj) {
	
		return repositorio.save(obj);
	}

	@Override
	public List<OportunidadInversion> listaOportunidadInversionTodos() {
		
		return repositorio.findAll();
	}

	@Override
	public Optional<OportunidadInversion> listaOportunidadInversionxId(int idOportunidaInversion) {
		
		return repositorio.findById(idOportunidaInversion);
	}

	@Override
	public Optional<OportunidadInversion> buscarxIdOportunidadInversion(int idUsu) {
		
		return repositorio.findById(idUsu);
	}

	@Override
	public List<OportunidadInversion> listaOportunidadInversionxIdUsuAct(long idUsuario) {
		
		return repositorio.listaOportunidadInversionXUsuAcT(idUsuario);
	}

	@Override
	public void eliminarOportunidadInversion(Long usuarioId) {
		}

	@Override
	public Optional<OportunidadInversion> listaEmpresaxId(int idUsu) {
		
		return repositorio.findById(idUsu);
	}

	@Override
	public Optional<OportunidadInversion> listaFacturaxId(int idUsu) {
		
		return repositorio.findById(idUsu);
	}


	

	

	
	
	

}
