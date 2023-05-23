package com.proyecto.integrador.controladores;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyecto.integrador.entidades.Empresa;
import com.proyecto.integrador.entidades.Factura;
import com.proyecto.integrador.servicios.EmpresaService;
import com.proyecto.integrador.servicios.FacturaService;
import com.proyecto.integrador.utils.AppSettings;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class FacturaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private FacturaService facturaService;
	
	
	//listado de facturas x empresa
	@GetMapping("/facturas/{idEmpresa}")
	@ResponseBody
	public ResponseEntity<?> listarFacturasPorEmpresa(@PathVariable int idEmpresa) {
	    HashMap<String, Object> salida = new HashMap<>();
	    try {
	        Optional<Empresa> empresaOptional = empresaService.buscarxId(idEmpresa);
	        if (empresaOptional.isEmpty()) {
	            salida.put("mensaje", "No se encontró la empresa con el id: " + idEmpresa);
	            return new ResponseEntity<>(salida, HttpStatus.NOT_FOUND);
	        }
	        
	        Empresa empresa = empresaOptional.get();
	        
	        List<Factura> facturas = facturaService.listarFacturasPorEmpresa(empresa);
	        salida.put("facturas", facturas);
	        
	        return ResponseEntity.ok(salida);
	    } catch (DataAccessException e) {
	        salida.put("mensaje", "Error al listar las facturas");
	        salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	@GetMapping("active/listaFactura")
	@ResponseBody
	public ResponseEntity<List<Factura>> listaFacturasAct() {
		List<Factura> lista = facturaService.listaDifNotEnable("No activo");
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/listaFacturas")
	@ResponseBody
	public ResponseEntity<List<Factura>> listaFacturas() {
		List<Factura> lista = facturaService.listaFactura();
		return ResponseEntity.ok(lista);
	}
	
	
	@PostMapping("/registrarFactura")
	@ResponseBody
	public ResponseEntity<?> registrarFactura(@RequestBody Factura factura) {
		HashMap<String, Object> salida = new HashMap<>();
		
		try {
			
		  // Realizar operaciones de registro
        factura.setFechaEmision(new Date()); // Asignar fecha actual
        factura.setEnable("Activo"); // Asignar estado activo
        // Obtener la empresa por id
        Optional<Empresa> empresaOptional = empresaService.buscarxId(factura.getIdEmpresa());
        if (empresaOptional.isEmpty()) {
            salida.put("mensaje", "No se encontró la empresa con el id: " + factura.getIdEmpresa());
            return new ResponseEntity<>(salida, HttpStatus.NOT_FOUND);
        }
        
        Empresa empresa = empresaOptional.get();
        factura.setEmpresas(empresa); // Establecer la empresa en la factura

        Factura facturaRegistrada = facturaService.insertarActualizarFactura(factura);
        
        if (facturaRegistrada != null) {
            salida.put("mensaje", "Factura registrada exitosamente");
            salida.put("factura", facturaRegistrada);
            return ResponseEntity.ok(salida);
        } else {
            salida.put("mensaje", "Error al registrar la factura");
            return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
        }
    } catch (DataAccessException e) {
        salida.put("mensaje", "Error al registrar la factura");
        salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
	
	@PutMapping("/actualizarFactura")
	@ResponseBody
	public ResponseEntity<?> actualizarFactura(@RequestBody Factura factura) {
	    HashMap<String, Object> salida = new HashMap<>();
	    try {
	    	
	    	// Verificar si la factura existe en la base de datos
	        Optional<Factura> existeFactura = facturaService.buscarxId(factura.getIdFactura());
	        if (existeFactura.isEmpty()) {
	            salida.put("mensaje", "No existe factura con id: " + factura.getIdFactura());
	            return new ResponseEntity<>(salida, HttpStatus.CONFLICT);
	        } else {
	            // Realizar operaciones de actualización
	            Factura facturaExistente = existeFactura.get();
	            
	         // Actualizar los campos necesarios de la factura existente
	            facturaExistente.setMonto(factura.getMonto());
	            facturaExistente.setFechaPago(factura.getFechaPago());
	            
	            Factura facturaActualizada = facturaService.insertarActualizarFactura(facturaExistente);
	            
	            if (facturaActualizada != null) {
	                salida.put("mensaje", "Factura actualizada exitosamente");
	                salida.put("factura", facturaActualizada);
	                return ResponseEntity.ok(salida);
	            } else {
	                salida.put("mensaje", "Error al actualizar la factura");
	                return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
	            }
	        }
	    } catch (DataAccessException e) {
	        salida.put("mensaje", "Error al actualizar la factura");
	        salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@DeleteMapping("/eliminarFactura/{id}")
	@ResponseBody
	public ResponseEntity<?> eliminarFactura(@PathVariable int id) {
	    HashMap<String, Object> response = new HashMap<>();
	    try {
	        Optional<Factura> existeFactura = facturaService.buscarxId(id);
	        if (existeFactura.isEmpty()) {
	            response.put("mensaje", "No existe factura con id: " + id);
	            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	        } else {
	            Factura facturaEliminada = existeFactura.get();
	            facturaEliminada.setEnable("No Activo");
	            facturaService.insertarActualizarFactura(facturaEliminada);
	            response.put("mensaje", "Se eliminó exitosamente la factura");
	            return ResponseEntity.ok(response);
	        }
	    } catch (Exception e) {
	        response.put("mensaje", "Hubo un error al eliminar la factura: " + e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	

	
}
	

