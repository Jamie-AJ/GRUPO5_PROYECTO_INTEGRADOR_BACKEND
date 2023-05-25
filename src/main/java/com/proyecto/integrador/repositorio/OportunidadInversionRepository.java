package com.proyecto.integrador.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.proyecto.integrador.entidades.OportunidadInversion;



public interface OportunidadInversionRepository extends JpaRepository<OportunidadInversion, Integer> {

	@Query(value ="SELECT c.* FROM  oportunidad_inversion as c where usuario_id = :idUsu AND  c.enable = \"Activo\"",nativeQuery = true)
	List<OportunidadInversion> listaOportunidadInversionXUsuAcT(@Param("idUsu") long idUsu);
	
	
}
