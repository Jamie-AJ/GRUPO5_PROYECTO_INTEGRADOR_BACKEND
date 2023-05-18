package com.proyecto.integrador.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.integrador.entidades.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{

}
