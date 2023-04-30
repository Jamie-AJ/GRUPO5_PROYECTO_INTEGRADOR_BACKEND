package com.proyecto.integrador.controladores;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.integrador.dto.UsuarioDTO;
import com.proyecto.integrador.entidades.Rol;
import com.proyecto.integrador.entidades.Usuario;
import com.proyecto.integrador.entidades.UsuarioRol;
import com.proyecto.integrador.servicios.RolService;
import com.proyecto.integrador.servicios.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@PostMapping("/")
	public ResponseEntity<?> registrar(@RequestBody @Valid UsuarioDTO usuario)throws Exception{
		
			//Mostrar mensaje de error
			//si el username ya esta en uso
		if(usuarioService.ExisteporUsuario(usuario.getUsername())) {
			return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
		}
			//si el email ya esta en uso
		if(usuarioService.ExisteporCorreo(usuario.getCorreo())) {
			return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
		}
		
		//Se recomienda realizar validaciones
		//algunos atributos no sean nulos

		/*
		 * Al registrar usuario
		 * */
			
		Usuario NuevoUsuario = new Usuario();
		NuevoUsuario.setNombre(usuario.getNombre());
		NuevoUsuario.setApellidoPa(usuario.getApellidoPa());
		NuevoUsuario.setApellidoMa(usuario.getApellidoMa());
		NuevoUsuario.setTelefono(usuario.getTelefono());
		NuevoUsuario.setCorreo(usuario.getCorreo());
		NuevoUsuario.setUsername(usuario.getUsername());
		NuevoUsuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		NuevoUsuario.setIdCuentaBancaria(usuario.getIdCuentaBancaria());
		NuevoUsuario.setFecha(usuario.getFecha());
		NuevoUsuario.setIdHisInver(usuario.getIdHisInver());
		NuevoUsuario.setDni(usuario.getDni());
		NuevoUsuario.setRuc(usuario.getRuc());
		NuevoUsuario.setRazonSocial(usuario.getRazonSocial());
		NuevoUsuario.setDescripcion(usuario.getDescripcion());
		NuevoUsuario.setIdSubasta(usuario.getIdSubasta());
		
	// Datos registrados automaticamente
		NuevoUsuario.setFoto("default.png");
		NuevoUsuario.setFecha(new Date());
		NuevoUsuario.setEnable(true);
		
	// Agregamos el rol del usuario
		Set<UsuarioRol> usuarioRoles = new HashSet<>();
		Rol rol = rolService.buscarporId(usuario.getIdTipoUsu());
		
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setUsuario(NuevoUsuario);
		usuarioRol.setRol(rol);
		usuarioRoles.add(usuarioRol);
		
		//metodo para guardar el usuario
		
		usuarioService.guardarUsuario(NuevoUsuario, usuarioRoles);
		return new ResponseEntity<>("Agregado con exito",HttpStatus.CREATED);
		
	}
	@GetMapping("/{username}")
	public Usuario obtenerUsuario(@PathVariable("username")String username) {
		
		return usuarioService.obtenerUsuario(username);
	}
	@DeleteMapping("/{usuarioId}")
	public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) {
		usuarioService.eliminarUsuario(usuarioId);
	}
}
