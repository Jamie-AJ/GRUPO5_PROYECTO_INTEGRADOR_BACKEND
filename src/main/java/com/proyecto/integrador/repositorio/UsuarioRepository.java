package com.proyecto.integrador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.integrador.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
	boolean existsByUsername(String username); 
	boolean existsByCorreo(String correo);
}
