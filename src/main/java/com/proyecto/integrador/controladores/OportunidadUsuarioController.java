package com.proyecto.integrador.controladores;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.integrador.entidades.Cartera;
import com.proyecto.integrador.entidades.OportunidadInversion;
import com.proyecto.integrador.entidades.OportunidadUsuario;
import com.proyecto.integrador.servicios.CarteraService;
import com.proyecto.integrador.servicios.OportunidadInversionService;
import com.proyecto.integrador.servicios.OportunidadUsuarioService;
import com.proyecto.integrador.utils.AppSettings;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class OportunidadUsuarioController {

	@Autowired
	private OportunidadUsuarioService OportunidadUsuarioservice;
	@Autowired
	private OportunidadInversionService oportunidadInversionservice;
	@Autowired
	private CarteraService carteraService;

	@GetMapping("/listarOportunidadUsuario")
	@ResponseBody
	public ResponseEntity<List<OportunidadUsuario>> listaOportunidadInversion() {
		List<OportunidadUsuario> lista = OportunidadUsuarioservice.listaOportunidadUsuario();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/listarOpoUsuXOpo/{idOportunidad}")
	@ResponseBody
	public ResponseEntity<?> listadoOporUsunxInversion(@PathVariable int idOportunidad) {
		List<OportunidadUsuario> lista = OportunidadUsuarioservice.listarUsuarioxOpo(idOportunidad);
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/listarOpoUsuXIdi")
	@ResponseBody
	public ResponseEntity<List<OportunidadUsuario>> listadoOporxUsuario(HttpSession session) {
		long idUsuAct = (long) session.getAttribute("idUsuActual");
		List<OportunidadUsuario> lista = OportunidadUsuarioservice.listarUsuarioOportunidadxID(idUsuAct);
		return ResponseEntity.ok(lista);
	}

	@PostMapping("/registaInversionUsuario")
	@ResponseBody
	public ResponseEntity<?> RegistarInversionUsuario(@RequestBody OportunidadUsuario objOpUsu, HttpSession session) {

		HashMap<String, Object> salida = new HashMap<>();

		try {
			long idUsuAct = (long) session.getAttribute("idUsuActual");
			Cartera cartera = carteraService.buscarCartera(idUsuAct);
			double saldoCartera = cartera.getSaldo();
			double montoInvertido = objOpUsu.getMontoInvertido();
			boolean esMayorMontoInver = montoInvertido > saldoCartera;
			if (esMayorMontoInver) {
				salida.put("mensaje", "No cuenta con saldo suficiente en su cartera");
				return new ResponseEntity<>(salida, HttpStatus.CONFLICT);
			}
			cartera.setSaldo(saldoCartera - montoInvertido);
			Cartera carteraSalida = carteraService.insertaActualizaCartera(cartera);
			if (carteraSalida == null) {
				salida.put("mensaje", "No se pudo actualizar los datos de la cartera");
				return new ResponseEntity<>(salida, HttpStatus.CONFLICT);
			}
			Optional<OportunidadInversion> existeOpInver = oportunidadInversionservice
					.buscarxIdOportunidadInversion(objOpUsu.getIdOportunidad());
			if (existeOpInver.isEmpty()) {
				salida.put("mensaje", "No se encontro la oportunidad de inversion");
				return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
			}
			OportunidadInversion opInver = existeOpInver.get();
			double montoRecaudadoActual = opInver.getMontoRecaudado();
			double montoRecaudadoActualizado = montoRecaudadoActual + montoInvertido;
			double montoOpInversion = opInver.getMonto();
			double restante = montoOpInversion - montoRecaudadoActual;
			boolean esMayorMontoRecaudado = montoRecaudadoActualizado > montoOpInversion;
			if (esMayorMontoRecaudado) {
				salida.put("mensaje", "La Inversion Excede El Monto Invertido. "
						+ "Ingrese un inversion menor o igual a: " + restante);
				return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
			}
			opInver.setMontoRecaudado(montoRecaudadoActualizado);
			boolean completado = montoRecaudadoActualizado == montoOpInversion;
			if(completado) {
				opInver.setEnable("No Activo");
				salida.put("completado", "Felicidades!, con su inversion se alcanzo la meta.");
			}
			OportunidadInversion opInverSalida = oportunidadInversionservice
					.insertaActualizaOportunidadInversion(opInver);
			if (opInverSalida == null) {
				salida.put("mensaje", "Error Al Actualizar El Monto Recaudado");
				return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
			}

			objOpUsu.setUsuarioId(idUsuAct);
			objOpUsu.setFecha(new Date());
			objOpUsu.setEstado("A Tiempo");
			OportunidadUsuario opUsuSalida = OportunidadUsuarioservice.RegistrarActualizarOportunidad(objOpUsu);
			if (opUsuSalida == null) {
				salida.put("mensaje", "No se registro la inversion");
				return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
			}
			
			salida.put("mensaje", "La inversion se realizo con exito");
			salida.put("oportunidadInversion", opUsuSalida);
			return ResponseEntity.ok(salida);
		} catch (DataAccessException e) {

			salida.put("mensaje", "Error al registrar la Inversion");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
