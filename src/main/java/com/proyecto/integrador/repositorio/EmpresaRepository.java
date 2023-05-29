package com.proyecto.integrador.repositorio;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.integrador.entidades.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	Optional<Empresa> findByRucAndIdEmpresaNot(String ruc, int idEmpresa);
	
	Optional<Empresa> findByRazonSocialAndIdEmpresaNot(String razonS, int idEmpresa);
	
	//Optional<Empresa> findByNroCuentaBancariaAndIdEmpresaNot(String numCB, int idEmpresa);
	
	Optional<Empresa> findByCorreoAndIdEmpresaNot(String correo, int idEmpresa);

	Page<Empresa> findByEnableNot(String noActivo,Pageable pageable);
	
	Page<Empresa> findByRazonSocialContainingAndEnableNot(String keyword,String noActivo, Pageable pageable);
}
