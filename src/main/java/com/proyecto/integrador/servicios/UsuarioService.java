package com.proyecto.integrador.servicios;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.integrador.entidades.Usuario;

public interface UsuarioService {
	public abstract Optional<Usuario> obtenerUsuario(String username);

	public abstract Optional<Usuario> buscarUsuarioPorId(long id);

	public abstract Optional<Usuario> buscarPorUsernameIdNot(String username, long idUsuAct);
	
	public abstract Optional<Usuario> buscarPorDniIdNot(String dni, long idUsuAct);

	public abstract Optional<Usuario> buscarPorCorreoIdNot(String correo, long idUsuAct);

	public abstract Page<Usuario> listaUsuarios(Pageable pageable);

	public abstract Usuario insertaActualizaUsuario(Usuario obj);
	
	public abstract Page<Usuario> listaDiffNotEnable(String noActivo,Pageable pageable);
}
