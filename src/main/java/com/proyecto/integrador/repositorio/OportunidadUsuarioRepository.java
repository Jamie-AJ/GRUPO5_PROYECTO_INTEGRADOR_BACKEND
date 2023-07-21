package com.proyecto.integrador.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.proyecto.integrador.entidades.OportunidadUsuario;

public interface OportunidadUsuarioRepository extends JpaRepository<OportunidadUsuario, Integer>{
	
	List<OportunidadUsuario> findByIdOportunidad(int idOpo);
	
	List<OportunidadUsuario> findByUsuarioId(long idUsu);
	
	Page<OportunidadUsuario> findByUsuarioId(Pageable pageable,long idUsu);
}
