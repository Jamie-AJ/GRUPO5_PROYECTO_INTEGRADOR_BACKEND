package com.proyecto.integrador.controladores;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/listarOportunidadInversion")
	@ResponseBody
	public ResponseEntity<List<OportunidadInversion>> listaOportunidadInversion() {
		List<OportunidadInversion> lista = oportunidadInversionservice.listaOportunidadInversionTodos();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/user/listarOportunidadInversion")
	@ResponseBody
	public ResponseEntity<List<OportunidadInversion>> listaOportunidadInversionXUsuAct(HttpSession session) {
		long idUsuAct = (long) session.getAttribute("idUsuActual");
		List<OportunidadInversion> lista = oportunidadInversionservice.listaOportunidadInversionxIdUsuAct(idUsuAct);
		return ResponseEntity.ok(lista);
		
	}
	
	
	@PostMapping("/user/InsertaOportunidadInversion")
	@ResponseBody
	public ResponseEntity<?> insertaOportunidadInversion(@RequestBody OportunidadInversion obj, HttpSession session) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			long idUsuAct = (long) session.getAttribute("idUsuActual");
			
			obj.setUsuarioId(idUsuAct);
			obj.setIdOportunidad(0);
			obj.setEnable("Activo");
			obj.setFechaPago(new Date());
			obj.setMonto(144.40);
			
			OportunidadInversion objsalida = oportunidadInversionservice.insertaOActualizaOportunidadInversion(obj);
			if (objsalida == null) {
				salida.put("mensaje", "No se registro");
				salida.put("oportunidadInversion", obj);
				return new ResponseEntity<>(salida,HttpStatus.BAD_REQUEST);
			} else {
				salida.put("mensaje", " inversion se registro exitosamente");
				salida.put("OportunidadInversion", obj);
			}
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al registrar el Oportunidad Inversion");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(salida,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	
	

}
