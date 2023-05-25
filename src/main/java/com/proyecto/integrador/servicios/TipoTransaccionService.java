package com.proyecto.integrador.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto.integrador.entidades.Cartera;
import com.proyecto.integrador.entidades.Factura;
import com.proyecto.integrador.entidades.TipoTransaccion;
import com.proyecto.integrador.entidades.Transacciones;
;

public interface TipoTransaccionService {
	
	public List<TipoTransaccion> listaTipoTransaccion();
	
	
	public abstract Optional<TipoTransaccion> buscarxId(int idTransaccion);
	

	public abstract TipoTransaccion insertarTipoTransaccion(TipoTransaccion obj);
	
}
