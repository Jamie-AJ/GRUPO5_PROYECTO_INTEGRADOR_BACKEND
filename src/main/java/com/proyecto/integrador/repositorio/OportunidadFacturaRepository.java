package com.proyecto.integrador.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.integrador.entidades.OportunidadFactura;

public interface OportunidadFacturaRepository  extends JpaRepository<OportunidadFactura, Long>{
	
	List<OportunidadFactura> findByIdOportunidad(int idOpo);
}
