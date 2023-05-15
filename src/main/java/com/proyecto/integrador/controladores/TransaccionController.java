package com.proyecto.integrador.controladores;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.integrador.entidades.CuentaBancaria;
import com.proyecto.integrador.entidades.TipoTransaccion;
import com.proyecto.integrador.entidades.Transacciones;
import com.proyecto.integrador.servicios.CuentaBancariaService;
import com.proyecto.integrador.servicios.TipoTransaccionService;
import com.proyecto.integrador.servicios.TransaccionService;
import com.proyecto.integrador.utils.AppSettings;

//hecho por bruno
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class TransaccionController {
	@Autowired
	private TransaccionService transaccionService;
	@Autowired
	private TipoTransaccionService tipoTransaccionService;
	@Autowired
	private CuentaBancariaService cuentaBancariaService;
	
	@GetMapping("/listaTipoTransacciones")
	@ResponseBody
	public ResponseEntity<?> listaTipoTransacciones() {
		List<TipoTransaccion> lista = tipoTransaccionService.listaTipoTransaccion();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/admin/listaTransacciones")
	@ResponseBody
	public ResponseEntity<List<Transacciones>> listaTransacciones() {
		List<Transacciones> lista = transaccionService.listaTransaccionesTodos();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/user/listaTransacciones")
	@ResponseBody
	public ResponseEntity<List<Transacciones>> listaTransaccionesxUsuarioActual(HttpSession session) {
		long idUsuAct = (long) session.getAttribute("idUsuActual");
		List<Transacciones> lista = transaccionService.listarTransaccionxIdUsuario(idUsuAct);
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/user/listaTransacciones/{id}")
	@ResponseBody
	public ResponseEntity<?> listaTransaccionesxIdCuentaBancaria(HttpSession session,@PathVariable long id) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			long idUsuAct = (long) session.getAttribute("idUsuActual");
			List<Transacciones> lista = transaccionService.listarTransaccionxIdCuentaBancaria(idUsuAct,id);
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al listar: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/movimientos")
	@ResponseBody
	public ResponseEntity<?> depostarCuenta(@RequestBody Transacciones transaccion) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			// actualizar el saldo de la cuenta bancaria
			CuentaBancaria cuenta = cuentaBancariaService.buscarxId(transaccion.getIdCuentaBancaria());
			double saldoActual = cuenta.getSaldo();
			if (transaccion.getIdTipoTransaccion() == 1) {
				// deposito
				double nuevoSaldo = saldoActual + transaccion.getMonto();
				cuenta.setSaldo(nuevoSaldo);
				cuentaBancariaService.insertaActualizaCuentaBancaria(cuenta);
			} else if (transaccion.getIdTipoTransaccion() == 2) {
				// retiro
				double nuevoSaldo = saldoActual - transaccion.getMonto();
				cuenta.setSaldo(nuevoSaldo);
				cuentaBancariaService.insertaActualizaCuentaBancaria(cuenta);
			}
			// Guardar la transaccion
			Date fecha = new Date();
			transaccion.setFecha(fecha);
			transaccionService.insertaTransaccion(transaccion);
			response.put("mensaje", "Transaccion realizada con exito");
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al realizar la transaccion: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
