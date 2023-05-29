package com.proyecto.integrador.repositorio;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.integrador.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public abstract Optional<Usuario> findByUsername(String username);

	public abstract Optional<Usuario> findByUsernameAndIdNot(String username, long idUsu);

	public abstract Optional<Usuario> findByCorreoAndIdNot(String correo, long idUsu);

	public abstract Optional<Usuario> findByDniAndIdNot(String dni, long idUsu);

	public abstract Page<Usuario> findByEnableNot(String noActivo,Pageable pageable);

}
