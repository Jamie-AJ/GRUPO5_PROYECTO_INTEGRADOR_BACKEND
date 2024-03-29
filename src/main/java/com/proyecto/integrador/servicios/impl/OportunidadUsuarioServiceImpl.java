package com.proyecto.integrador.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.OportunidadUsuario;
import com.proyecto.integrador.repositorio.OportunidadUsuarioRepository;
import com.proyecto.integrador.servicios.OportunidadUsuarioService;

@Service
public class OportunidadUsuarioServiceImpl implements OportunidadUsuarioService {
	
	@Autowired
	OportunidadUsuarioRepository Repository;

	@Override
	public List<OportunidadUsuario> listaOportunidadUsuario() {
		
		return Repository.findAll();
	}

	@Override
	public OportunidadUsuario RegistrarActualizarOportunidad(OportunidadUsuario opusu) {
		// TODO Auto-generated method stub
		return Repository.save(opusu);
	}

	@Override
	public List<OportunidadUsuario> listarUsuarioxOpo(int idOpo) {
		// TODO Auto-generated method stub
		return Repository.findByIdOportunidad(idOpo);
	}

	@Override
	public List<OportunidadUsuario> listarUsuarioOportunidadxID(long idUsu) {
		// TODO Auto-generated method stub
		return Repository.findByUsuarioId(idUsu);
	}

	@Override
	public Page<OportunidadUsuario> listarUsuarioOportunidadxIDPage(Pageable pageable, long idUsu) {
		return Repository.findByUsuarioId(pageable, idUsu);
	}


	
	
	
 
}
