package com.proyecto.integrador.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto.integrador.entidades.Usuario;


public interface UsuarioService {
	

	public Usuario obtenerUsuario(String username);
	public abstract  Optional<Usuario> listaUsuarioPorId(long id);
	public void eliminarUsuario(Long usuarioId);
	int ExisteporUsuario(String username, long idUsu);
	int ExisteporCorreo(String correo, long idUsu);
	int ExisteporDni(String dni, long idUsu);
	public abstract Usuario buscarUsuarioPorId(long idUsuario);
	public abstract List<Usuario> listaUsuarios();
	public abstract Usuario insertaActualizaUsuario(Usuario obj);
}
