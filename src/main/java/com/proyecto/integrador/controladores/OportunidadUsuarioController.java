package com.proyecto.integrador.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.integrador.entidades.OportunidadUsuario;
import com.proyecto.integrador.servicios.OportunidadUsuarioService;
import com.proyecto.integrador.utils.AppSettings;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class OportunidadUsuarioController {
	
	@Autowired
	private OportunidadUsuarioService OportunidadUsuarioservice;
	
	
	@GetMapping("/listarOportunidadUsuario")
	@ResponseBody
	public ResponseEntity<List<OportunidadUsuario>> listaOportunidadInversion() {
		List<OportunidadUsuario> lista = OportunidadUsuarioservice.listaOportunidadUsuario();
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/listarOpoUsuXOpo")
	@ResponseBody
	public ResponseEntity<?> listadoOporUsunxInversion(@RequestBody OportunidadUsuario oportunidad) {
		List<OportunidadUsuario> lista = OportunidadUsuarioservice.listarUsuarioxOpo(oportunidad.getIdOportunidad());
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/listarOpoUsuXIdi")
	@ResponseBody
	public ResponseEntity<?> listadoOporxUsuario(@RequestBody OportunidadUsuario usuario) {
		List<OportunidadUsuario> lista = OportunidadUsuarioservice.listarUsuarioOportunidadxID(usuario.getUsuarioId());
		return ResponseEntity.ok(lista);
	}

}
