package com.proyecto.integrador.controladores;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.proyecto.integrador.entidades.Cartera;

import com.proyecto.integrador.entidades.Rol;
import com.proyecto.integrador.entidades.Usuario;
import com.proyecto.integrador.servicios.CarteraService;
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
	private CarteraService carteraService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/buscar/{id}")
	@ResponseBody
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);
			if (usuario.isEmpty()) {
				response.put("mensaje", "El usuario con codigo " + id + " no existe");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al buscar al usuario: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// lista usuarios activos
	@GetMapping("/active/listarUsuario")
	@ResponseBody
	public ResponseEntity<Page<Usuario>> listaUsuarioActive(Pageable pageable) {
		Page<Usuario> lista = usuarioService.listaDiffNotEnable("No activo",pageable);
		return ResponseEntity.ok(lista);
	}

	// listar roles
	@GetMapping("/listarRoles")
	@ResponseBody
	public ResponseEntity<List<Rol>> listarRoles() {
		List<Rol> lista = rolService.listarRoles();
		return ResponseEntity.ok(lista);
	}

	// listado de usuarios

	@GetMapping("/listarUsuario")
	@ResponseBody
	public ResponseEntity<Page<Usuario>> listaUsuarios(Pageable pageable) {
		Page<Usuario> lista = usuarioService.listaUsuarios(pageable);
		return ResponseEntity.ok(lista);
	}

	// realizado por Hilario
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<?> registrar(@RequestBody @Valid Usuario usuario) throws Exception {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			// si el username ya esta en uso
			Optional<Usuario> existeUsername = usuarioService.buscarPorUsernameIdNot(usuario.getUsername(),usuario.getId());
			if (existeUsername.isPresent()) {
				salida.put("Mensaje", "Ese username ya existe");
				return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.CONFLICT);
			}
			// si el email ya esta en uso
			Optional<Usuario> existeCorreo = usuarioService.buscarPorCorreoIdNot(usuario.getCorreo(), usuario.getId());
			if (existeCorreo.isPresent()) {
				salida.put("Mensaje", "Ese email de usuario ya existe");
				return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.CONFLICT);
			}
			// si el dni ya esta en uso
			Optional<Usuario> existeDni = usuarioService.buscarPorDniIdNot(usuario.getDni(), usuario.getId());
			if (existeDni.isPresent()) {
				salida.put("Mensaje", "Ese dni de usuario ya existe");
				return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.CONFLICT);
			} else {
				usuario.setFecha(new Date());
				usuario.setEnable("Activo");
				usuario.setFoto("default.png");
				usuario.setId(0);
				usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
				// Obtener el rol por id
				Rol rol = rolService.buscarporId(AppSettings.ROL_USU_INVERSIONISTA);
				usuario.setTiporol(rol);
				usuario.setIdTipoUsu(rol.getIdTipoUsu());
				Usuario objUsuario = usuarioService.insertaActualizaUsuario(usuario);
				Cartera cartera = new Cartera();
				cartera.setSaldo(0);
				cartera.setIdUsu(objUsuario.getId());
				Cartera objCartera = carteraService.insertaActualizaCartera(cartera);
				if (objUsuario != null && objCartera != null) {
					salida.put("mensaje", "Has sido registrado exitosamente");
					salida.put("empleado", objUsuario);
					salida.put("cartera", objCartera);
					return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.CREATED);
				} else {
					salida.put("mensaje", "Error al registrar el usuario");
					return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.BAD_REQUEST);
				}
			}
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al registrar el empleado");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// realizado por Hilario
	@PutMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<?> actualizar(@RequestBody @Valid Usuario usuarioActualizado) {

		HashMap<String, Object> response = new HashMap<>();
		try {
			// Buscar el usuario por su id
			Optional<Usuario> usuarioExistente = usuarioService.buscarUsuarioPorId(usuarioActualizado.getId());
			// Verificar si el usuario existe
			if (usuarioExistente.isEmpty()) {
				response.put("mensaje", "No se puede actualizar, el usuario no existe");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			// si el username ya esta en uso
			Optional<Usuario> existeUsername = usuarioService.buscarPorUsernameIdNot(usuarioActualizado.getUsername(),usuarioActualizado.getId());
			if (existeUsername.isPresent()) {
				response.put("Mensaje", "Ese username ya existe");
				return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CONFLICT);
			}
			// si el email ya esta en uso
			Optional<Usuario> existeCorreo = usuarioService.buscarPorCorreoIdNot(usuarioActualizado.getCorreo(), usuarioActualizado.getId());
			if (existeCorreo.isPresent()) {
				response.put("Mensaje", "Ese email de usuario ya existe");
				return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CONFLICT);
			}
			// si el dni ya esta en uso
			Optional<Usuario> existeDni = usuarioService.buscarPorDniIdNot(usuarioActualizado.getDni(), usuarioActualizado.getId());
			if (existeDni.isPresent()) {
				response.put("Mensaje", "Ese dni de usuario ya existe");
				return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CONFLICT);
			}
			Usuario usuario = usuarioExistente.get();
			// Restricciones para actualizar
			usuarioActualizado.setUsername(usuario.getUsername()); // No se puede actualizar el username
			usuarioActualizado.setPassword(usuario.getPassword()); // No se puede actualizar el password
			usuarioActualizado.setIdTipoUsu(usuario.getIdTipoUsu()); // No se puede actualizar el rol
			usuarioActualizado.setFecha(usuario.getFecha()); // No se puede actualizar la fecha
			usuarioActualizado.setEnable(usuario.getEnable());
			usuarioActualizado.setFoto(usuario.getFoto());

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
			Optional<Usuario> existeUsu = usuarioService.buscarUsuarioPorId(id);
			if (existeUsu.isEmpty()) {
				response.put("mensaje", "No existe el usario");
				return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CONFLICT);
			} else {
				Usuario elimUsuario = existeUsu.get();
				elimUsuario.setEnable("No Activo");
				usuarioService.insertaActualizaUsuario(elimUsuario);
				response.put("mensaje", "Se elimino exitosamente el usuario");
				return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al eliminar el usuario: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/{username}")
	public ResponseEntity<?> obtenerUsuario(@PathVariable("username") String username) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			Optional<Usuario> usuario = usuarioService.obtenerUsuario(username);
			if (usuario.isEmpty()) {
				response.put("mensaje", "No existe usuario con Username"+ username);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al buscar al usuario: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
