package com.proyecto.integrador.servicios;

import java.util.Optional;
import java.util.Set;

import com.proyecto.integrador.entidades.Usuario;
import com.proyecto.integrador.entidades.UsuarioRol;

public interface UsuarioService {
	
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;
	public Usuario obtenerUsuario(String username);
	public Optional<Usuario> listaUsuarioPorId(long id);
	public Usuario rolUsuario(long id);
	public void eliminarUsuario(Long usuarioId);
	boolean ExisteporUsuario(String username);
	boolean ExisteporCorreo(String correo);

}
