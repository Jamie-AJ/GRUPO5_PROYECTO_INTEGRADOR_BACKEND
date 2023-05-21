package com.proyecto.integrador.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.integrador.entidades.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	Optional<Empresa> findByRucAndIdEmpresaNot(int ruc, int idEmpresa);
	
	Optional<Empresa> findByRazonSocialAndIdEmpresaNot(int razonS, int idEmpresa);
	
	Optional<Empresa> findByNroCuentaBancariaAndIdEmpresaNot(String numCB, int idEmpresa);
	
	Optional<Empresa> findByCorreoAndIdEmpresaNot(String correo, int idEmpresa);

	List<Empresa> findByEnableNot(String noActivo);
}
