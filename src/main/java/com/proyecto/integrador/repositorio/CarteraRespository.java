package com.proyecto.integrador.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.integrador.entidades.Cartera;

public interface CarteraRespository extends JpaRepository<Cartera, Integer>{	
	public abstract Optional<Cartera> findByIdUsu(long idUsu);
}
