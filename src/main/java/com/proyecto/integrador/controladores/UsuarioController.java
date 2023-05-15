package com.proyecto.integrador.controladores;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.integrador.entidades.Rol;
import com.proyecto.integrador.entidades.Usuario;
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

	@GetMapping("/buscar/{id}")
	@ResponseBody
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			Usuario usuario = usuarioService.buscarUsuarioPorId(id);
			if (usuario == null) {
				response.put("mensaje", "El usuario con id " + id + " no existe");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al buscar al usuario: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// listado de usuarios

	@GetMapping("/listarusuarios")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listaUsuarios() {
		List<Usuario> lista = usuarioService.listaUsuarios();
		return ResponseEntity.ok(lista);
	}

	// realizado por Hilario
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<?> registrar(@RequestBody @Valid Usuario usuario) throws Exception {
		HashMap<String, Object> response = new HashMap<>();
		try {
			// Mostrar mensaje de error
			// si el username ya esta en uso
			int existeUsername = usuarioService.ExisteporUsuario(usuario.getUsername(), usuario.getId());
			if (existeUsername != 0) {
				return new ResponseEntity<>("Ese usuario ya existe", HttpStatus.BAD_REQUEST);
			}
			// si el email ya esta en uso
			int existeCorreo = usuarioService.ExisteporCorreo(usuario.getCorreo(), usuario.getId());
			if (existeCorreo != 0) {
				return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
			}
			// si el dni ya esta en uso
			int existeDni = usuarioService.ExisteporDni(usuario.getDni(), usuario.getId());
			if (existeDni != 0) {
				return new ResponseEntity<>("El Dni de usuario ya existe", HttpStatus.BAD_REQUEST);
			}
			// Obtener el rol por id
			Rol rol = rolService.buscarporId(usuario.getIdTipoUsu());
			usuario.setTiporol(rol);

			usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
			// Datos registrados automaticamente
			usuario.setFoto("default.png");
			usuario.setFecha(new Date());
			usuario.setEnable("Activo");

			// Guardar el usuario en la base de datos
			usuarioService.insertaActualizaUsuario(usuario);

			response.put("mensaje", "Usuario registrado exitosamente");
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al registrar al usuario: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// realizado por Hilario
	@PutMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<?> actualizar(@RequestBody @Valid Usuario usuarioActualizado) {

		HashMap<String, Object> response = new HashMap<>();
		try {
			// Buscar el usuario por su id
			Usuario usuarioExistente = usuarioService.buscarUsuarioPorId(usuarioActualizado.getId());

			// Verificar si el usuario existe
			if (usuarioExistente == null) {
				response.put("mensaje", "No se puede actualizar, el usuario no existe");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			// si el username ya esta en uso
			int existeUsername = usuarioService.ExisteporUsuario(usuarioActualizado.getUsername(),
					usuarioActualizado.getId());
			if (existeUsername != 0) {
				return new ResponseEntity<>("Ese usuario ya existe", HttpStatus.BAD_REQUEST);
			}
			// si el email ya esta en uso
			int existeCorreo = usuarioService.ExisteporCorreo(usuarioActualizado.getCorreo(),
					usuarioActualizado.getId());
			if (existeCorreo != 0) {
				return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
			}
			// si el dni ya esta en uso
			int existeDni = usuarioService.ExisteporDni(usuarioActualizado.getDni(), usuarioActualizado.getId());
			if (existeDni != 0) {
				return new ResponseEntity<>("El Dni de usuario ya existe", HttpStatus.BAD_REQUEST);
			}

			// Restricciones para actualizar
			usuarioActualizado.setUsername(usuarioExistente.getUsername()); // No se puede actualizar el username
			usuarioActualizado.setPassword(usuarioExistente.getPassword()); // No se puede actualizar el password
			usuarioActualizado.setIdTipoUsu(usuarioExistente.getIdTipoUsu()); // No se puede actualizar el rol
			usuarioActualizado.setFecha(usuarioExistente.getFecha()); // No se puede actualizar la fecha
			usuarioActualizado.setCorreo(usuarioExistente.getCorreo()); // No se puede actualizar el correo

			// Actualizar el usuario
			usuarioService.insertaActualizaUsuario(usuarioActualizado);

			response.put("mensaje", "Usuario actualizado exitosamente");
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al actualizar al usuario: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			usuarioService.eliminarUsuario(id);
			response.put("mensaje", "Usuario eliminado exitosamente");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al eliminar al usuario: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{username}")
	public Usuario obtenerUsuario(@PathVariable("username") String username) {

		return usuarioService.obtenerUsuario(username);
	}
	
}
