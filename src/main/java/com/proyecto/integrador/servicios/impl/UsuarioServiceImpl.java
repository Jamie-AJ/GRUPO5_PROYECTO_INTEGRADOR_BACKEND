package com.proyecto.integrador.servicios.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Usuario;
import com.proyecto.integrador.repositorio.RolRepository;
import com.proyecto.integrador.repositorio.UsuarioRepository;
import com.proyecto.integrador.servicios.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;

	

	@Override
	public Usuario obtenerUsuario(String username) {
		return usuarioRepository.findByUsername(username);
	}
	@Override
	public void eliminarUsuario(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);
		
	}

	@Override
	public boolean ExisteporUsuario(String username) {
		// TODO Auto-generated method stub
		return usuarioRepository.existsByUsername(username);
	}

	@Override
	public boolean ExisteporCorreo(String correo) {
		// TODO Auto-generated method stub
		return usuarioRepository.existsByCorreo(correo);
	}

	@Override
	public Optional<Usuario> listaUsuarioPorId(long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}
	//Por Bruno
	@Override
	public Usuario rolUsuario(long id) {
		return usuarioRepository.usuarioPorRol(id);
	}

	@Override
	public Usuario buscarUsuarioPorId(long idUsuario) {
		return usuarioRepository.findById(idUsuario).orElse(null);
	}
	@Override
	public List<Usuario> listaUsuarios() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}
	@Override
	public Usuario insertaActualizaUsuario(Usuario obj) {
		// TODO Auto-generated method stub
		return usuarioRepository.save(obj);
	}
}
