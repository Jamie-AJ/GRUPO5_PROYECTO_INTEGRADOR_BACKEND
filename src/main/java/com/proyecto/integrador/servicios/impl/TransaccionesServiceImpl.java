package com.proyecto.integrador.servicios.impl;

import java.util.List;

import javax.transaction.Transactional;

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
	public List<Transacciones> listaTransaccionesTodos() {
		return transaccionRepositorio.findAll();
	}

	@Override
	public List<Transacciones> listarTransaccionxIdCuentaBancaria(long idUsu,long idCuenta) {
		return transaccionRepositorio.listarTransaccionxIdCuentaBancaria(idUsu, idCuenta);
	}


	@Override
	public List<Transacciones> listarTransaccionxIdUsuario(long idUsu) {
		return transaccionRepositorio.listarTransaccionxIdUsuario(idUsu);
	}

	@Override
	public Page<Transacciones> listarTransaccionesTodosPage(Pageable pageable) {
		return transaccionRepositorio.findAll(pageable);
	}

}
