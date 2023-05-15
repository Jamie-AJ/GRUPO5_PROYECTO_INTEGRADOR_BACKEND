package com.proyecto.integrador.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.integrador.entidades.CuentaBancaria;
import com.proyecto.integrador.servicios.CuentaBancariaService;
import com.proyecto.integrador.utils.AppSettings;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CuentaBancariaController {

	@Autowired
	private CuentaBancariaService cuentabancaria;

	@GetMapping("/listarCuentaBancaria")
	@ResponseBody
	public ResponseEntity<List<CuentaBancaria>> listaCuentaBancaria() {
		List<CuentaBancaria> lista = cuentabancaria.listaCuentaBancariaTodos();
		return ResponseEntity.ok(lista);
	}

	@PostMapping("/registrarCuentaBancaria")
	@ResponseBody
	public ResponseEntity<?> insertaCuentaBancaria(@RequestBody CuentaBancaria obj) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			CuentaBancaria nuevaCuenta = obj;
			nuevaCuenta.setSaldo(0.0);
			CuentaBancaria objsalida = cuentabancaria.insertaActualizaCuentaBancaria(nuevaCuenta);
			if (objsalida == null) {
				salida.put("mensaje", "No se registro");
				salida.put("usuario", obj);
			} else {
				salida.put("mensaje", " Tú cuenta se registro exitosamente");
				salida.put("cuentaBancaria", obj);
			}
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al registrar el empleado");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		return ResponseEntity.ok(salida);
	}

	@PutMapping("/actualizarCuentaBancaria")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaCuentaBancaria(@RequestBody CuentaBancaria obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<CuentaBancaria> optional = cuentabancaria.listaCuentaBancariaxId(obj.getIdCuentaBancaria());
			if (optional.isPresent()) {
				CuentaBancaria objsalida = cuentabancaria.insertaActualizaCuentaBancaria(obj);
				if (objsalida == null) {
					salida.put("mensaje", "No se actualizo");

				} else {
					salida.put("mensaje", " se actualizo la cuenta:   " + obj.getIdCuentaBancaria());

				}

			} else {
				salida.put("mensaje", "el id no existe");

			}

		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se actualizo");
		}

		return ResponseEntity.ok(salida);
	}

}
