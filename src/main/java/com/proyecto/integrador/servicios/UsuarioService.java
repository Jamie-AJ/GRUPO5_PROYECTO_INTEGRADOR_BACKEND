package com.proyecto.integrador.servicios;

import java.util.Optional;
import java.util.Set;
import java.util.List;

import com.proyecto.integrador.entidades.Usuario;


public interface UsuarioService {
	

	public Usuario obtenerUsuario(String username);
	public abstract  Optional<Usuario> listaUsuarioPorId(long id);
	public Usuario rolUsuario(long id);
	public void eliminarUsuario(Long usuarioId);
	boolean ExisteporUsuario(String username);
	boolean ExisteporCorreo(String correo);

	public abstract Usuario buscarUsuarioPorId(long idUsuario);
	public abstract List<Usuario> listaUsuarios();
	public abstract Usuario insertaActualizaUsuario(Usuario obj);
}
