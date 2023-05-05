package com.proyecto.integrador.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.monedas;
import com.proyecto.integrador.repositorio.MonedaRepository;
import com.proyecto.integrador.servicios.MonedasService;

@Service
public class MonedasServiceImpl implements MonedasService{
	
	@Autowired
	MonedaRepository monedaRepository;

	@Override
	public List<monedas> listarMonedas() {
		return monedaRepository.findAllMonedas();
	}

}
