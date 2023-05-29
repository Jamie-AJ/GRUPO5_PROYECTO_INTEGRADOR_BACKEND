package com.proyecto.integrador.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Transacciones;
import com.proyecto.integrador.repositorio.TransaccionRepository;
import com.proyecto.integrador.servicios.TransaccionService;
@Service
public class TransaccionesServiceImpl implements TransaccionService{
	@Autowired
	private TransaccionRepository transaccionRepositorio;
	
	@Override
	public Transacciones insertaTransaccion(Transacciones obj) {
		return transaccionRepositorio.save(obj);
	}

	@Override
	public Page<Transacciones> listaTransaccionesTodos(Pageable pageable) {
		return transaccionRepositorio.findAll(pageable);
	}

	@Override
	public Page<Transacciones> listarTransaccionxIdCuentaBancaria(long idUsu,long idCuenta,Pageable pageable) {
		return transaccionRepositorio.listarTransaccionxIdCuentaBancaria(idUsu, idCuenta,pageable);
	}


	@Override
	public Page<Transacciones> listarTransaccionxIdUsuario(long idUsu,Pageable pageable) {
		return transaccionRepositorio.listarTransaccionxIdUsuario(idUsu,pageable);
	}

}
