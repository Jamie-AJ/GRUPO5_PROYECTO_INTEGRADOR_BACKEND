package com.proyecto.integrador.servicios;


import java.util.List;
import java.util.Optional;


import com.proyecto.integrador.entidades.cuentaBancaria;

public interface cuentaBancariaService {
	
	public abstract cuentaBancaria insertaActualizaCuentaBancaria(cuentaBancaria obj);
	public abstract List<cuentaBancaria> listaCuentaBancariaTodos();
	public abstract Optional<cuentaBancaria> listaCuentaBancariaxId(int idCuentabancaria);

}
