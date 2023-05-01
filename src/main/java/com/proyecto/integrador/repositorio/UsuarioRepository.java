package com.proyecto.integrador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.integrador.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
	boolean existsByUsername(String username); 
	boolean existsByCorreo(String correo);
	@Query(value="select * from usuarios as u join usuario_rol as ur on u.id= ur.usuario_id where u.id = :idUsu", nativeQuery = true)
	public Usuario usuarioPorRol(@Param("idUsu") long idUsu);
}
