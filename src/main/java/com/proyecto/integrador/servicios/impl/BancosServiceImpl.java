package com.proyecto.integrador.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Bancos;
import com.proyecto.integrador.repositorio.BancoRepository;
import com.proyecto.integrador.servicios.BancosService;

@Service
public class BancosServiceImpl implements BancosService{
	@Autowired
	BancoRepository bancoRepository;

	@Override
	public List<Bancos> listaBancos() {
		return bancoRepository.findAllBancos();
	}

}
