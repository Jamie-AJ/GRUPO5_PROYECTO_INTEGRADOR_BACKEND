package com.proyecto.integrador.controladores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.integrador.dto.UsuarioDTO;
import com.proyecto.integrador.entidades.Rol;
import com.proyecto.integrador.entidades.Usuario;
import com.proyecto.integrador.entidades.UsuarioRol;
import com.proyecto.integrador.servicios.RolService;
import com.proyecto.integrador.servicios.UsuarioService;
import com.proyecto.integrador.utils.AppSettings;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class UsuarioController {
	
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RolService rolService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<?> registrar(@RequestBody @Valid UsuarioDTO usuario) throws Exception {

		HashMap<String,Object> response = new HashMap<>();
		try {
			// Mostrar mensaje de error
			// si el username ya esta en uso
			if (usuarioService.ExisteporUsuario(usuario.getUsername())) {
				return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
			}
			// si el email ya esta en uso
			if (usuarioService.ExisteporCorreo(usuario.getCorreo())) {
				return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
			}

			// Se recomienda realizar validaciones
			// algunos atributos no sean nulos

			/*
			 * Al registrar usuario
			 */

			Usuario NuevoUsuario = new Usuario();
			NuevoUsuario.setNombre(usuario.getNombre());
			NuevoUsuario.setApellidoPa(usuario.getApellidoPa());
			NuevoUsuario.setApellidoMa(usuario.getApellidoMa());
			NuevoUsuario.setTelefono(usuario.getTelefono());
			NuevoUsuario.setCorreo(usuario.getCorreo());
			NuevoUsuario.setUsername(usuario.getUsername());
			NuevoUsuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
			NuevoUsuario.setDni(usuario.getDni());
			// Datos registrados automaticamente
			NuevoUsuario.setFoto("default.png");
			NuevoUsuario.setFecha(new Date());
			NuevoUsuario.setEnable("Activo");

			// Agregamos el rol del usuario
			Set<UsuarioRol> usuarioRoles = new HashSet<>();
			Rol rol = rolService.buscarporId(usuario.getIdTipoUsu());

			UsuarioRol usuarioRol = new UsuarioRol();
			usuarioRol.setUsuario(NuevoUsuario);
			usuarioRol.setRol(rol);
			usuarioRoles.add(usuarioRol);
			usuarioService.guardarUsuario(NuevoUsuario, usuarioRoles);
		
			response.put("mensaje","El usuario ha sido registrado con éxito");
			return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CREATED);
		}catch(DataAccessException  e) {
			response.put("mensaje", "Error al registrar el empleado");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	// metodo para guardar el usuario

		//return new ResponseEntity<>("Agregado con exito", HttpStatus.OK);

	}
	//Hecho por Bruno
	@PutMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<?> actualizar(@RequestBody @Valid UsuarioDTO usuario) {
		try {
			Optional<Usuario> optinal = usuarioService.listaUsuarioPorId(usuario.getId());
			if(optinal.isPresent()) {
			// Mostrar mensaje de error
			// si el username ya esta en uso
			if (usuarioService.ExisteporUsuario(usuario.getUsername())) {
				return new ResponseEntity<>("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
			}
			// si el email ya esta en uso
			if (usuarioService.ExisteporCorreo(usuario.getCorreo())) {
				return new ResponseEntity<>("El email de usuario ya existe", HttpStatus.BAD_REQUEST);
			}

			// Se recomienda realizar validaciones
			// algunos atributos no sean nulos

			/*
			 * Al registrar usuario
			 */

			Usuario NuevoUsuario = new Usuario();
			NuevoUsuario.setId(usuario.getId()); 
			NuevoUsuario.setNombre(usuario.getNombre());
			NuevoUsuario.setApellidoPa(usuario.getApellidoPa());
			NuevoUsuario.setApellidoMa(usuario.getApellidoMa());
			NuevoUsuario.setTelefono(usuario.getTelefono());
			NuevoUsuario.setCorreo(usuario.getCorreo());
			NuevoUsuario.setUsername(usuario.getUsername());
			NuevoUsuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
			NuevoUsuario.setDni(usuario.getDni());
			
			NuevoUsuario.setFoto(usuario.getFoto());
			// Agregamos el rol del usuario
			Set<UsuarioRol> usuarioRoles = new HashSet<>();
			Rol rol = rolService.buscarporId(usuario.getIdTipoUsu());
			UsuarioRol usuarioRol = new UsuarioRol();
			Usuario usuarioRolAct = usuarioService.rolUsuario(usuario.getId());
	        Stream<Object> usuRolId = Stream.of(usuarioRolAct).map(usu -> usu.getUsuarioRoles()).flatMap(r -> r.stream()
	        		.map(ru -> ru.getUsuarioRolId()));
	        List<Object> lista = usuRolId.toList();
	        long id = (long) lista.get(0);
			usuarioRol.setUsuarioRolId(id);
			usuarioRol.setUsuario(NuevoUsuario);
			usuarioRol.setRol(rol);
			usuarioRoles.add(usuarioRol);

			// metodo para guardar el usuario

			usuarioService.guardarUsuario(NuevoUsuario, usuarioRoles);
			}else{
				return new ResponseEntity<>("No existe el usuario con id: "+usuario.getId(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Actualizado con exito", HttpStatus.CREATED);
	}

	@GetMapping("/{username}")
	public Usuario obtenerUsuario(@PathVariable("username") String username) {

		return usuarioService.obtenerUsuario(username);
	}

	@DeleteMapping("/{usuarioId}")
	public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) {
		usuarioService.eliminarUsuario(usuarioId);
	}
}
