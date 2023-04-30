package com.proyecto.integrador.servicios.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Usuario;
import com.proyecto.integrador.entidades.UsuarioRol;
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
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
		Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
		if(usuarioLocal != null) {
			System.out.println("El usuario ya existe");
			throw new Exception("El usuario ya esta presente");
		}else {
			for(UsuarioRol usuarioRol:usuarioRoles) {
				rolRepository.save(usuarioRol.getRol());
			}
			usuario.getUsuarioRoles().addAll(usuarioRoles);
			usuarioLocal = usuarioRepository.save(usuario);
		}
		return usuarioLocal;
	}

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
}
