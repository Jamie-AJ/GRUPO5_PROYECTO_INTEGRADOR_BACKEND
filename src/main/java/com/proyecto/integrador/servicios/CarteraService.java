package com.proyecto.integrador.servicios;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.integrador.entidades.Cartera;

public interface CarteraService {
	public abstract Optional<Cartera> buscarCartera(long idUsuario);
	public abstract Page<Cartera> listaCarteras(Pageable pageable);
	public abstract Cartera insertaActualizaCartera(Cartera obj);
}
