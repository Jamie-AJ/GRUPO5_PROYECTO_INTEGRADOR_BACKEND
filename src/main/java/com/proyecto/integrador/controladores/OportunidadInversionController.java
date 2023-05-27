package com.proyecto.integrador.controladores;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.proyecto.integrador.entidades.OportunidadInversion;
import com.proyecto.integrador.servicios.OportunidadInversionService;

import com.proyecto.integrador.utils.AppSettings;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class OportunidadInversionController {

	@Autowired
	private OportunidadInversionService oportunidadInversionservice;

	@GetMapping("/user/listarOportunidadInversion")
	@ResponseBody
	public ResponseEntity<List<OportunidadInversion>> listaOportunidadInversionActive() {
		List<OportunidadInversion> lista = oportunidadInversionservice.listaOportunidadInversionActivas("No Activo");
		return ResponseEntity.ok(lista);
	}
	@GetMapping("/listarOportunidadInversion")
	@ResponseBody
	public ResponseEntity<List<OportunidadInversion>> listaOportunidadInversion() {
		List<OportunidadInversion> lista = oportunidadInversionservice.listaOportunidadInversionTodos();
		return ResponseEntity.ok(lista);
	}
	@PostMapping("/insertaOportunidadInversion")
	@ResponseBody
	public ResponseEntity<?> insertaOportunidadInversion(@RequestBody OportunidadInversion obj, HttpSession session) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			long idUsuAct = (long) session.getAttribute("idUsuActual");

			obj.setIdidUsu(idUsuAct);
			obj.setEnable("Activo");
			obj.setFechaRegistro(new Date());
			obj.setMontoRecaudado(0.0);
			// Crear una instancia de Calendar
			Calendar calendar = Calendar.getInstance();

			// Establecer la fecha original en el Calendar
			calendar.setTime(obj.getFechaCaducidad());

			// Restar 2 mes
			calendar.add(Calendar.MONTH, -2);

			// Obtener la fecha resultante
			Date fechaPago = calendar.getTime();

			obj.setFechaPago(fechaPago);

			OportunidadInversion objsalida = oportunidadInversionservice.insertaActualizaOportunidadInversion(obj);
			if (objsalida == null) {
				salida.put("mensaje", "No se registro la oportunidad de inversion");
				salida.put("oportunidadInversion", obj);
				return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
			} else {
				salida.put("mensaje", "La oportunidad de inversion se registro exitosamente!");
				salida.put("OportunidadInversion", obj);
				return ResponseEntity.ok(salida);
			}
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al registrar el Oportunidad Inversion");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizarOportunidadInversion")
	@ResponseBody
	public ResponseEntity<?> actualizaOportunidadInversion(@RequestBody OportunidadInversion obj) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			int idOportunidad = obj.getIdOportunidad();
			Optional<OportunidadInversion> existeOportunidad = oportunidadInversionservice
					.buscarxIdOportunidadInversion(idOportunidad);
			if (existeOportunidad.isEmpty()) {
				salida.put("mensaje", "No existe oportunidad con Id: " + idOportunidad);
				return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
			} else {
				OportunidadInversion objOportunidad = existeOportunidad.get();
				obj.setIdOportunidad(objOportunidad.getIdOportunidad());
				obj.setEnable(objOportunidad.getEnable());
				obj.setFechaRegistro(objOportunidad.getFechaRegistro());
				// Crear una instancia de Calendar
				Calendar calendar = Calendar.getInstance();
				// Establecer la fecha original en el Calendar
				calendar.setTime(obj.getFechaCaducidad());
				// Restar 2 mes
				calendar.add(Calendar.MONTH, -2);
				// Obtener la fecha resultante
				Date fechaPago = calendar.getTime();
				obj.setFechaPago(fechaPago);
				obj.setMontoRecaudado(objOportunidad.getMontoRecaudado());
				//obj.setUsuarioId(objOportunidad.getUsuarioId());
				OportunidadInversion objsalida = oportunidadInversionservice.insertaActualizaOportunidadInversion(obj);
				if (objsalida == null) {
					salida.put("mensaje", "No se actualizo la oportunidad de inversion");
					salida.put("oportunidadInversion", obj);
					return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
				} else {
					salida.put("mensaje", "La oportunidad de inversion se actualizo exitosamente!");
					salida.put("OportunidadInversion", obj);
					return ResponseEntity.ok(salida);
				}
			}
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al actualizar el Oportunidad Inversion");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/eliminarOportunidadInversion/{id}")
	@ResponseBody
	public ResponseEntity<?> actualizaOportunidadInversion(@PathVariable int id) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			Optional<OportunidadInversion> existeOportunidad = oportunidadInversionservice
					.buscarxIdOportunidadInversion(id);
			if (existeOportunidad.isEmpty()) {
				salida.put("mensaje", "No existe oportunidad con Id: " + id);
				return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
			} else {
				OportunidadInversion objOportunidad = existeOportunidad.get();
				objOportunidad.setEnable("No Activo");
				OportunidadInversion objsalida = oportunidadInversionservice.insertaActualizaOportunidadInversion(objOportunidad);
				if (objsalida == null) {
					salida.put("mensaje", "No se elimino la oportunidad de inversion");
					salida.put("oportunidadInversion", objOportunidad);
					return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
				} else {
					salida.put("mensaje", "La oportunidad de inversion se elimino exitosamente!");
					salida.put("OportunidadInversion", objOportunidad);
					return ResponseEntity.ok(salida);
				}
			}
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al eliminar el Oportunidad Inversion");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/detalleOportunidadInversion/{id}")
	@ResponseBody
	public ResponseEntity<?> detalleOportunidad(@PathVariable int id) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			Optional<OportunidadInversion> existeOportunidad = oportunidadInversionservice
					.buscarxIdOportunidadInversion(id);
			if (existeOportunidad.isEmpty()) {
				salida.put("mensaje", "No existe oportunidad con Id: " + id);
				return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(existeOportunidad.get());
			}
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al registrar el Oportunidad Inversion");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

