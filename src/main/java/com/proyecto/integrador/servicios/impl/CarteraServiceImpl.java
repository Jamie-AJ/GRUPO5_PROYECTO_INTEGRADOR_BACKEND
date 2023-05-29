package com.proyecto.integrador.servicios.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Cartera;
import com.proyecto.integrador.repositorio.CarteraRespository;
import com.proyecto.integrador.servicios.CarteraService;

@Service
public class CarteraServiceImpl implements CarteraService{
	@Autowired
	private CarteraRespository repo;
	@Override
	public Optional<Cartera> buscarCartera(long idUsuario) {
		return repo.findByIdUsu(idUsuario);
	}

	@Override
	public Page<Cartera> listaCarteras(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Cartera insertaActualizaCartera(Cartera obj) {
		return repo.save(obj);
	}

}
