package com.proyecto.integrador.servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.cuentaBancaria;
import com.proyecto.integrador.repositorio.cuentaBancariaRepository;
import com.proyecto.integrador.servicios.cuentaBancariaService;

@Service
public class cuentaBancariaServiceImpl implements cuentaBancariaService {
	
	@Autowired
	private cuentaBancariaRepository repositorio;
	@Override
	public cuentaBancaria insertaActualizaCuentaBancaria(cuentaBancaria  obj) {
		return repositorio.save(obj);
	}
	@Override
	public List<cuentaBancaria> listaCuentaBancariaTodos() {
		return repositorio.findAll();
	}
	@Override
	public Optional<cuentaBancaria> listaCuentaBancariaxId(int idCuentabancaria) {
		
		return repositorio.findById(idCuentabancaria);
	}
	
	
	
	
	

}
