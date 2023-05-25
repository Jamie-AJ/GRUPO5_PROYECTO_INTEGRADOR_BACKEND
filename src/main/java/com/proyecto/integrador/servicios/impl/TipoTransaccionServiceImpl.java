package com.proyecto.integrador.servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Riesgo;
import com.proyecto.integrador.entidades.TipoTransaccion;
import com.proyecto.integrador.entidades.Transacciones;
import com.proyecto.integrador.repositorio.TipoTransaccionRepository;
import com.proyecto.integrador.servicios.TipoTransaccionService;

@Service
public class TipoTransaccionServiceImpl implements TipoTransaccionService{
	@Autowired
	TipoTransaccionRepository tipoTransaccionRepository;

	@Override
	public List<TipoTransaccion> listaTipoTransaccion() {
		return tipoTransaccionRepository.findAll();
	}

	@Override
	public Optional<TipoTransaccion> buscarxId(int idRiesgo) {
		return tipoTransaccionRepository.findById(idRiesgo);
	}

	@Override
	public TipoTransaccion insertarTipoTransaccion(TipoTransaccion obj) {
		// TODO Auto-generated method stub
		return TipoTransaccion.Save(obj);
	}

}
