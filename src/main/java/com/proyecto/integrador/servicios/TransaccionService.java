package com.proyecto.integrador.servicios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.integrador.entidades.Transacciones;

public interface TransaccionService {
	public abstract Transacciones insertaTransaccion(Transacciones obj);
	public abstract Page<Transacciones> listaTransaccionesTodos(Pageable pageable);
	public abstract Page<Transacciones> listarTransaccionxIdCuentaBancaria(long idUsu,long idCuenta,Pageable pageable);
	public abstract Page<Transacciones> listarTransaccionxIdUsuario(long idUsu,Pageable pageable);
	
}
