package com.proyecto.integrador.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.integrador.entidades.Empresa;
import com.proyecto.integrador.entidades.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
	
	List<Factura> findByEnableNot(String noActivo);

	 List<Factura> findByEmpresa(Empresa empresa);
}
