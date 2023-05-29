package com.proyecto.integrador.servicios;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.integrador.entidades.Empresa;

public interface EmpresaService {
	public abstract Empresa insertarActualizarEmpresa(Empresa obj);

	public abstract Page<Empresa> listaEmpresa(Pageable page);

	public abstract Optional<Empresa> buscarxId(int id);

	public abstract Optional<Empresa> listExistexCorreo(String correo, int idEmpresa);
	
	public abstract Optional<Empresa> listExistexRuc(String ruc, int idEmpresa);
	
	//public abstract Optional<Empresa> listExistexNroCuentaBancaria(String numCB, int idEmpresa);
	
	public abstract Optional<Empresa> listExistexRazonSocial(String razonS, int idEmpresa);

	public abstract Page<Empresa> listaDiffNotEnable(String noActivo,Pageable page);
	
	public abstract Page<Empresa> buscarxRazonSocialContainsActive(String keyword, String noActivo,Pageable page);

}
