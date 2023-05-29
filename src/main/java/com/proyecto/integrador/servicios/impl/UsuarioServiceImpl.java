package com.proyecto.integrador.servicios.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Usuario;
import com.proyecto.integrador.repositorio.UsuarioRepository;
import com.proyecto.integrador.servicios.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> obtenerUsuario(String username) {
		return usuarioRepository.findByUsername(username);
	}

	@Override
	public Optional<Usuario> buscarUsuarioPorId(long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Optional<Usuario> buscarPorUsernameIdNot(String username, long idUsuAct) {
		return usuarioRepository.findByUsernameAndIdNot(username, idUsuAct);
	}

	@Override
	public Optional<Usuario> buscarPorDniIdNot(String dni, long idUsuAct) {
		return usuarioRepository.findByDniAndIdNot(dni, idUsuAct);
	}

	@Override
	public Optional<Usuario> buscarPorCorreoIdNot(String correo, long idUsuAct) {
		return usuarioRepository.findByCorreoAndIdNot(correo, idUsuAct);
	}

	@Override
	public Page<Usuario> listaUsuarios(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	public Usuario insertaActualizaUsuario(Usuario obj) {
		return usuarioRepository.save(obj);
	}

	@Override
	public Page<Usuario> listaDiffNotEnable(String noActivo, Pageable pageable) {
		return usuarioRepository.findByEnableNot(noActivo, pageable);
	}
	
}
