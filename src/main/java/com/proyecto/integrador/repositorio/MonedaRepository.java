package com.proyecto.integrador.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.integrador.entidades.monedas;

public interface MonedaRepository extends JpaRepository<monedas,Integer>{
	
	@Query("from monedas")
	public List<monedas> findAllMonedas();
}
