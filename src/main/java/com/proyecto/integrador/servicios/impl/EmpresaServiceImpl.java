package com.proyecto.integrador.servicios.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Empresa;
import com.proyecto.integrador.repositorio.EmpresaRepository;
import com.proyecto.integrador.servicios.EmpresaService;


@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	EmpresaRepository repo;

	@Override
	public Empresa insertarActualizarEmpresa(Empresa obj) {
		return repo.save(obj);
	}

	@Override
	public Page<Empresa> listaEmpresa(Pageable page) {
		return repo.findAll(page);
	}

	@Override
	public Optional<Empresa> buscarxId(int id) {
		return repo.findById(id);
	}

	@Override
	public Optional<Empresa> listExistexCorreo(String correo, int idEmpresa) {
		return repo.findByCorreoAndIdEmpresaNot(correo, idEmpresa);
	}

	@Override
	public Page<Empresa> listaDiffNotEnable(String noActivo, Pageable page) {
		return repo.findByEnableNot(noActivo, page);
	}

	@Override
	public Optional<Empresa> listExistexRuc(String ruc, int idEmpresa) {
		return repo.findByRucAndIdEmpresaNot(ruc, idEmpresa);
	}

	/*
	 * @Override public Optional<Empresa> listExistexNroCuentaBancaria(String numCB,
	 * int idEmpresa) { return repo.findByNroCuentaBancariaAndIdEmpresaNot(numCB,
	 * idEmpresa); }
	 */

	@Override
	public Optional<Empresa> listExistexRazonSocial(String razonS, int idEmpresa) {
		return repo.findByRazonSocialAndIdEmpresaNot(razonS, idEmpresa);
	}

	@Override
	public Page<Empresa> buscarxRazonSocialContainsActive(String keyword, String noActivo,Pageable page) {
		return repo.findByRazonSocialContainingAndEnableNot(keyword, noActivo,page);
	}



}
