package com.proyecto.integrador.servicios;


import java.util.List;
import java.util.Optional;

import com.proyecto.integrador.entidades.CuentaBancaria;

public interface CuentaBancariaService {
	
	public abstract CuentaBancaria insertaActualizaCuentaBancaria(CuentaBancaria obj);
	public abstract List<CuentaBancaria> listaCuentaBancariaTodos();
	public abstract Optional<CuentaBancaria> listaCuentaBancariaxId(int idCuentabancaria);
	public abstract Optional<CuentaBancaria> buscarxId(int id);
	public abstract List<CuentaBancaria> listaCuentaBancariaxIdUsuAct(long id);
	public abstract Boolean existeNroCuentaBancaria(String nroCuenta,int idCuenta);
	public abstract Boolean existeNroCci(String nroCci,int idCuenta);
}
